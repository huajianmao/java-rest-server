package cn.hjmao.base.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import java.io.File;

/**
 * Created by hjmao on 2015/4/16.
 */
public class Main {
  private static final int port = 8080;
  private static final String rootContextPath = "/";
  private static final String apiMappingPrefix = "/api";
  private static final String staticMappingPrefix = "/html";
  private static final String staticPageDirectory = "src/main/html";

  private static void startService() throws Exception {
    Server server = new Server(port);
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath(rootContextPath);
    server.setHandler(context);

    // Set up API services.
    ServletHolder apiHolder = new ServletHolder(new HttpServletDispatcher());
    apiHolder.setInitParameter("javax.ws.rs.Application", ServiceContainer.class.getName());
    apiHolder.setInitParameter("resteasy.servlet.mapping.prefix", apiMappingPrefix);
    context.addServlet(apiHolder, apiMappingPrefix + "/*");

    // Set up static services.
    ServletHolder staticHolder = new ServletHolder("default", new DefaultServlet());
    if (!new File(staticPageDirectory).exists()) {
      System.err.println("Directory not found for the static pages.");
      System.exit(-1);
    }
    staticHolder.setInitParameter("resourceBase", staticPageDirectory);
    staticHolder.setInitParameter("dirAllowed", "true");
    staticHolder.setInitParameter("pathInfoOnly", "true");
    context.addServlet(staticHolder, staticMappingPrefix + "/*");

    try {
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws Exception {
    startService();
  }
}
