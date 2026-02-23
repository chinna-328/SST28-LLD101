public interface EligibilityRule {
    String evaluate(StudentProfile s, RuleInput config);
}