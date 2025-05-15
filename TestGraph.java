import org.junit.jupiter.api.*;

import java.io.*;

public class TestGraph {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }

    /**
     * Tests whether print() is printing an empty string
     */
    @Test
    @DisplayName("Test print()")
    public void testPrint(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether print() can add more than 1 value
     */
    @Test
    @DisplayName("Test print(), dependent on add()")
    public void testPrintTwoValues(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Auckland: \r\n" +
                        "Hamilton:";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether add() can add 1 value
     */
    @Test
    @DisplayName("Test add(), dependent on print()")
    public void testAddOneValue(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");

        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Auckland:";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether add() handles duplicates
     */
    @Test
    @DisplayName("Test add()")
    public void testAddDuplicates(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Auckland");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Add unsuccessful: string already exists";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether add() can handle null values
     */
    @Test
    @DisplayName("Test add()")
    public void testAddNull(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Add unsuccessful: string is null";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether delete() can delete a value
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and print()")
    public void testDeleteOneValue(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.deleteNode("Auckland");
        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether delete() handles non-existent values
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and print()")
    public void testDeleteNonExistentValues(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.deleteNode("Auckland");
        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Delete unsuccessful: string doesn't exists";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether delete() handles null values
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and print()")
    public void testDeleteNullValues(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.deleteNode("");
        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Delete unsuccessful: string is null";

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}