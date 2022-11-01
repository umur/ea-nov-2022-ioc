import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NotFoundBeanException {
        SpringApplication.run(Main.class, args);
        MyInjector i = new MyInjector();
        AClass a = (AClass) i.getBean(AClass.class) ;
        a.print();
        a.display();
    }
}
