package demo.ioc;

import demo.ioc.MyAutowired;
import demo.ioc.MyBean;
import demo.ioc.Order;
import demo.ioc.OrderRepoImp;

@MyBean
public class OrderService {

    @MyAutowired
    private OrderRepoImp orderRepo;

    public OrderService() {
    }

    public Order getOrderDetails(Integer orderId) {
        return orderRepo.getById(orderId);
    }
}