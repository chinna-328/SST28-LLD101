import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;
    private final RuleInput config;

    public EligibilityEngine(FakeEligibilityStore store, List<EligibilityRule> rules, RuleInput config) { 
        this.store = store; 
        this.rules = rules;
        this.config = config;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s); 
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        for (EligibilityRule rule : rules) {
            String failureReason = rule.evaluate(s, config);
            if (failureReason != null) {
                status = "NOT_ELIGIBLE";
                reasons.add(failureReason);
                break; 
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}