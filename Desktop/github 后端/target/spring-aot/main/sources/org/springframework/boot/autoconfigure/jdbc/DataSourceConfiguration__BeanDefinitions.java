package org.springframework.boot.autoconfigure.jdbc;

import javax.sql.DataSource;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DataSourceConfiguration}.
 */
public class DataSourceConfiguration__BeanDefinitions {
  /**
   * Bean definitions for {@link DataSourceConfiguration.Generic}.
   */
  public static class Generic {
    /**
     * Get the bean definition for 'generic'.
     */
    public static BeanDefinition getGenericBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(DataSourceConfiguration.Generic.class);
      beanDefinition.setInstanceSupplier(DataSourceConfiguration.Generic::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'dataSource'.
     */
    private static BeanInstanceSupplier<DataSource> getDataSourceInstanceSupplier() {
      return BeanInstanceSupplier.<DataSource>forFactoryMethod(DataSourceConfiguration.Generic.class, "dataSource", DataSourceProperties.class, JdbcConnectionDetails.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(DataSourceConfiguration.Generic.class).dataSource(args.get(0), args.get(1)));
    }

    /**
     * Get the bean definition for 'dataSource'.
     */
    public static BeanDefinition getDataSourceBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition();
      beanDefinition.setTargetType(DataSource.class);
      beanDefinition.setInstanceSupplier(getDataSourceInstanceSupplier());
      return beanDefinition;
    }
  }
}
