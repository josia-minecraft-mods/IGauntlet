package com.igauntlet.util;

public class ModUtil {


    public static void Log(Object text) {
        System.out.println("["+Reference.MODID+"] "+ text);
    }
    public static void Error(Object error) {
        System.err.println("["+Reference.MODID+"] "+ error);
    }


}
