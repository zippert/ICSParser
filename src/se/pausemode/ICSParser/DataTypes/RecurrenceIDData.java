package se.pausemode.ICSParser.DataTypes;


public class RecurrenceIDData extends DateData {


    public enum RANGE {
        THISANDFUTURE;
    }

    private RANGE range;

    public RANGE getRange() {
        return range;
    }

    public void setRange(RANGE range) {
        this.range = range;
    }

    public String toString(){
        return super.toString() + ", RANGE: " + this.range;
    }
}
