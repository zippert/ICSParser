package se.pausemode.ICSParser.DataTypes;

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

    public String getAltrep() {
        return altrep;
    }

    public void setAltrep(String altrep) {
        this.altrep = altrep;
    }

    @Override
    public String toString() {
        return "StringData{" +
                "string='" + string + '\'' +
                ", language='" + language + '\'' +
                ", altrep='" + altrep + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringData that = (StringData) o;

        if (altrep != null ? !altrep.equals(that.altrep) : that.altrep != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (string != null ? !string.equals(that.string) : that.string != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = string != null ? string.hashCode() : 0;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (altrep != null ? altrep.hashCode() : 0);
        return result;
    }
}
