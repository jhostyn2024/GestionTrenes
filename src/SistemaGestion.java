/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jhost
 */
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaGestion {
    private List<Tren> trenes;
    private List<Ruta> rutas;
    private List<Horario> horarios;
    private List<Empleado> empleados;
    private List<Boleto> boletos; 
    private Map<String, String> usuarios; 
    private Map<String, String> roles;

    public SistemaGestion() {
        trenes = new ArrayList<>();
        rutas = new ArrayList<>();
        horarios = new ArrayList<>();
        empleados = new ArrayList<>();
        boletos = new ArrayList<>(); 
        usuarios = new HashMap<>();
        roles = new HashMap<>();
        inicializarDatos();
    }

    private void inicializarDatos() {
        // Usuarios predefinidos para prueba
        usuarios.put("admin@tren.com", "admin123");
        roles.put("admin@tren.com", "empleado");
        usuarios.put("user@tren.com", "user123");
        roles.put("user@tren.com", "pasajero");

        // Ejemplo de rutas
        rutas.add(new Ruta("R1", "Estacion A", "Estacion B", 50.0));
        rutas.add(new Ruta("R2", "Estacion B", "Estacion C", 30.0));
        rutas.add(new Ruta("R3", "Estacion A", "Estacion C", 90.0));

        // Ejemplo de horarios
        horarios.add(new Horario("R1", "08:00", "09:00", "Lunes"));
        horarios.add(new Horario("R2", "10:00", "10:30", "Martes"));

        // Ejemplo de tren con vagones
        List<Vagon> vagones = new ArrayList<>();
        vagones.add(new Vagon(1, 50, 20, 10, true)); // 50 estándar, 20 ejecutivos, 10 premium
        trenes.add(new Tren("T1", "Marca1", "Modelo1", 80, 100.0, vagones));
    }

    
    public String autenticar(String usuario, String contrasena) {
        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contrasena)) {
            return roles.get(usuario);
        }
        return null;
    }

    
    public void agregarTren(Tren tren) {
        trenes.add(tren);
        registrarAccion("Tren agregado: " + tren.getId());
    }

    public void modificarTren(int indice, Tren tren) {
        trenes.set(indice, tren);
        registrarAccion("Tren modificado: " + tren.getId());
    }

    public void eliminarTren(int indice) {
        Tren tren = trenes.remove(indice);
        registrarAccion("Tren eliminado: " + tren.getId());
    }

    public List<Tren> getTrenes() {
        return trenes;
    }

    
    public void agregarRuta(Ruta ruta) {
        rutas.add(ruta);
        registrarAccion("Ruta agregada: " + ruta.getId());
    }

    public void modificarRuta(int indice, Ruta ruta) {
        rutas.set(indice, ruta);
        registrarAccion("Ruta modificada: " + ruta.getId());
    }

    public void eliminarRuta(int indice) {
        Ruta ruta = rutas.remove(indice);
        registrarAccion("Ruta eliminada: " + ruta.getId());
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    
    public void agregarHorario(Horario horario) {
        horarios.add(horario);
        registrarAccion("Horario agregado para ruta: " + horario.getIdRuta());
    }

    public void modificarHorario(int indice, Horario horario) {
        horarios.set(indice, horario);
        registrarAccion("Horario modificado para ruta: " + horario.getIdRuta());
    }

    public void eliminarHorario(int indice) {
        Horario horario = horarios.remove(indice);
        registrarAccion("Horario eliminado para ruta: " + horario.getIdRuta());
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    
    public boolean venderBoleto(String idRuta, String idHorario, String categoriaAsiento, double pesoMaleta1, double pesoMaleta2) {
        // Validar equipaje (máximo 2 maletas de 80 kg cada una)
        if ((pesoMaleta1 > 0 && pesoMaleta1 > 80) || (pesoMaleta2 > 0 && pesoMaleta2 > 80)) {
            return false; // Excede el peso máximo por maleta
        }
        if (pesoMaleta1 == 0 && pesoMaleta2 > 0) {
            return false; // Si hay maleta 2, debe haber maleta 1
        }

        
        if (trenes.isEmpty()) {
            return false; // No hay trenes disponibles
        }
        Tren tren = trenes.get(0); // Simplificación: usar el primer tren

        // Validar que el tren tenga vagones con espacio para equipaje si hay maletas
        boolean tieneEquipaje = pesoMaleta1 > 0 || pesoMaleta2 > 0;
        boolean tieneVagonEquipaje = tren.getVagones().stream().anyMatch(Vagon::isTieneEspacioEquipaje);
        if (tieneEquipaje && !tieneVagonEquipaje) {
            return false; // No hay vagones con espacio para equipaje
        }

        // Asignar asiento automáticamente
        Vagon vagon = tren.getVagones().get(0); // Simplificación: usar el primer vagón
        if (!vagon.hayAsientosDisponibles(categoriaAsiento)) {
            return false; // No hay asientos disponibles en la categoría
        }
        int numeroAsiento = vagon.asignarAsiento(categoriaAsiento);
        if (numeroAsiento == -1) {
            return false; // No se pudo asignar asiento
        }

        // Crear y guardar el boleto
        String idBoleto = "B" + (boletos.size() + 1);
        Boleto boleto = new Boleto(idBoleto, idRuta, idHorario, categoriaAsiento, numeroAsiento, pesoMaleta1, pesoMaleta2);
        boletos.add(boleto);
        registrarAccion("Boleto vendido: " + idBoleto + " para ruta " + idRuta + ", horario " + idHorario);
        return true;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }

    private void registrarAccion(String accion) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(LocalDateTime.now() + " - " + accion + "\n");
        } catch (IOException e) {
            System.out.println("Error al registrar acción: " + e.getMessage());
        }
    }

    public Map<String, Object> calcularRutaMasCorta(String origen, String destino) {
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("distancia", 0.0);
        resultado.put("tiempo", "0 minutos");
        return resultado;
    }
}