package se.pausemode.ICSHandler;


public class AttendeeData {

    private PeopleData MEMBER;
    private ROLE ROLE;
    private PARTSTAT PARTSTAT;
    private Boolean RSVP;
    private PeopleData DELEGATED_TO;
    private PeopleData DELEGATED_FROM;
    private PeopleData SENT_BY;
    private String CN;
    private String DIR;
    private String LANGUAGE;

    public enum CUTYPE{
        INDIVIDUAL,
        GROUP,
        RESOURCE,
        ROOM,
        UNKNOWN
    }

    public enum ROLE{
        CHAIR,
        REQ_PARTICIPANT,
        OPT_PARTICIPANT,
        NON_PARTICIPANT;

        public static ROLE getEnum(String value){
            ROLE retVal = null;
            if(value != null){
                if(value.equals(CHAIR)){
                    return CHAIR;
                } else if(value.equals("REQ-PARTICIPANT")){
                    return REQ_PARTICIPANT;
                } else if(value.equals("OPT-PARTICIPANT")){
                    return OPT_PARTICIPANT;
                } else if(value.equals("NON_PARTICIPANT")){
                    return NON_PARTICIPANT;
                }
            } else {
                throw new IllegalArgumentException();
            }
            return retVal;
        }
    }

    public enum PARTSTAT{
        NEEDS_ACTION,
        ACCEPTED,
        DECLINED,
        TENTATIVE,
        DELEGATED;

        public static PARTSTAT getEnum(String value){
            PARTSTAT retVal = null;
            if(value != null){
                if(value.equals("NEEDS-ACTION")){
                    return NEEDS_ACTION;
                } else if(value.equals("ACCEPTED")){
                    return ACCEPTED;
                } else if(value.equals("DECLINED")){
                    return DECLINED;
                } else if(value.equals("TENTATIVE")){
                    return TENTATIVE;
                } else if(value.equals("DELEGATED")){
                    return DELEGATED;
                }
            } else {
                throw new IllegalArgumentException();
            }
            return retVal;
        }
    }

    private String CALADDRESS;
    private CUTYPE CUTYPE;

    public String getCALADDRESS() {
        return CALADDRESS;
    }

    public void setCALADDRESS(String CALADDRESS) {
        this.CALADDRESS = CALADDRESS;
    }

    public AttendeeData.CUTYPE getCUTYPE() {
        return CUTYPE;
    }

    public void setCUTYPE(AttendeeData.CUTYPE CUTYPE) {
        this.CUTYPE = CUTYPE;
    }

    public PeopleData getMEMBER() {
        return MEMBER;
    }

    public void setMEMBER(PeopleData MEMBER) {
        this.MEMBER = MEMBER;
    }

    public AttendeeData.ROLE getROLE() {
        return ROLE;
    }

    public void setROLE(AttendeeData.ROLE ROLE) {
        this.ROLE = ROLE;
    }

    public AttendeeData.PARTSTAT getPARTSTAT() {
        return PARTSTAT;
    }

    public void setPARTSTAT(AttendeeData.PARTSTAT PARTSTAT) {
        this.PARTSTAT = PARTSTAT;
    }

    public Boolean getRSVP() {
        return RSVP;
    }

    public void setRSVP(Boolean RSVP) {
        this.RSVP = RSVP;
    }

    public PeopleData getDELEGATED_TO() {
        return DELEGATED_TO;
    }

    public void setDELEGATED_TO(PeopleData DELEGATED_TO) {
        this.DELEGATED_TO = DELEGATED_TO;
    }

    public PeopleData getDELEGATED_FROM() {
        return DELEGATED_FROM;
    }

    public void setDELEGATED_FROM(PeopleData DELEGATED_FROM) {
        this.DELEGATED_FROM = DELEGATED_FROM;
    }

    public PeopleData getSENT_BY() {
        return SENT_BY;
    }

    public void setSENT_BY(PeopleData SENT_BY) {
        this.SENT_BY = SENT_BY;
    }

    public String getCN() {
        return CN;
    }

    public void setCN(String CN) {
        this.CN = CN;
    }

    public String getDIR() {
        return DIR;
    }

    public void setDIR(String DIR) {
        this.DIR = DIR;
    }

    public String getLANGUAGE() {
        return LANGUAGE;
    }

    public void setLANGUAGE(String LANGUAGE) {
        this.LANGUAGE = LANGUAGE;
    }


    public String toString(){
        return "CALADDRESS: " + CALADDRESS + ", " +
                "CUTYPE: " + CUTYPE + ", " +
                "MEMBER: " + MEMBER + ", " +
                "ROLE: " + ROLE + ", " +
                "PARTSTAT: " + PARTSTAT + ", " +
                "RSVP: " + RSVP + ", " +
                "DELEGATED-TO: " + DELEGATED_TO + ", " +
                "DELEGATED-FROM: " + DELEGATED_FROM + ", " +
                "SENT-BY: " + SENT_BY + ", " +
                "CN: " + CN + ", " +
                "DIR: " + DIR + ", " +
                "LANGUAGE: " + LANGUAGE;

    }


    class PeopleData {
        public String[] members;

        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for(String s: members){
                sb.append("("+ s + ")");
            }
            sb.append("]");

            return "Members: " + sb.toString();
        }
    }
}
