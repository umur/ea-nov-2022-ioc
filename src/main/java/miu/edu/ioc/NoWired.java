package miu.edu.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoWired {
    static Logger logger = LoggerFactory.getLogger(NoWired.class);

    public NoWired() {
        logger.info("NoWired constructed");
    }
}
