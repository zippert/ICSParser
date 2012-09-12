package se.pausemode.ICSHandler;


public class AttendeeData {

    private PeopleData MEMBER;
    private ROLE_TYPES ROLE;
    private PARTSTAT_TYPE PARTSTAT;
    private Boolean RSVP;
    private PeopleData DELEGATED_TO;
    private PeopleData DELEGATED_FROM;
    private PeopleData SENT_BY;
    private String CN;
    private String DIR;
    private String LANGUAGE;
    private String URI;
    private String CALADDRESS;
    private CUTYPE_TYPES CUTYPE;


    public enum CUTYPE_TYPES{
        INDIVIDUAL,
        GROUP,
        RESOURCE,
        ROOM,
        UNKNOWN
    }

    public enum ROLE_TYPES {
        CHAIR,
        REQ_PARTICIPANT,
        OPT_PARTICIPANT,
        NON_PARTICIPANT;

        public static ROLE_TYPES getEnum(String value){
            ROLE_TYPES retVal = null;
            if(value != null){
                if(value.equals("CHAIR")){
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

    public enum PARTSTAT_TYPE{
        NEEDS_ACTION,
        ACCEPTED,
        DECLINED,
        TENTATIVE,
        DELEGATED;

        public static PARTSTAT_TYPE getEnum(String value){
            PARTSTAT_TYPE retVal = null;
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



    public String getCALADDRESS() {
        return CALADDRESS;
    }

    public void setCALADDRESS(String CALADDRESS) {
        this.CALADDRESS = CALADDRESS;
    }

    public CUTYPE_TYPES getCUTYPE() {
        return CUTYPE;
    }

    public void setCUTYPE(CUTYPE_TYPES CUTYPE) {
        this.CUTYPE = CUTYPE;
    }

    public PeopleData getMEMBER() {
        return MEMBER;
    }

    public void setMEMBER(PeopleData MEMBER) {
        this.MEMBER = MEMBER;
    }

    public ROLE_TYPES getROLE() {
        return ROLE;
    }

    public void setROLE(ROLE_TYPES ROLE_TYPES) {
        this.ROLE = ROLE_TYPES;
    }

    public PARTSTAT_TYPE getPARTSTAT() {
        return PARTSTAT;
    }

    public void setPARTSTAT(PARTSTAT_TYPE PARTSTAT) {
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

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String toString(){
        return "CALADDRESS: " + CALADDRESS + ", " +
                "CUTYPE: " + CUTYPE + ", " +
                "MEMBER: " + MEMBER + ", " +
                "ROLE_TYPES: " + ROLE + ", " +
                "PARTSTAT: " + PARTSTAT + ", " +
                "RSVP: " + RSVP + ", " +
                "DELEGATED-TO: " + DELEGATED_TO + ", " +
                "DELEGATED-FROM: " + DELEGATED_FROM + ", " +
                "SENT-BY: " + SENT_BY + ", " +
                "CN: " + CN + ", " +
                "DIR: " + DIR + ", " +
                "LANGUAGE: " + LANGUAGE + ", " +
                "URI: " + URI;

    }


}
