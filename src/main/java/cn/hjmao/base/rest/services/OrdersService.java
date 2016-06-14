package cn.hjmao.base.rest.services;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hjmao on 2015/4/16.
 */

@Path("orders")
public class OrdersService {
  // Stores state simply in a static collection class.
  private static Map<Integer, Order> orders = new TreeMap<Integer, Order>();

  @GET
  @Produces("application/json")
  public Response doQuery() {
    return Response.ok(orders).build();
  }

  private static int LAST_ORDER_ID = 0;
  @POST
  @Produces("application/json")
  public Response create(@FormParam("name") String name) {
    if (name != null) {
      int id = LAST_ORDER_ID + 1;
      Order order = new Order(id, name);
      orders.put(id, order);
      LAST_ORDER_ID = id;
      return Response.ok(order).build();
    } else {
      System.err.println("No name!");
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }

  @Path("/{id}")
  @GET
  @Produces("application/json")
  public Response get(@PathParam("id") int id) {
    Order order = null;
    if (orders.containsKey(id)) {
      order = orders.get(id);
    }

    if (order == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      return Response.ok(order).build();
    }
  }
  class Order {
    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    private int id;
    private String name;

    public Order(int id, String name) {
      this.id = id;
      this.name = name;
    }

  }
}
