package ru.maria.willey.testing;

import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.service.MemoryCache;
import ru.maria.willey.testing.util.CacheException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws CacheException, IOException, ClassNotFoundException {
        MemoryCache memoryCache = new MemoryCache<>();
        Object o1 = new Object();
        Cache cache1 = new Cache(o1);
        memoryCache.put(cache1, o1);
    }
}
