package se.pausemode.ICSHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.err;

public class Test {

    public static void main(String[] args){

        File f = new File("c:\\invite.ics");
        CalendarHandler ch = new CalendarHandler(f);
        Calendar c = ch.build();
        System.out.println(c.toString());
    }


}
