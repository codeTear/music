package com.hyh.service.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link MailServiceImpl}.
 */
public class MailServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'mailServiceImpl'.
   */
  public static BeanDefinition getMailServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MailServiceImpl.class);
    InstanceSupplier<MailServiceImpl> instanceSupplier = InstanceSupplier.using(MailServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(MailServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
