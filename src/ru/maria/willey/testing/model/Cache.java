package ru.maria.willey.testing.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by User on 024 24.07.16.
 */
public class Cache implements Comparable<Cache>, Serializable {

    private static final long serialVersionUID = 654742189L;


    Object cache;
    int frequency;
    UUID id;

   /* String filename;*/

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Cache(Object cache) {
        this.cache = cache;
        this.frequency = 0;
        this.id = UUID.randomUUID();
      /*  this.filename = id.toString().replaceAll("-","") + ".txt";*/

    }

    public Cache() {
    }

    public void setCache(Object cache) {
        this.cache = cache;
    }

    public Object getCache() {
        return cache;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cache cache1 = (Cache) o;

        if (frequency != cache1.frequency) return false;
        if (!cache.equals(cache1.cache)) return false;
        return id.equals(cache1.id);

    }

    @Override
    public int hashCode() {
        int result = cache.hashCode();
        result = 31 * result + frequency;
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public int compareTo(Cache o) {
        if (this.getFrequency() - o.getFrequency() == 0) {
            return this.hashCode() - o.hashCode();
        } else {
            return this.getFrequency() - o.getFrequency();
        }
    }

    @Override
    public String toString() {
        return "Cache{" +
                "cache=" + cache +
                ", frequency=" + frequency +
                ", id=" + id +
                '}';
    }
}
