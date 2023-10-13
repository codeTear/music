package com.hyh.service.impl;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link MailServiceImpl}.
 */
public class MailServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static MailServiceImpl apply(RegisteredBean registeredBean, MailServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("mailSender").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("templateEngine").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("from").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
