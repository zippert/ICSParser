package se.pausemode.ICSHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.err;

public class CalendarHandler {

    private HashMap<String, String> map;

    public CalendarHandler(File f){
        ArrayList<String> list = loadFile(f);
        ArrayList<String> foldedList = unfold(list);
        mapify(filterList(foldedList, "VEVENT"));
    }

    private ArrayList<String> filterList(ArrayList<String> completeFoldedList, String filterTag) {
        ArrayList<String> retVal = new ArrayList<String>();
        boolean startSave = false;
        boolean pauseSave = false;
        String element;
        for(int i = 0; i < completeFoldedList.size(); i++){
            element = completeFoldedList.get(i);
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
            map.put(key, value);
        }
    }

    private ArrayList<String> loadFile(File f){
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
            err.println(fnf.toString());
        }
        catch (Exception e){
            err.println(e.toString());
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
            //DTSTART:20120829T165000Z
            retVal.setDTSTART(parseDateData(map.get("DTSTART")));

            /** Optional **/
            //CLASS:XXXXXXX
            retVal.setCLASS(map.get("CLASS"));
            //CREATED:19960329T133000Z
            retVal.setCREATED(map.get("CREATED"));
            //DESCRIPTION:Meeting to provide technical review for "Phoenix"
            //design.\nHappy Face Conference Room. Phoenix design team
            //MUST attend this meeting.\nRSVP to team leader.
            retVal.setDESCRIPTION(parseStringData(map.get("DESCRIPTION")));
            //GEO:37.386013;-122.082932
            retVal.setGEO(parseGeo(map.get("GEO")));
            //LAST-MODIFIED:19960817T133000Z
            retVal.setLASTMODIFIED(map.get("LAST-MODIFIED"));
            //LOCATION:Conference Room - F123\, Bldg. 002
            retVal.setLOCATION(parseStringData(map.get("LOCATION")));
            //ORGANIZER;CN=JohnSmith;DIR="ldap://example.com:6666/o=DC%20Ass
            //ociates,c=US???(cn=John%20Smith)":mailto:jsmith@example.com
            retVal.setORGANIZER(parseOrganizer(map.get("ORGANIZER")));
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
            retVal.setSUMMARY(parseStringData(map.get("SUMMARY")));
            //TRANSP:TRANSPARENT
            String transp = map.get("TRANSP");
            if(transp != null){
                retVal.setTRANSP(Calendar.Transparency.valueOf(transp));
            }
            //URL:http://example.com/pub/calendars/jsmith/mytime.ics
            retVal.setURL(map.get("URL"));
            //RECURRENCE-ID;VALUE=DATE:19960401
            //RECURRENCE-ID;RANGE=THISANDFUTURE:19960120T120000Z
            retVal.setRECURRENCEID(parseRecurrenceID(map.get("RECURRENCE-ID")));
            //RRULE:FREQ=YEARLY;INTERVAL=2;BYMONTH=1;BYDAY=SU;BYHOUR=8,9;
            //BYMINUTE=30
            retVal.setRECURRENCERULE(parseRecurrenceRule(map.get("RRULE")));
            /** Either DTEND *or* DURATION */
            //DTEND:20120829T175000Z
            retVal.setDTEND(parseDateData(map.get("DTEND")));
            //DURATION:PT1H0M0S
            retVal.setDURATION(parseDurationData(map.get("DURATION")));
            //ATTACH;FMTTYPE=text/plain;ENCODING=BASE64;VALUE=BINARY:VGhlIH
            //F1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZy4
            retVal.setATTACH(parseAttachData(map.get("ATTACH")));
            //ATTENDEE;ROLE_TYPES=REQ-PARTICIPANT;DELEGATED-FROM="mailto:bob@
            //example.com";PARTSTAT=ACCEPTED;CN=Jane Doe:mailto:jdoe@
            //example.com
            retVal.setATTENDEE(parseAttendeeData(map.get("ATTENDEE")));
            //CATEGORIES:APPOINTMENT,EDUCATION
            retVal.setCATEGORIES(parseCategoriesData(map.get("CATEGORIES")));
            //COMMENT:Hej hopp vad kul!
            retVal.setCOMMENT(parseStringData(map.get("COMMENT")));
            //CONTACT;ALTREP="ldap://example.com:6666/o=ABC%20Industries\,
            //c=US???(cn=Jim%20Dolittle)":Jim Dolittle\, ABC Industries\,
            //+1-919-555-1234
            retVal.setCONTACT(parseStringData(map.get("CONTACT")));
            //EXDATE:19960402T010000Z,19960403T010000Z,19960404T010000Z
            retVal.setEXDATE(parseDateData(map.get("EXDATE")));
            //REQUEST-STATUS:2.8; Success\, repeating event ignored. Scheduled
            //as a single event.;RRULE:FREQ=WEEKLY\;INTERVAL=2
            retVal.setREQUESTSTATUS(parseRequestStatusData(map.get("REQUEST-STATUS")));
        }

