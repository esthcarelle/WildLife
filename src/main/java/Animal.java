import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Animal {
    public int id;
    public String name;
    public String type;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getId() == animal.getId() &&
                getName().equals(animal.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type) VALUES (:name,:type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }

    }

    public List<Sighting> getSightings() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE animalId = :id;";
            List<Sighting> sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
            return sightings;
        }
    }
//    public  List<Object> getAnimals() {
//        List<Object> allAnimals = new ArrayList<Object>();
//
//        try(Connection con = DB.sql2o.open()) {
//            String sqlFire = "SELECT * FROM animals WHERE type='Normal animal';";
//            List<NormalAnimal> normalAnimals = con.createQuery(sqlFire)
//                    .addParameter("id", this.id)
//                    .throwOnMappingFailure(false)
//                    .executeAndFetch(NormalAnimal.class);
//            allAnimals.addAll(normalAnimals);
//
//            String sqlWater = "SELECT * FROM monsters WHERE type='Endangered Animal';";
//            List<EndangeredAnimal> endangeredAnimals = con.createQuery(sqlWater)
//                    .addParameter("id", this.id)
//                    .throwOnMappingFailure(false)
//                    .executeAndFetch(EndangeredAnimal.class);
//            allAnimals.addAll(endangeredAnimals);
//        }
//
//        return allAnimals;
//    }
}
