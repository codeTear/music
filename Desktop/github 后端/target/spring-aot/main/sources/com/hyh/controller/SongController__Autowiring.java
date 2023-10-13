package com.hyh.controller;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link SongController}.
 */
public class SongController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static SongController apply(RegisteredBean registeredBean, SongController instance) {
    AutowiredFieldValueResolver.forRequiredField("songService").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("songService1").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("songSingerService").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
