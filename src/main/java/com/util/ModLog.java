package com.util;

public class ModLog {

    public static void Log(Object text) {
        System.out.println("["+Reference.MODID+"] "+ text);
    }

    public static void Error(String error) {
        System.err.println("["+Reference.MODID+"] "+ error);
    }
}
