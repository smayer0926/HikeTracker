package models;


public class Hikes {
    private String hikeName;
    private int id;

    public Hikes(String hikeName) {
        this.hikeName = hikeName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hikes hikes = (Hikes) o;

        return hikeName.equals(hikes.hikeName);
    }

    @Override
    public int hashCode() {
        return hikeName.hashCode();
    }
}
