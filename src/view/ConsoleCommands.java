package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ConsoleCommands {
    SET_DATE("(?i)\\s*set\\s+date\\s+(\\d{4}\\s*\\/\\s*\\d+\\s*\\/\\s*\\d+)\\s*"),//1
    CREATE_BOOK("(?i)\\s*create\\s+book\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)" +//2
            "\\s+(\\w+)\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)(\\s+\\w+)?\\s*"),//2
    ADD_BOOK_TO_LIBRARY("(?i)\\s*add\\s+book\\s+(\\w+)\\s+(\\w+)" +//3
            "\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*"),//3
    CREATE_STUDENT("(?i)\\s*create\\s+person\\s+student\\s+(\\w+)\\s+" +//4
            "(\\d+)\\s+(\\d+)\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s+(\\w+)\\s+(\\d+)\\s+(\\w+)\\s*"),//4
    CREATE_PROFESSOR("(?i)\\s*create\\s+person\\s+professor\\s+(\\w+)" +//5
            "\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s*"),//5
    CREATE_WORKER("(?i)\\s*create\\s+person\\s+worker\\s+" +//6
            "(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s*"),//6
    DEPOSIT_STUDENT("(?i)\\s*deposit\\s+student\\s+(\\d+)\\s+(\\d+)\\s*"),//7
    DEPOSIT_PROFESSOR("(?i)\\s*deposit\\s+professor\\s+(\\d+)\\s+(\\d+)\\s*"),//8
    ADD_STUDENT("(?i)\\s*add\\s+person\\s+student\\s+(\\d+)\\s*"),//9
    ADD_PROFESSOR("(?i)\\s*add\\s+person\\s+professor\\s+(\\d+)\\s*"),//10
    ADD_WORKER("(?i)\\s*add\\s+worker\\s+(\\d+)\\s+(\\w+)\\s*"),//11
    SET_SCHEDULE("(?i)\\s*set\\s+schedule\\s+(\\d+)\\s+(\\w+)\\s+" +//12
            "(0|1)\\s+(0|1)\\s+(0|1)\\s+(0|1)\\s+(0|1)\\s+(0|1)\\s*"),//12
    FIND_BOOK("(?i)\\s*find\\s+book\\s+(\\w+)\\s*,\\s*(\\d+)\\s*,\\s*" +//13
            "(\\d+)\\s+(\\w+)\\s+(\\d+)\\s*"),//13
    LOAN_BOOK("(?i)\\s*loan\\s+book\\s+(\\w+\\s+\\w+\\s+(\\w+\\s+)?)(\\w+)" +//14
            "\\s*,\\s*(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+\\s*:\\s*\\w+)\\s+(\\d+\\/\\d+\\/\\d+)\\s*"),//14
    GIVE_BACK_BOOK("(?i)\\s*giveback\\s+book\\s+(\\w+\\s+\\w+\\s+(\\w+\\s+)?)" +//15
            "(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\d+\\/\\d+\\/\\d+)\\s*"),//15
    NEXT_DAY("(?i)\\s*next\\s+day(\\s+\\w+)?\\s*"),//16
    ADD_BOOK_TO_STORE("(?i)\\s*add\\s+book\\s+store\\s+(mainLibrary|centralLibrary)" +//17
            "\\s+(\\w+)\\s*,\\s*(\\w+)\\s*,\\s*(\\w+)\\s*"),//17
    SET_DISCOUNT("(?i)\\s*set\\s+discountCode\\s+(.+)"),//18
    SELL_BOOK("(?i)\\s*sell\\s+book\\s+(\\w+)\\s*,\\s*(\\w+)\\s*,\\s*(\\w+)" +//19
            "\\s+(\\w+)\\s+(\\w+)\\s+(\\w+\\s*:\\s*\\w+)(\\s+.+)?\\s*"),//19
    GIVE_BACK_TO_STORE("(?i)\\s*give\\s+back\\s+store\\s+(\\w+)\\s*,\\s*" +//20
            "(\\w+)\\s*,\\s*(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+\\s*:\\s*\\w+)\\s*"),//20
    END("(?i)\\s*end\\s*");//21

    private final Pattern commandPattern;

    ConsoleCommands(String commandString) {
        this.commandPattern = Pattern.compile(commandString);
    }

    public Matcher getMatcher(String input) {
        return this.commandPattern.matcher(input);
    }
}
