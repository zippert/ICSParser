package se.pausemode.ICSHandler.DataTypes;

import se.pausemode.ICSHandler.DataTypes.RecurrenceRuleData;

public class WeekDayNumData {

    private int occurence;
    private RecurrenceRuleData.WEEKDAY weekday;

    public int getOccurence() {
        return occurence;
    }

    public void setOccurence(int occurence) {
        this.occurence = occurence;
    }

    public RecurrenceRuleData.WEEKDAY getWeekday() {
        return weekday;
    }

    public void setWeekday(RecurrenceRuleData.WEEKDAY weekday) {
        this.weekday = weekday;
    }

    @Override
    public String toString() {
        return "WeekDayNumData{" +
                "occurence=" + occurence +
                ", weekday=" + weekday +
                '}';
    }
}