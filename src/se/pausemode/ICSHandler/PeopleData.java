package se.pausemode.ICSHandler;

import java.util.Arrays;

public class PeopleData {

    private String[] members;

    @Override
    public String toString() {
        return "PeopleData{" +
                "members=" + (members == null ? null : Arrays.asList(members)) +
                '}';
    }

    public void setMembers(String[] members) {
        this.members = members;
    }
    public String[] getMembers() {
        return members;
    }
}