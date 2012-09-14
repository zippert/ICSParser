package se.pausemode.ICSHandler;

public class WeekDayNum{

    private int occurence;
    private RecurrenceRule.WEEKDAY weekday;

    public int getOccurence() {
        return occurence;
    }

    public void setOccurence(int occurence) {
        this.occurence = occurence;
    }

    public RecurrenceRule.WEEKDAY getWeekday() {
        return weekday;
    }

    public void setWeekday(RecurrenceRule.WEEKDAY weekday) {
        this.weekday = weekday;
    }

    @Override
    public String toString() {
        return "WeekDayNum{" +
                "occurence=" + occurence +
                ", weekday=" + weekday +
                '}';
    }
}