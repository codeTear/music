package com.hyh.controller;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SongController}.
 */
public class SongController__BeanDefinitions {
  /**
   * Get the bean definition for 'songController'.
   */
  public static BeanDefinition getSongControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SongController.class);
    InstanceSupplier<SongController> instanceSupplier = InstanceSupplier.using(SongController::new);
    instanceSupplier = instanceSupplier.andThen(SongController__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
