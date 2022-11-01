public class Main {
    
    public static void main(String[] args) {
        try {
            MyInjector.scan();
            MyRepository myRepository = new  MyRepository();
            myRepository.printHello();
            MyService myService = new MyService();
            myService.print();
            MyInjector.getBean(myRepository.getClass());
            MyInjector.getBean(myService.getClass());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}