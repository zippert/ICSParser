package se.pausemode.ICSHandler.DataTypes;

/**
 * Class used generically to represent text data. Includes both the string as
 * well as any language-data provided.
 */
public class StringData {
    private String string;
    private String language;
    private String altrep;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "StringData{" +
                "string='" + string + '\'' +
                ", language='" + language + '\'' +
                ", altrep='" + altrep + '\'' +
                '}';
    }

    public String getAltrep() {
        return altrep;
    }

    public void setAltrep(String altrep) {
        this.altrep = altrep;
    }
}
