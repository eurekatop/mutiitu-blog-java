package com.mutiitu.persistence;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.LoggerFactory;

import java.util.HashMap;

// TODO : BY REQUEST
public class SQLiteDBScope implements Scope {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final ThreadLocal<Map<Key<?>, Object>> storage = ThreadLocal.withInitial(HashMap::new);

    // private final ConcurrentHashMap<String, Object> storageDebug = new
    // ConcurrentHashMap<>();

    @Override
    public <T> Provider<T> scope(Key<T> key, Provider<T> unscoped) {
        return () -> {
            Map<Key<?>, Object> scopedObjects = storage.get();
            @SuppressWarnings("unchecked")
            T current = (T) scopedObjects.get(key);
            if (current == null) {
                current = unscoped.get();
                scopedObjects.put(key, current);

                var keyString = key.getTypeLiteral().toString() + "-" + Thread.currentThread();
                logger.debug(keyString);

                // storageDebug.put(keyString, current);

            }
            logger.info(current + " Add instance to thread: " + key + ";" + scopedObjects.size()); // + " key:=" +
            // storageDebug.size());
            // debug
            // Contador para contar las entradas con valores null
            // int contador = 0;
            //
            // for (Object valor : storageDebug.values()) {
            // if (valor != null) {
            // contador++;
            // }
            // }
            //
            // logger.debug("contador := " + contador);

            return current;
        };
    }
}
