import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class TravelPlanner{
    public void main(String[] args){
        // Checks to see if the program is running 
        boolean isRunning = true; 

        // Initialise the graph
        Graph graph = new Graph();

        // Initialising scanner
        Scanner scanner;
        try{
            // Initialising to read the appliance csv
            String filePath = "nz_city_connections.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = "";


                // Read the header then read the next line
                reader.readLine();
                line = reader.readLine();
                
                // Read the entire csv file
                while (line != null){
                    // Split and store each value in a line to an array
                    String[] csvRow = line.split(",");

                    // Add the nodes
                    graph.addNode(csvRow[0]);
                    graph.addNode(csvRow[1]);
                    graph.addEdge(csvRow[0], csvRow[1], csvRow[2]);

                    // Read the next line
                    line = reader.readLine();
                }

                 // Close the reader 
                 reader.close();
        }
        catch (Exception e){
            System.out.println("There was an error with the file processing.");
        }


        while(isRunning){
            try{
    
                // Initalise user input
                int userInput = 0;
                scanner = new Scanner(System.in);
    
                // Print out the list of console functions that the user can run
                System.out.println("--------------------");
                System.out.println("Main Menu: ");
                System.out.println("1. Add an edge");
                System.out.println("2. Remove an edge");
                System.out.println("3. Find edge");
                System.out.println("4. Print edge");
                System.out.println("5. Print reachable cities");
                System.out.println("6: Exit");
    
                // Loops through user input to ensure they pick the right numbner
                while(true){
                    try{
                        System.out.print("Enter a number that corresponds to your request: ");
                        userInput = Integer.parseInt(scanner.nextLine());

                        // If number if valid then break
                        if (userInput > 0  && userInput <= 6){
                            break;
                        }
                        else{
                            System.err.println("Invalid number. Choose from 1 to 6.");
                            continue;
                        }
                    }
                    catch (Exception e){
                        System.out.println("Invalid number. " + e.getMessage());
                    }
                }
    
                // Adds an edge
                if (userInput == 1){
    
                    System.out.println("--------------------");
                    System.out.println("Adding an edge...");
                    
                    // Ask user for locations
                    System.out.println("Enter the first city: ");
                    String lineFirst = scanner.nextLine().trim();
                    String firstLocation = lineFirst.substring(0, 1).toUpperCase() + lineFirst.substring(1).toLowerCase();

                    System.out.println("Enter the second city: ");
                    String lineSecond = scanner.nextLine().trim();
                    String secondLocation = lineSecond.substring(0, 1).toUpperCase() + lineSecond.substring(1).toLowerCase();

                    System.out.println("Enter the edge type");
                    String lineType = scanner.nextLine().trim();
                    String type = lineType.substring(0, 1).toUpperCase() + lineType.substring(1).toLowerCase();

                    graph.addEdge(secondLocation, firstLocation, type);
    
                    // Return or quit
                    System.out.println("Returning to main menu...");
                    Thread.sleep(2000);
                    
                }

                // Remove an edge
                else if (userInput == 2){
                    System.out.println("--------------------");
                    System.out.println("Adding an edge...");
                    
                    // Ask user for locations
                    System.out.println("Enter the first city: ");
                    String lineFirst = scanner.nextLine().trim();
                    String firstLocation = lineFirst.substring(0, 1).toUpperCase() + lineFirst.substring(1).toLowerCase();

                    System.out.println("Enter the second city: ");
                    String lineSecond = scanner.nextLine().trim();
                    String secondLocation = lineSecond.substring(0, 1).toUpperCase() + lineSecond.substring(1).toLowerCase();

                    System.out.println("Enter the edge type");
                    String lineType = scanner.nextLine().trim();
                    String type = lineType.substring(0, 1).toUpperCase() + lineType.substring(1).toLowerCase();

                    graph.deleteEdge(secondLocation, firstLocation, type);
    
                    // Return or quit
                    System.out.println("Returning to main menu...");
                    Thread.sleep(2000);
                }

                // Find an edge
                else if (userInput == 3){
                    System.out.println("--------------------");
                    System.out.println("Finding an edge...");
                    
                    // Ask user for locations
                    System.out.println("Enter the first city: ");
                    String lineFirst = scanner.nextLine().trim();
                    String firstLocation = lineFirst.substring(0, 1).toUpperCase() + lineFirst.substring(1).toLowerCase();

                    System.out.println("Enter the second city: ");
                    String lineSecond = scanner.nextLine().trim();
                    String secondLocation = lineSecond.substring(0, 1).toUpperCase() + lineSecond.substring(1).toLowerCase();

                    System.out.println("Enter the edge type");
                    String lineType = scanner.nextLine().trim();
                    String type = lineType.substring(0, 1).toUpperCase() + lineType.substring(1).toLowerCase();

                    // Check if edge exists
                    boolean hasEdge = graph.hasEdge(secondLocation, firstLocation, type);

                    // Print results
                    if (hasEdge){
                        System.out.println("An edge of type " + type + " exists between " + firstLocation + " and " + secondLocation);
                    }
                    else{
                        System.out.println("An edge of type " + type + " does not exist between " + firstLocation + " and " + secondLocation);
                    }

                    // Return or quit
                    System.out.println("Returning to main menu...");
                    Thread.sleep(2000);
                }
            
                // Print edge
                else if (userInput == 4){
                    System.out.println("--------------------");
                    System.out.println("Printing an edge...");
                    
                    // Ask user for edge type
                    System.out.println("Enter the edge type");
                    String typeLine = scanner.nextLine().trim();
                    String type = typeLine.substring(0, 1).toUpperCase() + typeLine.substring(1).toLowerCase();

                    // Check if edge exists
                    String edges = graph.getEdgesOfType(type);

                    // Print results
                    System.out.println(edges);

                    // Return or quit
                    System.out.println("Returning to main menu...");
                    Thread.sleep(2000);
                }
            
                // Print reachable cities
                else if (userInput == 5){
                    System.out.println("--------------------");
                    System.out.println("Finding an reachable cities...");
                    
                    // Ask user for locations
                    System.out.println("Enter the staring city: ");
                    String line = scanner.nextLine().trim();
                    String startLocation = line.substring(0, 1).toUpperCase() + line.substring(1).toLowerCase();;
                    

                    // Ask user for number of bus tickets
                    int busTickets = 0;
                    while(true){
                        try{
                            System.out.print("Enter number of bus tickets: ");
                            busTickets = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch (Exception e){
                            System.out.println("Invalid price." + e.getMessage());
                        }
                    }

                    // Ask user for number of train tickets
                    int trainTickets = 0;
                    while(true){
                        try{
                            System.out.print("Enter number of train tickets: ");
                            trainTickets = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch (Exception e){
                            System.out.println("Invalid price." + e.getMessage());
                        }
                    }

                    // Ask user for number of plane tickets
                    int planeTickets = 0;
                    while(true){
                        try{
                            System.out.print("Enter number of plane tickets: ");
                            planeTickets = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch (Exception e){
                            System.out.println("Invalid price." + e.getMessage());
                        }
                    }

                    // Find the reachable cities
                    String reachableCities = graph.ticketTraverse(startLocation, busTickets, planeTickets, trainTickets);
                    System.out.println("Reachable cities from " + startLocation + ": " + reachableCities);
                    

                    // Return or quit
                    System.out.println("Returning to main menu...");
                    Thread.sleep(2000);
                }
            
                // Exits the program
                else if (userInput == 6){
                    
                    // Stops the program
                    System.out.println("----------");
                    System.err.println("Program terminated...");
                    isRunning = false;
                    System.exit(0);
                }
            }

            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}