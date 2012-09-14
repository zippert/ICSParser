package se.pausemode.ICSHandler;

public class RelatedToData {

    public enum RELTYPE_TYPE{
        PARENT,
        CHILD,
        SIBLING
    }

    private RELTYPE_TYPE RELTYPE;
    private String VALUE;

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }

    public RELTYPE_TYPE getRELTYPE() {
        return RELTYPE;
    }

    public void setRELTYPE(RELTYPE_TYPE RELTYPE) {
        this.RELTYPE = RELTYPE;
    }

    @Override
    public String toString() {
        return "RelatedToData{" +
                "RELTYPE=" + RELTYPE +
                ", VALUE='" + VALUE + '\'' +
                '}';
    }
}
