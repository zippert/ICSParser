package se.pausemode.ICSHandler;

/**
 * Class used generically to represent text data. Includes both the string as
 * well as any language-data provided.
 */
public class StringData {
    private String string;
    private String language;

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

    public String toString(){
        return "Value: " + this.string + ", Language: " + this.language;
    }
}
