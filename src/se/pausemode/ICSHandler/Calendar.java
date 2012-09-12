package se.pausemode.ICSHandler;

/**
 * Class definined acording to RCF5545 (VEVENT only)
 */
public class Calendar {

    /** Mandatory **/
    private String DTSTAMP;
    private DateData DTSTART;
    /** Optional **/
    private String CLASS;
    private String CREATED;
    private StringData DESCRIPTION;
    private Organizer ORGANIZER;
    private Position GEO;
    private String LASTMODIFIED;
    private StringData LOCATION;
    private Integer PRIORITY;
    private Integer SEQUENCE;
    private EventStatus STATUS;
    private StringData SUMMARY;
    private Transparency TRANSP;
    private String URL;
    private RecurrenceID RECURRENCEID;
    private RecurrenceRule RECURRENCERULE;

    /** Either DTEND *or* DURATION */
    private DateData DTEND;
    private DurationData DURATION;

    /** Optional **/
    private AttachData ATTACH;
    private AttendeeData ATTENDEE;
    private StringData CATEGORIES;

    public enum EventStatus{
        TENTATIVE,
        CONFIRMED,
        CANCELLED
    }

    public enum Transparency{
        OPAQUE,
        TRANSPARENT
    }

    public String toString(){
        return "DTSTAMP: " + this.getDTSTAMP() + "\n" +
                "DTSTART: " + this.getDTSTART() + "\n" +

                "CLASS: " + this.getCLASS() + "\n" +
                "CREATED: " + this.getCREATED() + "\n" +
                "DESCRIPTION: " + this.getDESCRIPTION() + "\n" +
                "GEO: " + this.getGEO() + "\n" +
                "LAST-MODIFIED: " + this.getLASTMODIFIED() + "\n" +
                "LOCATION: " + this.getLOCATION() + "\n" +
                "ORGANIZER: " + this.getORGANIZER() + "\n" +
                "PRIORITY: " + this.getPRIORITY() + "\n" +
                "SEQUENCE: " + this.getSEQUENCE() + "\n" +
                "STATUS: " + this.getSTATUS() + "\n" +
                "SUMMARY: " + this.getSUMMARY() + "\n" +
                "TRANSP: " + this.getTRANSP() + "\n" +
                "URL: " + this.getURL() + "\n" +
                "RECURRENCE-ID: " + this.getRECURRENCEID() + "\n" +
                "RECURRENCERULE: " + this.getRECURRENCERULE() + "\n" +
                "DTEND: " + this.getDTEND() + "\n" +
                "DURATION: " + this.getDURATION() + "\n" +
                "ATTACH: " + this.getATTACH() + "\n" +
                "ATTENDEE: " + this.getATTENDEE() + "\n" +
                "CATEGORIES: " + this.getCATEGORIES() + "\n";
    }


    public String getDTSTAMP() {
        return DTSTAMP;
    }

    public void setDTSTAMP(String DTSTAMP) {
        this.DTSTAMP = DTSTAMP;
    }

    public DateData getDTSTART() {
        return DTSTART;
    }

    public void setDTSTART(DateData DTSTART) {
        this.DTSTART = DTSTART;
    }

    public DateData getDTEND() {
        return DTEND;
    }

    public void setDTEND(DateData DTEND) {
        this.DTEND = DTEND;
    }

    public String getCLASS() {
        return CLASS;
    }

    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public String getCREATED() {
        return CREATED;
    }

    public void setCREATED(String CREATED) {
        this.CREATED = CREATED;
    }

    public StringData getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(StringData DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public Organizer getORGANIZER() {
        return ORGANIZER;
    }

    public void setORGANIZER(Organizer ORGANIZER) {
        this.ORGANIZER = ORGANIZER;
    }

    public Position getGEO() {
        return GEO;
    }

    public void setGEO(Position GEO) {
        this.GEO = GEO;
    }

    public String getLASTMODIFIED() {
        return LASTMODIFIED;
    }

    public void setLASTMODIFIED(String LASTMODIFIED) {
        this.LASTMODIFIED = LASTMODIFIED;
    }

    public StringData getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(StringData LOCATION) {
        this.LOCATION = LOCATION;
    }

    public Integer getPRIORITY() {
        return PRIORITY;
    }

    public void setPRIORITY(Integer PRIORITY) {
        if(PRIORITY < 0 || PRIORITY > 9){
            throw new IndexOutOfBoundsException("Priority set outside allowed interval.");
        }
        this.PRIORITY = PRIORITY;
    }

    public Integer getSEQUENCE() {
        return SEQUENCE;
    }

    public void setSEQUENCE(Integer SEQUENCE) {
        this.SEQUENCE = SEQUENCE;
    }

    public EventStatus getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(EventStatus STATUS) {
        this.STATUS = STATUS;
    }

    public StringData getSUMMARY() {
        return SUMMARY;
    }

    public void setSUMMARY(StringData SUMMARY) {
        this.SUMMARY = SUMMARY;
    }

    public Transparency getTRANSP() {
        return TRANSP;
    }

    public void setTRANSP(Transparency TRANSP) {
        this.TRANSP = TRANSP;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public RecurrenceID getRECURRENCEID() {
        return RECURRENCEID;
    }

    public void setRECURRENCEID(RecurrenceID RECURRENCEID) {
        this.RECURRENCEID = RECURRENCEID;
    }

    public RecurrenceRule getRECURRENCERULE() {
        return RECURRENCERULE;
    }

    public void setRECURRENCERULE(RecurrenceRule RECURRENCERULE) {
        this.RECURRENCERULE = RECURRENCERULE;
    }

    public DurationData getDURATION() {
        return DURATION;
    }

    public void setDURATION(DurationData DURATION) {
        this.DURATION = DURATION;
    }

    public AttachData getATTACH() {
        return ATTACH;
    }

    public void setATTACH(AttachData ATTACH) {
        this.ATTACH = ATTACH;
    }

    public AttendeeData getATTENDEE() {
        return ATTENDEE;
    }

    public void setATTENDEE(AttendeeData ATTENDEE) {
        this.ATTENDEE = ATTENDEE;
    }

    public StringData getCATEGORIES() {
        return CATEGORIES;
    }

    public void setCATEGORIES(StringData CATEGORIES) {
        this.CATEGORIES = CATEGORIES;
    }
}
