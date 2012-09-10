package se.pausemode.ICSHandler;

public class RecurrenceRule {
    public enum FREQVALUE{
        SECONDLY,
        MINUTELY,
        HOURLY,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    public enum WEEKDAY{
        SU,
        MO,
        TU,
        WE,
        TH,
        FR,
        SA
    }

    private FREQVALUE FREQ;
    private DateData UNTIL;
    private int COUNT;
    private int INTERVAL;
    private int[] BYSECOND;
    private int[] BYMINUTE;
    private int[] BYHOUR;
    private WeekDayNum[] BYDAY;
    private int[] BYMONTHDAY;
    private int[] BYYEARDAY;
    private int[] BYWEEKNO;
    private int[] BYMONTH;
    private int[] BYSETPOS;

    public FREQVALUE getFREQ() {
        return FREQ;
    }

    public void setFREQ(FREQVALUE FREQ) {
        this.FREQ = FREQ;
    }

    public DateData getUNTIL() {
        return UNTIL;
    }

    public void setUNTIL(DateData UNTIL) {
        this.UNTIL = UNTIL;
    }

    public int getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(int COUNT) {
        this.COUNT = COUNT;
    }

    public int getINTERVAL() {
        return INTERVAL;
    }

    public void setINTERVAL(int INTERVAL) {
        this.INTERVAL = INTERVAL;
    }

    public int[] getBYSECOND() {
        return BYSECOND;
    }

    public void setBYSECOND(int[] BYSECOND) {
        this.BYSECOND = BYSECOND;
    }

    public int[] getBYMINUTE() {
        return BYMINUTE;
    }

    public void setBYMINUTE(int[] BYMINUTE) {
        this.BYMINUTE = BYMINUTE;
    }

    public int[] getBYHOUR() {
        return BYHOUR;
    }

    public void setBYHOUR(int[] BYHOUR) {
        this.BYHOUR = BYHOUR;
    }

    public WeekDayNum[] getBYDAY() {
        return BYDAY;
    }

    public void setBYDAY(WeekDayNum[] BYDAY) {
        this.BYDAY = BYDAY;
    }

    public int[] getBYMONTHDAY() {
        return BYMONTHDAY;
    }

    public void setBYMONTHDAY(int[] BYMONTHDAY) {
        this.BYMONTHDAY = BYMONTHDAY;
    }

    public int[] getBYYEARDAY() {
        return BYYEARDAY;
    }

    public void setBYYEARDAY(int[] BYYEARDAY) {
        this.BYYEARDAY = BYYEARDAY;
    }

    public int[] getBYWEEKNO() {
        return BYWEEKNO;
    }

    public void setBYWEEKNO(int[] BYWEEKNO) {
        this.BYWEEKNO = BYWEEKNO;
    }

    public int[] getBYMONTH() {
        return BYMONTH;
    }

    public void setBYMONTH(int[] BYMONTH) {
        this.BYMONTH = BYMONTH;
    }

    public int[] getBYSETPOS() {
        return BYSETPOS;
    }

    public void setBYSETPOS(int[] BYSETPOS) {
        this.BYSETPOS = BYSETPOS;
    }

    public WEEKDAY getWKST() {
        return WKST;
    }

    public void setWKST(WEEKDAY WKST) {
        this.WKST = WKST;
    }

    private WEEKDAY WKST;


    public String toString(){
        StringBuffer sb = new StringBuffer();
        if(BYDAY != null){
            sb.append("[");
            for(WeekDayNum i: BYDAY){
                sb.append("("+i.getOccurence()+";" + i.getWeekday()+")");
            }
            sb.append("]");
        } else {
            sb.append("null");
        }
        return "FREQ: " + FREQ
                + ", UNTIL: " + UNTIL
                + ", COUNT: " + COUNT
                + ", INTERVAL: " + INTERVAL
                + ", BYSECOND: " + BYSECOND
                + ", BYMINUTE: " + BYMINUTE
                + ", BYHOUR: " + BYHOUR
                + ", BYDAY: " + sb.toString()
                + ", BYMONTHDAY: " + BYMONTHDAY
                + ", BYYEARDAY: " + BYYEARDAY
                + ", BYWEEKNO: " + BYWEEKNO
                + ", BYMONTH: " + BYMONTH
                + ", BYSETPOS: " + BYSETPOS
                + ", WKST: " + WKST;
    }

}
