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
    @DisplayName("Test addNode()")
    public void testAddNodeOneValue(){
        // Assign
        Graph graph = new Graph();

        // Act
        int actual = graph.addNode("Auckland");
        int expected = 1;

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
        int actual = graph.addNode("Auckland");

        int expected = 0;

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
        int actual = graph.addNode("");

        int expected = 0;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether delete() can delete a value
     */
    @Test
    @DisplayName("Test deleteNode(), dependent on addNode()")
    public void testDeleteNodeOneValue(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        int actual = graph.deleteNode("Auckland");
        int expected = 1;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether delete() handles non-existent values
     */
    @Test
    @DisplayName("Test deleteNode(), dependent on addNode()")
    public void testDeleteNodeNonExistentValues(){
        // Assign
        Graph graph = new Graph();

        // Act
        int actual = graph.deleteNode("Auckland");
        int expected = 0;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether delete() handles null values
     */
    @Test
    @DisplayName("Test deleteNode(), dependent on addNode()")
    public void testDeleteNodeNullValues(){
        // Assign
        Graph graph = new Graph();

        // Act
        int actual = graph.deleteNode("");
        int expected = 0;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() can add a rail edge
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode()")
    public void testAddEdgeRail(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        int actual = graph.addEdge("Auckland", "Hamilton", "Rail");
        int expected = 1;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() can add a road edge
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode()")
    public void testAddEdgeRoad(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        int actual = graph.addEdge("Auckland", "Hamilton", "Road");
        int expected = 1;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() can add a plane edge
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode()")
    public void testAddEdgePlane(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        int actual = graph.addEdge("Auckland", "Hamilton", "Plane");
        int expected = 1;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() can add multiple edges from the same two nodes
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode()")
    public void testAddEdgesTwoLocations(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        int actual = graph.addEdge("Auckland", "Hamilton", "Rail");
        actual += graph.addEdge("Auckland", "Hamilton", "Road");
        actual += graph.addEdge("Auckland", "Hamilton", "Plane");
        int expected = 3;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether addEdge() can add multiple edges from the three nodes
     */
    @Test
    @DisplayName("Test addEdge(), dependent on addNode()")
    public void testAddEdgesThreeLocations(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addNode("Tauranga");
        int actual = graph.addEdge("Auckland", "Hamilton", "Rail");
        actual += graph.addEdge("Hamilton", "Tauranga", "Road");
        actual += graph.addEdge("Auckland", "Tauranga", "Plane");
        int expected = 3;

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
        int actual = graph.addEdge("", "", "");
        int expected = 0;

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
        int actual = graph.addEdge("Hamilton", "Auckland", "Road");
        int expected = 0;

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
        int actual = graph.addEdge("Hamilton", "Auckland", "Boat");
        int expected = 0;

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
        int actual = graph.addEdge("Auckland", "Hamilton", "Rail");
        int expected = 0;

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether deleteEdge() deletes one edge
     */
    @Test
    @DisplayName("Test deleteEdge(), dependent on addNode(), addEdge()")
    public void testDeleteEdgeOneValue(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addEdge("Auckland", "Hamilton", "Rail");
        int actual = graph.deleteEdge("Auckland", "Hamilton", "Rail");
        int expected = 1;

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
        int actual = graph.deleteEdge("Auckland", "Hamilton", "Rail");
        int expected = 0;

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
        int actual = graph.deleteEdge("Hamilton", "Auckland", "Road");
        int expected = 0;

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
        int actual = graph.deleteEdge("", "", "");
        int expected = 0;

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
        int actual = graph.deleteEdge("Hamilton", "Auckland", "Boat");
        int expected = 0;

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
        boolean actual = graph.hasEdge("", "", "");

        // Assert
        Assertions.assertFalse(actual);
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
        boolean actual = graph.hasEdge("Hamilton", "Auckland", "Road");

        // Assert
        Assertions.assertFalse(actual);
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
        boolean actual = graph.hasEdge("Hamilton", "Auckland", "Boat");

        // Assert
        Assertions.assertFalse(actual);
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
        String expected = "";


        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /** 
     * Tests whether traverseTicket() works correctly for road
     */
    @Test
    @DisplayName("Test traverseTicket, dependent on addNode, addEdge()")
    public void testTraverseTicketRoad(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addNode("Tauranga");
        graph.addNode("Wellington");
        graph.addEdge("Hamilton", "Auckland", "Road");
        graph.addEdge("Hamilton", "Tauranga", "Plane");
        graph.addEdge("Hamilton", "Wellington", "Rail");

        String actual = graph.ticketTraverse("Hamilton", 1, 0, 0);
        String expected = "Auckland, Hamilton, ";


        // Assert
        Assertions.assertEquals(expected, actual);
    }

     /** 
     * Tests whether traverseTicket() works correctly for rail
     */
    @Test
    @DisplayName("Test traverseTicket, dependent on addNode, addEdge()")
    public void testTraverseTicketRail(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addNode("Tauranga");
        graph.addNode("Wellington");
        graph.addEdge("Hamilton", "Auckland", "Road");
        graph.addEdge("Hamilton", "Tauranga", "Plane");
        graph.addEdge("Hamilton", "Wellington", "Rail");

        String actual = graph.ticketTraverse("Hamilton", 0, 0, 1);
        String expected = "Hamilton, Wellington, ";


        // Assert
        Assertions.assertEquals(expected, actual);
    }

     /** 
     * Tests whether traverseTicket() works correctly for Plane
     */
    @Test
    @DisplayName("Test traverseTicket, dependent on addNode, addEdge()")
    public void testTraverseTicketPlane(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addNode("Tauranga");
        graph.addNode("Wellington");
        graph.addEdge("Hamilton", "Auckland", "Road");
        graph.addEdge("Hamilton", "Tauranga", "Plane");
        graph.addEdge("Hamilton", "Wellington", "Rail");

        String actual = graph.ticketTraverse("Hamilton", 0, 1, 0);
        String expected = "Hamilton, Tauranga, ";


        // Assert
        Assertions.assertEquals(expected, actual);
    }

    /** 
     * Tests whether traverseTicket() works correctly for all edges
     */
    @Test
    @DisplayName("Test traverseTicket, dependent on addNode, addEdge()")
    public void testTraverseTicketAllEdges(){
        // Assign
        Graph graph = new Graph();

        // Act
        graph.addNode("Auckland");
        graph.addNode("Hamilton");
        graph.addNode("Tauranga");
        graph.addNode("Wellington");
        graph.addEdge("Hamilton", "Auckland", "Road");
        graph.addEdge("Hamilton", "Tauranga", "Plane");
        graph.addEdge("Hamilton", "Wellington", "Rail");

        String actual = graph.ticketTraverse("Auckland", 1, 1, 1);
        String expected = "Auckland, Hamilton, Tauranga, Wellington, ";


        // Assert
        Assertions.assertEquals(expected, actual);
    }


}