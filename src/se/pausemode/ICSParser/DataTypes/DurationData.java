package se.pausemode.ICSParser.DataTypes;


public class DurationData {

    public enum DURATION_TYPE {
        DUR_DATE,
        DUR_TIME,
        DUR_WEEK
    }

    private boolean posValue;
    private DURATION_TYPE durationType;
    private int day;
    private int week;
    private int hour;
    private int minute;
    private int second;


    public boolean isPosValue() {
        return posValue;
    }

    public void setPosValue(boolean posValue) {
        this.posValue = posValue;
    }

    public DURATION_TYPE getDurationType() {
        return durationType;
    }

    public void setDurationType(DURATION_TYPE durationType) {
        this.durationType = durationType;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "DurationData{" +
                "posValue=" + posValue +
                ", durationType=" + durationType +
                ", day=" + day +
                ", week=" + week +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DurationData that = (DurationData) o;

        if (day != that.day) return false;
        if (hour != that.hour) return false;
        if (minute != that.minute) return false;
        if (posValue != that.posValue) return false;
        if (second != that.second) return false;
        if (week != that.week) return false;
        if (durationType != that.durationType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (posValue ? 1 : 0);
        result = 31 * result + (durationType != null ? durationType.hashCode() : 0);
        result = 31 * result + day;
        result = 31 * result + week;
        result = 31 * result + hour;
        result = 31 * result + minute;
        result = 31 * result + second;
        return result;
    }
}
