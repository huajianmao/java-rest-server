package cn.hjmao.base.rest;

import cn.hjmao.base.rest.services.OrdersService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hjmao on 2015/4/16.
 */
public class ServiceContainer extends Application {
  private static Set<Object> services = new HashSet<Object>();

  public ServiceContainer() {
    services.add(new OrdersService());
    // FIXME: if you add new service, put it here in the services set.
  }

  @Override
  public Set<Object> getSingletons() {
    return services;
  }

  public static Set<Object> getServices() {
    return services;
  }
}
