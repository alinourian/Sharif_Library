package controller;

import enums.*;
import model.*;
import view.ConsoleViewOut;

import java.util.ArrayList;

public class LibrariesController {
    private static LibrariesController instance;

    private final MyDate startDate;
    private MyDate currentDay;

    public static LibrariesController getInstance() {
        if(instance == null) {
            instance = new LibrariesController();
        }
        return instance;
    }

    private LibrariesController() {
        startDate = new MyDate();
    }

    public void setDate(int year, int month, int day) {
        startDate.setDate(year, month, day);
        currentDay = startDate;
        ConsoleViewOut.setDate(year, month, day);
    }

    public void createBook(String bookName, int pages, int publishedYear,
                           String writer, String language, long ISBN, int price) {
        Book book;
        book = new Book(bookName, pages, publishedYear, writer, language, ISBN, price);
        Book test = CentralManagement.searchBookInAllBooks(book);
        if (test == null) {
            CentralManagement.allBooksEverExist.add(book);
            book.setNumbersAvailable(1);
            ConsoleViewOut.createBook(CreateBook.NEW_BOOK_CREATED, book);
        } else {
            test.setNumbersAvailable(test.getNumbersAvailable()+1);
            ConsoleViewOut.createBook(CreateBook.NUMBER_OF_BOOK_INCREASED, test);
        }
    }
    public void createBook(String bookName, int pages, int publishedYear, String writer,
                           String language, long ISBN, int price, String translator) {
        Book book;
        book = new Book(bookName, pages, publishedYear, writer, language, ISBN, price, translator);
        Book test = CentralManagement.searchBookInAllBooks(book);
        if (test == null) {
            CentralManagement.allBooksEverExist.add(book);
            book.setNumbersAvailable(1);
            ConsoleViewOut.createBook(CreateBook.NEW_BOOK_CREATED, book);
        } else {
            test.setNumbersAvailable(test.getNumbersAvailable()+1);
            ConsoleViewOut.createBook(CreateBook.NUMBER_OF_BOOK_INCREASED, test);
        }
    }

    public void addBook(String libraryName, String bookName, long ISBN, int publishedYear) {
        Book book = new Book(bookName, ISBN, publishedYear);
        AddBook addBookStatus;
        if (libraryName.equalsIgnoreCase("mainLibrary") ||
                libraryName.equalsIgnoreCase("centralLibrary")) {
            addBookStatus = CentralLibrary.getInstance().addBook(book);
        } else if (libraryName.equalsIgnoreCase("libraryA") ||
                libraryName.equalsIgnoreCase("A")) {
            addBookStatus = LibraryA.getInstance().addBook(book);
        } else if (libraryName.equalsIgnoreCase("libraryB") ||
                libraryName.equalsIgnoreCase("B")) {
            addBookStatus = LibraryB.getInstance().addBook(book);
        } else {
            addBookStatus = AddBook.LIBRARY_NEVER_EXIST;
        }
        ConsoleViewOut.addBook(addBookStatus, book);
    }

    public void createStudent(String fullName, int age, String nationalCode, Gender gender, int studentId,
                              int yearOfEntry, String grade, long budget, String department) {
        for (Student stu : CentralManagement.getAllStudents()) {
            if (stu.getStudentId() == studentId || stu.getNationalCode().equals(nationalCode)) {
                ConsoleViewOut.createPerson(stu, false);
                return;
            }
        }
        Student student = new Student(fullName, age, nationalCode, gender,
                studentId, yearOfEntry, grade, budget, department);
        CentralManagement.getAllStudents().add(student);
        ConsoleViewOut.createPerson(student, true);
    }

    public void createProfessor(String fullName, int age, String nationalCode, Gender gender,
                              int yearOfEntry, long budget, String department) {
        for (Professor pro : CentralManagement.getAllProfessors()) {
            if (pro.getNationalCode().equals(nationalCode)) {
                ConsoleViewOut.createPerson(pro, false);
                return;
            }
        }
        Professor professor = new Professor(fullName, age, nationalCode, gender,
                yearOfEntry, budget, department);
        CentralManagement.getAllProfessors().add(professor);
        ConsoleViewOut.createPerson(professor, true);
    }

    public void createWorker(String fullName, int age, String nationalCode,
                             Gender gender, Libraries libraries) {
        for (Employee emp : CentralManagement.getAllEmployees()) {
            if (emp.getNationalCode().equals(nationalCode)) {
                ConsoleViewOut.createPerson(emp, false);
                return;
            }
        }
        Employee employee = new Employee(fullName, age, nationalCode, gender, libraries);
        CentralManagement.getAllEmployees().add(employee);
        ConsoleViewOut.createPerson(employee, true);
    }

    public void addStudent(int studentId) {
        Student student = CentralManagement.getStudentByStudentIdInAllStudents(studentId);
        if (student == null) {
            ConsoleViewOut.addStudentFailed(studentId);
        } else {
            if (!CentralManagement.allActiveStudents.contains(student)) {
                CentralLibrary.getInstance().addMember(student);
                ConsoleViewOut.addPerson(student, true);
            } else {
                ConsoleViewOut.addPerson(student, false);
            }
        }
    }

