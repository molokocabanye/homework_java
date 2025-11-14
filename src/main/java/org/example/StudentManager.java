package org.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StudentManager {

    public static void removeBadStudents(Set<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() < 3.0) {
                iterator.remove();
            }
        }
    }

    public static void promoteStudents(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3.0) {
                student.nextCourse();
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты " + course + " курса:");
        boolean hasStudents = false;
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName() + " - " + student.getGroup());
                hasStudents = true;
            }
        }
        if (!hasStudents) {
            System.out.println("Нет студентов на этом курсе");
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        Student student1 = new Student("Иван Иванов", "ГР-1", 1);
        student1.addGrade("Математика", 4);
        student1.addGrade("Физика", 5);
        student1.addGrade("Программирование", 4);

        Student student2 = new Student("Петр Петров", "ГР-2", 1);
        student2.addGrade("Математика", 3);
        student2.addGrade("Физика", 2);
        student2.addGrade("Программирование", 3);

        Student student3 = new Student("Мария Сидорова", "ГР-1", 2);
        student3.addGrade("Математика", 5);
        student3.addGrade("Физика", 5);
        student3.addGrade("Программирование", 5);

        students.add(student1);
        students.add(student2);
        students.add(student3);

        System.out.println("Все студенты:");
        for (Student student : students) {
            System.out.println(student.getName() + " - курс " + student.getCourse() + ", средний балл: " + student.getAverageGrade());
        }

        removeBadStudents(students);
        System.out.println("\nПосле удаления студентов с плохими оценками:");
        for (Student student : students) {
            System.out.println(student.getName() + " - курс " + student.getCourse());
        }

        promoteStudents(students);
        System.out.println("\nПосле перевода на следующий курс:");
        for (Student student : students) {
            System.out.println(student.getName() + " - курс " + student.getCourse());
        }

        System.out.println();
        printStudents(students, 2);
        printStudents(students, 3);
    }
}