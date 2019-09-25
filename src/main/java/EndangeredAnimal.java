import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimal extends Animal{
    public String health;
    public String age;
    public static final String DATABASE_TYPE = "Endangered animal";
    public EndangeredAnimal(String name,String health,String age){
        this.name=name;
        this.health=health;
        this.age=age;
        type=DATABASE_TYPE;
    }
    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM animals WHERE type='Endangered animal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }
    @Override
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type,health,age) VALUES (:name,:type,:health,:age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }

    }
    public static EndangeredAnimal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id AND type='Endangered animal';";
            EndangeredAnimal endangeredAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return endangeredAnimal;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

}
