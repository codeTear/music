package com.hyh.service.impl;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link UserServiceImpl}.
 */
public class UserServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static UserServiceImpl apply(RegisteredBean registeredBean, UserServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("userDao").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
