package se.pausemode.ICSHandler.DataTypes;

public class OrganizerData {

    private String commonName;
    private String directory;
    private String sentBy;
    private String language;
    private String calAddress;

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "OrganizerData{" +
                "commonName='" + commonName + '\'' +
                ", directory='" + directory + '\'' +
                ", sentBy='" + sentBy + '\'' +
                ", language='" + language + '\'' +
                ", calAddress='" + calAddress + '\'' +
                '}';
    }

    public String getCalAddress() {
        return calAddress;
    }

    public void setCalAddress(String calAddress) {
        this.calAddress = calAddress;
    }
}
