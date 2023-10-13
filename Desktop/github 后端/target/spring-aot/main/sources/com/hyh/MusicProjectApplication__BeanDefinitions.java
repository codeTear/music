package com.hyh;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link MusicProjectApplication}.
 */
public class MusicProjectApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'musicProjectApplication'.
   */
  public static BeanDefinition getMusicProjectApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MusicProjectApplication.class);
    beanDefinition.setTargetType(MusicProjectApplication.class);
    ConfigurationClassUtils.initializeConfigurationClass(MusicProjectApplication.class);
    beanDefinition.setInstanceSupplier(MusicProjectApplication$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
