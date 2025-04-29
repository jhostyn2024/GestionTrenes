/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    public static <T> List<T> readJson(String filePath, Class<T> clazz) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                System.out.println("Archivo no encontrado: " + filePath + ". Creando archivo vacío.");
                writeJson(filePath, new ArrayList<>());
                return initializeDefaultData(filePath, clazz);
            }
            System.out.println("Leyendo archivo JSON: " + filePath);
            List<T> data = mapper.readValue(file, new TypeReference<List<T>>() {});
            System.out.println("Datos cargados desde " + filePath + ": " + data.size() + " elementos");
            return data;
        } catch (IOException e) {
            System.err.println("Error al leer JSON desde " + filePath + ": " + e.getMessage());
            e.printStackTrace();
            return initializeDefaultData(filePath, clazz);
        }
    }

    public static <T> void writeJson(String filePath, List<T> data) {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                System.out.println("Creando directorio: " + parentDir.getAbsolutePath());
                if (!parentDir.mkdirs()) {
                    throw new IOException("No se pudo crear el directorio: " + parentDir.getAbsolutePath());
                }
            }
            System.out.println("Guardando datos en: " + filePath + " (" + data.size() + " elementos)");
            mapper.writeValue(file, data);
            System.out.println("Datos guardados exitosamente en: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al escribir JSON en " + filePath + ": " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo escribir en " + filePath, e);
        }
    }

    private static <T> List<T> initializeDefaultData(String filePath, Class<T> clazz) {
        List<T> defaultData = new ArrayList<>();
        System.out.println("Inicializando datos predeterminados para: " + filePath);
        if (filePath.endsWith("rutas.json") && clazz.equals(Ruta.class)) {
            defaultData.add((T) new Ruta("RUTA-0001", "Cabecera del Llano", "San Francisco", 30.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0002", "Cabecera del Llano", "La Universidad", 40.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0003", "Cabecera del Llano", "Sotomayor", 50.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0004", "Cabecera del Llano", "La Concordia", 50.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0005", "San Francisco", "Cabecera del Llano", 30.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0006", "La Universidad", "Cabecera del Llano", 40.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0007", "La Universidad", "Girardot", 80.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0008", "La Universidad", "La Joya", 120.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0009", "La Universidad", "Kennedy", 110.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0010", "Sotomayor", "Cabecera del Llano", 50.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0011", "Sotomayor", "Provenza", 20.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0012", "Provenza", "Sotomayor", 20.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0013", "Provenza", "La Concordia", 65.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0014", "La Concordia", "Cabecera del Llano", 50.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0015", "La Concordia", "Provenza", 65.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0016", "La Concordia", "Diamante II", 80.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0017", "Diamante II", "La Concordia", 80.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0018", "Diamante II", "Mutis", 30.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0019", "Diamante II", "Girardot", 145.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0020", "Mutis", "Diamante II", 30.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0021", "Girardot", "La Universidad", 80.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0022", "Girardot", "Diamante II", 145.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0023", "La Joya", "La Universidad", 120.0, "Activa"));
            defaultData.add((T) new Ruta("RUTA-0024", "Kennedy", "La Universidad", 110.0, "Activa"));
            writeJson(filePath, defaultData);
        } else if (filePath.endsWith("horarios.json") && clazz.equals(Horario.class)) {
            defaultData.add((T) new Horario("HORARIO-0001", "RUTA-0001", "TREN-0001", 
                "2025-05-01T08:00:00", "2025-05-01T08:30:00", "Activo"));
            defaultData.add((T) new Horario("HORARIO-0002", "RUTA-0007", "TREN-0002", 
                "2025-05-01T09:00:00", "2025-05-01T10:20:00", "Activo"));
            defaultData.add((T) new Horario("HORARIO-0003", "RUTA-0011", "TREN-0001", 
                "2025-05-01T07:00:00", "2025-05-01T07:15:00", "Activo"));
            writeJson(filePath, defaultData);
        } else if (filePath.endsWith("trenes.json") && clazz.equals(Tren.class)) {
            defaultData.add((T) new Tren("TREN-0001", "Expreso Regional", 200, "Activo"));
            defaultData.add((T) new Tren("TREN-0002", "Intermunicipal", 150, "Activo"));
            writeJson(filePath, defaultData);
        } else if (filePath.endsWith("vagones.json") && clazz.equals(Vagon.class)) {
            defaultData.add((T) new Vagon("VAGON-0001", "TREN-0001", "Primera Clase", 50, "Activo"));
            defaultData.add((T) new Vagon("VAGON-0002", "TREN-0001", "Económico", 100, "Activo"));
            defaultData.add((T) new Vagon("VAGON-0003", "TREN-0002", "Primera Clase", 40, "Activo"));
            writeJson(filePath, defaultData);
        } else if (filePath.endsWith("boletos.json") && clazz.equals(Boleto.class)) {
            writeJson(filePath, defaultData);
        } else if (filePath.endsWith("empleados.json") && clazz.equals(Empleado.class)) {
            defaultData.add((T) new Empleado("EMP-0001", "Juan", "Pérez", "Conductor", "Activo"));
            defaultData.add((T) new Empleado("EMP-0002", "María", "Gómez", "Administrador", "Activo"));
            defaultData.add((T) new Empleado("EMP-0003", "Carlos", "López", "Inspector", "Activo"));
            writeJson(filePath, defaultData);
        }
        System.out.println("Datos predeterminados inicializados para " + filePath + ": " + defaultData.size() + " elementos");
        return defaultData;
    }
}