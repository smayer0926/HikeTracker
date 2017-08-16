package models;


public class Locations {
    private int locationDistance;
    private int locationDifficulty;
    private String locationCity;
    private String locationState;
    private String locationCountry;
    private int id;

    public Locations(int locationDistance, int locationDifficulty, String locationCity, String locationState, String locationCountry) {
        this.locationDistance = locationDistance;
        this.locationDifficulty = locationDifficulty;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.locationCountry = locationCountry;
    }

    public int getLocationDistance() {
        return locationDistance;
    }

    public void setLocationDistance(int locationDistance) {
        this.locationDistance = locationDistance;
    }

    public int getLocationDifficulty() {
        return locationDifficulty;
    }

    public void setLocationDifficulty(int locationDifficulty) {
        this.locationDifficulty = locationDifficulty;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locations locations = (Locations) o;

        if (locationDistance != locations.locationDistance) return false;
        if (locationDifficulty != locations.locationDifficulty) return false;
        if (!locationCity.equals(locations.locationCity)) return false;
        if (!locationState.equals(locations.locationState)) return false;
        return locationCountry.equals(locations.locationCountry);
    }

    @Override
    public int hashCode() {
        int result = locationDistance;
        result = 31 * result + locationDifficulty;
        result = 31 * result + locationCity.hashCode();
        result = 31 * result + locationState.hashCode();
        result = 31 * result + locationCountry.hashCode();
        return result;
    }
}
