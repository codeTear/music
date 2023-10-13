package com.hyh.service.impl;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link SongServiceImpl}.
 */
public class SongServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static SongServiceImpl apply(RegisteredBean registeredBean, SongServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("songDao").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("singerDao").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("musicListDao").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("listSongDao").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