    public void addProfessor(String nationalCode) {
        Professor professor = CentralManagement.getProfessorByNCInAllProfessors(nationalCode);
        if (professor == null) {
            ConsoleViewOut.addProfessorFailed(nationalCode);
        } else {
            if (!CentralManagement.allActiveProfessors.contains(professor)) {
                CentralLibrary.getInstance().addMember(professor);
                ConsoleViewOut.addPerson(professor, true);
            } else {
                ConsoleViewOut.addPerson(professor, false);
            }
        }
    }

    public void addEmployee(String nationalCode, Libraries libraries) {
        for (Employee employee : CentralManagement.getAllEmployees()) {
            if (employee.getNationalCode().equals(nationalCode)) {
                if (CentralManagement.allActiveEmployees.contains(employee)) {
                    ConsoleViewOut.addPerson(employee, false);
                    return;
                }
                if (libraries == employee.getWorkPlace()) {
                    if (employee.getWorkPlace() == Libraries.CENTRAL_LIBRARY) {
                        if (CentralLibrary.getInstance().getNumbersOfEmployee() < Library.NUMBERS_OF_EMPLOYEES) {
                            CentralLibrary.getInstance().addEmployee(employee);
                            ConsoleViewOut.addPerson(employee, true);
                        } else {
                            ConsoleViewOut.addEmployeeFailed(nationalCode, AddWorker.LIBRARY_IS_FULL);
                        }
                    } else if (employee.getWorkPlace() == Libraries.LIBRARY_A) {
                        if (LibraryA.getInstance().getNumbersOfEmployee() < Library.NUMBERS_OF_EMPLOYEES) {
                            LibraryA.getInstance().addEmployee(employee);
                            ConsoleViewOut.addPerson(employee, true);
                        } else {
                            ConsoleViewOut.addEmployeeFailed(nationalCode, AddWorker.LIBRARY_IS_FULL);
                            // false is because of the capacity of the library
                        }
                    } else if (employee.getWorkPlace() == Libraries.LIBRARY_B) {
                        if (LibraryB.getInstance().getNumbersOfEmployee() < Library.NUMBERS_OF_EMPLOYEES) {
                            LibraryB.getInstance().addEmployee(employee);
                            ConsoleViewOut.addPerson(employee, true);
                        } else {
                            ConsoleViewOut.addEmployeeFailed(nationalCode, AddWorker.LIBRARY_IS_FULL);
                            // false is because of the capacity of the library
                        }
                    }
                } else if (libraries != employee.getWorkPlace()) {
                    ConsoleViewOut.addEmployeeFailed(nationalCode, AddWorker.WRONG_LIBRARY_TO_ADD);
                }
                return;
            }
        }
        ConsoleViewOut.addEmployeeFailed(nationalCode, AddWorker.INVALID_NC);
    }

    public void depositStudent(int studentId, long increase) {
        Student student = CentralManagement.getStudentByStudentIdInAllStudents(studentId);
        if (student == null) {
            ConsoleViewOut.depositFailed();
        } else {
            student.addBudget(increase);
            ConsoleViewOut.depositStudent(student);
        }
    }

    public void depositProfessor(String nationalCode, long increase) {
        Professor professor = CentralManagement.getProfessorByNCInAllProfessors(nationalCode);
        if (professor == null) {
            ConsoleViewOut.depositFailed();
        } else {
            professor.addBudget(increase);
            ConsoleViewOut.depositProfessor(professor);
        }
    }

    public void setSchedule(String nationalCode, Libraries libraries, ArrayList<WeekDays> schedule) {
        Employee employee = CentralManagement.getEmployeeByNCInAllEmployees(nationalCode);
        if (employee == null) {
            ConsoleViewOut.setSchedule(nationalCode, SetSchedule.WORKER_NOT_EXIST);
        } else {
            if (libraries == Libraries.CENTRAL_LIBRARY && employee.getWorkPlace() == Libraries.CENTRAL_LIBRARY) {
                CentralLibrary.getInstance().changeEmployeeSchedule(nationalCode, schedule);
                CentralManagement.refreshWorkersSchedule();
                ConsoleViewOut.setSchedule(nationalCode, SetSchedule.SUCCESSFUL);
            } else if (libraries == Libraries.LIBRARY_A && employee.getWorkPlace() == Libraries.LIBRARY_A) {
                LibraryA.getInstance().changeEmployeeSchedule(nationalCode, schedule);
                CentralManagement.refreshWorkersSchedule();
                ConsoleViewOut.setSchedule(nationalCode, SetSchedule.SUCCESSFUL);
            } else if (libraries == Libraries.LIBRARY_B && employee.getWorkPlace() == Libraries.LIBRARY_B) {
                LibraryB.getInstance().changeEmployeeSchedule(nationalCode, schedule);
                CentralManagement.refreshWorkersSchedule();
                ConsoleViewOut.setSchedule(nationalCode, SetSchedule.SUCCESSFUL);
            } else {
                ConsoleViewOut.setSchedule(nationalCode, SetSchedule.WRONG_LIBRARY);
            }
        }
    }

