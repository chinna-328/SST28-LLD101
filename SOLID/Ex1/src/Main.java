public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        
        // Dependency setup
        StudentRepository db = new FakeDb();
        StudentParser parser = new StudentParser();
        StudentValidator validator = new StudentValidator();
        OnboardingPresenter presenter = new OnboardingPresenter();
        
        OnboardingService svc = new OnboardingService(db, parser, validator, presenter);

        // Execution
        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}