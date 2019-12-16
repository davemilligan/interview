package com.davemilligan.interview.hackerrank;

import com.davemilligan.interview.io.Input;

import java.io.FileNotFoundException;
import java.util.*;

class StudentComparator implements Comparator<Student> {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(Input.getFile("cgpa.txt"));
        int testCases = Integer.parseInt(in.nextLine());

        List<Student> studentList = new ArrayList<Student>();
        while(testCases>0){
            int id = in.nextInt();
            String fname = in.next();
            double cgpa = in.nextDouble();

            Student st = new Student(id, fname, cgpa);
            studentList.add(st);

            testCases--;
        }

        //  Java 8
//        Collections.sort(studentList,
//              Comparator.comparing(Student::getCgpa).reversed().
//                thenComparing(Student::getFname).
//                thenComparing(Student::getId));

        Collections.sort(studentList, new StudentComparator());
        for(Student st: studentList){
            System.out.printf("%d %s %s \n", st.getId(), st.getFname(), st.getCgpa());
        }
    }

    @Override
    public int compare(Student s1, Student s2) {
        if (s1.getCgpa() == s2.getCgpa()) {
            if (s1.getFname().compareTo(s2.getFname()) == 0) {
                return s1.getId() - s2.getId();
            } else {
                return s1.getFname().compareTo(s2.getFname());
            }
        } else if (s1.getCgpa() > s2.getCgpa()) {
            return -1;
        } else return 1;
    }
}

class Student{
    private int id;
    private String fname;
    private double cgpa;
    public Student(int id, String fname, double cgpa) {
        super();
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }
    public int getId() {
        return id;
    }
    public String getFname() {
        return fname;
    }
    public double getCgpa() {
        return cgpa;
    }
}
