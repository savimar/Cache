package ru.maria.willey.testing.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.model.User;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.junit.Assert.*;

/**
 * Created by User on 025 25.07.16.
 */
public class DiskCacheTest {
    Map<Cache, Object> map;
    DiskCache diskCache;
    User user1;
    Cache cache1;
    @Before
    public void setUp() throws Exception {

        user1 = new User(1, "Olga", "Ivanova");
        map = new ConcurrentSkipListMap<>();
        cache1 = new Cache(user1);
        map.put(cache1, user1);
        diskCache = new DiskCache();

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void put() throws Exception {

    }

    @Test
    public void count() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void clear() throws Exception {

    }

    @Test
    public void writeToDisk() throws Exception {
       File tmp = diskCache.writeToDisk(cache1);
        Assert.assertEquals("C:\\", tmp.getAbsolutePath().substring(0, 3));
        diskCache.deleteFile(tmp);

    }

    @Test
    public void deleteFile() throws Exception {

    }

}