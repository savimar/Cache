package ru.maria.willey.testing.model;

import java.util.Comparator;

/**
 * Created by User on 024 24.07.16.
 */
public class Cache implements Comparable{
    Object cache;
    int frequency;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Cache(Object cache) {
        this.cache = cache;
    }

    public Cache() {
    }

    public void setCache(Object cache) {
        this.cache = cache;
    }

    public Object getCache() {
        return cache;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cache cache1 = (Cache) o;

        return cache.equals(cache1.cache);

    }

    @Override
    public int hashCode() {
        return cache.hashCode();
    }

    @Override
    public String toString() {
        return "Cache{" +
                "cache=" + cache +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (this.hashCode() <= o.hashCode()){
            return 1;
        }
        else return -1;
    }
}
