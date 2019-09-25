import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class NormalAnimal extends Animal {
    public static final String DATABASE_TYPE = "Normal animal";

    public NormalAnimal(String name) {

        this.name = name;
        type = DATABASE_TYPE;
    }


    public static List<NormalAnimal> getAll() {
        String sql = "SELECT * FROM animals WHERE type='Normal animal';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(NormalAnimal.class);
        }
    }

    public static NormalAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id AND type='Normal animal';";
            NormalAnimal normalAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(NormalAnimal.class);
            return normalAnimal;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }

    }


}


