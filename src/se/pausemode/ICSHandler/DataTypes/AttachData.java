package se.pausemode.ICSHandler.DataTypes;

public class AttachData {
    private String FMTTYPE;
    private String ENCODING;
    private String VALUE;
    private String URI;
    private String BINARY;

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

    public String getBINARY() {
        return BINARY;
    }

    public void setBINARY(String BINARY) {
        this.BINARY = BINARY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttachData that = (AttachData) o;

        if (BINARY != null ? !BINARY.equals(that.BINARY) : that.BINARY != null) return false;
        if (ENCODING != null ? !ENCODING.equals(that.ENCODING) : that.ENCODING != null) return false;
        if (FMTTYPE != null ? !FMTTYPE.equals(that.FMTTYPE) : that.FMTTYPE != null) return false;
        if (URI != null ? !URI.equals(that.URI) : that.URI != null) return false;
        if (VALUE != null ? !VALUE.equals(that.VALUE) : that.VALUE != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = FMTTYPE != null ? FMTTYPE.hashCode() : 0;
        result = 31 * result + (ENCODING != null ? ENCODING.hashCode() : 0);
        result = 31 * result + (VALUE != null ? VALUE.hashCode() : 0);
        result = 31 * result + (URI != null ? URI.hashCode() : 0);
        result = 31 * result + (BINARY != null ? BINARY.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AttachData{" +
                "FMTTYPE='" + FMTTYPE + '\'' +
                ", ENCODING='" + ENCODING + '\'' +
                ", VALUE='" + VALUE + '\'' +
                ", URI='" + URI + '\'' +
                ", BINARY='" + BINARY + '\'' +
                '}';
    }
}
