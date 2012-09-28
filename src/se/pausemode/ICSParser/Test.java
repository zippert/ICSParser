package se.pausemode.ICSParser;

import java.io.*;

public class Test {

    public static void main(String[] args){

        File f = new File("c:\\invite.ics");
        CalendarHandler ch = new CalendarHandler(f);
        Calendar c = ch.build();
        System.out.println(c.toString());
    }


}
