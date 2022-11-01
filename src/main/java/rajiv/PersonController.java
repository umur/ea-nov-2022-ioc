package rajiv;

@MyBean
public class PersonController {

    @MyAutowired
    PetController petController;

    public boolean isPetSet(){
        return petController != null;
    }
}
