package cn.hjmao.base.rest;

import cn.hjmao.base.rest.servelet.WebServiceServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Created by hjmao on 2015/4/16.
 */
public class Main {
  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);
    ServletHandler handler = new ServletHandler();
    server.setHandler(handler);
    handler.addServletWithMapping(WebServiceServlet.class, "/web");
    server.start();
    server.join();
  }
}
