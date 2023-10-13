package com.hyh.controller;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link MailController}.
 */
public class MailController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static MailController apply(RegisteredBean registeredBean, MailController instance) {
    AutowiredFieldValueResolver.forRequiredField("mailService").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
