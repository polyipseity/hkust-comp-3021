package hk.ust.comp3021;

public class Location {
  public final Double latitude;

  public final Double altitude;

  public Location(double latitude, double altitude) {
    this.latitude = latitude;
    this.altitude = altitude;
  }

  public double distanceTo(Location location) {
    return Math.sqrt(Math.pow(latitude - location.latitude, 2) + Math.pow(altitude - location.altitude, 2));
  }

  public double getLatitude() {
    return latitude;
  }

  public double getAltitude() {
    return altitude;
  }
}
