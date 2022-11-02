package demo.ioc;

import demo.ioc.MyBean;
import demo.ioc.Order;

import java.util.HashMap;
import java.util.Map;

@MyBean
public class OrderRepoImp  {
    Map<Integer, Order> orderIdToOrderMap = new HashMap<>();

    public OrderRepoImp() {
        orderIdToOrderMap.put(1, new Order(1, "Order 1", 10.0));
        orderIdToOrderMap.put(2, new Order(2, "Order 2", 20.0));
        orderIdToOrderMap.put(3, new Order(3, "Order 3", 30.0));
        orderIdToOrderMap.put(4, new Order(4, "Order 4", 40.0));
    }

    public Order getById(Integer orderId) {
        return orderIdToOrderMap.get(orderId);
    }

}
