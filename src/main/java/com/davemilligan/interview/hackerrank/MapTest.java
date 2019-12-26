package com.davemilligan.interview.hackerrank;

import java.util.*;

public class MapTest {

    public static void main(String[] args) {
        treeMap();
        linkedHashMap();
        navigableMap();
        weakHashMap();
    }

    /**
     * Lets GC if key is only reference to the value
     */
    public static void weakHashMap() {
        Map<Animal, String> map = new WeakHashMap<>();
        map.put(new Animal("Horse"),"Zebra");
        map.put(new Animal("Deer"),"Antelope");
        
        while (!map.isEmpty()) {
            try {
                System.gc();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("WeakHashMap GCd");
    }

    /**
     * Maintains inserted key order
     */
    public static void linkedHashMap() {
        Map<String, Animal> linkedHashMap = populate(new LinkedHashMap<>());
        print(linkedHashMap);
    }

    public static void navigableMap() {
        NavigableMap<String, Animal> treeMap = (NavigableMap)populate(new TreeMap<>());
        System.out.println("Floor:" + treeMap.floorEntry("Antelopf"));
    }

    /**
     * Maintains sorted key order
     */
    public static void treeMap() {
        NavigableMap<String, Animal> treeMap = (NavigableMap)populate(new TreeMap<>());
        System.out.println("Floor:" + treeMap.floorEntry("Czq"));
        print(treeMap);
    }

    public static Map<String, Animal> populate(Map<String, Animal> map) {
        map.put("Zebra", new Animal("Horse"));
        map.put("Antelope", new Animal("Deer"));
        map.put("Cow", new Animal("Ox"));
        map.put("Pig", new Animal("Dog"));
        return map;
    }

    public static void print(Map<String, Animal> map) {
        for (Map.Entry<String,Animal> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}

class Animal {
    String val;
    public Animal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "val='" + val + '\'' +
                '}';
    }
}
