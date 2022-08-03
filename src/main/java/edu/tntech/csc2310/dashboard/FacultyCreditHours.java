package edu.tntech.csc2310.dashboard;

public class FacultyCreditHours extends SubjectCreditHours {

    private Faculty faculty;

    public FacultyCreditHours(int creditHoursGenerated, SubjectTerm subjectTerm, Faculty employeed) {
        super(creditHoursGenerated, subjectTerm);
        this.faculty = employeed;

    }

}
