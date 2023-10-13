package com.hyh.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link CorsConfig}.
 */
public class CorsConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'corsConfig'.
   */
  public static BeanDefinition getCorsConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CorsConfig.class);
    beanDefinition.setTargetType(CorsConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(CorsConfig.class);
    beanDefinition.setInstanceSupplier(CorsConfig$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
