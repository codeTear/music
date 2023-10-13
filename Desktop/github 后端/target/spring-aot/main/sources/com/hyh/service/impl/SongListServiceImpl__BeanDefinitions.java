package com.hyh.service.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SongListServiceImpl}.
 */
public class SongListServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'songListServiceImpl'.
   */
  public static BeanDefinition getSongListServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SongListServiceImpl.class);
    InstanceSupplier<SongListServiceImpl> instanceSupplier = InstanceSupplier.using(SongListServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(SongListServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
