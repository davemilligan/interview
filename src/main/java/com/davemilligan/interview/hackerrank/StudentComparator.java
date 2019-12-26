package com.davemilligan.interview.hackerrank;

import com.davemilligan.interview.io.Input;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

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
//              Comparator.comparing(Student::getCGPA).reversed().
//                thenComparing(Student::getName).
//                thenComparing(Student::getID));

        Collections.sort(studentList, new StudentComparator());
        for(Student st: studentList){
            System.out.printf("%d %s %s \n", st.getID(), st.getName(), st.getCGPA());
        }
    }

    @Override
    public int compare(Student s1, Student s2) {
        if (s1.getCGPA() == s2.getCGPA()) {
            if (s1.getName().compareTo(s2.getName()) == 0) {
                return s1.getID() - s2.getID();
            } else {
                return s1.getName().compareTo(s2.getName());
            }
        } else if (s1.getCGPA() > s2.getCGPA()) {
            return -1;
        } else return 1;
    }
}

class Priorities {

    PriorityQueue<Student> pQueue = new PriorityQueue<Student>(Comparator.comparing(Student::getCGPA).reversed().
            thenComparing(Student::getName).
            thenComparing(Student::getID));

    public List<Student> getStudents(List<String> events) {
        events.forEach(e -> {
            String[] eles = e.split("\\s+");
            String event = eles[0];
            if (event.equals("SERVED"))
                pQueue.poll();
            else {
                String name = eles[1];
                double cgpa = Double.parseDouble(eles[2]);
                int id = Integer.parseInt(eles[3]);
                Student s = new Student(id, name, cgpa);
                pQueue.add(s);
            }
        });
        List<Student> list = new ArrayList<>();
        while (!pQueue.isEmpty())
            list.add(pQueue.poll());
        return list;
    }
}

class Student{
    private int ID;
    private String name;
    private double CGPA;
    public Student(int ID, String name, double CGPA) {
        super();
        this.ID = ID;
        this.name = name;
        this.CGPA = CGPA;
    }
    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public double getCGPA() {
        return CGPA;
    }
}
