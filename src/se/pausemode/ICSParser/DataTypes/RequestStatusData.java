package se.pausemode.ICSParser.DataTypes;

public class RequestStatusData {
    private String language;
    private String statCode;
    private String statDesc;
    private String extData;

    @Override
    public String toString() {
        return "RequestStatusData{" +
                "language='" + language + '\'' +
                ", statCode='" + statCode + '\'' +
                ", statDesc='" + statDesc + '\'' +
                ", extData='" + extData + '\'' +
                '}';
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatCode() {
        return statCode;
    }

    public void setStatCode(String statCode) {
        this.statCode = statCode;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }

    public String getStatDesc() {
        return statDesc;
    }

    public void setStatDesc(String statDesc) {
        this.statDesc = statDesc;
    }
}
