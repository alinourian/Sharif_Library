package view;

import enums.*;
import model.*;

public abstract class ConsoleViewOut {
    public static void setDate(int year, int month, int day) {
        String s = "Date has been set at " + year + "/" + month + "/" + day + ".";
        System.out.println(s);
    }

    public static void createBook(CreateBook status, Book book) {
        if (status == CreateBook.NEW_BOOK_CREATED) {
            System.out.println("You have successfully created new book.");
        } else if (status == CreateBook.NUMBER_OF_BOOK_INCREASED) {
            System.out.println("You have successfully added one of this book.");
        }
    }

    public static void addBook(AddBook status, Book book) {
        if (status == AddBook.ADDED_SUCCESSFULLY) {
            Book test = CentralManagement.searchBook(book);
            System.out.println("You just successfully added one book to this library.");
            System.out.println("available: " + test.getNumbersAvailable());
        } else if (status == AddBook.NEW_ADDED_SUCCESSFULLY) {
            System.out.println("You just successfully added a new book to this library.");
        } else if (status == AddBook.LIBRARY_IS_FULL) {
            System.out.println("Sorry! The library is full! You can't add your book.");
        } else if (status == AddBook.NO_OTHER_BOOK_TO_ADD) {
            System.out.println("No book to add! Please create one another first.");
        } else if (status == AddBook.BELONG_TO_ANOTHER_LIBRARY) {
            System.out.println("This book was added to another library, it belongs to that!" +
                    " You can't add it to this library.");
        } else if (status == AddBook.BOOK_NEVER_EXIST) {
            System.out.println("This book does not exist! Please create your new book first.");
        } else if (status == AddBook.LIBRARY_NEVER_EXIST) {
            System.err.println("The library you're looking for does not exist! please choose one of these: ");
            System.out.println("1. CentralLibrary Or MainLibrary\n" +
                    "2. LibraryA Or A\n" +
                    "3. LibraryB Or B");
        }
    }

    public static void createPerson(Person person, boolean bool) {
        if (bool) {
            if (person.getType() == Type.STUDENT) {
                System.out.println("Student successfully created.");
            } else if (person.getType() == Type.PROFESSOR) {
                System.out.println("Professor successfully created.");
            } else if (person.getType() == Type.WORKER) {
                System.out.println("Worker successfully created.");
            }
        } else {
            System.err.println("Person ID number has been used!" +
                    "\nYou can't use same ID number for two persons.");
        }
    }

    public static void depositStudent(Student student) {
        System.out.println("Budget successfully increased.\nnew budget becomes: "
                + student.getBudget());
    }

    public static void depositProfessor(Professor professor) {
        System.out.println("Budget successfully increased.\nnew budget becomes: "
                + professor.getBudget());
    }

    public static void depositFailed() {
        System.err.println("Person with this ID does not exist.");
    }

    public static void addPerson(Person person, boolean bool) {
        Type type = person.getType();
        if (bool) {
            if (type == Type.STUDENT) {
                System.out.println("New student with national-code\"" + person.getNationalCode() +
                        "\" becomes a member of CentralLibrary");
            } else if (type == Type.PROFESSOR) {
                System.out.println("New professor with national-code \"" + person.getNationalCode() +
                        "\" becomes a member of CentralLibrary");
            } else if (type == Type.WORKER) {
                System.out.println("New employee with national-code \"" + person.getNationalCode() +
                        "\" becomes a member of CentralLibrary");
            }
        } else {
            if (type == Type.STUDENT) {
                System.err.println("The student with national-code\"" + person.getNationalCode() +
                        "\" had became a member of CentralLibrary before! Can not add again.");
            } else if (type == Type.PROFESSOR) {
                System.err.println("The professor with national-code \"" + person.getNationalCode() +
                        "\" had became a member of CentralLibrary before! Can not add again.");
            } else if (type == Type.WORKER) {
                System.err.println("The employee with national-code \"" + person.getNationalCode() +
                        "\" had became a member of CentralLibrary before! Can not add again.");
            }
        }
    }

    public static void addStudentFailed(int studentId) {
        System.out.println("Student with studentID \"" + studentId + "\" does not exist!");
    }

    public static void addProfessorFailed(long nationalCode) {
        System.out.println("Professor with national-code \"" + nationalCode + "\" does not exist!");
    }

    public static void addEmployeeFailed(long nationalCode, AddWorker status) {
        if (status == AddWorker.INVALID_NC) {
            System.out.println("Worker with national-code \"" + nationalCode + "\" does not exist!");
        } else if (status == AddWorker.LIBRARY_IS_FULL) {
            System.out.println("Sorry! Library capacity for workers is full!\nCan not add worker to this library.");
        } else if (status == AddWorker.WRONG_LIBRARY_TO_ADD) {
            System.out.println("Sorry! Can not add this worker to this library!\n" +
                    "Worker with national-code \"" + nationalCode + "\" is not for this library");
        }
    }

    public static void setSchedule(long nationalCode, SetSchedule status) {
        if (status == SetSchedule.WORKER_NOT_EXIST) {
            System.out.println("Sorry! Worker with national-code \"" + nationalCode +
                    "\" is not exist.");
        } else if (status == SetSchedule.WRONG_LIBRARY) {
            System.out.println("Sorry! The worker with national-code \"" + nationalCode +
                    "\" belongs to another library.\nPlease enter the correct library.");
        } else if (status == SetSchedule.SUCCESSFUL) {
            System.out.println("The schedule of worker with national-code \"" + nationalCode +
                    "\" has been changed.");
        }
    }

    public static void invalidCommands() {
        System.err.println("INVALID COMMAND! PLEASE TRY AGAIN!");
    }
}
