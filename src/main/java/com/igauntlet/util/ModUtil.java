package com.igauntlet.util;

import com.igauntlet.Infinity;

public class ModUtil {


    public static void Log(Object text) {
        System.out.println("[" + Infinity.MODID + "] " + text);
    }

    public static void Error(Object error) {
        System.err.println("[" + Infinity.MODID + "] " + error);
    }


}
