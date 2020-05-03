package view;

import controller.*;
import enums.Gender;
import enums.Libraries;

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
                        Integer.parseInt(matcher.group(7)), matcher.group(8));
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
            Libraries libraries;
            if (matcher.group(5).equalsIgnoreCase("CentralLibrary") ||
            matcher.group(5).equalsIgnoreCase("MainLibrary")) {
                libraries = Libraries.CENTRAL_LIBRARY;
            } else if (matcher.group(5).equalsIgnoreCase("LibraryA") ||
                    matcher.group(5).equalsIgnoreCase("A")) {
                libraries = Libraries.LIBRARY_A;
            } else if (matcher.group(5).equalsIgnoreCase("LibraryB") ||
                    matcher.group(5).equalsIgnoreCase("B")) {
                libraries = Libraries.LIBRARY_B;
            } else {
                System.err.println("Sorry! We can't just add worker. Library not found!");
                return;
            }
            controller.createWorker(matcher.group(1), Integer.parseInt(matcher.group(2)),
                    Long.parseLong(matcher.group(3)), gender, libraries);
        }
    }

    private static Gender setGender (String string) {
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



}
