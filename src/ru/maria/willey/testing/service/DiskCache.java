package ru.maria.willey.testing.service;

import ru.maria.willey.testing.interfaces.ICache;
import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.util.CacheException;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by User on 025 25.07.16.
 */
public class DiskCache implements ICache<Cache, Object> {
    String filename;
    Map<Cache, Object> diskCaches;
    File tmpFile;


    public DiskCache() {
        diskCaches = new ConcurrentSkipListMap<>();
        File tempFolder = new File("temp\\");
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }


    }

    @Override
    public Object get(Cache key) throws CacheException, IOException, ClassNotFoundException {
        return diskCaches.get(key);
    }

    @Override
    public void put(Cache key, Object value) throws CacheException, IOException, ClassNotFoundException {
        diskCaches.put(key, value);
    }

    @Override
    public int count() {
        return diskCaches.size();
    }

    @Override
    public void remove(Cache key) throws CacheException, IOException {
        diskCaches.remove(key);

    }

    @Override
    public void clear() throws IOException {
        diskCaches.clear();

    }

    public File writeToDisk(Cache cache) {
        filename = cache.getId().toString().replaceAll("-", "") + ".temp";
        try {
            tmpFile = File.createTempFile("cache", filename, new File("temp\\"));
            String text = cache.getCache().toString();
            Writer writer = new FileWriter(tmpFile.getAbsoluteFile(), false);
            writer.write(text);
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmpFile;

    }

    public void deleteFile(File tmpFile) {
        tmpFile.deleteOnExit();
    }
}
