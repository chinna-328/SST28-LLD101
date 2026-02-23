import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");
        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);
        
        RuleInput config = new RuleInput();
        
        List<EligibilityRule> rules = Arrays.asList(
            new DisciplinaryRule(),
            new CgrRule(),
            new AttendanceRule(),
            new CreditsRule()
        );
        
        EligibilityEngine engine = new EligibilityEngine(new FakeEligibilityStore(), rules, config);
        engine.runAndPrint(s);
    }
}