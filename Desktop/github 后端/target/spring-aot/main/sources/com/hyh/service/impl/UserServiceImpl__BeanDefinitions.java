package com.hyh.service.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link UserServiceImpl}.
 */
public class UserServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'userServiceImpl'.
   */
  public static BeanDefinition getUserServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(UserServiceImpl.class);
    InstanceSupplier<UserServiceImpl> instanceSupplier = InstanceSupplier.using(UserServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(UserServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
