package model;

import controller.MyDate;
import controller.MyTime;
import enums.WeekDays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CentralManagement {

    private static final ArrayList<Employee> allEmployees = new ArrayList<>();
    private static final ArrayList<Professor> allProfessors = new ArrayList<>();
    private static final ArrayList<Student> allStudents = new ArrayList<>();

    public static List<Book> allBooksEverExist = new ArrayList<>();
    public static List<Employee> allActiveEmployees = new ArrayList<>();
    public static List<Professor> allActiveProfessors = new ArrayList<>();
    public static List<Student> allActiveStudents = new ArrayList<>();
    public static Map<Book, Integer> allBooksInLibraries = new HashMap<>();
    public static Map<String, String> allBorrowedBooks = new HashMap<>();//just been used in controller - ID + details
    public static Map<String, String> allReturnedBooks = new HashMap<>();//just been used in controller - ID + details

    private static ArrayList<Employee> saturday = new ArrayList<>();
    private static ArrayList<Employee> sunday = new ArrayList<>();
    private static ArrayList<Employee> monday = new ArrayList<>();
    private static ArrayList<Employee> tuesday = new ArrayList<>();
    private static ArrayList<Employee> wednesday = new ArrayList<>();
    private static ArrayList<Employee> thursday = new ArrayList<>();

    public static Book searchBookInAllBooks(Book book) {
        for(Book test : allBooksEverExist) {
            if(test.getISBN() == book.getISBN())  {
                if(test.getPublishedYear() == book.getPublishedYear()
                        && test.getBookName().equals(book.getBookName())) {
                    return test;
                }
            }
        }
        return null;
    }

    public static Book searchBookInLibraries(Book book) {
        for(Book test : allBooksInLibraries.keySet()) {
            if(test.getISBN() == book.getISBN())  {
                if(test.getPublishedYear() == book.getPublishedYear()
                        && test.getBookName().equals(book.getBookName())) {
                    return test;
                }
            }
        }
        return null;
    }

    public static Student getStudentByStudentIdInAllStudents(int studentId) {
        for (Student student : allStudents) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public static Student getStudentByStudentIdInAllActiveStudents(int studentId) {
        for (Student student : allActiveStudents) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public static Professor getProfessorByNCInAllProfessors(long nationalCode) {
        for (Professor professor : allProfessors) {
            if (professor.getNationalCode() == nationalCode) {
                return professor;
            }
        }
        return null;
    }

    public static Professor getProfessorByNCInAllActiveProfessors(long nationalCode) {
        for (Professor professor : allActiveProfessors) {
            if (professor.getNationalCode() == nationalCode) {
                return professor;
            }
        }
        return null;
    }

    public static Employee getEmployeeByNCInAllEmployees(long nationalCode) {
        for (Employee employee : allEmployees) {
            if (employee.getNationalCode() == nationalCode) {
                return employee;
            }
        }
        return null;
    }

    public static Employee getWorkerByTime(WeekDays day, int hour) {
        refreshWorkersSchedule();
        setWorkersTime();
        Employee employee;
        if (day == WeekDays.SATURDAY) {
            employee = getWorker(saturday, hour);
        } else if (day == WeekDays.SUNDAY) {
            employee = getWorker(sunday, hour);
        } else if (day == WeekDays.MONDAY) {
            employee = getWorker(monday, hour);
        } else if (day == WeekDays.TUESDAY) {
            employee = getWorker(tuesday, hour);
        } else if (day == WeekDays.WEDNESDAY) {
            employee = getWorker(wednesday, hour);
        } else {
            employee = getWorker(thursday, hour);
        }
        return employee;
    }

    private static Employee getWorker(ArrayList<Employee> day, int hour) {
        int shift;
        int worker;
        try {
            shift = 12 / day.size();
            worker = (hour - 8) / shift + 1;
        } catch (ArithmeticException e) {
           return null;
        }
        return  day.get(worker);
    }

    private static void refreshWorkersSchedule() {
        for (Employee activeEmployee : allActiveEmployees) {
            if (activeEmployee.getWorkingDays().contains(WeekDays.SATURDAY)) {
                saturday.add(activeEmployee);
            } if (activeEmployee.getWorkingDays().contains(WeekDays.SUNDAY)) {
                sunday.add(activeEmployee);
            } if (activeEmployee.getWorkingDays().contains(WeekDays.MONDAY)) {
                monday.add(activeEmployee);
            } if (activeEmployee.getWorkingDays().contains(WeekDays.TUESDAY)) {
                tuesday.add(activeEmployee);
            } if (activeEmployee.getWorkingDays().contains(WeekDays.WEDNESDAY)) {
                wednesday.add(activeEmployee);
            } if (activeEmployee.getWorkingDays().contains(WeekDays.THURSDAY)) {
                thursday.add(activeEmployee);
            }
        }
    }

    private static void setWorkersTime() {
        try {
            int saturdayShift = 12 / saturday.size();
            int sundayShift = 12 / sunday.size();
            int mondayShift = 12 / monday.size();
            int tuesdayShift = 12 / tuesday.size();
            int wednesdayShift = 12 / wednesday.size();
            int thursdayShift = 12 / thursday.size();
            int i = 0;
            for (Employee employee : saturday) {
                employee.getWorkTime().put(8 + i * saturdayShift, saturdayShift);
                i++;
            } i = 0;
            for (Employee employee : sunday) {
                employee.getWorkTime().put(8 + i * sundayShift, sundayShift);
                i++;
            } i = 0;
            for (Employee employee : monday) {
                employee.getWorkTime().put(8 + i * mondayShift, mondayShift);
                i++;
            }
            for (Employee employee : tuesday) {
                employee.getWorkTime().put(8 + i * tuesdayShift, tuesdayShift);
                i++;
            } i = 0;
            for (Employee employee : wednesday) {
                employee.getWorkTime().put(8 + i * wednesdayShift, wednesdayShift);
                i++;
            } i = 0;
            for (Employee employee : thursday) {
                employee.getWorkTime().put(8 + i * thursdayShift, thursdayShift);
                i++;
            }
        } catch (ArithmeticException e) {
            System.out.println("\"A day in a week doesn't have employee\"");
        }
    }

    public static ArrayList<Employee> getAllEmployees() {
        return allEmployees;
    }

    public static ArrayList<Professor> getAllProfessors() {
        return allProfessors;
    }

    public static ArrayList<Student> getAllStudents() {
        return allStudents;
    }
}