    public void findBookForStudent(int studentId, String bookName, long ISBN, int publishedYear) {
        if (CentralManagement.getStudentByStudentIdInAllActiveStudents(studentId) == null) {
            ConsoleViewOut.findBookFailed(false);
        } else {
            Book test = new Book(bookName, ISBN, publishedYear);
            Book book = CentralManagement.searchBookInLibraries(test);
            if (book == null) {
                ConsoleViewOut.findBookFailed(true);
            } else {
                ConsoleViewOut.findBookSuccessful(book);
            }
        }
    }

    public void findBookForProfessors(String nationalCode, String bookName, long ISBN, int publishedYear) {
        if (CentralManagement.getProfessorByNCInAllActiveProfessors(nationalCode) == null) {
            ConsoleViewOut.findBookFailed(false);
        } else {
            Book test = new Book(bookName, ISBN, publishedYear);
            Book book = CentralManagement.searchBookInLibraries(test);
            if (book == null) {
                ConsoleViewOut.findBookFailed(true);
            } else {
                ConsoleViewOut.findBookSuccessful(book);
            }
        }
    }

    public void loanBookInCentralLibraryForStudent(long ISBN, int publishedYear, Libraries library,
                                                   int studentId, MyTime loanTime, MyDate giveBackDate) {
        if (library == Libraries.CENTRAL_LIBRARY) {
            Student student = CentralManagement.getStudentByStudentIdInAllActiveStudents(studentId);
            if (student == null) {
                ConsoleViewOut.loanBookFailed(LoanBook.PERSON_NOT_MEMBER);
            } else if (student.getBudget() <= -10000) {
                ConsoleViewOut.loanBookFailed(LoanBook.BUDGET_NOT_ENOUGH);
            } else {
                Book test = new Book(ISBN, publishedYear);
                Book book = CentralLibrary.getInstance().search(test);
                if (book == null) {
                    ConsoleViewOut.loanBookFailed(LoanBook.BOOK_NOT_FIND);
                } else {
                    doLoanForStudent(studentId, book, loanTime, giveBackDate);
                }
            }
        } else {
            ConsoleViewOut.loanBookFailed(LoanBook.DETAILS_NOT_MATCH);
        }
    }

    public void loanBookInCentralLibraryForProfessor(long ISBN, int publishedYear, Libraries library,
                                                     String nationalCode, MyTime loanTime, MyDate giveBackDate) {
        if (library == Libraries.CENTRAL_LIBRARY) {
            Professor professor = CentralManagement.getProfessorByNCInAllActiveProfessors(nationalCode);
            if (professor == null) {
                ConsoleViewOut.loanBookFailed(LoanBook.PERSON_NOT_MEMBER);
            } else if (professor.getBudget() <= -10000) {
                ConsoleViewOut.loanBookFailed(LoanBook.BUDGET_NOT_ENOUGH);
            } else {
                Book test = new Book(ISBN, publishedYear);
                Book book = CentralLibrary.getInstance().search(test);
                if (book == null) {
                    ConsoleViewOut.loanBookFailed(LoanBook.BOOK_NOT_FIND);
                } else {
                    doLoanForProfessor(nationalCode, book, loanTime, giveBackDate);
                }
            }
        } else {
            ConsoleViewOut.loanBookFailed(LoanBook.DETAILS_NOT_MATCH);
        }
    }

    public void loanBookInLibrary_A_B_ForStudent(String bookNameOrWriter, int publishedYear, String translator
            , Libraries library, int studentId, MyTime loanTime, MyDate giveBackDate) {
        Student student = CentralManagement.getStudentByStudentIdInAllActiveStudents(studentId);
        if (library == Libraries.LIBRARY_A) {
            if (student == null) {
                ConsoleViewOut.loanBookFailed(LoanBook.PERSON_NOT_MEMBER);
            } else if (student.getBudget() <= -10000) {
                ConsoleViewOut.loanBookFailed(LoanBook.BUDGET_NOT_ENOUGH);
            } else {
                Book test = new Book(bookNameOrWriter, publishedYear, translator);
                Book book = LibraryA.getInstance().search(test);
                if (book == null) {
                    ConsoleViewOut.loanBookFailed(LoanBook.BOOK_NOT_FIND);
                } else {
                    doLoanForStudent(studentId, book, loanTime, giveBackDate);
                }
            }
        } else if (library == Libraries.LIBRARY_B) {
            if (student == null) {
                ConsoleViewOut.loanBookFailed(LoanBook.PERSON_NOT_MEMBER);
            } else if (student.getBudget() <= -10000) {
                ConsoleViewOut.loanBookFailed(LoanBook.BUDGET_NOT_ENOUGH);
            } else {
                Book test = new Book(bookNameOrWriter, publishedYear, translator);
                Book book = LibraryB.getInstance().search(test);
                if (book == null) {
                    ConsoleViewOut.loanBookFailed(LoanBook.BOOK_NOT_FIND);
                } else {
                    doLoanForStudent(studentId, book, loanTime, giveBackDate);
                }
            }
        } else {
            ConsoleViewOut.loanBookFailed(LoanBook.DETAILS_NOT_MATCH);
        }
    }

