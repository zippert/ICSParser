package se.pausemode.ICSHandler;

class PeopleData {

    private String[] members;

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(String s: members){
            sb.append("("+ s + ")");
        }
        sb.append("]");

        return "Members: " + sb.toString();
    }

    public void setMembers(String[] members) {
        this.members = members;
    }
    public String[] getMembers() {
        return members;
    }
}