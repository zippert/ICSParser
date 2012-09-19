package se.pausemode.ICSHandler.Util;


import se.pausemode.ICSHandler.DataTypes.*;

public class ParserUtil {

    public static RelatedToData parseRelatedToData(String relatedToString) {
        RelatedToData retVal = null;

        if(relatedToString != null){
            retVal = new RelatedToData();
            int indexOfColon = -1;
            if(relatedToString.contains(":")){
                indexOfColon = relatedToString.indexOf(":");
                for(String arg:relatedToString.substring(0, indexOfColon).split(";")){
                    if(arg.startsWith("RELTYPE=")){
                        try {
                        retVal.setRELTYPE(RelatedToData.RELTYPE_TYPE.valueOf(arg.substring(arg.indexOf("=")+1)));
                        } catch (IllegalArgumentException iae){
                            retVal.setRELTYPE(RelatedToData.RELTYPE_TYPE.OTHER);
                        }
                    }
                }
            }
            retVal.setVALUE(relatedToString.substring(indexOfColon+1));
        }
        return retVal;
    }

    public static RequestStatusData parseRequestStatusData(String requestStatusString) {
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

    public static StringData parseCategoriesData(String categoriesString) {
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

    public static AttendeeData parseAttendeeData(String attendeeString) {
        AttendeeData retVal = null;

        if(attendeeString != null){
            retVal = new AttendeeData();

            int indexOfURI = attendeeString.toLowerCase().lastIndexOf(":mailto:");
            String[] arr = attendeeString.substring(0,indexOfURI).split(";");
            retVal.setURI(attendeeString.substring(indexOfURI+1));
            for(String s:arr){
                if(s.startsWith("CUTYPE=")){
                    try{
                    retVal.setCUTYPE(AttendeeData.CUTYPE_TYPES.valueOf(s.substring(s.indexOf("=") + 1)));
                    } catch (IllegalArgumentException iae){
                        retVal.setCUTYPE(AttendeeData.CUTYPE_TYPES.OTHER);
                    }

                } else if(s.startsWith("MEMBER=")){
                    retVal.setMEMBER(parsePeopleData(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("ROLE=")){
                    try{
                        retVal.setROLE(AttendeeData.ROLE_TYPES.getEnum(s.substring(s.indexOf("=")+1)));
                    } catch (IllegalArgumentException iae){
                        retVal.setROLE(AttendeeData.ROLE_TYPES.OTHER);
                    }
                } else if(s.startsWith("PARTSTAT=")){
                    try{
                        retVal.setPARTSTAT(AttendeeData.PARTSTAT_TYPE.getEnum(s.substring(s.indexOf("=")+1)));
                    } catch (IllegalArgumentException iae){
                        retVal.setPARTSTAT(AttendeeData.PARTSTAT_TYPE.OTHER);
                    }
                } else if(s.startsWith("RSVP=")){
                    retVal.setRSVP(Boolean.parseBoolean(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("DELEGATED-TO=")){
                    retVal.setDELEGATED_TO(parsePeopleData(s.substring(s.indexOf("=") + 1)));
                } else if(s.startsWith("DELEGATED-FROM=")){
                    retVal.setDELEGATED_FROM(parsePeopleData(s.substring(s.indexOf("=")+1)));
                } else if(s.startsWith("SENT-BY=")){
                    retVal.setSENT_BY(parsePeopleData(s.substring(s.indexOf("=") +1)));
                } else if(s.startsWith("CN=")){
                    String value = s.substring(s.indexOf("=")+1);
                    retVal.setCN(value.substring(1,value.length()-1));
                } else if(s.startsWith("DIR=")){
                    retVal.setDIR(s.substring(s.indexOf("=")+1).replace("\"",""));
                } else if(s.startsWith("LANGUAGE=")){
                    retVal.setLANGUAGE(s.substring(s.indexOf("=")+1));
                }
            }
        }

        return retVal;
    }

    public static PeopleData parsePeopleData(String peopleDataString){
        PeopleData pd = null;
        if(peopleDataString != null){
            pd = new PeopleData();
            String[] members = peopleDataString.split(",");
            for(int i = 0; i < members.length; i++){
                //Remove trailing/ending "
                members[i] = members[i].substring(1,members[i].length()-1);
            }
            pd.setMembers(members);
        }
        return pd;
    }

    public static AttachData parseAttachData(String attachString) {
        AttachData retVal = null;
        if(attachString != null){
            retVal = new AttachData();
            if(!attachString.contains("=")){
                retVal.setURI(attachString);
            } else {
                String value = null;
                boolean isBinaryValue = false;
                for(String s: attachString.split(";")){
                    //The logic of separating valu/uri like this depends upon existing standard default values.
                    if(s.contains(":")){
                        value = s.substring(s.indexOf(":")+1);
                        s = s.substring(0,s.indexOf(":"));
                    }

                    if(s.startsWith("FMTTYPE=")){
                        retVal.setFMTTYPE(s.substring(s.indexOf("=")+1));
                    } else if(s.startsWith("ENCODING=")){
                        retVal.setENCODING(s.substring(s.indexOf("=")+1));
                    } else if(s.startsWith("VALUE=")){
                        retVal.setVALUE(s.substring(s.indexOf("=")+1));
                        isBinaryValue = true;
                    }

                    if(isBinaryValue){
                        retVal.setBINARY(value);
                    } else {
                        retVal.setURI(value);
                    }
                }
            }
        }
        return retVal;
    }

    public static DurationData parseDurationData(String durationString) {
        DurationData retVal = null;
        if(durationString != null){
            retVal = new DurationData();
            int currIndex = 0;
            //Remove any "other"-params we don't need.
            if(durationString.contains(":")){
                durationString = durationString.substring(durationString.indexOf(":")+1);
            }
            retVal.setPosValue(!(durationString.charAt(currIndex) == '-'));
            if(durationString.charAt(currIndex) == '+' || durationString.charAt(currIndex) == '-'){
                currIndex++;
            }
            if(durationString.charAt(currIndex++) != 'P'){
                throw new IllegalArgumentException();
            }
            if(durationString.charAt(currIndex) == 'T'){
                retVal.setDurationType(DurationData.DURATION_TYPE.DUR_TIME);
                currIndex++;
            } else if(durationString.charAt(currIndex+1) == 'D'){
                retVal.setDurationType(DurationData.DURATION_TYPE.DUR_DATE);
            } else if(durationString.charAt(currIndex+1) == 'W'){
                retVal.setDurationType(DurationData.DURATION_TYPE.DUR_WEEK);
            }

            StringBuffer value = new StringBuffer();
            Character c;
            while(currIndex < durationString.length()){
                c = durationString.charAt(currIndex++);
                while(Character.isDigit(c)){
                    value.append(c);
                    c = durationString.charAt(currIndex++);
                }

                int i = Integer.parseInt(value.toString());
                switch(c){
                    case 'W':
                        retVal.setWeek(i);
                        break;
                    case 'D':
                        retVal.setDay(i);
                        if(durationString.charAt(currIndex) == 'T'){
                            currIndex++;
                        }
                        break;
                    case 'H':
                        retVal.setHour(i);
                        break;
                    case 'M':
                        retVal.setMinute(i);
                        break;
                    case 'S':
                        retVal.setSecond(i);
                        break;
                }
                value = new StringBuffer();
            }
        }
        return retVal;
    }

    public static RecurrenceRuleData parseRecurrenceRule(String recurString) {
        RecurrenceRuleData retVal = null;
        if(recurString != null){
            if(recurString.contains(":")){
                recurString = recurString.substring(recurString.indexOf(":")+1);
            }
            retVal = new RecurrenceRuleData();
            for(String s: recurString.split(";")){
                if(s.startsWith("FREQ=")){
                    retVal.setFREQ(RecurrenceRuleData.FREQVALUE.valueOf(s.substring(s.indexOf("=")+1)));
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
                    retVal.setWKST(RecurrenceRuleData.WEEKDAY.valueOf(s.substring(s.indexOf("=") + 1)));
                }
            }
        }

        return retVal;
    }

    public static WeekDayNumData[] parseWeekDay(String[] weekDays) {
        WeekDayNumData[] retVal = null;

        if(weekDays != null){
            retVal = new WeekDayNumData[weekDays.length];
            int i = 0;
            for(String s : weekDays){
                WeekDayNumData element = new WeekDayNumData();
                if(s.length() == 4){
                    element.setOccurence(Integer.parseInt(s.substring(0,2)));
                    element.setWeekday(RecurrenceRuleData.WEEKDAY.valueOf(s.substring(2)));
                } else if(s.length() == 3){
                    element.setOccurence(Integer.parseInt(s.substring(0, 1)));
                    element.setWeekday(RecurrenceRuleData.WEEKDAY.valueOf(s.substring(1)));
                } else if(s.length() == 2){
                    element.setWeekday(RecurrenceRuleData.WEEKDAY.valueOf(s));
                }
                retVal[i++] = element;
            }
        }
        return retVal;
    }



    private static int[] convertStringArrayToIntegerArray(String[] numberArray) {
        int[] retVal = new int[numberArray.length];
        int i = 0;
        for(String s:numberArray){
            retVal[i++] = Integer.parseInt(s);
        }
        return retVal;
    }

    public static StringData parseStringData(String stringData) {
        StringData retVal = null;
        if(stringData != null){
            retVal = new StringData();
            int valueSeparator = stringData.lastIndexOf(":");
            if(valueSeparator > -1){
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

    public static DateData parseDateData(String dateDataString){
        DateData retVal = null;
        if(dateDataString != null){
            retVal = new DateData();
            int indexOfColon = dateDataString.lastIndexOf(":");
            if(indexOfColon > 0){
                retVal.setValue(dateDataString.substring(indexOfColon+1));
                for(String s: dateDataString.substring(0,indexOfColon).split(";")){
                    if(s.startsWith("VALUE")){
                        retVal.setValue_type(RecurrenceIDData.VALUE_TYPE.getEnum(s.split("=")[1]));
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

    public static RecurrenceIDData parseRecurrenceID(String recurrenceString) {
        RecurrenceIDData recurrenceIDData = null;
        if(recurrenceString != null){
            recurrenceIDData = (RecurrenceIDData) parseDateData(recurrenceString);
            int indexOfColon = recurrenceString.lastIndexOf(":");
            for(String s: recurrenceString.substring(0,indexOfColon).split(";")){
                if(s.startsWith("RANGE")){
                    recurrenceIDData.setRange(RecurrenceIDData.RANGE.valueOf(s.split("=")[1]));
                }
            }
        }
        return recurrenceIDData;
    }

    public static PositionData parseGeo(String geoString){
        PositionData pos = null;
        if(geoString != null){
            String[] geo = geoString.split(";");
            pos = new PositionData(Float.parseFloat(geo[0]),Float.parseFloat(geo[1]));
        }
        return pos;
    }

    public static OrganizerData parseOrganizer(String organizerString) {
        //ORGANIZER;CN=JohnSmith;DIR="ldap://example.com:6666/o=DC%20Ass
        //ociates,c=US???(cn=John%20Smith)":mailto:jsmith@example.com
        OrganizerData organizerData = new OrganizerData();
        String mailTo = null;
        if(organizerString.contains(":mailto:")){
            mailTo = ":mailto:";
        } else if(organizerString.contains(":MAILTO:")) {
            mailTo = ":MAILTO:";
        }

        int indexOfCalAddress = organizerString.lastIndexOf(mailTo);
        String[] arr = organizerString.substring(0,indexOfCalAddress).split(";");
        organizerData.setCalAddress(organizerString.split(mailTo)[1]);
        for(String s: arr){
            if(s.startsWith("CN")){
                organizerData.setCommonName(s.substring(s.indexOf("=") + 1));
            } else if(s.startsWith("DIR")){
                organizerData.setDirectory(s.substring(s.indexOf("=") + 1));
            } else if(s.startsWith("SENT-BY")){
                organizerData.setSentBy(s.substring(s.indexOf("=") + 1));
            } else if(s.startsWith("LANGUAGE")){
                organizerData.setLanguage(s.substring(s.indexOf("=")+1));
            }
        }
        return organizerData;
    }
}
