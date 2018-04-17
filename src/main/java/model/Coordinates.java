package model;

public class Coordinates {
    double lat;
    double lng;
    public Coordinates(double lat, double lng){
        this.lat=lat;
        this.lng=lng;
    }

    @Override
    public String toString() {
        return "{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
