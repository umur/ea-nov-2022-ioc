package edu.miu.eaplayground;

import edu.miu.eaplayground.Lab1.MyInjector;
import edu.miu.eaplayground.Lab1.NotFoundBeanException;
import edu.miu.eaplayground.Lab1.sub.Test;
import javassist.NotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EaPlaygroundApplication {

    public static void main(String[] args) throws NotFoundBeanException, ClassNotFoundException, NotFoundException {
//        SpringApplication.run(EaPlaygroundApplication.class, args);
        SpringApplication.run(EaPlaygroundApplication.class, args);
        MyInjector myInjector = new MyInjector();
        Test test = (Test) myInjector.getBean(Test.class);
        test.print();
        test.display();
    }

}
