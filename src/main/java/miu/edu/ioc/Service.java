package miu.edu.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MyBean
public class Service {
    static Logger logger = LoggerFactory.getLogger(Service.class);
    public Service() {
        logger.info("Service constructed");
    }

    public void print() {
        logger.info("Service printing message");
    }
}
