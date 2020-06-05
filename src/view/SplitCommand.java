package view;

import controller.*;
import enums.*;
import model.Book;

import java.util.ArrayList;
import java.util.regex.Matcher;

public abstract class SplitCommand {
    private static final LibrariesController controller = LibrariesController.getInstance();

    public static void setDate(String command) {
        Matcher matcher = ConsoleCommands.SET_DATE.getMatcher(command);
        if (matcher.find()) {
            String[] help = matcher.group(1).split("/");
            int year = Integer.parseInt(help[0].trim());
            int month = Integer.parseInt(help[1].trim());
            int day = Integer.parseInt(help[2].trim());
            controller.setDate(year, month, day);
        }
    }

    public static void createBook(String command) {
        Matcher matcher = ConsoleCommands.CREATE_BOOK.getMatcher(command);
        if (matcher.find()) {
            if (matcher.group(8) == null) {
                controller.createBook(matcher.group(1), Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)), matcher.group(4),
                        matcher.group(5), Long.parseLong(matcher.group(6)), Integer.parseInt(matcher.group(7)));
            } else {
                controller.createBook(matcher.group(1), Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)), matcher.group(4),
                        matcher.group(5), Long.parseLong(matcher.group(6)),
                        Integer.parseInt(matcher.group(7)), matcher.group(8).trim());
            }
        }
    }

    public static void addBookToLibrary(String command) {
        Matcher matcher = ConsoleCommands.ADD_BOOK_TO_LIBRARY.getMatcher(command);
        if (matcher.find()) {
            controller.addBook(matcher.group(1), matcher.group(2),
                    Long.parseLong(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        }
    }

    public static void createStudent(String command) {
        Matcher matcher = ConsoleCommands.CREATE_STUDENT.getMatcher(command);
        if (matcher.find()) {
            Gender gender = setGender(matcher.group(4));
            controller.createStudent(matcher.group(1), Integer.parseInt(matcher.group(2)),
                    Long.parseLong(matcher.group(3)), gender, Integer.parseInt(matcher.group(5)),
                    Integer.parseInt(matcher.group(6)), matcher.group(7), Long.parseLong(matcher.group(8)),
                    matcher.group(9));
        }
    }

    public static void createProfessor(String command) {
        Matcher matcher = ConsoleCommands.CREATE_PROFESSOR.getMatcher(command);
        if (matcher.find()) {
            Gender gender = setGender(matcher.group(4));
            controller.createProfessor(matcher.group(1), Integer.parseInt(matcher.group(2)),
                    Long.parseLong(matcher.group(3)), gender, Integer.parseInt(matcher.group(5)),
                    Long.parseLong(matcher.group(6)), matcher.group(7));
        }
    }

    public static void createWorker(String command) {
        Matcher matcher = ConsoleCommands.CREATE_WORKER.getMatcher(command);
        if (matcher.find()) {
            Gender gender = setGender(matcher.group(4));
            Libraries libraries = setLibrary(matcher.group(5));
            if (libraries != Libraries.NO_WHERE_YET) {
                controller.createWorker(matcher.group(1), Integer.parseInt(matcher.group(2)),
                        Long.parseLong(matcher.group(3)), gender, libraries);
            } else {
                System.err.println("Sorry! We can't create worker. Library is not found!");
            }
        }
    }

    public static void depositStudent(String command) {
        Matcher matcher = ConsoleCommands.DEPOSIT_STUDENT.getMatcher(command);
        if (matcher.find()) {
            controller.depositStudent(Integer.parseInt(matcher.group(1)),
                    Long.parseLong(matcher.group(2)));
        }
    }

    public static void depositProfessor(String command) {
        Matcher matcher = ConsoleCommands.DEPOSIT_PROFESSOR.getMatcher(command);
        if (matcher.find()) {
            controller.depositProfessor(Long.parseLong(matcher.group(1)),
                    Long.parseLong(matcher.group(2)));
        }
    }

    public static void addStudent(String command) {
        Matcher matcher = ConsoleCommands.ADD_STUDENT.getMatcher(command);
        if (matcher.find()) {
            controller.addStudent(Integer.parseInt(matcher.group(1)));
        }
    }

    public static void addProfessor(String command) {
        Matcher matcher = ConsoleCommands.ADD_PROFESSOR.getMatcher(command);
        if (matcher.find()) {
            controller.addProfessor(Long.parseLong(matcher.group(1)));
        }
    }

    public static void addEmployee(String command) {
        Matcher matcher = ConsoleCommands.ADD_WORKER.getMatcher(command);
        if (matcher.find()) {
            Libraries libraries = setLibrary(matcher.group(2));
            if (libraries != null) {
                controller.addEmployee(Long.parseLong(matcher.group(1)), libraries);
            } else {
                System.err.println("Sorry! We can't create worker. Library is not found!");
            }
        }
    }

    public static void setSchedule(String command) {
        Matcher matcher = ConsoleCommands.SET_SCHEDULE.getMatcher(command);
        if (matcher.find()) {
            ArrayList<WeekDays> schedule = new ArrayList<>();
            long nationalCode = Long.parseLong(matcher.group(1));
            Libraries libraries = setLibrary(matcher.group(2));
            if (libraries != null) {
                for (int i = 0; i < 6; i++) {
                    setDaysInSchedule(schedule, i+1, Integer.parseInt(matcher.group(i + 3)) != 0);
                }
                controller.setSchedule(nationalCode, libraries, schedule);
            } else {
                System.err.println("Sorry! We can't create worker. Library is not found!");
            }
        }
    }

    public static void findBook(String command) {
        Matcher matcher = ConsoleCommands.FIND_BOOK.getMatcher(command);
        if (matcher.find()) {
            if (matcher.group(4).equalsIgnoreCase("student")) {
                controller.findBookForStudent(Integer.parseInt(matcher.group(5)), matcher.group(1),
                        Long.parseLong(matcher.group(2)), Integer.parseInt(matcher.group(3)));
            } else if (matcher.group(4).equalsIgnoreCase("professor")) {
                controller.findBookForProfessors(Long.parseLong(matcher.group(5)), matcher.group(1),
                        Long.parseLong(matcher.group(2)), Integer.parseInt(matcher.group(3)));
            } else {
                ConsoleViewOut.invalidCommands();
            }
        }
    }

    public static void loanBook(String command) {
        Matcher matcher = ConsoleCommands.LOAN_BOOK.getMatcher(command);
        if (matcher.find()) {
            MyTime loanTime = setTime(matcher.group(8));
            MyDate giveBackDate = setDay(matcher.group(9));
            try {
                if (matcher.group(5).equalsIgnoreCase("mainLibrary") ||
                        matcher.group(5).equalsIgnoreCase("centralLibrary")) {
                    if (matcher.group(6).equalsIgnoreCase("student")) {
                        controller.loanBookInCentralLibraryForStudent(Long.parseLong(matcher.group(1)),
                                Integer.parseInt(matcher.group(2)), Libraries.CENTRAL_LIBRARY,
                                Integer.parseInt(matcher.group(7)), loanTime, giveBackDate);
                    } else {
                        controller.loanBookInCentralLibraryForProfessor(Long.parseLong(matcher.group(1)),
                                Integer.parseInt(matcher.group(2)), Libraries.CENTRAL_LIBRARY,
                                Long.parseLong(matcher.group(7)), loanTime, giveBackDate);
                    }
                } else if (matcher.group(5).equalsIgnoreCase("LibraryA") ||
                        matcher.group(5).equalsIgnoreCase("A")) {
                    String translator;
                    if (matcher.group(4) == null) {
                        translator = "";
                    } else {
                        translator = matcher.group(4).trim();
                    }
                    if (matcher.group(6).equalsIgnoreCase("student")) {
                        controller.loanBookInLibrary_A_B_ForStudent(matcher.group(1),
                                Integer.parseInt(matcher.group(2)), translator,
                                Libraries.LIBRARY_A, Integer.parseInt(matcher.group(7)), loanTime, giveBackDate);
                    } else {
                        controller.loanBookInLibrary_A_B_ForProfessor(matcher.group(1),
                                Integer.parseInt(matcher.group(2)), translator,
                                Libraries.LIBRARY_A, Long.parseLong(matcher.group(7)), loanTime, giveBackDate);
                    }
                } else if (matcher.group(5).equalsIgnoreCase("LibraryB") ||
                        matcher.group(5).equalsIgnoreCase("B")) {
                    String translator;
                    if (matcher.group(4) == null) {
                        translator = "";
                    } else {
                        translator = matcher.group(4).trim();
                    }
                    if (matcher.group(6).equalsIgnoreCase("student")) {
                        controller.loanBookInLibrary_A_B_ForStudent(matcher.group(1),
                                Integer.parseInt(matcher.group(2)), translator,
                                Libraries.LIBRARY_B, Integer.parseInt(matcher.group(7)), loanTime, giveBackDate);
                    } else {
                        controller.loanBookInLibrary_A_B_ForProfessor(matcher.group(1),
                                Integer.parseInt(matcher.group(2)), translator,
                                Libraries.LIBRARY_B, Long.parseLong(matcher.group(7)), loanTime, giveBackDate);
                    }
                }
            } catch (NumberFormatException e) {
                ConsoleViewOut.loanBookFailed(LoanBook.DETAILS_NOT_MATCH);
            }

        }
    }

    public static void giveBackBook(String command) {
        Matcher matcher = ConsoleCommands.GIVE_BACK_BOOK.getMatcher(command);
        if (matcher.find()) {
            MyTime time = setTime(matcher.group(8));
            Libraries library;
            Book book;
            int studentId;
            long nationalCode;
            try {
                if (matcher.group(5).equalsIgnoreCase("centralLibrary") ||
                        matcher.group(5).equalsIgnoreCase("mainLibrary")) {
                    library = Libraries.CENTRAL_LIBRARY;
                    book = new Book(Long.parseLong(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                } else if (matcher.group(5).equalsIgnoreCase("libraryA") ||
                        matcher.group(5).equalsIgnoreCase("A")) {
                    library = Libraries.LIBRARY_A;
                    book = new Book(matcher.group(1), Integer.parseInt(matcher.group(2)), matcher.group(4));
                } else if (matcher.group(5).equalsIgnoreCase("libraryB") ||
                        matcher.group(5).equalsIgnoreCase("B")) {
                    library = Libraries.LIBRARY_B;
                    book = new Book(matcher.group(1), Integer.parseInt(matcher.group(2)), matcher.group(4));
                } else {
                    book = new Book();
                    library = Libraries.NO_WHERE_YET;
                }
                if (matcher.group(6).equalsIgnoreCase("student")) {
                    studentId = Integer.parseInt(matcher.group(7));
                    controller.giveBackBookFromStudent(book, library, studentId, time);
                } else {
                    nationalCode = Long.parseLong(matcher.group(7));
                    controller.giveBackBookFromProfessor(book, library, nationalCode, time);
                }
            } catch (NumberFormatException e) {
                ConsoleViewOut.giveBackBook(GiveBackBook.DETAILS_NOT_MATCH);
            }
        }
    }

    public static void goNextDay(String command) {
        Matcher matcher = ConsoleCommands.NEXT_DAY.getMatcher(command);
        if (matcher.find()) {
            int day;
            if (matcher.group(1) == null) {
                day = 1;
            } else {
                day = Integer.parseInt(matcher.group(1).trim());
            }
            controller.goNextDay(day);
        }
    }

    public static void addBookToStore(String command) {
        Matcher matcher = ConsoleCommands.ADD_BOOK_TO_STORE.getMatcher(command);
        if (matcher.find()) {
            controller.addBookToStore(matcher.group(2),
                        Long.parseLong(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        }
    }

    public static void setDiscount(String command) {
        Matcher matcher = ConsoleCommands.SET_DISCOUNT.getMatcher(command);
        if (matcher.find()) {
            controller.setDiscount(matcher.group(1), Integer.parseInt(matcher.group(2)));
        }
    }

    public static void sellBook(String command) {
        Matcher matcher = ConsoleCommands.SELL_BOOK.getMatcher(command);
        if (matcher.find()) {
            MyTime time = setTime(matcher.group(6));
            String discountCode = matcher.group(7) == null ? "-" : matcher.group(7).trim();
            if (matcher.group(4).equalsIgnoreCase("student")) {
                controller.sellBookToStudent(matcher.group(1), Long.parseLong(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(5)), time, discountCode);
            } else { //professor
                controller.sellBookToProfessor(matcher.group(1), Long.parseLong(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)), Long.parseLong(matcher.group(5)), time, discountCode);
            }
        }
    }

    public static void giveBackBookToStore(String command) {
        Matcher matcher = ConsoleCommands.GIVE_BACK_TO_STORE.getMatcher(command);
        if (matcher.find()) {
            MyTime time = setTime(matcher.group(6));
            if (matcher.group(4).equalsIgnoreCase("student")) {
                controller.giveBackBookToStoreFromStudent(matcher.group(1), Long.parseLong(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(5)), time);
            } else { //professor
                controller.giveBackBookToStoreFromProfessor(matcher.group(1), Long.parseLong(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)), Long.parseLong(matcher.group(5)), time);
            }
        }
    }

    private static MyTime setTime(String time) {
        MyTime myTime;
        int min;
        int hour;
        int mid = time.indexOf(':');
        hour = Integer.parseInt(time.substring(0, mid).trim());
        min = Integer.parseInt(time.substring(mid + 1).trim());
        myTime = new MyTime(hour, min);
        return myTime;
    }

    private static MyDate setDay(String time) {
        MyDate myDate;
        String[] help = time.split("/");
        int year = Integer.parseInt(help[0].trim());
        int month = Integer.parseInt(help[1].trim());
        int day = Integer.parseInt(help[2].trim());
        myDate = new MyDate(year, month, day);
        return myDate;
    }

    private static void setDaysInSchedule(ArrayList<WeekDays> schedule, int i, boolean bool) {
        if (bool) {
            if (i == 1) {
                schedule.add(WeekDays.SATURDAY);
            } else  if (i == 2) {
                schedule.add(WeekDays.SUNDAY);
            } else  if (i == 3) {
                schedule.add(WeekDays.MONDAY);
            } else  if (i == 4) {
                schedule.add(WeekDays.TUESDAY);
            } else  if (i == 5) {
                schedule.add(WeekDays.WEDNESDAY);
            } else  if (i == 6) {
                schedule.add(WeekDays.THURSDAY);
            }
        }
    }

    private static Gender setGender(String string) {
        Gender gender;
        if (string.equalsIgnoreCase("male") ||
                string.equalsIgnoreCase("m"))
            gender = Gender.MALE;
        else if (string.equalsIgnoreCase("female") ||
                string.equalsIgnoreCase("f"))
            gender = Gender.FEMALE;
        else gender = Gender.NON;
        return gender;
    }

    private static Libraries setLibrary(String string) {
        Libraries libraries;
        if (string.equalsIgnoreCase("mainLibrary") ||
                string.equalsIgnoreCase("centralLibrary")) {
            libraries = Libraries.CENTRAL_LIBRARY;
        } else if (string.equalsIgnoreCase("libraryA") ||
                string.equalsIgnoreCase("A")) {
            libraries = Libraries.LIBRARY_A;
        } else if (string.equalsIgnoreCase("libraryB") ||
                string.equalsIgnoreCase("B")) {
            libraries = Libraries.LIBRARY_B;
        } else {
            libraries = Libraries.NO_WHERE_YET;
        }
        return libraries;
    }
}
