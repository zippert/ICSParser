package se.pausemode.ICSHandler;

public class RequestStatusData {
    private String language;
    private String statCode;
    private String statDesc;
    private String extData;

    public String toString(){
        return "Language: " + language +
                ", statCode: " + statCode +
                ", statDesc: " + statDesc +
                ", extData: " + extData;
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
