package se.pausemode.ICSHandler.DataTypes;

import java.util.Arrays;

public class RecurrenceRuleData {
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
    private WeekDayNumData[] BYDAY;
    private int[] BYMONTHDAY;
    private int[] BYYEARDAY;
    private int[] BYWEEKNO;
    private int[] BYMONTH;
    private int[] BYSETPOS;
    private WEEKDAY WKST;

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

    public WeekDayNumData[] getBYDAY() {
        return BYDAY;
    }

    public void setBYDAY(WeekDayNumData[] BYDAY) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecurrenceRuleData that = (RecurrenceRuleData) o;

        if (COUNT != that.COUNT) return false;
        if (INTERVAL != that.INTERVAL) return false;
        if (!Arrays.equals(BYDAY, that.BYDAY)) return false;
        if (!Arrays.equals(BYHOUR, that.BYHOUR)) return false;
        if (!Arrays.equals(BYMINUTE, that.BYMINUTE)) return false;
        if (!Arrays.equals(BYMONTH, that.BYMONTH)) return false;
        if (!Arrays.equals(BYMONTHDAY, that.BYMONTHDAY)) return false;
        if (!Arrays.equals(BYSECOND, that.BYSECOND)) return false;
        if (!Arrays.equals(BYSETPOS, that.BYSETPOS)) return false;
        if (!Arrays.equals(BYWEEKNO, that.BYWEEKNO)) return false;
        if (!Arrays.equals(BYYEARDAY, that.BYYEARDAY)) return false;
        if (FREQ != that.FREQ) return false;
        if (UNTIL != null ? !UNTIL.equals(that.UNTIL) : that.UNTIL != null) return false;
        if (WKST != that.WKST) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = FREQ != null ? FREQ.hashCode() : 0;
        result = 31 * result + (UNTIL != null ? UNTIL.hashCode() : 0);
        result = 31 * result + COUNT;
        result = 31 * result + INTERVAL;
        result = 31 * result + (BYSECOND != null ? Arrays.hashCode(BYSECOND) : 0);
        result = 31 * result + (BYMINUTE != null ? Arrays.hashCode(BYMINUTE) : 0);
        result = 31 * result + (BYHOUR != null ? Arrays.hashCode(BYHOUR) : 0);
        result = 31 * result + (BYDAY != null ? Arrays.hashCode(BYDAY) : 0);
        result = 31 * result + (BYMONTHDAY != null ? Arrays.hashCode(BYMONTHDAY) : 0);
        result = 31 * result + (BYYEARDAY != null ? Arrays.hashCode(BYYEARDAY) : 0);
        result = 31 * result + (BYWEEKNO != null ? Arrays.hashCode(BYWEEKNO) : 0);
        result = 31 * result + (BYMONTH != null ? Arrays.hashCode(BYMONTH) : 0);
        result = 31 * result + (BYSETPOS != null ? Arrays.hashCode(BYSETPOS) : 0);
        result = 31 * result + (WKST != null ? WKST.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RecurrenceRuleData{" +
                "FREQ=" + FREQ +
                ", UNTIL=" + UNTIL +
                ", COUNT=" + COUNT +
                ", INTERVAL=" + INTERVAL +
                ", BYSECOND=" + BYSECOND +
                ", BYMINUTE=" + BYMINUTE +
                ", BYHOUR=" + BYHOUR +
                ", BYDAY=" + (BYDAY == null ? null : Arrays.asList(BYDAY)) +
                ", BYMONTHDAY=" + BYMONTHDAY +
                ", BYYEARDAY=" + BYYEARDAY +
                ", BYWEEKNO=" + BYWEEKNO +
                ", BYMONTH=" + BYMONTH +
                ", BYSETPOS=" + BYSETPOS +
                ", WKST=" + WKST +
                '}';
    }

}
