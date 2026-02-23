public class DisciplinaryRule implements EligibilityRule {
    @Override
    public String evaluate(StudentProfile s, RuleInput config) {
        if (s.disciplinaryFlag != LegacyFlags.NONE) {
            return "disciplinary flag present";
        }
        return null;
    }
}