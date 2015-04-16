package cn.hjmao.base.rest.services;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hjmao on 2015/4/16.
 */
public class MyServices extends Application {
  private static Set<Object> services = new HashSet<Object>();

  public MyServices() {
    services.add(new OrdersService());
  }

  @Override
  public Set<Object> getSingletons() {
    return services;
  }

  public static Set<Object> getServices() {
    return services;
  }
}
