package hk.ust.comp3021;

public class Location {
  public final Double latitude;

  public final Double altitude;

  public Location(double latitude, double altitude) {
    this.latitude = latitude;
    this.altitude = altitude;
  }

  public double distanceTo(Location location) {
  }
}
