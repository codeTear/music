package com.hyh.service.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SongSingerServiceImpl}.
 */
public class SongSingerServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'songSingerServiceImpl'.
   */
  public static BeanDefinition getSongSingerServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SongSingerServiceImpl.class);
    InstanceSupplier<SongSingerServiceImpl> instanceSupplier = InstanceSupplier.using(SongSingerServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(SongSingerServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