    public void loanBookInLibrary_A_B_ForProfessor(String bookNameOrWriter, int publishedYear, String translator
            , Libraries library, String nationalCode, MyTime loanTime, MyDate giveBackDate) {
        Professor professor = CentralManagement.getProfessorByNCInAllActiveProfessors(nationalCode);
        if (library == Libraries.LIBRARY_A) {
            if (professor == null) {
                ConsoleViewOut.loanBookFailed(LoanBook.PERSON_NOT_MEMBER);
            } else if (professor.getBudget() <= -10000) {
                ConsoleViewOut.loanBookFailed(LoanBook.BUDGET_NOT_ENOUGH);
            } else {
                Book test = new Book(bookNameOrWriter, publishedYear, translator);
                Book book = LibraryA.getInstance().search(test);
                if (book == null) {
                    ConsoleViewOut.loanBookFailed(LoanBook.BOOK_NOT_FIND);
                } else {
                    doLoanForProfessor(nationalCode, book, loanTime, giveBackDate);
                }
            }
        } else if (library == Libraries.LIBRARY_B) {
            if (professor == null) {
                ConsoleViewOut.loanBookFailed(LoanBook.PERSON_NOT_MEMBER);
            } else if (professor.getBudget() <= -10000) {
                ConsoleViewOut.loanBookFailed(LoanBook.BUDGET_NOT_ENOUGH);
            } else {
                Book test = new Book(bookNameOrWriter, publishedYear, translator);
                Book book = LibraryB.getInstance().search(test);
                if (book == null) {
                    ConsoleViewOut.loanBookFailed(LoanBook.BOOK_NOT_FIND);
                } else {
                    doLoanForProfessor(nationalCode, book, loanTime, giveBackDate);
                }
            }
        } else {
            ConsoleViewOut.loanBookFailed(LoanBook.DETAILS_NOT_MATCH);
        }
    }

    public void giveBackBookFromStudent(Book book, Libraries library, int studentId, MyTime time) {
        Student student = CentralManagement.getStudentByStudentIdInAllActiveStudents(studentId);
        if (student == null) {
            ConsoleViewOut.giveBackBook(GiveBackBook.PERSON_NOT_MEMBER);
        } else {
            if (library == Libraries.CENTRAL_LIBRARY) {
                Book test = CentralLibrary.getInstance().search(book);
                boolean bool = CentralLibrary.getInstance().getBorrowedBooks().containsKey(test);
                if (bool) {
                    giveBackBook(student, test, time);
                } else {
                    ConsoleViewOut.giveBackBook(GiveBackBook.BOOK_NOT_LOAN);
                }
            } else if (library == Libraries.LIBRARY_A) {
                Book test = LibraryA.getInstance().search(book);
                boolean bool = LibraryA.getInstance().getBorrowedBooks().containsKey(test);
                if (bool) {
                    giveBackBook(student, test, time);
                } else {
                    ConsoleViewOut.giveBackBook(GiveBackBook.BOOK_NOT_LOAN);
                }
            } else if (library == Libraries.LIBRARY_B) {
                Book test = LibraryB.getInstance().search(book);
                boolean bool = LibraryB.getInstance().getBorrowedBooks().containsKey(test);
                if (bool) {
                    giveBackBook(student, test, time);
                } else {
                    ConsoleViewOut.giveBackBook(GiveBackBook.BOOK_NOT_LOAN);
                }
            } else {
                ConsoleViewOut.giveBackBook(GiveBackBook.LIBRARY_NOT_EXIST);
            }
        }
    }

    public void giveBackBookFromProfessor(Book book, Libraries library, String nationalCode, MyTime time) {
        Professor professor = CentralManagement.getProfessorByNCInAllActiveProfessors(nationalCode);
        if (professor == null) {
            ConsoleViewOut.giveBackBook(GiveBackBook.PERSON_NOT_MEMBER);
        } else if (book.getBookPlace() == Libraries.NO_WHERE_YET) {
            ConsoleViewOut.giveBackBook(GiveBackBook.DETAILS_NOT_MATCH);
        } else {
            if (library == Libraries.CENTRAL_LIBRARY) {
                Book test = CentralLibrary.getInstance().search(book);
                boolean bool = CentralLibrary.getInstance().getBorrowedBooks().containsKey(test);
                if (bool) {
                    giveBackBook(professor, test, time);
                } else {
                    ConsoleViewOut.giveBackBook(GiveBackBook.BOOK_NOT_LOAN);
                }
            } else if (library == Libraries.LIBRARY_A) {
                Book test = LibraryA.getInstance().search(book);
                boolean bool = LibraryA.getInstance().getBorrowedBooks().containsKey(test);
                if (bool) {
                    giveBackBook(professor, test, time);
                } else {
                    ConsoleViewOut.giveBackBook(GiveBackBook.BOOK_NOT_LOAN);
                }
            } else if (library == Libraries.LIBRARY_B) {
                Book test = LibraryB.getInstance().search(book);
                boolean bool = LibraryB.getInstance().getBorrowedBooks().containsKey(test);
                if (bool) {
                    giveBackBook(professor, test, time);
                } else {
                    ConsoleViewOut.giveBackBook(GiveBackBook.BOOK_NOT_LOAN);
                }
            } else {
                ConsoleViewOut.giveBackBook(GiveBackBook.LIBRARY_NOT_EXIST);
            }
        }
    }

