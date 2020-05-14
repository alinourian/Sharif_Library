package view;

import controller.MyDate;
import controller.MyTime;
import enums.*;
import model.*;

import java.util.regex.Matcher;

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
            Book test = CentralManagement.searchBookInAllBooks(book);
            System.out.println("You just successfully added one book to this library.");
            if (test == null) {
                System.err.println("An error happened in program!" +
                        "-View\\ConsoleViewOut\\addBook\\25-test-must-never-be-null");
                System.out.println("Sorry! try again");
            } else {
                System.out.println("available: " + test.getNumbersAvailable());
            }
        } else if (status == AddBook.NEW_ADDED_SUCCESSFULLY) {
            System.out.println("You just successfully added a new book to this library.");
        } else if (status == AddBook.LIBRARY_IS_FULL) {
            System.err.println("Sorry! The library is full! You can't add your book.");
        } else if (status == AddBook.NO_OTHER_BOOK_TO_ADD) {
            System.err.println("No book to add! Please create one another first.");
        } else if (status == AddBook.BELONG_TO_ANOTHER_LIBRARY) {
            System.err.println("This book was added to another library, it belongs to that!" +
                    " You can't add it to this library.");
        } else if (status == AddBook.BOOK_NEVER_EXIST) {
            System.err.println("This book does not exist! Please create your new book first.");
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
        System.err.println("Student with studentID \"" + studentId + "\" does not exist!");
    }

    public static void addProfessorFailed(long nationalCode) {
        System.err.println("Professor with national-code \"" + nationalCode + "\" does not exist!");
    }

    public static void addEmployeeFailed(long nationalCode, AddWorker status) {
        if (status == AddWorker.INVALID_NC) {
            System.err.println("Worker with national-code \"" + nationalCode + "\" does not exist!");
        } else if (status == AddWorker.LIBRARY_IS_FULL) {
            System.err.println("Sorry! Library capacity for workers is full!\nCan not add worker to this library.");
        } else if (status == AddWorker.WRONG_LIBRARY_TO_ADD) {
            System.err.println("Sorry! Can not add this worker to this library!\n" +
                    "Worker with national-code \"" + nationalCode + "\" is not for this library");
        }
    }

    public static void setSchedule(long nationalCode, SetSchedule status) {
        if (status == SetSchedule.WORKER_NOT_EXIST) {
            System.err.println("Sorry! Worker with national-code \"" + nationalCode +
                    "\" is not exist.");
        } else if (status == SetSchedule.WRONG_LIBRARY) {
            System.err.println("Sorry! The worker with national-code \"" + nationalCode +
                    "\" belongs to another library.\nPlease enter the correct library.");
        } else if (status == SetSchedule.SUCCESSFUL) {
            System.out.println("The schedule of worker with national-code \"" + nationalCode +
                    "\" has been changed.");
        }
    }

    public static void findBookSuccessful(Book book) {
        System.out.println("Well, You can find this book at \"" + book.getBookPlace() + "\".");
    }

    public static void findBookFailed(boolean bool) {
        if (bool) {
        System.err.println("Sorry! This book does not exist in any library!");
        } else {
            System.err.println("Sorry! Person with this Id is not a member of Central-Library." +
                    "\nplease register to first.");
        }
    }

    public static void loanBookFailed(LoanBook loanBook) {
        if (loanBook == LoanBook.DETAILS_NOT_MATCH) {
            System.err.println("Book details for finding the book do not match to this library search system!");
            System.out.println("Use this format:\nMainLibrary : ISBN, PublishedYear");
            System.out.println("LibraryA : BookName, PublishedYear, Translator*");
            System.out.println("LibraryB : Writer, PublishedYear, Translator*");
        } else if (loanBook == LoanBook.BOOK_NOT_FIND) {
            System.err.println("Sorry! This book does not exist in this library!");
        } else if (loanBook == LoanBook.PERSON_NOT_MEMBER) {
            System.err.println("Sorry! Person with this Id is not a member of Central-Library." +
                    "\nplease register to first.");
        } else if (loanBook == LoanBook.BOOK_NOT_AVAILABLE) {
            System.err.println("Sorry! The library does not have any of this book now.");
        } else if (loanBook == LoanBook.DATE_PASSED) {
            System.err.println("Give back date has passed!!! Enter a day in future!");
        } else if (loanBook == LoanBook.LIBRARY_IS_CLOSED) {
            System.err.println("Sorry! Today is friday! Library is closed.");
        } else if (loanBook == LoanBook.BUDGET_NOT_ENOUGH) {
            System.err.println("Sorry! The Budget is not enough to loan book!");
        } else if (loanBook == LoanBook.BORROW_THE_SAME_BOOK) {
            System.err.println("Sorry! You have been already loaned one of this book and do not have gave it back!");
            System.err.println("The library can not loan you another one.");
        }
    }

    public static void loanBook() {
        System.out.println("Loan book successfully.");
    }

    public static void giveBackBook(GiveBackBook giveBackBook) {
        if (giveBackBook == GiveBackBook.PERSON_NOT_MEMBER) {
            System.err.println("The person is not a member of library!");
        } else if (giveBackBook == GiveBackBook.BOOK_NOT_LOAN) {
            System.err.println("This book have been never loaned:/");
        } else if (giveBackBook == GiveBackBook.DETAILS_NOT_MATCH) {
            System.err.println("Book details do not match to this library search system!");
            System.out.println("Use this format:\nMainLibrary : ISBN, PublishedYear");
            System.out.println("LibraryA : BookName, PublishedYear, Translator*");
            System.out.println("LibraryB : Writer, PublishedYear, Translator*");
        } else if (giveBackBook == GiveBackBook.LIBRARY_NOT_EXIST) {
            System.err.println("The library you entered does not ever exist!");
        } else if (giveBackBook == GiveBackBook.BOOK_FOR_SOMEONE_ELSE) {
            System.err.println("This book were loan to another person! How do you have it?!-_-");
        } else if (giveBackBook == GiveBackBook.SUCCESSFUL) {
            System.out.println("You successfully gave back this book.");
        }
    }

    public static void goNextDay(MyDate date) {
        System.out.println("Time changed.");
        System.out.println("Today date is " + date);
    }

    public static void setFines() {
        System.out.println("Fines updates!");
    }

    public static void addBookToStore(boolean bool) {
        if (bool) {
            System.out.println("The book have successfully added to store.");
        } else {
            System.err.println("The book does not exist!");
        }
    }

    public static void setDiscount() {
        System.out.println("Discount-Code set.");
    }

    public static void checkDiscount(boolean bool) {
        if (bool)
            System.out.println("Discount-Code valid.");
        else
            System.err.println("Discount-Code not valid!");
    }

    public static void sellBook(SellBook sellBook) {
        if (sellBook == SellBook.SUCCESSFULL) {
            System.out.println("You successfully bought the book.");
        } else if (sellBook == SellBook.PERSON_NOT_EXIST) {
            System.err.println("Person with this ID never exist!");
        } else if (sellBook == SellBook.BOOK_NOT_EXIST) {
            System.err.println("The book with this details does not found");
        } else if (sellBook == SellBook.BOOK_NOT_AVAILABLE) {
            System.err.println("Sorry! This book is not available now.");
        } else if (sellBook == SellBook.BUDGET_NOT_ENOUGH) {
            System.err.println("Sorry! Budget is not enough to buy!");
        }
    }

    public static void giveBackBookToStore() {

    }

    public static void invalidCommands() {
        System.err.println("INVALID COMMAND! PLEASE TRY AGAIN!");
    }
}
