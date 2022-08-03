package edu.tntech.csc2310.dashboard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceBridgeTest {

    @Test
    //This was developed for the all course test.
    public void test1(){

        // Happy path
        SemesterSchedule test = new ServiceBridge().allcourses("202210");
        assertTrue(test.getSchedule().length > 1);

        //kinda sad path
        SemesterSchedule testa = new ServiceBridge().allcourses("202250");
        assertTrue(testa.getSchedule().length <= 1);


    }

    @Test
    //This was developed for the courses by subject test.
    public void test2(){
        //Happy Path
        SemesterSchedule instance = new ServiceBridge().coursesbysubject("202210","CSC");
        assertTrue(instance.getSchedule().length > 10);

        //kinda sad path
        SemesterSchedule instancea = new ServiceBridge().coursesbysubject("202233","CSC");
        assertTrue(instancea.getSchedule().length <= 1);
    }

    @Test
    //This was developed for the courses by faculty test
    public void test12(){
        //Happy path
        CourseInstance[] instances = new ServiceBridge().coursesbyfaculty("202210","CSC","Gerald C", "Gannod");
        assertTrue(instances.length>0);

        //Kinda sad path
        CourseInstance[] instancesa = new ServiceBridge().coursesbyfaculty("202210","CSC","Harrold", "Gannod");
        assertTrue(instancesa.length == 0);

    }

    @Test
    //Developed for the courses by section test
    public void test4(){
        //Happy path
        CourseInstance instacesa = new ServiceBridge().coursebysection("202180","CSC","001","2310");
        assertTrue(instacesa.getSECTION() != null);

        //Kinda sad path
        CourseInstance instancesb = new ServiceBridge().coursebysection("202130","APP","001","2300");
        assertTrue(instancesb.getPROF() == null);
    }

    @Test
    //Developed for the sch by dept test.
    public void test5(){

        //Happy path
        SubjectCreditHours sch = new ServiceBridge().schbydepartment("202210","CSC");
        assertTrue(sch.CreditHoursGenerated > 200);

        //Kinda sad path
        SubjectCreditHours scha = new ServiceBridge().schbydepartment("202210","AAA");
        assertTrue(scha.CreditHoursGenerated == 0);



    }

    @Test
    //Developed for the sch by faculty
    public void test6(){

        //Happy path
        FacultyCreditHours fch = new ServiceBridge().schbyfaculty("202210","CSC","Gerald C","Gannod");
        assertTrue(fch.CreditHoursGenerated > 100);

        //Kinda sad path
        FacultyCreditHours fcha = new ServiceBridge().schbyfaculty("202210","CSC","Morgan","Freeman");
        assertTrue(fcha.CreditHoursGenerated == 0);



    }

}