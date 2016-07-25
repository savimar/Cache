package ru.maria.willey.testing.service;

import ru.maria.willey.testing.interfaces.ICache;
import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.util.CacheException;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;


public class DiskCache implements ICache<Cache, Object> {
    private String filename;
    private Map<Cache, Object> diskCaches;
    private File tmpFile;


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Map<Cache, Object> getDiskCaches() {
        return diskCaches;
    }

    public void setDiskCaches(Map<Cache, Object> diskCaches) {
        this.diskCaches = diskCaches;
    }

    public File getTmpFile() {
        return tmpFile;
    }

    public void setTmpFile(File tmpFile) {
        this.tmpFile = tmpFile;
    }

    public DiskCache(Map<Cache, Object> diskCaches) {
        new DiskCache();

        this.diskCaches = diskCaches;
    }

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

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmpFile;

    }

    public void deleteFile(File tmpFile) {
        tmpFile.deleteOnExit();
    }


}
