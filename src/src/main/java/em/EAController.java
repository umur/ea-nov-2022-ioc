package em;

@MyBean
public class EAController {
    @MyAutowired
    private StudentController studentController;

    public boolean isStudentPresent(){
        return studentController!=null;
    }
}
