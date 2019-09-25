import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Test
    public void EndangeredAnimal_instantiatesCorrectly_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("dog","ill","newBorn");
        assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
    }
    @Test
    public void getName_EndangeredAnimalInstantiatesWithName_Henry() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("dog","ill","newBorn");
        assertEquals("dog", testEndangeredAnimal.getName());
    }
    @Test
    public void saveName_EndangeredAnimalInstantiatesWithName_Henry() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("dog","ill","newBorn");
        testEndangeredAnimal.save();
        assertTrue(testEndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
        assertEquals("dog", testEndangeredAnimal.getName());
    }

}