    public void goNextDay(int add) {
        int y = add / 365;
        int m = (add % 365) > 186 ? ((add % 365) - 186) / 30 + 6 : (add % 365) / 31;
        int d = m > 6 ? (add % 365) - 186 - (m - 6) * 30 : (add % 365) - m * 31;

        int day = currentDay.getDay() + d;
        int month = currentDay.getMonth() + m;
        int year = currentDay.getYear() + y;
        if (month > 12) {
            month -= 12;
            year++;
        }
        if (month < 6) {
            if (day > 31) {
                day -= 31;
                month++;
            }
        }else if (month < 12) {
            if (day > 30) {
                day -= 30;
                month++;
            }
        } else {// month = 12
            if (day > 30) {
                day -= 30;
                month = 1;
                year++;
            }
        }
        currentDay.setDate(year, month, day);
        ConsoleViewOut.goNextDay(currentDay);
        calcFines(add);
    }

    //methods for store...
    public void addBookToStore(String bookName, long ISBN, int publishedYear) {
        Book test = new Book(bookName, ISBN, publishedYear);
        Book book = CentralManagement.searchBookInAllBooks(test);
        if (book == null) {
            ConsoleViewOut.addBookToStore(0);
        } else {
            if (CentralLibrary.getInstance().getBooksForSale().contains(book)) {
                ConsoleViewOut.addBookToStore(-1);
            } else {
                CentralLibrary.getInstance().addToStore(book);
                ConsoleViewOut.addBookToStore(1);
            }
        }
    }

    public void setDiscount(String code, int percent) {
        CentralLibrary.getInstance().setDiscountCode(code, percent);
        ConsoleViewOut.setDiscount();
    }

    public void sellBookToStudent(String bookName, long ISBN, int publishedYear,
                                  int studentId, MyTime time, String discountCode) {
        Student student = CentralManagement.getStudentByStudentIdInAllStudents(studentId);
        Book test = new Book(bookName, ISBN, publishedYear);
        Book book = CentralLibrary.getInstance().searchBookInStore(test);
        if (student == null) {
            ConsoleViewOut.sellBook(SellBook.PERSON_NOT_EXIST);
        } else if (book == null) {
            ConsoleViewOut.sellBook(SellBook.BOOK_NOT_EXIST);
        } else {
            boolean bool = false;
            if (Store.checkDiscountCode(discountCode)) {
                bool = true;
                ConsoleViewOut.checkDiscount(true);
            } else if (!discountCode.equals("-")) {
                ConsoleViewOut.checkDiscount(false);
                return;
            }
            int price = CentralLibrary.getInstance().sellBook(book, bool);
            if (Store.pay(student, price) && Store.getBooksForSale().get(book) > 0) {
                Store.sellBook(book);
                setSellBookStringForStudent(student, book, price, time);
                ConsoleViewOut.sellBook(SellBook.SUCCESSFUL);
            } else if (Store.getBooksForSale().get(book) == 0) {
                ConsoleViewOut.sellBook(SellBook.BOOK_NOT_AVAILABLE);
            } else {
                ConsoleViewOut.sellBook(SellBook.BUDGET_NOT_ENOUGH);
            }
        }
    }

    public void sellBookToProfessor(String bookName, long ISBN, int publishedYear,
                                    String nationalCode, MyTime time, String discountCode) {
        Professor professor = CentralManagement.getProfessorByNCInAllProfessors(nationalCode);
        Book test = new Book(bookName, ISBN, publishedYear);
        Book book = CentralLibrary.getInstance().searchBookInStore(test);
        if (professor == null) {
            ConsoleViewOut.sellBook(SellBook.PERSON_NOT_EXIST);
        } else if (book == null) {
            ConsoleViewOut.sellBook(SellBook.BOOK_NOT_EXIST);
        } else {
            boolean bool = false;
            if (Store.checkDiscountCode(discountCode)) {
                bool = true;
                ConsoleViewOut.checkDiscount(true);
            } else if (!discountCode.equals("-")) {
                ConsoleViewOut.checkDiscount(false);
                return;
            }
            int price = CentralLibrary.getInstance().sellBook(book, bool);
            if (Store.pay(professor, price) && Store.getBooksForSale().get(book) > 0) {
                Store.sellBook(book);
                setSellBookStringForProfessor(professor, book, price, time);
                ConsoleViewOut.sellBook(SellBook.SUCCESSFUL);
            } else if (Store.getBooksForSale().get(book) == 0) {
                ConsoleViewOut.sellBook(SellBook.BOOK_NOT_AVAILABLE);
            } else {
                ConsoleViewOut.sellBook(SellBook.BUDGET_NOT_ENOUGH);
            }
        }
    }

