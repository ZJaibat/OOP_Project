package edu.tntech.csc2310.dashboard;

public class CourseInstance {

    private String DEPARTMENT;
    private int STUDENTCOUNT;
    private int CREDITS;
    private String CRN;
    private String COURSE;
    private String SECTION;
    private String ISTIMEDETERMINED;
    private String STARTTIME;
    private String STARTAM_PM;
    private String ENDTIME;
    private String ENDAM_PM;
    private String CLASSDAYS;
    private String ISLOCDETERMINED;
    private String BUILDING;
    private String ROOM;
    public String ISONLINE;
    public String PROF;
    public String MAXIMUMSTUDENTS;
    public String ISOPEN;
    public String TITLE;
    private Faculty faculty;
    private SubjectTerm subjectTerm;

    public int getSTUDENTCOUNT() {
        return STUDENTCOUNT;
    }

    public int getCREDITS() {
        return CREDITS;
    }

    public String getCRN() {
        return CRN;
    }

    public String getCOURSE() {
        return COURSE;
    }

    public String getSECTION() {
        return SECTION;
    }

    public String getISTIMEDETERMINED() {
        return ISTIMEDETERMINED;
    }

    public String getSTARTTIME() {
        return STARTTIME;
    }

    public String getSTARTAM_PM() {
        return STARTAM_PM;
    }

    public String getENDTIME() {
        return ENDTIME;
    }

    public String getENDAM_PM() {
        return ENDAM_PM;
    }

    public String getCLASSDAYS() {
        return CLASSDAYS;
    }

    public String getISLOCDETERMINED() {
        return ISLOCDETERMINED;
    }

    public String getBUILDING() {
        return BUILDING;
    }

    public String getROOM() {
        return ROOM;
    }

    public String getISONLINE() {
        return ISONLINE;
    }


    public String getPROF() {
        return PROF;
    }

    public String getMAXIMUMSTUDENTS() {
        return MAXIMUMSTUDENTS;
    }

    public String getISOPEN() {
        return ISOPEN;
    }

    public String getTITLE() {
        return TITLE;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public SubjectTerm getSubjectTerm() {
        return subjectTerm;
    }

    public String toString(){
        return DEPARTMENT + " " + COURSE + "-" + SECTION + " (" + PROF + ") " +
                CLASSDAYS + "\t\t" + STARTTIME + STARTAM_PM + " - " + ENDTIME + ENDAM_PM + "\t" + STUDENTCOUNT + "/" + MAXIMUMSTUDENTS;
    }

    public void Splitter(){

        if(PROF != null){
            String[] nm = PROF.split(",");

            String Lname = nm[1].trim();
            String Fname = nm[0].trim();
            faculty = new Faculty(Lname,Fname);
        }
    }

    public void Assigning(String term){

        subjectTerm = new SubjectTerm(DEPARTMENT, term);

    }
}
