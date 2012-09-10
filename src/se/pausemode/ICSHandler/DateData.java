package se.pausemode.ICSHandler;



public class DateData {

    public enum VALUE_TYPE {
        DATE_TIME,
        DATE;

        /**
         * Since normal "-" is not allowed in enums, we need to create method
         * equivalent of "valueOf" to handle input
         */
        public static VALUE_TYPE getEnum(String value){
            VALUE_TYPE retVal = null;
            if(value != null){
                if(value.equals(DATE)){
                    return DATE;
                } else if(value.equals("DATE-TIME")){
                    return DATE_TIME;
                }
            } else {
                throw new IllegalArgumentException();
            }
            return retVal;
        }
    }



    private VALUE_TYPE value_type;
    private String TZID;
    private String value;

    public DateData(){};

    public VALUE_TYPE getValue_type() {
        return value_type;
    }

    public void setValue_type(VALUE_TYPE value_type) {
        this.value_type = value_type;
    }

    public String getTZID() {
        return TZID;
    }

    public void setTZID(String TZID) {
        this.TZID = TZID;
    }

    public String toString(){
        return "VALUE_TYPE: " + this.value_type +
                ", TZID: " + this.TZID +
                ", Value: " + this.value;

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
