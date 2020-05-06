package com.jmteam.igauntlet.util.helpers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom Reflection Helper
 * Made By Josia50
 */
public class ReflectionHelper {

    public static void setValuePrivateDeclaredField(String name, Class clazz, Object instance, Object object) {
        Field field;

        try {
            field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, object);
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setValuePrivateField(String name, Class clazz, Object instance, Object object) {
        Field field;

        try {
            field = clazz.getField(name);
            field.setAccessible(true);
            field.set(instance, object);
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static List<Class> getSuperClasses(Object o) {
        List<Class> classList = new ArrayList<Class>();
        Class clazz = o.getClass();
        Class superclass = clazz.getSuperclass();
        classList.add(superclass);
        while (superclass != null) {
            classList.add(superclass);
            clazz = superclass;
            superclass = clazz.getSuperclass();
        }

        return classList;
    }

    public static Class getClassFromList(List<Class> list, Class clazz) {
        Class cl = null;

        for (int x = 0; x < list.size(); x++) {

            Class cli = list.get(x);
            if (cli.getSimpleName().equalsIgnoreCase(clazz.getSimpleName())) {
                cl = cli;
                break;
            }
        }

        return cl;
    }

    public static Class getClassFromSuperClasses(Object o, Class clazz) {
        Class cl = o.getClass();
        Class superclass = cl.getSuperclass();

        while (superclass != null) {
            if (superclass.getSimpleName().equalsIgnoreCase(clazz.getSimpleName())) return superclass;
            cl = superclass;
            superclass = cl.getSuperclass();
        }

        return null;
    }
}
