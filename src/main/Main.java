package main;

import controller.SaveDate;
import model.CentralManagement;
import view.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        //SaveDate.UploadFile();
        ConsoleViewIn.start();
        //AtTheEnd.run();
        String string = "aliNourian 10@gmail.com";
        Pattern pattern = Pattern.compile("(\\w+)\\s+(\\d+)\\s*@(gmail)(\\s*yahoo)?\\s*(.com)");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            System.out.println(matcher.group(4) == null);
        }
    }

    private static boolean check(String s) {
        if (s == null) {
            Ali ali = new Ali();
            ali.s = s;
            System.out.println("" + ali.a + ali.s);
            return false;
        } else {
            return true;
        }
    }
}

class Ali {
    int a;
    String s;
}
