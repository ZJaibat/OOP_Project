package edu.tntech.csc2310.dashboard;

public class Faculty implements Comparable<Faculty>{

    private String firstname;
    private String lastname;

    public Faculty(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public int compareTo(Faculty o) {
        int result = 1;
        if(this.lastname.compareTo(o.getLastname()) == 0){
            if(this.firstname.compareTo(o.getFirstname()) == 0)
                result = 0;
        }
        return result;
    }
}