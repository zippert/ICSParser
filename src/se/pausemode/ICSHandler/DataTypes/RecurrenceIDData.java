package se.pausemode.ICSHandler.DataTypes;


import se.pausemode.ICSHandler.DataTypes.DateData;

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
