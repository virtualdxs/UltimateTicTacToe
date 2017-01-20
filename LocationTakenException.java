@SuppressWarnings("serial")
public class LocationTakenException extends Exception {
    private int location;
    public LocationTakenException(int location) {
        super("Location "+location+" is taken");
        this.location = location;
    }
    public int getLocation() {
        return location;
    }
    public String toString() {
        return "Location "+getLocation()+" on is taken. Please choose another location.";
    }
}
