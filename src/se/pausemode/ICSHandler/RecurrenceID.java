package se.pausemode.ICSHandler;



public class RecurrenceID extends DateData {


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
