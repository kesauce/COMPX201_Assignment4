import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
     * @return 0 if unsuccessful, 1 if successful
     */
    public int addNode(String s){
        // Checks if the string is null
        if (s == null || s == ""){
            System.err.println("Add unsuccessful: string is null");
            return 0;
        }
        // Checks if the node already exists
        else if(this.contains(s)){
            System.err.println("Add unsuccessful: string already exists");
            return 0;
        }

        // Create a new node and add it to nodes
        Node newNode = new Node(s);
        nodes.add(newNode);
        return 1;
    }

    /**
     * Deletes a location from the list of nodes and any edges
     * @param s Name of location as a string
     * @return 0 if unsuccessful, 1 if successful
     */
    public int deleteNode(String s){
        // Checks if the string is null
        if (s == null || s == ""){
            System.err.println("Delete unsuccessful: string is null");
            return 0;
        }
        // Checks if the node already exists
        else if(!this.contains(s)){
            System.err.println("Delete unsuccessful: string doesn't exists");
            return 0;
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
        return 1;

    }

    /**
     * Inserts an edge between two points of a specific type
     * @param s1 One location
     * @param s2 The other location
     * @param type The type of edge
     * @return 0 if unsuccessful, 1 if successful
     */
    public int addEdge(String s1, String s2, String type){
        // Checks if the strings are empty
        if (s1 == null || s1 == "" || s2 == null || s2 == "" || type == null || type == ""){
            System.err.println("Add unsuccessful: string(s) is empty");
            return 0;
        }
        // Checks if both strings doesn't exist
        else if(!this.contains(s1) || !this.contains(s2)){
            System.err.println("Add unsuccessful: string(s) doesn't exist");
            return 0;
        }

        // Grab the arraylist needed for the specific edge
        ArrayList<Node[]> edgeList = getEdges(type);
        
        // Ensure that arraylist is not null
        if (edgeList == null){
            System.err.println("Add unsuccessful: invalid type");
            return 0;
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
            System.err.println("Add unsuccessful: edge already exists");
            return 0;
        }

        // Add the edge array
        edgeList.add(edgeArray);
        
        // Add the neighbours to each node
        getNode(s1).addNeighbour(getNode(s2));
        getNode(s2).addNeighbour(getNode(s1));
        return 1;
    }

    /**
     * Deletes a given edge between two locations
     * @param s1 The first location
     * @param s2 The second location
     * @param type The type of edge
     * @return 0 if unsuccessful, 1 if successful
     */
    public int deleteEdge(String s1, String s2, String type){
        // Checks if the strings are empty
        if (s1 == null || s1 == "" || s2 == null || s2 == "" || type == null || type == ""){
            System.err.println("Delete unsuccessful: string(s) is empty");
            return 0;
        }
        // Checks if both strings doesn't exist
        else if (!this.contains(s1) || !this.contains(s2)){
            System.err.println("Delete unsuccessful: string(s) doesn't exist");
            return 0;
        }

    	// Grab the arraylist needed for the specific edge
        ArrayList<Node[]> edgeList = getEdges(type);
        
        // Ensure that arraylist is not null
        if (edgeList == null){
            System.err.println("Delete unsuccessful: invalid type");
            return 0;
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
            System.err.println("Delete unsuccessful: edge doesn't exist");
            return 0;
        }
        
        // Loop through the array list and find the exact edge
        for (Node[] nodes : edgeList) {
            if (nodes[0].getValue().equals(edgeArray[0].getValue()) && nodes[1].getValue().equals(edgeArray[1].getValue())){
                // Delete the edge
                edgeList.remove(nodes);

                // Delete the nodes from each neighbour
                getNode(s1).deleteNeighbour(getNode(s2));
                getNode(s2).deleteNeighbour(getNode(s1));

                return 1;
            }
        }
        return 0;
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
            System.err.println("Check unsuccessful: string(s) is empty");
            return false;
        }
        // Checks if both strings doesn't exist
        else if(!this.contains(s1) || !this.contains(s2)){
            System.err.println("Check unsuccessful: string(s) doesn't exist");
            return false;
        }

    	// Grab the arraylist needed for the specific edge
        ArrayList<Node[]> edgeList = getEdges(type);
        
        // Ensure that arraylist is not null
        if (edgeList == null){
            System.err.println("Check unsuccessful: invalid type");
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

        // Ensure each node array is formatted alphabetically
        for (Node[] n : edgeList){
            if (n[0].getValue().compareTo(n[1].getValue()) > 0){
                // Swap if they're not alphabetical
                Node temp = n[0];
                n[0] = n[1];
                n[1] = temp;
            }
        }

        // Sort the edge list by the first node then the second node, if the first node is equal
        Collections.sort(edgeList, Comparator.comparing((Node[] arr) -> arr[0].getValue()).thenComparing(arr -> arr[1].getValue()));
 
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
        // Sort the nodes alphabetically
        Collections.sort(nodes, Comparator.comparing(Node::getValue));

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

        // Initialise a to-visit list of nodes and add the starting node to it
        Queue<Object[]> toVisitList = new LinkedList<Object[]>();
        toVisitList.add(new Object[] {getNode(startCity), busTickets, planeTickets, trainTickets});

        // Initalise string of visited cities
        ArrayList<String> visitedCities = new ArrayList<String>();


        // Go through the to visit list until it's empty
        while (!toVisitList.isEmpty()){
            // Pop the front of the queue
            Object[] current = toVisitList.remove();
            Node currentNode = (Node) current[0];
            int remainingBus = (int) current[1];
            int remainingPlane = (int) current[2];
            int remainingTrain = (int) current[3];


            // If current node has been visited already then skip
            if (visitedCities.contains(currentNode.getValue())){
                continue;
            }

            // Consider the current node visited
            visitedCities.add(currentNode.getValue());

            // Grab the neighbours of the current node
            ArrayList<Node> neighbours = currentNode.getNeighbours();
            

            // Go through each neighbour
            for (Node neighbour : neighbours) {
                // If a road exists between the current node and its neighbour and the user has sufficient tickets
                if (hasEdge(currentNode.getValue(), neighbour.getValue(), "Road") && busTickets > 0){
                    // Add the neighbour to the to-visit list and only decrement bus ticket
                    toVisitList.add(new Object[] {neighbour, remainingBus - 1, remainingPlane, remainingTrain});
                }
                // If a train exists between the current node and its neigbour and the user has sufficient tickets
                else if (hasEdge(currentNode.getValue(), neighbour.getValue(), "Rail") && trainTickets > 0){
                    // Add the neighbour to the to-visit list and only decrement train ticket
                    toVisitList.add(new Object[] {neighbour, remainingBus, remainingPlane, remainingTrain - 1});
                }
                // If a plane exists between the current node and its neigbour and the user has sufficient tickets
                else if (hasEdge(currentNode.getValue(), neighbour.getValue(), "Plane") && planeTickets > 0){
                    // Add the neighbour to the to-visit list and only decrement plane ticket
                    toVisitList.add(new Object[] {neighbour, remainingBus - 1, remainingPlane - 1, remainingTrain});
                }
            }
        }

        // Sort the visited cities
        Collections.sort(visitedCities);

        // Return the strings
        String output = "";
        for (String city : visitedCities) {
            output += city + ", ";
        }

        return output;
    }

    // Helper methods also used in the travel planner

    /**
     * Checks if a certain value is in the nodes list
     * @param s The node to find
     * @return Whether the list contains that value or not
     */
    public boolean contains(String s){
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
    public ArrayList<Node[]> getEdges(String type){
        // Make string uppercase
        String typeUpper = type.trim().toUpperCase();

        // Check what the string is referring to
        if (typeUpper.equals("PLANE")){
            return planeEdges;
        }
        else if (typeUpper.equals("ROAD") || typeUpper.equals("BUS")){
            return roadEdges;
        }
        else if (typeUpper.equals("RAIL") || typeUpper.equals("TRAIN")){
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
    public Node getNode(String s){
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
    public boolean containsEdge(ArrayList<Node[]> edgeList, Node[] array){
        // Loop through the edge list
        for (Node[] nodes : edgeList) {
            if (nodes[0].getValue().equals(array[0].getValue()) && nodes[1].getValue().equals(array[1].getValue()) || nodes[0].getValue().equals(array[1].getValue()) && nodes[1].getValue().equals(array[0].getValue())){
                return true;
            }
        }
        return false;
    }

    
}