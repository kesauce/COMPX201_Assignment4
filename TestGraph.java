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
    @DisplayName("Test addNode(), dependent on print()")
    public void testAddNodeOneValue(){
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
    @DisplayName("Test addNode()")
    public void testAddNodeDuplicates(){
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
    @DisplayName("Test addNode()")
    public void testAddNodeNull(){
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
    @DisplayName("Test deleteNode(), dependent on addNode() and print()")
    public void testDeleteNodeOneValue(){
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
    @DisplayName("Test deleteNode(), dependent on addNode() and print()")
    public void testDeleteNodeNonExistentValues(){
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
    @DisplayName("Test deleteNode(), dependent on addNode() and print()")
    public void testDeleteNodeNullValues(){
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

    /**
     * Tests whether addEdge() can add a rail edge
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode() and print()")
    public void addEdgeRail(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Auckland", "Hamilton", "Rail");

        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Auckland: (Hamilton, Rail), \r\n" + 
                        "Hamilton: (Auckland, Rail),";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() can add a road edge
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode() and print()")
    public void addEdgeRoad(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Auckland", "Hamilton", "Road");

        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Auckland: (Hamilton, Road), \r\n" + 
                        "Hamilton: (Auckland, Road),";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() can add a plane edge
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode() and print()")
    public void addEdgePlane(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Auckland", "Hamilton", "Plane");

        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Auckland: (Hamilton, Plane), \r\n" + 
                        "Hamilton: (Auckland, Plane),";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() can add multiple edges from the same two nodes
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode() and print()")
    public void addEdgesTwoLocations(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Auckland", "Hamilton", "Rail");
        graph.addEdge("Auckland", "Hamilton", "Road");
        graph.addEdge("Auckland", "Hamilton", "Plane");

        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Auckland: (Hamilton, Plane), (Hamilton, Rail), (Hamilton, Road), \r\n" + 
                        "Hamilton: (Auckland, Plane), (Auckland, Rail), (Auckland, Road),";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() can add multiple edges from the three nodes
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode() and print()")
    public void addEdgesThreeLocations(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addNode("Tauranga");
        graph.addEdge("Auckland", "Hamilton", "Rail");
        graph.addEdge("Hamilton", "Tauranga", "Road");
        graph.addEdge("Auckland", "Tauranga", "Plane");

        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Auckland: (Hamilton, Rail), (Tauranga, Plane), \r\n" + 
                        "Hamilton: (Auckland, Rail), (Tauranga, Road), \r\n" +
                        "Tauranga: (Auckland, Plane), (Hamilton, Road),";

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}