package com.hyh.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link MpConfig}.
 */
public class MpConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'mpConfig'.
   */
  public static BeanDefinition getMpConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MpConfig.class);
    beanDefinition.setTargetType(MpConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(MpConfig.class);
    beanDefinition.setInstanceSupplier(MpConfig$$SpringCGLIB$$0::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mpInterceptor'.
   */
  private static BeanInstanceSupplier<MybatisPlusInterceptor> getMpInterceptorInstanceSupplier() {
    return BeanInstanceSupplier.<MybatisPlusInterceptor>forFactoryMethod(MpConfig.class, "mpInterceptor")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(MpConfig.class).mpInterceptor());
  }

  /**
   * Get the bean definition for 'mpInterceptor'.
   */
  public static BeanDefinition getMpInterceptorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition();
    beanDefinition.setTargetType(MybatisPlusInterceptor.class);
    beanDefinition.setInstanceSupplier(getMpInterceptorInstanceSupplier());
    return beanDefinition;
  }
}
