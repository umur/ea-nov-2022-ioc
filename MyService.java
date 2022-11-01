
@MyBean
public class MyService {
    @MyAutowired
    private MyRepository myRepository;

    public void print() {
        System.out.println("Hello from MyService");
    }

}
