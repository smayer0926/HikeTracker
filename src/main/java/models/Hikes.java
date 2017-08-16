package models;


public class Hikes {
    private String hikeName;
    private String hikeLocation;
    private String hikeNotes;
    private int hikeRating;
    private boolean hikeCompleted;
    private int id;

    public Hikes(String hikeName, String hikeLocation, String hikeNotes, int hikeRating) {
        this.hikeName = hikeName;
        this.hikeLocation = hikeLocation;
        this.hikeNotes = hikeNotes;
        this.hikeRating = hikeRating;
        this.hikeCompleted = false;
    }

    public String getHikeName() {
        return hikeName;
    }

    public void setHikeName(String hikeName) {
        this.hikeName = hikeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHikeLocation() {
        return hikeLocation;
    }

    public void setHikeLocation(String hikeLocation) {
        this.hikeLocation = hikeLocation;
    }

    public String getHikeNotes() {
        return hikeNotes;
    }

    public void setHikeNotes(String hikeNotes) {
        this.hikeNotes = hikeNotes;
    }

    public int getHikeRating(){
        return hikeRating;
    }

//    public String getHikeRating() {
//        int numRating = hikeRating;
//        String starRating ="";
//        for (int i = 0; i < numRating; i++) {
//            starRating +="★";
//        } return starRating;
//    }

    public void setHikeRating(int hikeRating) {
        this.hikeRating = hikeRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hikes hikes = (Hikes) o;

        if (hikeRating != hikes.hikeRating) return false;
        if (hikeCompleted != hikes.hikeCompleted) return false;
        if (!hikeName.equals(hikes.hikeName)) return false;
        if (!hikeLocation.equals(hikes.hikeLocation)) return false;
        return hikeNotes != null ? hikeNotes.equals(hikes.hikeNotes) : hikes.hikeNotes == null;
    }

    @Override
    public int hashCode() {
        int result = hikeName.hashCode();
        result = 31 * result + hikeLocation.hashCode();
        result = 31 * result + (hikeNotes != null ? hikeNotes.hashCode() : 0);
        result = 31 * result + hikeRating;
        result = 31 * result + (hikeCompleted ? 1 : 0);
        return result;
    }
}
