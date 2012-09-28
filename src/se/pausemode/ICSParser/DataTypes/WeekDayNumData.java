package se.pausemode.ICSParser.DataTypes;

public class WeekDayNumData {

    private int occurence;
    private RecurrenceRuleData.WEEKDAY weekday;

    public WeekDayNumData(){};

    public WeekDayNumData(RecurrenceRuleData.WEEKDAY weekDay){
        this.weekday = weekDay;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeekDayNumData that = (WeekDayNumData) o;

        if (occurence != that.occurence) return false;
        if (weekday != that.weekday) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = occurence;
        result = 31 * result + (weekday != null ? weekday.hashCode() : 0);
        return result;
    }
}