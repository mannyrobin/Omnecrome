package ru.armd.pbk.core.caches;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;

import java.lang.reflect.Method;

/**
 * Генератор ключений для загрущика ГИСа.
 */
public class GisLoaderCacheKeyGenerator
	implements KeyGenerator {

   @Override
   public Object generate(Object o, Method method, Object... objects) {


	  return new SimpleKey(method.getName(), objects);
   }
}
