import java.util.ArrayList;

public class Node{

    // Parameters
    private String value;
    private ArrayList<Node> neighbours;

    // Constructor
    public Node(String s){
        value = s;
        neighbours = new ArrayList<Node>();
    }

    // Methods

    /**
     * Get the location value of the node
     * @return The string of the location
     */
    public String getValue(){
        return value;
    }

    /**
     * Adds a new neighbour
     * @param n A node location
     */
    public void addNeighbour(Node n){
        neighbours.add(n);
    }

    /**
     * Deletes a neighbour
     * @param n The node location to delete
     */
    public void deleteNeighbour(Node n){
        neighbours.remove(n);
    }

    /**
     * Returns a given neighbour 
     * @param s The value of the neighbour
     * @return The node of the neighbour
     */
    public String getNeighbour(String s){
        for(Node n : neighbours){
            if(n.value.equals(s)){
                return s;
            }
        }

        return "";
    }

    /**
     * Gets all the neighbours of a node
     * @return The arraylist of neighbours
     */
    public ArrayList<Node> getNeighbours(){
        return neighbours;
    }
}