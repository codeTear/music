package com.hyh.service.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SongServiceImpl}.
 */
public class SongServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'songServiceImpl'.
   */
  public static BeanDefinition getSongServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SongServiceImpl.class);
    InstanceSupplier<SongServiceImpl> instanceSupplier = InstanceSupplier.using(SongServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(SongServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