        return retVal;
    }

    private RequestStatusData parseRequestStatusData(String requestStatusString) {
        RequestStatusData retVal = null;
        if(requestStatusString != null){
            retVal = new RequestStatusData();
            int trackIndex = 0;
            if(!Character.isDigit(requestStatusString.charAt(0))){
                //Handle optional (Language) strings
                for(String arg : requestStatusString.substring(0,requestStatusString.indexOf(":")).split(";")){
                    if(arg.startsWith("LANGUAGE=")){
                        retVal.setLanguage(arg.substring(arg.indexOf("=")+1));
                    }
                }
                trackIndex = requestStatusString.indexOf(":") + 1;
            }
            retVal.setStatCode(requestStatusString.substring(trackIndex, requestStatusString.indexOf(";", trackIndex)));
            trackIndex = requestStatusString.indexOf(";", trackIndex) + 1;

            if(requestStatusString.indexOf(";", trackIndex) == -1){
                retVal.setStatDesc(requestStatusString.substring(trackIndex));
            } else {
                retVal.setStatDesc(requestStatusString.substring(trackIndex,requestStatusString.indexOf(";",trackIndex)));
                trackIndex = requestStatusString.indexOf(";",trackIndex);
                retVal.setExtData(requestStatusString.substring(requestStatusString.indexOf(";", trackIndex) + 1));
            }

        }

        return retVal;
    }

    private StringData parseCategoriesData(String categoriesString) {
        StringData retVal = null;
        if(categoriesString != null){
            retVal = new StringData();
            String[] parseData = categoriesString.split(":");
            if(parseData.length > 1){
                retVal.setString(parseData[1]);
                for(String s:parseData[0].split(";")){
                    if(s.startsWith("LANGUAGE")){
                        retVal.setLanguage(s.substring(s.indexOf("=")+1));
                    }
                }
            } else {
                retVal.setString(parseData[0]);
            }

        }
        return retVal;
    }

    private AttendeeData parseAttendeeData(String attendeeString) {
        AttendeeData retVal = null;

        if(attendeeString != null){
            retVal = new AttendeeData();
            String mailTo = null;
            if(attendeeString.contains(":mailto:")){
                mailTo = ":mailto:";
            } else if(attendeeString.contains(":MAILTO:")) {
                mailTo = ":MAILTO:";
            }
            int indexOfCalAddress = attendeeString.lastIndexOf(mailTo);
            String[] arr = attendeeString.substring(0,indexOfCalAddress).split(";");
            retVal.setURI(attendeeString.split(mailTo)[1]);
            for(String s:arr){
                if(s.startsWith("CUTYPE=")){
                    retVal.setCUTYPE(AttendeeData.CUTYPE_TYPES.valueOf(s.substring(s.indexOf("=") + 1)));
                } else if(s.startsWith("MEMBER=")){
                    retVal.setMEMBER(parsePeopleData(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("ROLE_TYPES=")){
                    retVal.setROLE(AttendeeData.ROLE_TYPES.getEnum(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("PARTSTAT=")){
                    retVal.setPARTSTAT(AttendeeData.PARTSTAT_TYPE.getEnum(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("RSVP=")){
                    retVal.setRSVP(Boolean.parseBoolean(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("DELEGATED-TO=")){
                    retVal.setDELEGATED_TO(parsePeopleData(s.substring(s.indexOf("=") + 1)));
                } else if(s.startsWith("DELEGATED-FROM=")){
                    retVal.setDELEGATED_FROM(parsePeopleData(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("SENT-BY=")){
                    retVal.setSENT_BY(parsePeopleData(s.substring(s.indexOf("=") +1)));
                } else if(s.startsWith("CN=")){
                    retVal.setCN(s.substring(s.indexOf("=")+1).replace("\"",""));
                } else if(s.startsWith("DIR=")){
                    retVal.setDIR(s.substring(s.indexOf("=")+1).replace("\"",""));
                } else if(s.startsWith("LANGUAGE=")){
                    retVal.setLANGUAGE(s.substring(s.indexOf("=")+1));
                }
            }
        }

        return retVal;
    }

    private PeopleData parsePeopleData(String peopleDataString){
        PeopleData pd = new PeopleData();
        String[] members = peopleDataString.split(",");
        for(int i = 0; i < members.length; i++){
            //Remove trailing/ending "
            members[i] = members[i].substring(1,members[i].length()-1);
        }
        pd.setMembers(members);
        return pd;
    }

    private AttachData parseAttachData(String attachString) {
        AttachData retVal = null;
        if(attachString != null){
            retVal = new AttachData();
            if(!attachString.contains("=")){
                retVal.setURI(attachString);
            } else {
                int indexOfURI = attachString.indexOf(":");
                retVal.setURI(attachString.substring(indexOfURI+1));
                for(String s: attachString.substring(0,indexOfURI).split(";")){
                    if(s.startsWith("FMTTYPE=")){
                        retVal.setFMTTYPE(s.substring(s.indexOf("=")+1));
                    } else if(s.startsWith("ENCODING=")){
                        retVal.setENCODING(s.substring(s.indexOf("=")+1));
                    } else if(s.startsWith("VALUE=")){
                        retVal.setVALUE(s.substring(s.indexOf("=")+1));
                    }
                }
            }
        }
        return retVal;
    }

    private DurationData parseDurationData(String durationString) {
        DurationData retVal = null;
        if(durationString != null && durationString.startsWith("PT")){
            retVal = new DurationData();
            int startIndex = 2;
            if(durationString.contains("H")){
                retVal.setHour(Integer.parseInt(durationString.substring(startIndex,durationString.indexOf("H"))));
                startIndex = durationString.indexOf("H")+1;
            }
            if(durationString.contains("M")){
                retVal.setMinute(Integer.parseInt(durationString.substring(startIndex,durationString.indexOf("M"))));
                startIndex = durationString.indexOf("M") + 1;
            }
            if(durationString.contains("S")){
                retVal.setSecond(Integer.parseInt(durationString.substring(startIndex,durationString.indexOf("S"))));
            }
        }
        return retVal;
    }

    private RecurrenceRule parseRecurrenceRule(String recurString) {
        RecurrenceRule retVal = null;
        if(recurString != null){
            retVal = new RecurrenceRule();
            for(String s: recurString.split(";")){
                if(s.startsWith("FREQ=")){
                    retVal.setFREQ(RecurrenceRule.FREQVALUE.valueOf(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("UNTIL=")){
                    retVal.setUNTIL(parseDateData(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("COUNT=")){
                    retVal.setCOUNT(Integer.parseInt(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("INTERVAL=")){
                    retVal.setINTERVAL(Integer.parseInt(s.substring(s.indexOf("=") + 1)));
                } else if(s.startsWith("BYSECOND=")){
                    retVal.setBYSECOND(convertStringArrayToIntegerArray(s.substring(s.indexOf("=") + 1).split(",")));
                } else if(s.startsWith("BYMINUTE=")){
                    retVal.setBYMINUTE(convertStringArrayToIntegerArray(s.substring(s.indexOf("=") + 1).split(",")));
                } else if(s.startsWith("BYHOUR=")){
                    retVal.setBYHOUR(convertStringArrayToIntegerArray(s.substring(s.indexOf("=") + 1).split(",")));
                } else if(s.startsWith("BYDAY=")){
                    retVal.setBYDAY(parseWeekDay(s.substring(s.indexOf("=")+1).split(",")));
                } else if(s.startsWith("BYMONTHDAY=")){
                    retVal.setBYMONTHDAY(convertStringArrayToIntegerArray(s.substring(s.indexOf("=")+1).split(",")));
                } else if(s.startsWith("BYYEARDAY=")){
                    retVal.setBYYEARDAY(convertStringArrayToIntegerArray(s.substring(s.indexOf("=")+1).split(",")));
                } else if(s.startsWith("BYWEEKNO=")){
                    retVal.setBYWEEKNO(convertStringArrayToIntegerArray(s.substring(s.indexOf("=")+1).split(",")));
                } else if(s.startsWith("BYMONTH=")){
                    retVal.setBYMONTH(convertStringArrayToIntegerArray(s.substring(s.indexOf("=")+1).split(",")));
                } else if(s.startsWith("BYSETPOS=")){
                    retVal.setBYSETPOS(convertStringArrayToIntegerArray(s.substring(s.indexOf("=")+1).split(",")));
                } else if(s.startsWith("WKST=")){
                    retVal.setWKST(RecurrenceRule.WEEKDAY.valueOf(s.substring(s.indexOf("=") + 1)));
                }
            }
        }

        return retVal;
    }

    private WeekDayNum[] parseWeekDay(String[] weekDays) {
        WeekDayNum[] retVal = null;

        if(weekDays != null){
            retVal = new WeekDayNum[weekDays.length];
            int i = 0;
            for(String s : weekDays){
                WeekDayNum element = new WeekDayNum();
                if(s.length() == 4){
                    element.setOccurence(Integer.parseInt(s.substring(0,2)));
                    element.setWeekday(RecurrenceRule.WEEKDAY.valueOf(s.substring(2)));
                } else if(s.length() == 3){
                    element.setOccurence(Integer.parseInt(s.substring(0, 1)));
                    element.setWeekday(RecurrenceRule.WEEKDAY.valueOf(s.substring(1)));
                } else if(s.length() == 2){
                    element.setWeekday(RecurrenceRule.WEEKDAY.valueOf(s));
                }
                retVal[i++] = element;
            }
        }
        return retVal;
    }

    private int[] convertStringArrayToIntegerArray(String[] numberArray) {
        int[] retVal = new int[numberArray.length];
        int i = 0;
        for(String s:numberArray){
            retVal[i++] = Integer.parseInt(s);
        }
        return retVal;
    }

    private StringData parseStringData(String stringData) {
        StringData retVal = null;
        if(stringData != null){
            retVal = new StringData();
            int valueSeparator = stringData.lastIndexOf(":");
            if(valueSeparator != 0){
                retVal.setString(stringData.substring(valueSeparator+1));
                for(String s:stringData.substring(0,valueSeparator).split(";")){
                    if(s.startsWith("LANGUAGE=")){
                        retVal.setLanguage(s.substring(s.indexOf("=")+1));
                    } else if(s.startsWith("ALTREP=")){
                        retVal.setAltrep(s.substring(s.indexOf("=")+1).replace("\"",""));
                    }
                }
            } else {
                retVal.setString(stringData);
            }

        }
        return retVal;
    }

    private DateData parseDateData(String dateDataString){
        DateData retVal = null;
        if(dateDataString != null){
            retVal = new DateData();
            int indexOfColon = dateDataString.lastIndexOf(":");
            if(indexOfColon > 0){
                retVal.setValue(dateDataString.substring(indexOfColon+1));
                for(String s: dateDataString.substring(0,indexOfColon).split(";")){
                    if(s.startsWith("VALUE")){
                        retVal.setValue_type(RecurrenceID.VALUE_TYPE.getEnum(s.split("=")[1]));
                    } else if(s.startsWith("TZID")){
                        retVal.setTZID(s.split("=")[1]);
                    }
                }
            } else {
               retVal.setValue(dateDataString);
            }
        }
        return retVal;
    }

    private RecurrenceID parseRecurrenceID(String recurrenceString) {
        RecurrenceID recurrenceID = null;
        if(recurrenceString != null){
            recurrenceID = (RecurrenceID) parseDateData(recurrenceString);
            int indexOfColon = recurrenceString.lastIndexOf(":");
            for(String s: recurrenceString.substring(0,indexOfColon).split(";")){
                if(s.startsWith("RANGE")){
                    recurrenceID.setRange(RecurrenceID.RANGE.valueOf(s.split("=")[1]));
                }
            }
        }
        return recurrenceID;
    }

    private Position parseGeo(String geoString){
        Position pos = null;
        if(geoString != null){
            String[] geo = geoString.split(";");
            pos = new Position(Float.parseFloat(geo[0]),Float.parseFloat(geo[1]));
        }
        return pos;
    }

    private Organizer parseOrganizer(String organizerString) {
        //ORGANIZER;CN=JohnSmith;DIR="ldap://example.com:6666/o=DC%20Ass
        //ociates,c=US???(cn=John%20Smith)":mailto:jsmith@example.com
       Organizer organizer = new Organizer();
        String mailTo = null;
        if(organizerString.contains(":mailto:")){
            mailTo = ":mailto:";
        } else if(organizerString.contains(":MAILTO:")) {
            mailTo = ":MAILTO:";
        }

        int indexOfCalAddress = organizerString.lastIndexOf(mailTo);
        String[] arr = organizerString.substring(0,indexOfCalAddress).split(";");
        organizer.setCalAddress(organizerString.split(mailTo)[1]);
        for(String s: arr){
            if(s.startsWith("CN")){
                organizer.setCommonName(s.substring(s.indexOf("=") + 1));
            } else if(s.startsWith("DIR")){
                organizer.setDirectory(s.substring(s.indexOf("=") + 1));
            } else if(s.startsWith("SENT-BY")){
                organizer.setSentBy(s.substring(s.indexOf("=") + 1));
            } else if(s.startsWith("LANGUAGE")){
                organizer.setLanguage(s.substring(s.indexOf("=")+1));
            }
        }
        return organizer;
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
