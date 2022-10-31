package miu.edu.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MyBean
public class Demo {

    static Logger logger = LoggerFactory.getLogger(Demo.class);
    @MyAutowired
    Service service;

    public Demo() {
        logger.info("Demo constructed");
    }

    public void print() {
        logger.info("Demo printing message");
    }

    public void servicePrint() {
        service.print();
    }

}