    public void giveBackBookToStoreFromStudent(String bookName, long ISBN, int publishedYear,
                                    int studentId, MyTime time) {
        Student student = CentralManagement.getStudentByStudentIdInAllStudents(studentId);
        if (student == null) {
            ConsoleViewOut.giveBackBookToStore(GiveBackBookToStore.PERSON_NOT_EXIST);
            return;
        }
        Book test = new Book(bookName, ISBN, publishedYear);
        Book book = CentralLibrary.getInstance().searchBookInStore(test);
        if (book == null) {
            ConsoleViewOut.giveBackBookToStore(GiveBackBookToStore.BOOK_NOT_EXIST);
            return;
        }
        if (!CentralLibrary.getInstance().getBooksForSale().contains(book)) {
            ConsoleViewOut.giveBackBookToStore(GiveBackBookToStore.BOOK_NOT_FOR_SAIL);
            return;
        }
        String key = "" + studentId + "" + book.getISBN();
        if (!CentralLibrary.getInstance().getBooksSold().containsKey(key)) {
            ConsoleViewOut.giveBackBookToStore(GiveBackBookToStore.BOOK_NOT_SOLD_TO_THIS_PERSON);
            return;
        }
        doGiveBackToStoreFromStudent(student, book, time);
    }

    public void giveBackBookToStoreFromProfessor(String bookName, long ISBN, int publishedYear,
                                               String nationalCode, MyTime time) {
        Professor professor = CentralManagement.getProfessorByNCInAllProfessors(nationalCode);
        if (professor == null) {
            ConsoleViewOut.giveBackBookToStore(GiveBackBookToStore.PERSON_NOT_EXIST);
            return;
        }
        Book test = new Book(bookName, ISBN, publishedYear);
        Book book = CentralLibrary.getInstance().searchBookInStore(test);
        if (book == null) {
            ConsoleViewOut.giveBackBookToStore(GiveBackBookToStore.BOOK_NOT_EXIST);
            return;
        }
        if (!CentralLibrary.getInstance().getBooksForSale().contains(book)) {
            ConsoleViewOut.giveBackBookToStore(GiveBackBookToStore.BOOK_NOT_FOR_SAIL);
            return;
        }
        String key = "" + nationalCode + "" + book.getISBN();
        if (!CentralLibrary.getInstance().getBooksSold().containsKey(key)) {
            ConsoleViewOut.giveBackBookToStore(GiveBackBookToStore.BOOK_NOT_SOLD_TO_THIS_PERSON);
            return;
        }
        doGiveBackToStoreFromProfessor(professor, book, time);
    }

    //privates methods...
    private void doGiveBackToStoreFromStudent(Student student, Book book, MyTime time) {
        String string = "" + student.getStudentId() + "" + book.getISBN();
        String[] splitDetails;
        splitDetails = CentralLibrary.getInstance().getBooksSold().get(string).split(",");
        MyDate sellDate = setDayByString(splitDetails[4].trim());
        int price = Integer.parseInt(splitDetails[5].trim());
        int dayPassed = daysPassed(currentDay, sellDate);
        if (dayPassed > 5) { //Can't give back
            ConsoleViewOut.giveBackBookToStore(GiveBackBookToStore.CANNOT_GIVE_BACK);
        } else {
            CentralLibrary.getInstance().giveBackBookToStore(student, book, dayPassed, price);
            setGiveBackStoreString(student, book, time, sellDate, price * dayPassed /10);
            ConsoleViewOut.giveBackBookToStore(price * (10 - dayPassed) /10);
        }
    }

    private void doGiveBackToStoreFromProfessor(Professor professor, Book book, MyTime time) {
        String string = "" + professor.getNationalCode() + "" + book.getISBN();
        String[] splitDetails;
        splitDetails = CentralLibrary.getInstance().getBooksSold().get(string).split(",");
        MyDate sellDate = setDayByString(splitDetails[4].trim());
        int price = Integer.parseInt(splitDetails[5].trim());
        int dayPassed = daysPassed(currentDay, sellDate);
        if (dayPassed > 5) { //Can't give back
            ConsoleViewOut.giveBackBookToStore(GiveBackBookToStore.CANNOT_GIVE_BACK);
        } else {
            CentralLibrary.getInstance().giveBackBookToStore(professor, book, dayPassed, price);
            setGiveBackStoreString(professor, book, time, sellDate, price * dayPassed /10);
            ConsoleViewOut.giveBackBookToStore(price * (10 - dayPassed) /10);
        }
    }

    private void setGiveBackStoreString(Person person, Book book, MyTime time, MyDate sellDate, int newPrice) {
        Employee employee = CentralManagement.getWorkerByTime(Libraries.CENTRAL_LIBRARY, calcWeekDay(currentDay), time.getHour());
        String string;
        String key;
        if (person.getType() == Type.PROFESSOR) {
            Professor professor = (Professor)person;
            string = book.getBookDetails() + ", Professor, " + professor.getNationalCode() + ", " +
            sellDate + ", " + currentDay + ", " + newPrice;
            key = "" + professor.getNationalCode() + "" + book.getISBN();
        } else { //Student
            Student student = (Student)person;
            string = book.getBookDetails() + ", Student, " + student.getStudentId() + ", " +
                    sellDate + ", " + currentDay + ", " + newPrice;
            key = "" + student.getStudentId() + "" + book.getISBN();
        }
        String help;
        try {
            help = ", " + employee.getFullName();
        } catch (NullPointerException e) {
            help = ", -";
        }
        CentralLibrary.getInstance().getBooksGiveBack().put(key, string + help);
    }

