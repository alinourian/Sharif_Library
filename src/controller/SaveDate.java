package controller;

import main.ShowProgramDetails;
import view.ConsoleCommands;
import view.SplitCommand;

import java.io.*;
import java.util.Scanner;

public abstract class SaveDate {
    private static final File file = new File("D:\\sharif\\ترم 2\\Object Orient Programing java term2\\تمرین ها\\3\\New folder\\test.txt");

    public static void UploadFile() {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String command;
                command = scanner.nextLine();
                run(command);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file does not found!");
        }
        System.out.println("Upload saves finished!");
    }

    private static void run(String command) {
        if (ConsoleCommands.END.getMatcher(command).matches()) {//1
            System.out.println("Program has finished! Good luck;)");
            return;
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
}