import demo.ioc.AppConfig;
import demo.ioc.OrderService;

public class Main {
    public static void main(String[] args) throws Exception {
        MyInjector applicationContext = new MyInjector(AppConfig.class);
        OrderService orderService = applicationContext.getBean(OrderService.class);
        System.out.println(orderService.getOrderDetails(1));
    }
}