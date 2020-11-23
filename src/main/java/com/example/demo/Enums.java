package com.example.demo;

import java.util.EnumSet;

public final class Enums {

    public static <E extends Enum<E> & SafeEnum> E from(Class<E> clazz, long id) {
        for (E e : EnumSet.allOf(clazz)) {
            if (e.getId() == id) {
                return e;
            }
        }

        throw new IllegalArgumentException("Unknown " + clazz.getSimpleName() + " id: " + id);
    }

    public static <E extends Enum<E> & SafeEnum> E from(Class<E> clazz, String name) {
        if (name == null) {
            return null;
        }

        for (E e : EnumSet.allOf(clazz)) {
            if (e.getName().equals(name)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Unknown " + clazz.getSimpleName() + " name: " + name);
    }

}