import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class NormalAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Test
    public void NormalAnimal_instantiatesCorrectly_true() {
        NormalAnimal testNormalAnimal = new NormalAnimal("dog");
        assertEquals(true, testNormalAnimal instanceof NormalAnimal);
    }
    @Test
    public void getName_NormalAnimalInstantiatesWithName_Henry() {
        NormalAnimal testNormalAnimal = new NormalAnimal("Dog)");
        assertEquals("Dog", testNormalAnimal.getName());
    }
    }



