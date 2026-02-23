import java.util.List;

public class OnboardingService {
    private final StudentRepository db;
    private final StudentParser parser;
    private final StudentValidator validator;
    private final OnboardingPresenter presenter;

    public OnboardingService(StudentRepository db, StudentParser parser, 
                             StudentValidator validator, OnboardingPresenter presenter) {
        this.db = db;
        this.parser = parser;
        this.validator = validator;
        this.presenter = presenter;
    }

    public void registerFromRawInput(String raw) {
        presenter.printInput(raw);

        // 1. Parse
        RawStudentData data = parser.parse(raw);

        // 2. Validate
        List<String> errors = validator.validate(data);
        if (!errors.isEmpty()) {
            presenter.printErrors(errors);
            return;
        }

        // 3. Generate ID & Create Record
        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, data.name, data.email, data.phone, data.program);

        // 4. Persist
        db.save(rec);

        // 5. Present Success
        presenter.printSuccess(rec, db.count());
    }
}