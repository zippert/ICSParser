package se.pausemode.ICSParser;

import se.pausemode.ICSParser.DataTypes.AttendeeData;
import se.pausemode.ICSParser.Util.ParserUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CalendarHandler {

    private HashMap<String, String> map;

    public CalendarHandler(InputStream inputStream){
        ArrayList<String> list = load(inputStream);
        ArrayList<String> foldedList = unfold(list);
        mapify(filterList(foldedList, "VEVENT"));
    }

    public CalendarHandler(File f){
        ArrayList<String> list = load(f);
        ArrayList<String> foldedList = unfold(list);
        mapify(filterList(foldedList, "VEVENT"));
    }

    private ArrayList<String> filterList(ArrayList<String> completeFoldedList, String filterTag) {
        ArrayList<String> retVal = new ArrayList<String>();
        boolean startSave = false;
        boolean pauseSave = false;
        for(String element : completeFoldedList){
            if(element.startsWith("BEGIN:" + filterTag)){
                startSave = true;
            }
            if(element.startsWith("END:" + filterTag)){
                break;
            }
            if(!pauseSave){
                pauseSave = !element.startsWith("BEGIN:"+filterTag) && element.startsWith("BEGIN:");
            }
            if(startSave && !pauseSave && !element.startsWith("BEGIN:" + filterTag)){
                retVal.add(element);
            }

            if(pauseSave && element.startsWith("END:")){
                    pauseSave = false;
            }
        }

        return retVal;
    }

    private void mapify(ArrayList<String> list) {
        String key, value;
        int delim;
        map = new HashMap<String, String>();
        for(String s:list){
            delim = !s.contains(";") ? s.indexOf(":") : Math.min(s.indexOf(";"), s.indexOf(":"));
            key = s.substring(0,delim);
            value = s.substring(delim+1);
            int index = 1;
            while(map.containsKey(key)){
                key = key + "_" + index++;
            }
            map.put(key, value);
        }
    }

    private ArrayList<String> load(InputStream inputStream){
        ArrayList<String> source = null;
        if(inputStream != null){
            source = new ArrayList<String>();
            Scanner scanner = new Scanner(inputStream);
            while(scanner.hasNextLine()){
                source.add(scanner.nextLine());
            }
        }
        return source;
    }

    private ArrayList<String> load(File f){
        ArrayList<String> source = new ArrayList<String>();
        try{
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String s = bfr.readLine();
            while(s != null){
                if(!s.equals("")){
                    source.add(s);
                }
                s = bfr.readLine();
            }
            bfr.close();

        }catch(FileNotFoundException fnf){
            System.err.println(fnf.toString());
        }
        catch (Exception e){
            System.err.println(e.toString());
        }
        return source;
    }

    public Calendar build(){
        Calendar retVal = null;
        if(map != null && map.size()>=0){
            retVal = new Calendar();
            /** Mandatory **/
            //DTSTAMP:19971210T080000Z
            retVal.setDTSTAMP(map.get("DTSTAMP"));
            //UID:19960401T080045Z-4000F192713-0052@example.com
            retVal.setUID(ParserUtil.parseUID(map.get("UID")));
            //DTSTART:20120829T165000Z
            retVal.setDTSTART(ParserUtil.parseDateData(map.get("DTSTART")));

            /** Optional **/
            //CLASS:XXXXXXX
            retVal.setCLASS(map.get("CLASS"));
            //CREATED:19960329T133000Z
            retVal.setCREATED(map.get("CREATED"));
            //DESCRIPTION:Meeting to provide technical review for "Phoenix"
            //design.\nHappy Face Conference Room. Phoenix design team
            //MUST attend this meeting.\nRSVP to team leader.
            retVal.setDESCRIPTION(ParserUtil.parseStringData(map.get("DESCRIPTION")));
            //GEO:37.386013;-122.082932
            retVal.setGEO(ParserUtil.parseGeo(map.get("GEO")));
            //LAST-MODIFIED:19960817T133000Z
            retVal.setLASTMODIFIED(map.get("LAST-MODIFIED"));
            //LOCATION:Conference Room - F123\, Bldg. 002
            retVal.setLOCATION(ParserUtil.parseStringData(map.get("LOCATION")));
            //ORGANIZER;CN=JohnSmith;DIR="ldap://example.com:6666/o=DC%20Ass
            //ociates,c=US???(cn=John%20Smith)":mailto:jsmith@example.com
            retVal.setORGANIZER(ParserUtil.parseOrganizer(map.get("ORGANIZER")));
            //PRIORITY:2
            String prio = map.get("PRIORITY");
            if(prio != null){
                retVal.setPRIORITY(Integer.parseInt(prio));
            }
            //SEQUENCE:0
            String seq = map.get("SEQUENCE");
            if(seq != null){
                retVal.setSEQUENCE(Integer.parseInt(seq));
            }
            //STATUS:TENTATIVE
            String status = map.get("STATUS");
            if(status != null){
                retVal.setSTATUS(Calendar.EventStatus.valueOf(status));
            }
            //SUMMARY:Department Party
            retVal.setSUMMARY(ParserUtil.parseStringData(map.get("SUMMARY")));
            //TRANSP:TRANSPARENT
            String transp = map.get("TRANSP");
            if(transp != null){
                retVal.setTRANSP(Calendar.Transparency.valueOf(transp));
            }
            //URL:http://example.com/pub/calendars/jsmith/mytime.ics
            retVal.setURL(map.get("URL"));
            //RECURRENCE-ID;VALUE=DATE:19960401
            //RECURRENCE-ID;RANGE=THISANDFUTURE:19960120T120000Z
            retVal.setRECURRENCEID(ParserUtil.parseRecurrenceID(map.get("RECURRENCE-ID")));
            //RRULE:FREQ=YEARLY;INTERVAL=2;BYMONTH=1;BYDAY=SU;BYHOUR=8,9;
            //BYMINUTE=30
            retVal.setRECURRENCERULE(ParserUtil.parseRecurrenceRule(map.get("RRULE")));
            /** Either DTEND *or* DURATION */
            //DTEND:20120829T175000Z
            retVal.setDTEND(ParserUtil.parseDateData(map.get("DTEND")));
            //DURATION:PT1H0M0S
            retVal.setDURATION(ParserUtil.parseDurationData(map.get("DURATION")));
            //ATTACH;FMTTYPE=text/plain;ENCODING=BASE64;VALUE=BINARY:VGhlIH
            //F1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZy4
            retVal.setATTACH(ParserUtil.parseAttachData(map.get("ATTACH")));
            //ATTENDEE;ROLE_TYPES=REQ-PARTICIPANT;DELEGATED-FROM="mailto:bob@
            //example.com";PARTSTAT=ACCEPTED;CN=Jane Doe:mailto:jdoe@
            //example.com
            ArrayList<AttendeeData> attendees = new ArrayList<AttendeeData>();
            int index = 1;
            String value = map.get("ATTENDEE");
            while(value != null){
                attendees.add(ParserUtil.parseAttendeeData(value));
                value = map.get("ATTENDEE_" + index++);
            }
            retVal.setATTENDEES(attendees.toArray(new AttendeeData[attendees.size()]));
            //CATEGORIES:APPOINTMENT,EDUCATION
            retVal.setCATEGORIES(ParserUtil.parseCategoriesData(map.get("CATEGORIES")));
            //COMMENT:Hej hopp vad kul!
            retVal.setCOMMENT(ParserUtil.parseStringData(map.get("COMMENT")));
            //CONTACT;ALTREP="ldap://example.com:6666/o=ABC%20Industries\,
            //c=US???(cn=Jim%20Dolittle)":Jim Dolittle\, ABC Industries\,
            //+1-919-555-1234
            retVal.setCONTACT(ParserUtil.parseStringData(map.get("CONTACT")));
            //EXDATE:19960402T010000Z,19960403T010000Z,19960404T010000Z
            retVal.setEXDATE(ParserUtil.parseDateData(map.get("EXDATE")));
            //REQUEST-STATUS:2.8; Success\, repeating event ignored. Scheduled
            //as a single event.;RRULE:FREQ=WEEKLY\;INTERVAL=2
            retVal.setREQUESTSTATUS(ParserUtil.parseRequestStatusData(map.get("REQUEST-STATUS")));
            //RELATED-TO:jsmith.part7.19960817T083000.xyzMail@example.com
            retVal.setRELATEDTO(ParserUtil.parseRelatedToData(map.get("RELATED-TO")));
            //RESOURCES;LANGUAGE=fr:Nettoyeur haute pression
            retVal.setRESOURCES(ParserUtil.parseStringData(map.get("RESOURCES")));
            //RDATE;VALUE=PERIOD:19960403T020000Z/19960403T040000Z,
            //19960404T010000Z/PT3H
            retVal.setRDATE(ParserUtil.parseDateData(map.get("RDATE")));
        }

        return retVal;
    }



    private ArrayList<String> unfold(ArrayList<String> source){
        ArrayList<String> unfoldedList = new ArrayList<String>();
        String str;
        for(int i = 0;i<source.size();){
            str = source.get(i);
            while(++i<source.size() && source.get(i).charAt(0) == ' '){
                str = str.concat(source.get(i).trim());
            }
            unfoldedList.add(str);
        }
        return unfoldedList;
    }


}
