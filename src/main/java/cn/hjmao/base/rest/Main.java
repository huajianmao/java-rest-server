package cn.hjmao.base.rest;

import cn.hjmao.base.rest.servelet.WebServiceServlet;
import cn.hjmao.base.rest.services.MyServices;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

/**
 * Created by hjmao on 2015/4/16.
 */
public class Main {
  private static void startService() throws Exception {
    Server server = new Server(8080);
    ServletContextHandler context = new ServletContextHandler(
        ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    ServletHolder h = new ServletHolder(new HttpServletDispatcher());
    h.setInitParameter("javax.ws.rs.Application",
        MyServices.class.getName());
    context.addServlet(h, "/*");
    server.setHandler(context);
    try {
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void startServlet() throws Exception {
    Server server = new Server(8080);
    ServletHandler handler = new ServletHandler();
    server.setHandler(handler);
    handler.addServletWithMapping(WebServiceServlet.class, "/web");
    server.start();
    server.join();
  }

  public static void main(String[] args) throws Exception {
    startService();
  }
}
