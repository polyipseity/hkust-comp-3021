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

  public Double getLatitude() {
    return latitude;
  }

  public Double getAltitude() {
    return altitude;
  }

  @Override
  public String toString() {
    return "Location(" +
        "latitude=" + latitude +
        ", altitude=" + altitude +
        ')';
  }
}
