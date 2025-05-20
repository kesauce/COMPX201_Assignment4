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
    public void testAddEdgeRail(){
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
    public void testAddEdgeRoad(){
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
    public void testAddEdgePlane(){
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
    public void testAddEdgesTwoLocations(){
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
    public void testAddEdgesThreeLocations(){
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

    /**
     * Tests whether addEdge() skips null strings
     */
    @Test
    @DisplayName("Test addEdge()")
    public void testAddEdgesNull(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addEdge("", "", "");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Add unsuccessful: string(s) is empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() skips non-existent locations
     */
    @Test
    @DisplayName("Test addEdge()")
    public void testAddEdgesNonExistent(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addEdge("Hamilton", "Auckland", "Road");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Add unsuccessful: string(s) doesn't exist";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() skips invalid types
     */
    @Test
    @DisplayName("Test addEdge()")
    public void testAddEdgesInvalidType(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Hamilton", "Auckland", "Boat");


        String actual = outputStreamCaptor.toString().trim();
        String expected = "Add unsuccessful: invalid type";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() skips duplicate edges
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode()")
    public void testAddEdgesDuplicates(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Auckland", "Hamilton", "Rail");
        graph.addEdge("Auckland", "Hamilton", "Rail");


        String actual = outputStreamCaptor.toString().trim();
        String expected = "Add unsuccessful: edge already exists";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether deleteEdge() deletes one edge
     */
    @Test
    @DisplayName("Test deleteEdge(), dependent on addNode(), addEdge(), print()")
    public void testDeleteEdgeOneValue(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Auckland", "Hamilton", "Rail");
        graph.deleteEdge("Auckland", "Hamilton", "Rail");

        graph.print();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Auckland: \r\n" + 
                        "Hamilton:";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether deleteEdge() deletes a non-existent edge
     */
    @Test
    @DisplayName("Test deleteEdge(), dependent on print()")
    public void testDeleteEdgeNonExistentEdge(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.deleteEdge("Auckland", "Hamilton", "Rail");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Delete unsuccessful: edge doesn't exist";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether deleteEdge() skips non-existent locations
     */
    @Test
    @DisplayName("Test addEdge()")
    public void testDeleteEdgesNonExistentValue(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.deleteEdge("Hamilton", "Auckland", "Road");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Delete unsuccessful: string(s) doesn't exist";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether deleteEdge() skips null strings
     */
    @Test
    @DisplayName("Test deleteEdge()")
    public void testDeleteEdgesNull(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.deleteEdge("", "", "");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Delete unsuccessful: string(s) is empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether deleteEdge() skips invalid types
     */
    @Test
    @DisplayName("Test deleteEdge()")
    public void testDeleteEdgesInvalidType(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.deleteEdge("Hamilton", "Auckland", "Boat");


        String actual = outputStreamCaptor.toString().trim();
        String expected = "Delete unsuccessful: invalid type";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether hasEdge() can detect an edge
     */
    @Test
    @DisplayName("Test hasEdge(), dependent on addNode(), addEdge()")
    public void testHasEdgeTrue(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Auckland", "Hamilton", "Rail");

        graph.print();

        boolean expected = graph.hasEdge("Hamilton", "Auckland", "Rail");

        // Assert
        Assertions.assertTrue(expected);
    }

    /**
     * Tests whether hasEdge() can detect an edge
     */
    @Test
    @DisplayName("Test hasEdge(), dependent on addNode(), addEdge()")
    public void testHasEdgeFalse(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Auckland", "Hamilton", "Plane");

        graph.print();

        boolean expected = graph.hasEdge("Hamilton", "Auckland", "Rail");

        // Assert
        Assertions.assertFalse(expected);
    }

    /**
     * Tests whether hasEdge() skips null strings
     */
    @Test
    @DisplayName("Test hasEdge()")
    public void testHasEdgesNull(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.hasEdge("", "", "");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Check unsuccessful: string(s) is empty";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether hasEdge() skips non-existent locations
     */
    @Test
    @DisplayName("Test hasEdge()")
    public void testHasEdgesNonExistentValue(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.hasEdge("Hamilton", "Auckland", "Road");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Check unsuccessful: string(s) doesn't exist";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether hasEdge() skips invalid types
     */
    @Test
    @DisplayName("Test deleteEdge(), dependent on addNode()")
    public void testHasEdgesInvalidType(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.hasEdge("Hamilton", "Auckland", "Boat");


        String actual = outputStreamCaptor.toString().trim();
        String expected = "Check unsuccessful: invalid type";

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether getEdgesOfType() works with Rails
     */
    @Test
    @DisplayName("Test getEdgesOfType(), dependent on addNode(), addEdge(),")
    public void testGetEdgesOfTypeRail(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Hamilton", "Auckland", "Rail");

        String actual = graph.getEdgesOfType("Rail");
        String expected = "(Auckland, Hamilton), ";


        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether getEdgesOfType() works with Roads
     */
    @Test
    @DisplayName("Test getEdgesOfType(), dependent on addNode(), addEdge(),")
    public void testGetEdgesOfTypeRoad(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Hamilton", "Auckland", "Road");

        String actual = graph.getEdgesOfType("Road");
        String expected = "(Auckland, Hamilton), ";


        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether getEdgesOfType() works with Planes
     */
    @Test
    @DisplayName("Test getEdgesOfType(), dependent on addNode(), addEdge(),")
    public void testGetEdgesOfTypePlane(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Hamilton", "Auckland", "Plane");

        String actual = graph.getEdgesOfType("Plane");
        String expected = "(Auckland, Hamilton), ";


        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether getEdgesOfType() handles invalid types
     */
    @Test
    @DisplayName("Test getEdgesOfType(),")
    public void testGetEdgesOfTypeInvalidType(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.getEdgesOfType("Boat");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "Check unsuccessful: invalid type";


        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether
     */
}