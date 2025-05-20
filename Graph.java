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
            System.out.println("Delete unsuccessful: string(s) is empty");
            return;
        }
        // Checks if both strings doesn't exist
        else if (!this.contains(s1) || !this.contains(s2)){
            System.out.println("Delete unsuccessful: string(s) doesn't exist");
            return;
        }

    	// Grab the arraylist needed for the specific edge
        ArrayList<Node[]> edgeList = getEdges(type);
        
        // Ensure that arraylist is not null
        if (edgeList == null){
            System.out.println("Delete unsuccessful: invalid type");
            return;
        }

        // Grab the nodes and put it in array
        Node[] edgeArray = {null, null};

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

        // Checks if the array list doesn't contains the edge
        if (!this.containsEdge(edgeList, edgeArray)){
            System.out.println("Delete unsuccessful: edge doesn't exist");
            return;
        }
        
        // Loop through the array list and find the exact edge
        for (Node[] nodes : edgeList) {
            if (nodes[0].getValue().equals(edgeArray[0].getValue()) && nodes[1].getValue().equals(edgeArray[1].getValue())){
                // Delete the edge
                edgeList.remove(nodes);
                return;
            }
        }
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
            System.out.println("Check unsuccessful: string(s) is empty");
            return false;
        }
        // Checks if both strings doesn't exist
        else if(!this.contains(s1) || !this.contains(s2)){
            System.out.println("Check unsuccessful: string(s) doesn't exist");
            return false;
        }

    	// Grab the arraylist needed for the specific edge
        ArrayList<Node[]> edgeList = getEdges(type);
        
        // Ensure that arraylist is not null
        if (edgeList == null){
            System.out.println("Check unsuccessful: invalid type");
            return false;
        }

        // Grab the nodes and put it in array
        Node[] edgeArray = {null, null};

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

        // Loop through the edge list and return true if edge array is found
        for (Node[] nodes : edgeList) {
            System.out.println(nodes[0].getValue());
            System.out.println(nodes[1].getValue());
            if (nodes[0].getValue().equals(edgeArray[0].getValue()) && nodes[1].getValue().equals(edgeArray[1].getValue()) || nodes[0].getValue().equals(edgeArray[1].getValue()) && nodes[1].getValue().equals(edgeArray[0].getValue())){
                // Edge found
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
            System.out.println("Check unsuccessful: invalid type");
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

    /**
     * Finds out which cities you can reach with given tickets
     * @param startCity The starting city
     * @param busTickets The number of bus tickets
     * @param planeTickets The number of plane tickets
     * @param trainTickets The number of train tickets
     * @return The list of reachable cities
     */
    public String ticketTraverse(String startCity, int busTickets, int planeTickets, int trainTickets){
        // Checks whether the starting city is a real node
        if (!this.contains(startCity)){
            System.out.println("Check unsuccessful: starting city doesn't exist");
            return "";
        }

        // Initalise string of visited cities and add the starting city to it
        ArrayList<String> visitedCities = new ArrayList<String>();
        visitedCities.add(startCity);

        // Find the neighbours of the starting city
        Node startNode = getNode(startCity);
        ArrayList<Node> neighbours = startNode.getNeighbours();

        // Get the edge type between the start city and the neighbour
        if (hasEdge(startCity, neighbours.get(0).getValue(), "Road")){
            busTickets--;
        }
        else if (hasEdge(startCity, neighbours.get(0).getValue(), "Rail")){
            trainTickets--;
        }
        else{
            planeTickets--;
        }

        // Loop through each neighbour
        for (int i = 0; i < neighbours.size(); i++) {
            // Find the reachable cities using bus
            for (int j = busTickets; i > 0; i--) {
                // Get the neighbours using bus
                ArrayList<Node> busCities = traverse(neighbours.get(i), "Road");

                // Add to the visited cities if it doesn't exist yet
                for (Node node : busCities) {
                    if (!visitedCities.contains(node.getValue())){
                        visitedCities.add(node.getValue());
                    }
                }
            }

            // Find the reachable cities using train
            for (int j = trainTickets; i > 0; i--) {
                // Get the neighbours using bus
                ArrayList<Node> trainCities = traverse(neighbours.get(i), "Rail");

                // Add to the visited cities if it doesn't exist yet
                for (Node node : trainCities) {
                    if (!visitedCities.contains(node.getValue())){
                        visitedCities.add(node.getValue());
                    }
                }
            }

            // Find the reachable cities using plane
            for (int j = planeTickets; i > 0; i--) {
                // Get the neighbours using bus
                ArrayList<Node> planeCities = traverse(neighbours.get(i), "Plane");

                // Add to the visited cities if it doesn't exist yet
                for (Node node : planeCities) {
                    if (!visitedCities.contains(node.getValue())){
                        visitedCities.add(node.getValue());
                    }
                }
            }

            // Get the edge type between the neighbour and the next neighbour
            if (hasEdge(neighbours.get(i).getValue(), neighbours.get(i + 1).getValue(), "Road")){
                busTickets--;
            }
            else if (hasEdge(neighbours.get(i).getValue(), neighbours.get(i + 1).getValue(), "Rail")){
                trainTickets--;
            }
            else{
                planeTickets--;
            }
        }
        
        // Return the strings
        String output = "";
        for (String city : visitedCities) {
            output += city + ", ";
        }

        return output;
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
     * Checks if and edge list contains a particular edge
     * @param edgeList The edge list
     * @param array The target array
     * @return The boolean determining if the edge list contains an array
     */
    private boolean containsEdge(ArrayList<Node[]> edgeList, Node[] array){
        // Loop through the edge list
        for (Node[] nodes : edgeList) {
            if (nodes[0].getValue().equals(array[0].getValue()) && nodes[1].getValue().equals(array[1].getValue())){
                return true;
            }
        }
        return false;
    }

    /**
     * Traverses the nodes adjacent to the starting node of a certain type
     * @param n The current node to traverse
     * @return The arraylist of all the visited nodes
     */
    private ArrayList<Node> traverse(Node n, String type){
        // Initialise the visited nodes array list
        ArrayList<Node> canVisitNeighbourNodes = new ArrayList<Node>();

        // Grab the node's neighbours
        ArrayList<Node> neighbours = n.getNeighbours();

        // If the current has no neighbours then end
        if (neighbours.isEmpty()){
            return null;
        }

        // Loop through each neighbour
        for (Node neighbour : neighbours) {
            // Check if there is a certain edge
            if (hasEdge(neighbour.getValue(), n.getValue(), type)){
                // Add it to the list of can visit nodes
                canVisitNeighbourNodes.add(neighbour);
            }
        }

        return canVisitNeighbourNodes;
    }

    // /**
    //  * Traverses the nodes adjacent to the starting node
    //  * @param n The current node to traverse
    //  * @return The arraylist of all the visited nodes
    //  */
    // private Node traverseHelper(Node n){
    //     // Grab the node's neighbours
    //     ArrayList<Node> neighbours = n.getNeighbours();

    //     // If the current has no neighbours then end
    //     if (neighbours.isEmpty()){
    //         return null;
    //     }

    //     // Go through each neighbour and traverse each neighbour
    //     for (Node neighbour : neighbours) {
            
    //     }
    // }
}