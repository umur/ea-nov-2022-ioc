
@MyBean
public class AClass {

    @MyAutowired
    public BClass b;
    public void print(){
        System.out.println("printing from A class");
    }
    public void display(){
        b.print();
    }
}