    public MyDate setDayByString(String date) {
        MyDate myDate;
        String[] help = date.split("/");
        int year = Integer.parseInt(help[0].trim());
        int month = Integer.parseInt(help[1].trim());
        int day = Integer.parseInt(help[2].trim());
        myDate = new MyDate(year, month, day);
        return myDate;
    }

    private void setSellBookStringForStudent(Student student, Book book, int price, MyTime time) {
        Employee employee = CentralManagement.getWorkerByTime(Libraries.CENTRAL_LIBRARY, calcWeekDay(currentDay), time.getHour());
        String string = book.getBookDetails() + ", Student, " + student.getStudentId() + ", " + currentDay +
                ", " + price;
        String help;
        try {
            help = ", " + employee.getFullName();
        } catch (NullPointerException e) {
            help = ", -";
        }
        String key = "" + student.getStudentId() + "" + book.getISBN();
        CentralLibrary.getInstance().getBooksSold().put(key, string + help);
    }

    private void setSellBookStringForProfessor(Professor professor, Book book, int price, MyTime time) {
        Employee employee = CentralManagement.getWorkerByTime(Libraries.CENTRAL_LIBRARY, calcWeekDay(currentDay), time.getHour());
        String string = book.getBookDetails() + ", Professor, " + professor.getNationalCode() + ", " + currentDay +
                ", " + price;
        String help;
        try {
            help = ", " + employee.getFullName();
        } catch (NullPointerException e) {
            help = ", -";
        }
        String key = "" + professor.getNationalCode() + "" + book.getISBN();
        CentralLibrary.getInstance().getBooksSold().put(key, string + help);
    }

    private void calcFines(int add) {
        CentralLibrary.getInstance().setFineForDelay(currentDay, add);
        LibraryA.getInstance().setFineForDelay(currentDay, add);
        LibraryB.getInstance().setFineForDelay(currentDay, add);
        ConsoleViewOut.setFines();
    }

    public boolean datePass(MyDate thisDate, MyDate thatDate) {
        if (thisDate.getYear() > thatDate.getYear()) {
            return true;
        } else if (thisDate.getYear() < thatDate.getYear()) {
            return false;
        } else {
            if (thisDate.getMonth() > thatDate.getMonth()) {
                return true;
            } else if (thisDate.getMonth() < thatDate.getMonth()) {
                return false;
            } else {
                return thisDate.getDay() > thatDate.getDay();
            }
        }
    }

    private void giveBackBook(Person person, Book book, MyTime time) {
        if (book.getBorrowers().containsKey(person)) {
            book.getBorrowers().remove(person);
            String[] loanDate = CentralManagement.allBorrowedBooks.get("" +
                    person.getNationalCode() + "" + book.getISBN()).split(",");
            CentralManagement.allBorrowedBooks.remove("" + person.getNationalCode() + "" + book.getISBN());
            String string = setGiveBackBookString(person, book, time, loanDate[4].trim(), loanDate[5].trim());
            CentralManagement.allReturnedBooks.put("" + person.getNationalCode() + "" + book.getISBN(), string);
            if (book.getBookPlace() == Libraries.CENTRAL_LIBRARY) {
                CentralLibrary.getInstance().returnBook(book);
            } else if (book.getBookPlace() == Libraries.LIBRARY_A) {
                LibraryA.getInstance().returnBook(book);
            } else { // LibraryB
                LibraryB.getInstance().returnBook(book);
            }
            ConsoleViewOut.giveBackBook(GiveBackBook.SUCCESSFUL);
        } else {
            ConsoleViewOut.giveBackBook(GiveBackBook.BOOK_FOR_SOMEONE_ELSE);
        }
    }

    private String setGiveBackBookString(Person person, Book book, MyTime time, String loanDate, String giveBackDate) {
        String string;
        Employee employee = CentralManagement.getWorkerByTime(book.getBookPlace(), calcWeekDay(currentDay), time.getHour());
        if (person.getType() == Type.STUDENT) {
            Student student = (Student) person;
            string = book.getBookDetails() + ", Student, " + student.getStudentId() + ", " + loanDate + ", " +
                    giveBackDate + ", " + currentDay + ", " + time;

        } else {
            Professor professor = (Professor) person;
            string = book.getBookDetails() + ", Professor, " + professor.getNationalCode() + ", " + loanDate + ", " +
                    giveBackDate + ", " + currentDay + ", " + time;
        }
        String help;
        try {
            help = ", " + employee.getFullName();
        } catch (NullPointerException e) {
            help = ", -";
        }
        return string + help;
    }

    private void doLoanForStudent(int studentId, Book book, MyTime loanTime, MyDate giveBackDate) {
        if (calcWeekDay(giveBackDate) == null) {
            ConsoleViewOut.loanBookFailed(LoanBook.DATE_PASSED);
            return;
        }if (calcWeekDay(currentDay) == WeekDays.FRIDAY) {
            ConsoleViewOut.loanBookFailed(LoanBook.LIBRARY_IS_CLOSED);
            return;
        }if (book.getBorrowers().containsKey(CentralManagement.getStudentByStudentIdInAllActiveStudents(studentId))) {
            ConsoleViewOut.loanBookFailed(LoanBook.BORROW_THE_SAME_BOOK);
            return;
        }
        boolean bool;
        if (book.getBookPlace() == Libraries.LIBRARY_A) {
            bool = LibraryA.getInstance().borrowBook(book);
        } else if (book.getBookPlace() == Libraries.LIBRARY_B) {
            bool = LibraryB.getInstance().borrowBook(book);
        } else {
            bool = CentralLibrary.getInstance().borrowBook(book);
        }
        if (bool) {
            setLoanBookStringForStudent(studentId, book, loanTime, giveBackDate);
            ConsoleViewOut.loanBook();
        } else {
            ConsoleViewOut.loanBookFailed(LoanBook.BOOK_NOT_AVAILABLE);
        }
    }

