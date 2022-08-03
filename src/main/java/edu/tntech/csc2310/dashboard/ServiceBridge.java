package edu.tntech.csc2310.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
public class ServiceBridge {

    private static final String apiKey = "801116ED-23E2-4875-BA9C-66C3735302D0";
    private static final String urlString = "https://portapit.tntech.edu/express/api/unprotected/getCourseInfoByAPIKey.php?Subject=%s&Term=%s&Key=%s";

    private CourseInstance[] courses;

    @GetMapping("/allcourses")
    public SemesterSchedule allcourses(
            @RequestParam(value = "term", defaultValue = "na")
                    String term) {
        Gson gson = new Gson();

        try {
            String urlFmt = String.format(urlString, "", term, apiKey);
            URL url = new URL(urlFmt);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonReader jr = gson.newJsonReader(in);
            courses = gson.fromJson(jr, CourseInstance[].class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubjectTerm semsch = new SubjectTerm("ALL", term);

        for (int i = 0; i < courses.length; i++) {
            courses[i].Splitter();
            courses[i].Assigning(term);
        }
        SemesterSchedule times = new SemesterSchedule(semsch, courses);
        return times;

    }

    @GetMapping("/coursesbysubject")
    public SemesterSchedule coursesbysubject(
            @RequestParam(value = "term", defaultValue = "na") String term,
            @RequestParam(value = "subject", defaultValue = "na") String subject) {
        Gson gson = new Gson();

        try {
            String urlFmt = String.format(urlString, subject, term, apiKey);
            URL url = new URL(urlFmt);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonReader jr = gson.newJsonReader(in);
            courses = gson.fromJson(jr, CourseInstance[].class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubjectTerm semsch = new SubjectTerm("ALL", term);

        for (int i = 0; i < courses.length; i++) {
            courses[i].Splitter();
            courses[i].Assigning(term);
        }
        {
            SemesterSchedule times = new SemesterSchedule(semsch, courses);
            return times;

        }

    }

    @GetMapping("/coursesbyfaculty")
    public CourseInstance[] coursesbyfaculty(
            @RequestParam(value = "term", defaultValue = "na") String term,
            @RequestParam(value = "subject", defaultValue = "na") String subject,
            @RequestParam(value = "firstname", defaultValue = "na") String firstname,
            @RequestParam(value = "lastname", defaultValue = "na") String lastname) {
        Gson gson = new Gson();

        try {
            String urlFmt = String.format(urlString, subject, term, apiKey);
            URL url = new URL(urlFmt);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonReader jr = gson.newJsonReader(in);
            courses = gson.fromJson(jr, CourseInstance[].class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Faculty fac = new Faculty(firstname, lastname);
        ArrayList<CourseInstance> what = new ArrayList<>();
        for (int i = 0; i < courses.length; i++) {
            courses[i].Splitter();
            courses[i].Assigning(term);

            if (courses[i].getPROF() != null) {
                if (courses[i].getFaculty().compareTo(fac) == 0) {
                    what.add(courses[i]);
                }
            }
        }
        CourseInstance[] a = new CourseInstance[what.size()];

        return what.toArray(a);

    }

    @GetMapping("/coursebysection")
    public CourseInstance coursebysection(
            @RequestParam(value = "term", defaultValue = "na") String term,
            @RequestParam(value = "subject", defaultValue = "na") String subject,
            @RequestParam(value = "section", defaultValue = "na") String section,
            @RequestParam(value = "course", defaultValue = "na")String course){

            Gson gson = new Gson();

        try {
            String urlFmt = String.format(urlString, subject, term, apiKey);
            URL url = new URL(urlFmt);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonReader jr = gson.newJsonReader(in);
            courses = gson.fromJson(jr, CourseInstance[].class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i < courses.length;i++){

            courses[i].Splitter();
            courses[i].Assigning(term);
                if(courses[i].getCOURSE().contentEquals(course) && courses[i].getSECTION().contentEquals(section)){
                    return courses[i];
}
        }
        return new CourseInstance();
    }

    @GetMapping("/schbydepartment")
    public SubjectCreditHours schbydepartment(
            @RequestParam(value = "term", defaultValue = "na") String term,
            @RequestParam(value = "subject", defaultValue = "na") String subject){

        Gson gson = new Gson();

        try {
            String urlFmt = String.format(urlString, subject, term, apiKey);
            URL url = new URL(urlFmt);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonReader jr = gson.newJsonReader(in);
            courses = gson.fromJson(jr, CourseInstance[].class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubjectTerm subt = new SubjectTerm(subject, term);
        int total = 0;
        for(int i=0;i < courses.length;i++){

            courses[i].Splitter();
            courses[i].Assigning(term);
            total=(courses[i].getSTUDENTCOUNT() * courses[i].getCREDITS()) + total;

        }
        SubjectCreditHours sch = new SubjectCreditHours(total, subt);

        return sch;
    }

    @GetMapping("/schbyfaculty")
    public FacultyCreditHours schbyfaculty(
            @RequestParam(value = "term", defaultValue = "na") String term,
            @RequestParam(value = "subject", defaultValue = "na") String subject,
            @RequestParam(value = "firstname", defaultValue = "na") String firstname,
            @RequestParam(value = "lastname", defaultValue = "na") String lastname){
        Gson gson = new Gson();

        try {
            String urlFmt = String.format(urlString, subject, term, apiKey);
            URL url = new URL(urlFmt);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonReader jr = gson.newJsonReader(in);
            courses = gson.fromJson(jr, CourseInstance[].class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubjectTerm subtem = new SubjectTerm(subject,term);
        Faculty facul = new Faculty(firstname, lastname);
        int total = 0;
        for(int i=0;i < courses.length; i++){
            courses[i].Splitter();
            courses[i].Assigning(term);

            if (courses[i].getPROF() != null){
                if (courses[i].getFaculty().compareTo(facul) == 0) {
                     total = (courses[i].getSTUDENTCOUNT() * courses[i].getCREDITS()) + total;
                }
            }

        }
        FacultyCreditHours FacCred = new FacultyCreditHours(total,subtem, facul);
            return FacCred;

    }

    @GetMapping("/facultybysubject")
    public CourseInstance[] facultybysection(
            @RequestParam(value = "term", defaultValue = "na") String term,
            @RequestParam(value = "subject", defaultValue = "na") String subject
    ) {
        Gson gson = new Gson();

        try {
            String urlFmt = String.format(urlString, subject, term, apiKey);
            URL url = new URL(urlFmt);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonReader jr = gson.newJsonReader(in);
            courses = gson.fromJson(jr, CourseInstance[].class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;


    }


    public CourseInstance[] getCourses () {
            return courses;
        }}


























   /* @GetMapping("/coursesbyfaculty")
    public SemesterSchedule coursesbyfaculty(
            @RequestParam(value = "term", defaultValue = "na")String term,
            @RequestParam(value = "subject", defaultValue = "na")String subject,
            @RequestParam(value = "lastname", defaultValue = "na")String ln,
            @RequestParam(value = "firstname", defaultValue = "na")String fn){
        Gson gson = new Gson();
        try{
            String urlFmt = String.format(urlString,subject, term, apiKey);
            URL url = new URL(urlFmt);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonReader jr = gson.newJsonReader(in);
            courses = gson.fromJson(jr, CourseInstance[].class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubjectTerm semsch = new SubjectTerm(subject,term);
        for(int i=0;i< courses.length;i++){
            return SubjectTerm;


        }*/

    /*public ServiceBridge(String term, String subject){

        Gson gson = new Gson();

        try {
            String urlFmt = String.format(urlString, subject.toUpperCase(), term, apiKey);
            URL url = new URL(urlFmt);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonReader jr = gson.newJsonReader(in);
            courses = gson.fromJson(jr, CourseInstance[].class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    //}


