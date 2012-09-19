package se.pausemode.ICSHandler.DataTypes;



public class DateData {

    public enum VALUE_TYPE {
        DATE_TIME,
        DATE,
        PERIOD; //Needed for RDATE

        /**
         * Since normal "-" is not allowed in enums, we need to create method
         * equivalent of "valueOf" to handle input
         */
        public static VALUE_TYPE getEnum(String value){
            VALUE_TYPE retVal = null;
            if(value != null){
                if(value.equals("DATE")){
                    return DATE;
                } else if(value.equals("DATE-TIME")){
                    return DATE_TIME;
                } else if(value.equals("PERIOD")){
                    return PERIOD;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DateData{" +
                "value_type=" + value_type +
                ", TZID='" + TZID + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateData dateData = (DateData) o;

        if (TZID != null ? !TZID.equals(dateData.TZID) : dateData.TZID != null) return false;
        if (value != null ? !value.equals(dateData.value) : dateData.value != null) return false;
        if (value_type != dateData.value_type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value_type != null ? value_type.hashCode() : 0;
        result = 31 * result + (TZID != null ? TZID.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
