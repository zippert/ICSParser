package se.pausemode.ICSHandler;

public class AttachData {
    private String FMTTYPE;
    private String ENCODING;
    private String VALUE;
    private String URI;

    public String getFMTTYPE() {
        return FMTTYPE;
    }

    public void setFMTTYPE(String FMTTYPE) {
        this.FMTTYPE = FMTTYPE;
    }

    public String getENCODING() {
        return ENCODING;
    }

    public void setENCODING(String ENCODING) {
        this.ENCODING = ENCODING;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    @Override
    public String toString() {
        return "AttachData{" +
                "FMTTYPE='" + FMTTYPE + '\'' +
                ", ENCODING='" + ENCODING + '\'' +
                ", VALUE='" + VALUE + '\'' +
                ", URI='" + URI + '\'' +
                '}';
    }
}