    private void doLoanForProfessor(String nationalCode, Book book, MyTime loanTime, MyDate giveBackDate) {
        if (calcWeekDay(giveBackDate) == null) {
            ConsoleViewOut.loanBookFailed(LoanBook.DATE_PASSED);
            return;
        }if (calcWeekDay(currentDay) == WeekDays.FRIDAY) {
            ConsoleViewOut.loanBookFailed(LoanBook.LIBRARY_IS_CLOSED);
            return;
        }if (book.getBorrowers().containsKey(CentralManagement.getProfessorByNCInAllActiveProfessors(nationalCode))) {
            ConsoleViewOut.loanBookFailed(LoanBook.BORROW_THE_SAME_BOOK);
            return;
        }
        boolean bool;
        if (book.getBookPlace() == Libraries.LIBRARY_A) {
            bool = LibraryA.getInstance().borrowBook(book);
        } else if (book.getBookPlace() == Libraries.LIBRARY_B) {
            bool = LibraryB.getInstance().borrowBook(book);
        } else {
            bool = CentralLibrary.getInstance().borrowBook(book);
        }
        if (bool) {
            setLoanBookStringForProfessor(nationalCode, book, loanTime, giveBackDate);
            ConsoleViewOut.loanBook();
        } else {
            ConsoleViewOut.loanBookFailed(LoanBook.BOOK_NOT_AVAILABLE);
        }
    }

    private void setLoanBookStringForStudent(int studentId, Book book, MyTime loanTime, MyDate giveBackDate) {
        Employee employee = CentralManagement.getWorkerByTime(book.getBookPlace(), calcWeekDay(currentDay), loanTime.getHour());
        String string = book.getBookDetails() + ", Student, " + studentId +
                ", " + currentDay + ", " + loanTime + ", " + giveBackDate;
        String help;
        try {
            help = ", " + employee.getFullName();
        } catch (NullPointerException e) {
            help = ", -";
        }
        Student student = CentralManagement.getStudentByStudentIdInAllActiveStudents(studentId);
        assert student != null;
        CentralManagement.allBorrowedBooks.put("" + student.getNationalCode() + "" + book.getISBN(), string + help);
        book.getBorrowers().put(student, giveBackDate);
    }

    private void setLoanBookStringForProfessor(String nationalCode, Book book, MyTime loanTime, MyDate giveBackDate) {
        Employee employee = CentralManagement.getWorkerByTime(book.getBookPlace(), calcWeekDay(currentDay), loanTime.getHour());
        String string = book.getBookDetails() + ", Professor, " + nationalCode +
                ", " + currentDay + ", " + loanTime + ", " + giveBackDate;
        String help;
        try {
            help = ", " + employee.getFullName();
        } catch (NullPointerException e) {
            help = ", -";
        }
        CentralManagement.allBorrowedBooks.put("" + nationalCode + "" + book.getISBN() , string + help);
        book.getBorrowers().put(CentralManagement.getProfessorByNCInAllActiveProfessors(nationalCode), giveBackDate);
    }

    public WeekDays calcWeekDay(MyDate date) {
        int dayPassed = daysPassed(date, startDate);
        if (dayPassed < 0) {
            return null;
        }
        WeekDays day;
        if (dayPassed % 7 == 0) {
            day = WeekDays.SATURDAY;
        } else if (dayPassed % 7 == 1) {
            day = WeekDays.SUNDAY;
        } else if (dayPassed % 7 == 2) {
            day = WeekDays.MONDAY;
        } else if (dayPassed % 7 == 3) {
            day = WeekDays.TUESDAY;
        } else if (dayPassed % 7 == 4) {
            day = WeekDays.WEDNESDAY;
        } else if (dayPassed % 7 == 5) {
            day = WeekDays.THURSDAY;
        } else {
            day = WeekDays.FRIDAY;
        }
        return  day;
    }

    public int daysPassed(MyDate date, MyDate fromDate) {//if kab is 3 years after now!
        int dayPassed1 = fromDate.getMonth() > 6 ?  (fromDate.getMonth() - 7) * 30 + 186 + fromDate.getDay() :
                (fromDate.getMonth() - 1) * 31 + fromDate.getDay();

        int year = date.getYear() - fromDate.getYear();
        int daysOfYears = year * 365;
        int daysOfMonth = date.getMonth() > 6 ? (date.getMonth() - 7) * 30 + 186 : (date.getMonth() - 1) * 31;
        int days = date.getDay();
        int dayPassed2 = daysOfYears + daysOfMonth + days;
        return dayPassed2 - dayPassed1;
    }
}
