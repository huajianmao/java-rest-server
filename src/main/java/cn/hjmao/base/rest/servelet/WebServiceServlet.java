package cn.hjmao.base.rest.servelet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hjmao on 2015/4/16.
 */
public class WebServiceServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public WebServiceServlet() {
  }
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
    System.out.println("get");
  }
  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
    System.out.println("post");
  }
}
