package com.bintangSiahaanJBusAF.dbjson;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;

/**
 * JsonTable is used to write and read JSON in every class or method that calls json table
 * @param <T> generic as a universal type of data.
 * @author Bintang Siahaan
 */
public class JsonTable<T> extends Vector<T> {
    private static final Gson gson = new Gson();
    public final String filepath;

    /**
     * Constructor for JsonTable class.
     * @param clazz The class type parameter.
     * @param filepath The file path for JSON data storage.
     * @throws IOException If an I/O error occurs while creating the JsonTable.
     */
    @SuppressWarnings("unchecked")
    public JsonTable(Class<T> clazz, String filepath) throws IOException {
        this.filepath = filepath;
        try {
            Class<T[]> arrayType = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
            T[] loaded = readJson(arrayType, filepath);
            if (loaded != null) {
                Collections.addAll(this, loaded);

                int lastId = 0;
                for (T item : this) {
                    if (item instanceof com.bintangSiahaanJBusAF.dbjson.Serializable) {
                        com.bintangSiahaanJBusAF.dbjson.Serializable serializableItem =
                                (com.bintangSiahaanJBusAF.dbjson.Serializable) item;
                        lastId = Math.max(lastId, serializableItem.id);
                    }
                }

                Serializable.setLastAssignedId(clazz, lastId);
            }
        } catch (FileNotFoundException e) {
            File file = new File(filepath);
            File parent = file.getParentFile();
            if (parent != null)
                parent.mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Writes the JsonTable data to the JSON file.
     * @throws IOException If an I/O error occurs while writing the JSON data.
     */
    public void writeJson() throws IOException {
        writeJson(this, this.filepath);
    }

    /**
     * Writes the specified object to the JSON file.
     * @param object The object to be written to the JSON file.
     * @param filepath The file path for JSON data storage.
     * @throws IOException If an I/O error occurs while writing the JSON data.
     */
    public static void writeJson(Object object, String filepath) throws IOException {
        final FileWriter writer = new FileWriter(filepath);
        writer.write(gson.toJson(object));
        writer.close();
    }

    /**
     * Reads JSON data from the specified file and converts it to the specified class type.
     * @param clazz The class type to which the JSON data should be converted.
     * @param filepath The file path from which to read JSON data.
     * @return An instance of the specified class type containing the deserialized JSON data.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static <T> T readJson(Class<T> clazz, String filepath) throws FileNotFoundException {
        final JsonReader reader = new JsonReader(new FileReader(filepath));
        return gson.fromJson(reader, clazz);
    }

    /**
     * Writes the specified string.
     * @param s The string to be written.
     */
    public void write(String s) {
    }
}
