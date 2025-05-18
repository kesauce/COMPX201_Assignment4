import java.util.ArrayList;

public class Graph{

    // Parameters
    private ArrayList<Node> nodes;
    private ArrayList<Node[]> planeEdges;
    private ArrayList<Node[]> roadEdges;
    private ArrayList<Node[]> railEdges;


    // Constructor
    public Graph(){
        nodes = new ArrayList<Node>();
        planeEdges =  new ArrayList<Node[]>();
        roadEdges = new ArrayList<Node[]>();
        railEdges = new ArrayList<Node[]>();
    }

    // Methods

    /**
     * Adds a new location to the list of nodes
     * @param s Name of location as a string
     */
    public void addNode(String s){
        // Checks if the string is null
        if (s == null || s == ""){
            System.out.println("Add unsuccessful: string is null");
            return;
        }
        // Checks if the node already exists
        else if(this.contains(s)){
            System.out.println("Add unsuccessful: string already exists");
            return;
        }

        // Create a new node and add it to nodes
        Node newNode = new Node(s);
        nodes.add(newNode);
    }

    /**
     * Deletes a location from the list of nodes and any edges
     * @param s Name of location as a string
     */
    public void deleteNode(String s){
        // Checks if the string is null
        if (s == null || s == ""){
            System.out.println("Delete unsuccessful: string is null");
            return;
        }
        // Checks if the node already exists
        else if(!this.contains(s)){
            System.out.println("Delete unsuccessful: string doesn't exists");
            return;
        }

        // Find the node to delete
        Node node = getNode(s);

        // Grab the node's neighbours
        ArrayList<Node> nodeNeighbours = node.getNeighbours();

        // Loop through the neighbours
        for(Node neighbour : nodeNeighbours){
            // Delete itself from its neighbours
            neighbour.deleteNeighbour(node);
        }

        // Delete the node from the node list
        nodes.remove(node);

    }

    /**
     * Inserts an edge between two points of a specific type
     * @param s1 One location
     * @param s2 The other location
     * @param type The type of edge
     */
    public void addEdge(String s1, String s2, String type){
        // Checks if the strings are empty
        if (s1 == null || s1 == "" || s2 == null || s2 == "" || type == null || type == ""){
            System.out.println("Add unsuccessful: string(s) is empty");
            return;
        }
        // Checks if both strings doesn't exist
        else if(!this.contains(s1) || !this.contains(s2)){
            System.out.println("Add unsuccessful: string(s) doesn't exist");
            return;
        }

        // Grab the arraylist needed for the specific edge
        ArrayList<Node[]> edgeList = getEdges(type);
        
        // Ensure that arraylist is not null
        if (edgeList == null){
            System.out.println("Add unsuccessful: invalid type");
            return;
        }
        
        // Initialise edge array
        Node [] edgeArray = {null, null};

        // Check whether s1 or s2 comes first
        if (s1.compareTo(s2) == -1){
            // Grab the nodes and put it in array
            edgeArray[0] = getNode(s1);
            edgeArray[1] = getNode(s2);
        }
        else{
            // Grab the nodes and put it in array
            edgeArray[1] = getNode(s1);
            edgeArray[0] = getNode(s2);
        }
        
        // Checks if that edge already exists
        if (this.containsEdge(edgeList, edgeArray)){
            System.out.println("Add unsuccessful: edge already exists");
            return;
        }

        // Add the edge array
        edgeList.add(edgeArray);
        
    }

    /**
     * Deletes a given edge between two locations
     * @param s1 The first location
     * @param s2 The second location
     * @param type The type of edge
     */
    public void deleteEdge(String s1, String s2, String type){
        // Checks if the strings are empty
        if (s1 == null || s1 == "" || s2 == null || s2 == "" || type == null || type == ""){
            System.out.println("Add unsuccessful: string(s) is empty");
            return;
        }
        // Checks if both strings doesn't exist
        else if(!this.contains(s1) || !this.contains(s2)){
            System.out.println("Add unsuccessful: string(s) doesn't exist");
            return;
        }

    	// Grab the arraylist needed for the specific edge
        ArrayList<Node[]> edgeList = getEdges(type);
        
        // Ensure that arraylist is not null
        if (edgeList == null){
            System.out.println("Add unsuccessful: invalid type");
            return;
        }

        // Grab the nodes and put it in array
        Node firstLocation = getNode(s1);
        Node secondLocation = getNode(s2);
        Node[] edgeArray = {firstLocation, secondLocation};

        // Remove the edge array in the edge list
        edgeList.remove(edgeArray);
        
    }

    /**
     * Checks if the graph has a given edge
     * @param s1 The first location
     * @param s2 The second location
     * @param type The edge type
     * @return True or false whether the graph has a given edge
     */
    public boolean hasEdge(String s1, String s2, String type){
        // Checks if the strings are empty
        if (s1 == null || s1 == "" || s2 == null || s2 == "" || type == null || type == ""){
            System.out.println("Add unsuccessful: string(s) is empty");
            return false;
        }
        // Checks if both strings doesn't exist
        else if(!this.contains(s1) || !this.contains(s2)){
            System.out.println("Add unsuccessful: string(s) doesn't exist");
            return false;
        }

    	// Grab the arraylist needed for the specific edge
        ArrayList<Node[]> edgeList = getEdges(type);
        
        // Ensure that arraylist is not null
        if (edgeList == null){
            System.out.println("Add unsuccessful: invalid type");
            return false;
        }

        // Grab the nodes and put it in array
        Node firstLocation = getNode(s1);
        Node secondLocation = getNode(s2);
        Node[] edgeArray = {firstLocation, secondLocation};

        // Loop through the edge list and return true if edge array is found
        for (Node[] n : edgeList) {
            if (compareToEdges(edgeArray, n) == 0){
                return true;
            }
        }

        return false;
    }

