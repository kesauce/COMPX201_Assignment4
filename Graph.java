import java.util.ArrayList;

public class Graph{

    // Parameters
    private ArrayList<Node> nodes;

    // Constructor
    public Graph(){
        nodes = new ArrayList<Node>();
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
        Node node;
        for(Node n : nodes){
            if(n.getValue().equals(s)){
                node = n;
            }
        }


    }

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
}