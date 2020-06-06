package view;

import controller.SaveDate;
import main.ShowProgramDetails;

import java.util.Scanner;

public abstract class ConsoleViewIn {
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        System.out.println("Hi guy, Please set the date to start...");

        while (true) {
            String command = scanner.nextLine();
            if (ConsoleCommands.SET_DATE.getMatcher(command).matches()) {//0
                SplitCommand.setDate(command);
                break;
            } else if (command.equals("file")) {
                SplitCommand.setDate("Set Date 1398/08/17");
                SaveDate.UploadFile();
                break;
            } else {
                System.err.println("INVALID COMMAND! PLEASE TRY AGAIN!");
            }
        }
        while (true) {
            String command = scanner.nextLine();
            if (ConsoleCommands.END.getMatcher(command).matches()) {//1
                break;
            } else if (ConsoleCommands.ADD_BOOK_TO_LIBRARY.getMatcher(command).matches()) {//2
                SplitCommand.addBookToLibrary(command);
                System.out.println("\n");
            } else if (ConsoleCommands.ADD_BOOK_TO_STORE.getMatcher(command).matches()) {//3
                SplitCommand.addBookToStore(command);
                System.out.println("\n");
            } else if (ConsoleCommands.ADD_STUDENT.getMatcher(command).matches()) {//4
                SplitCommand.addStudent(command);
                System.out.println("\n");
            } else if (ConsoleCommands.ADD_PROFESSOR.getMatcher(command).matches()) {//5
                SplitCommand.addProfessor(command);
                System.out.println("\n");
            } else if (ConsoleCommands.ADD_WORKER.getMatcher(command).matches()) {//6
                SplitCommand.addEmployee(command);
                System.out.println("\n");
            } else if (ConsoleCommands.CREATE_BOOK.getMatcher(command).matches()) {//7
                SplitCommand.createBook(command);
                System.out.println("\n");
            } else if (ConsoleCommands.CREATE_PROFESSOR.getMatcher(command).matches()) {//8
                SplitCommand.createProfessor(command);
                System.out.println("\n");
            } else if (ConsoleCommands.CREATE_STUDENT.getMatcher(command).matches()) {//9
                SplitCommand.createStudent(command);
                System.out.println("\n");
            } else if (ConsoleCommands.CREATE_WORKER.getMatcher(command).matches()) {//10
                SplitCommand.createWorker(command);
                System.out.println("\n");
            } else if (ConsoleCommands.DEPOSIT_PROFESSOR.getMatcher(command).matches()) {//11
                SplitCommand.depositProfessor(command);
                System.out.println("\n");
            } else if (ConsoleCommands.DEPOSIT_STUDENT.getMatcher(command).matches()) {//12
                SplitCommand.depositStudent(command);
                System.out.println("\n");
            } else if (ConsoleCommands.FIND_BOOK.getMatcher(command).matches()) {//13
                SplitCommand.findBook(command);
                System.out.println("\n");
            } else if (ConsoleCommands.GIVE_BACK_BOOK.getMatcher(command).matches()) {//14
                SplitCommand.giveBackBook(command);
                System.out.println("\n");
            } else if (ConsoleCommands.GIVE_BACK_TO_STORE.getMatcher(command).matches()) {//15
                SplitCommand.giveBackBookToStore(command);
                System.out.println("\n");
            } else if (ConsoleCommands.LOAN_BOOK.getMatcher(command).matches()) {//16
                SplitCommand.loanBook(command);
                System.out.println("\n");
            } else if (ConsoleCommands.SET_SCHEDULE.getMatcher(command).matches()) {//17
                SplitCommand.setSchedule(command);
                System.out.println("\n");
            } else if (ConsoleCommands.SET_DISCOUNT.getMatcher(command).matches()) {//18
                SplitCommand.setDiscount(command);
                System.out.println("\n");
            } else if (ConsoleCommands.SELL_BOOK.getMatcher(command).matches()) {//19
                SplitCommand.sellBook(command);
                System.out.println("\n");
            } else if (ConsoleCommands.NEXT_DAY.getMatcher(command).matches()) {//20
                SplitCommand.goNextDay(command);
                System.out.println("\n");
            } else if (command.equalsIgnoreCase("show details") ||
                    command.equalsIgnoreCase("000")) {//0000 test
                ShowProgramDetails.run();
            } else {//21
                System.err.println("INVALID COMMAND! PLEASE TRY AGAIN!\n");
            }
        }
        System.out.println("Program has finished! Good luck;)");
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
