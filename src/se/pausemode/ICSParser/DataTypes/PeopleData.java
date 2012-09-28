package se.pausemode.ICSParser.DataTypes;

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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        PeopleData that = (PeopleData) o;

        if (!Arrays.equals(members, that.members)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return members != null ? Arrays.hashCode(members) : 0;
    }
}