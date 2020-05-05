package com.jmteam.igauntlet.util.helpers;

import com.jmteam.igauntlet.Infinity;

import java.io.*;

public class IOUtil {

    public static void ObjectToFile(Object object, String filePath) {
        File file = new File(filePath.substring(0, filePath.lastIndexOf("/")));

        if (!file.exists()) {
            file.mkdirs();
        }

        try {

            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file, false);
            ObjectOutputStream stream = new ObjectOutputStream(out);
            stream.writeObject(object);
            stream.close();
        } catch (Exception e) {

        }
    }

    public static Object ObjectFromFile(String filePath, Class c) {
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileReader reader = new FileReader(file);

            Object o =  Infinity.GSON.fromJson(reader.toString(), c);
            reader.close();
            return o;
        } catch (Exception e) {}

        return null;
    }
}
