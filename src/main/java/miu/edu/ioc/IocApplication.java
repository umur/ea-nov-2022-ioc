package miu.edu.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IocApplication {
    static Logger logger = LoggerFactory.getLogger(IocApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IocApplication.class, args);
        logger.info("--------- Injector Started");
        MyInjector injector = new MyInjector();
        logger.info("--------- Injector Ended");
        logger.info("--------- Demo");
        Demo demo = (Demo) injector.getBean(Demo.class);
        demo.print(); // will be accessed to MyBean
        demo.servicePrint(); // will be accessed to MyAutowired field
        logger.info("--------- Service");
        Service service = (Service) injector.getBean(Service.class);
        service.print();
        logger.info("--------- NoWired");
        try {
            injector.getBean(NoWired.class);
        } catch (BeanNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

}
