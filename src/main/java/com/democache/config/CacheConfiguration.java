package com.democache.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {
    CacheManagerCustomizer<ConcurrentMapCacheManager> customizer() {
        return new ConcurrentCustomizer();
    }

    class ConcurrentCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

        @Override
        public void customize(ConcurrentMapCacheManager cacheManager) {
            System.out.println("Cache manager customized invoked");
//            cacheManager.setAllowNullValues(false);

            // stores data in byte instead of data reference
            // have to implement serializable by the model in order for this to work
//            cacheManager.setStoreByValue(true);
        }
    }
}
