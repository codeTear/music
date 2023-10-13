package com.hyh.service.impl;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link SongSingerServiceImpl}.
 */
public class SongSingerServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static SongSingerServiceImpl apply(RegisteredBean registeredBean,
      SongSingerServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("songDao").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("songDTODao").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("userDao").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
