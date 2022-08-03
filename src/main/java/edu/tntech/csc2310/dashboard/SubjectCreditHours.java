package edu.tntech.csc2310.dashboard;

public class SubjectCreditHours {
    int CreditHoursGenerated;
    SubjectTerm subjectTerm;

    public SubjectCreditHours(int creditHoursGenerated, SubjectTerm subjectTerm) {
        CreditHoursGenerated = creditHoursGenerated;
        this.subjectTerm = subjectTerm;
    }

    public int getCreditHoursGenerated() {
        return CreditHoursGenerated;
    }

    public SubjectTerm getSubjectTerm() {
        return subjectTerm;
    }
}
