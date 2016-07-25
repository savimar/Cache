package ru.maria.willey.testing;

import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.service.MemoryCache;
import ru.maria.willey.testing.util.CacheException;

import java.io.IOException;

/**
 * Created by User on 025 25.07.16.
 */
public class Main {
    public static void main(String[] args) throws CacheException, IOException, ClassNotFoundException {
        MemoryCache memoryCache = new MemoryCache<>();
        Object o1 = new Object();
        Cache cache1 = new Cache(o1);
        memoryCache.put(cache1, o1);
    }
}
