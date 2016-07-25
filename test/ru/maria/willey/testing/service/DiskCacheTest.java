package ru.maria.willey.testing.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.model.User;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class DiskCacheTest {
    private Map<Cache, Object> map;
    private DiskCache diskCache;
    private User user1;
    private Cache cache1;

    @Before
    public void setUp() throws Exception {

        user1 = new User(1, "Olga", "Ivanova");
        map = new ConcurrentSkipListMap<>();
        cache1 = new Cache(user1);
        map.put(cache1, user1);
        diskCache = new DiskCache(map);

    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(diskCache.get(cache1), user1);

    }

    @Test
    public void put() throws Exception {
        User user2 = new User(2, "Ivan", "Sidorov");
        Cache cache2 = new Cache(user2);
        diskCache.put(cache2, user2);
        map.put(cache2, user2);
        Assert.assertEquals(map, diskCache.getDiskCaches());

    }

    @Test
    public void count() throws Exception {
        Assert.assertEquals(1, diskCache.count());
    }

    @Test
    public void remove() throws Exception {
        diskCache.remove(cache1);
        Assert.assertEquals(0, diskCache.count());

    }

    @Test
    public void clear() throws Exception {
        diskCache.clear();
        Assert.assertEquals(0, diskCache.count());

    }

    @Test
    public void writeToDisk() throws Exception {
        File tmp = diskCache.writeToDisk(cache1);
        Assert.assertEquals("C:\\", tmp.getAbsolutePath().substring(0, 3));
        diskCache.deleteFile(tmp);

    }


}