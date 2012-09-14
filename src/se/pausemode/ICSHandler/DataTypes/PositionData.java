package se.pausemode.ICSHandler.DataTypes;

public class PositionData {

    private float latitude;
    private float longitude;

    public PositionData(float latitude, float longitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "PositionData{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
