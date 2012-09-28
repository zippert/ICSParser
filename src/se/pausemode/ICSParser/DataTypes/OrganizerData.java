package se.pausemode.ICSParser.DataTypes;

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

    public String getCalAddress() {
        return calAddress;
    }

    public void setCalAddress(String calAddress) {
        this.calAddress = calAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizerData that = (OrganizerData) o;

        if (calAddress != null ? !calAddress.equals(that.calAddress) : that.calAddress != null) return false;
        if (commonName != null ? !commonName.equals(that.commonName) : that.commonName != null) return false;
        if (directory != null ? !directory.equals(that.directory) : that.directory != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (sentBy != null ? !sentBy.equals(that.sentBy) : that.sentBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commonName != null ? commonName.hashCode() : 0;
        result = 31 * result + (directory != null ? directory.hashCode() : 0);
        result = 31 * result + (sentBy != null ? sentBy.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (calAddress != null ? calAddress.hashCode() : 0);
        return result;
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
}
