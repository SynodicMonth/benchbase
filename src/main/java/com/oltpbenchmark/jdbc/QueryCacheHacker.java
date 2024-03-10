package com.oltpbenchmark.jdbc;

// import java.sql.ResultSet;

import javax.sql.rowset.CachedRowSet;

public final class QueryCacheHacker {
  private static QueryCacheHacker instance = null;
  private QueryCache cache = null;
  private Boolean enabled = false;

  private QueryCacheHacker() {
    cache = new QueryCache();
  }

  private QueryCacheHacker(int maxSize) {
    cache = new QueryCache(maxSize);
  }

  public static synchronized QueryCacheHacker getInstance() {
    if (instance == null) {
      instance = new QueryCacheHacker();
    }
    return instance;
  }

  public static synchronized QueryCacheHacker getInstance(int maxSize) {
    if (instance == null) {
      instance = new QueryCacheHacker(maxSize);
    }
    return instance;
  }

  public synchronized CachedRowSet get(String key) {
    if (instance != null) {
      return instance.cache.get(key);
    } else {
      throw new RuntimeException("QueryCacheHacker not initialized");
    }
  }

  public synchronized void set(String key, CachedRowSet value) {
    if (instance != null) {
      instance.cache.set(key, value);
    } else {
      throw new RuntimeException("QueryCacheHacker not initialized");
    }
  }

  public synchronized void invalidateAll() {
    if (instance != null) {
      instance.cache.invalidateAll();
    } else {
      throw new RuntimeException("QueryCacheHacker not initialized");
    }
  }

  public void setEnabled() {
    enabled = true;
  }

  public boolean isEnabled() {
    return enabled;
  }
}

