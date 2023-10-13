package com.hyh.service.impl;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link SongListServiceImpl}.
 */
public class SongListServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static SongListServiceImpl apply(RegisteredBean registeredBean,
      SongListServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("listSongDao").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("songDao").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