    /**
     * Lists out the edges of a particular type
     * @param type The edge type
     * @return A string of the list
     */
    public String getEdgesOfType(String type){
        // Grab the arraylist needed for the specific edge
        ArrayList<Node[]> edgeList = getEdges(type);
        
        // Ensure that arraylist is not null
        if (edgeList == null){
            System.out.println("Add unsuccessful: invalid type");
            return "";
        }

        // Initialise output
        String output = "";

        // Loop through the edge list
        for (Node[] n : edgeList) {
            output += "(" + n[0].getValue() + ", " + n[1].getValue() + "), ";
        }

        return output;
    }

    /**
     * Prints out the entire graph including all nodes and all edges
     */
    public void print(){
        // Loop through the nodes in the graph
        for (Node n : nodes) {
            // Initialise the output line
            String nodeOutput = n.getValue() + ": ";

            // Store the list of strings
            ArrayList<String> neighbours = new ArrayList<String>();

            // Loop through to rail edges
            if (railEdges != null){
                for (Node[] edges : railEdges) {
                    // If current node has an edge 
                    if (n.equals(edges[0])){
                        // Grab the destination and the edge type
                        neighbours.add("(" + edges[1].getValue() + ", Rail), ");
                    }
                    else if (n.equals(edges[1])){
                        // Grab the destination and the edge type
                        neighbours.add("(" + edges[0].getValue() + ", Rail), ");
                    }
                }
            }

            // Loop through to road edges
            if (roadEdges != null){
                for (Node[] edges : roadEdges) {
                    // If current node has an edge 
                    if (n.equals(edges[0])){
                        // Grab the destination and the edge type
                        neighbours.add("(" + edges[1].getValue() + ", Road), ");
                    }
                    else if (n.equals(edges[1])){
                        // Grab the destination and the edge type
                        neighbours.add("(" + edges[0].getValue() + ", Road), ");
                    }
                }
            }

            // Loop through to plane edges
            if (planeEdges != null){
                for (Node[] edges : planeEdges) {
                    // If current node has an edge 
                    if (n.equals(edges[0])){
                        // Grab the destination and the edge type
                        neighbours.add("(" + edges[1].getValue() + ", Plane), ");
                    }
                    else if (n.equals(edges[1])){
                        // Grab the destination and the edge type
                        neighbours.add("(" + edges[0].getValue() + ", Plane), ");
                    }
                }
            }

            // Sort the list of strings
            neighbours.sort(null);

            // Add the strings to the output
            for (String neighbour : neighbours) {
                nodeOutput += neighbour;
            }

            // Print out the node's output
            System.out.println(nodeOutput);
        }
    }

    // Helper methods

    /**
     * Checks if a certain value is in the nodes list
     * @param s The node to find
     * @return Whether the list contains that value or not
     */
    private boolean contains(String s){
        // Checks if the node already exists
        for (Node node : nodes) {
            if(node.getValue().equals(s)){
                return true;
            }
        }

        return false;
    }

    /**
     * Grabs the arraylist of the edge
     * @param type The type of edge
     * @return The arraylist of the edges
     */
    private ArrayList<Node[]> getEdges(String type){
        // Make string uppercase
        String typeUpper = type.trim().toUpperCase();

        // Check what the string is referring to
        if (typeUpper.equals("PLANE")){
            return planeEdges;
        }
        else if (typeUpper.equals("ROAD")){
            return roadEdges;
        }
        else if (typeUpper.equals("RAIL")){
            return railEdges;
        }
        else{
            return null;
        }
    }

    /**
     * Finds the node of a given string
     * @param s The location of node
     * @return The node object
     */
    private Node getNode(String s){
        Node node = null;

        // Loop through to find the node
        for(Node n : nodes){
            if(n.getValue().equals(s)){
                node = n;
            }
        }

        return node;
    }

    /**
     * Compares two edge arrays and determines which should go first
     * @param nodeArray1 The first array
     * @param nodeArray2 The second array
     * @return An integer determining where the first array should go compared to the second array
     */
    private int compareToEdges(Node[] nodeArray1, Node[] nodeArray2){

        // Compare the first values of the two arrays
        if (nodeArray1[0].getValue().compareTo(nodeArray2[0].getValue()) != 0){
            return nodeArray1[0].getValue().compareTo(nodeArray2[0].getValue());
        }
        // Compare the second values of the two arrays
        else{
            return nodeArray1[1].getValue().compareTo(nodeArray2[1].getValue());
        }
    }

    private boolean containsEdge(ArrayList<Node[]> edgeList, Node[] array){
        // Loop through the edge list
        for (Node[] nodes : edgeList) {
            if (nodes[0].getValue().equals(array[0].getValue()) && nodes[1].getValue().equals(array[1].getValue())){
                return true;
            }
        }
        return false;
    }
}