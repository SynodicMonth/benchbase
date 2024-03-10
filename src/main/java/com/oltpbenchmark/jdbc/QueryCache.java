package com.oltpbenchmark.jdbc;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
// import java.sql.ResultSet;
import javax.sql.rowset.CachedRowSet;

public final class QueryCache {
  private Cache<String, CachedRowSet> cache = null;

  public QueryCache() {
    cache = Caffeine.newBuilder().maximumSize(32).build();
  }

  public QueryCache(int maximumSize) {
    cache = Caffeine.newBuilder().maximumSize(maximumSize).build();
  }

  public synchronized void set(String key, CachedRowSet value) {
    // System.out.println("Setting key: " + key + " value: " + value);
    cache.put(key, value);
  }

  public synchronized CachedRowSet get(String key) {
    CachedRowSet res = cache.getIfPresent(key);
    if (res != null) {
      // print a blue HIT!
      // System.out.println("\u001B[34mHIT\u001B[0m");
    }
    return res;
  }

  public synchronized void invalidateAll() {
    cache.invalidateAll();
  }
}
