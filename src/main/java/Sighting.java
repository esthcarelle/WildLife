import org.sql2o.Connection;

import java.sql.Timestamp;

public class Sighting {
    private int animalId;
    private String location;
    private String ranger;
    private int id;
    private Timestamp date;
    public Sighting(int animalId,String location,String ranger) {
        this.animalId=animalId;
        this.location=location;
        this.ranger=ranger;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getAnimalId() {
        return animalId;
    }

    public String getLocation() {
        return location;
    }

    public String getRanger() {
        return ranger;
    }
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalId,location,ranger,date) VALUES (:animalId,:location,:ranger,now());";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("ranger", this.ranger)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }

    }
    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id=:id;";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }
}
