// FindShortestPath java class prepared by Adam Yassine
public class FindShortestPath {
	
	// create a static counter that counts the length of the path 
	private static int counter;
	
	public static void main (String[] args) throws Exception {
		try{
			if (args.length < 1) {throw new Exception("No input file specified");}
				String dungeonFileName = args[0];
				
				Dungeon dungeon = new Dungeon(dungeonFileName);
				
				// Create an empty priority queue
				DLPriorityQueue<Hexagon> newQueue = new DLPriorityQueue<Hexagon>(); 
				
			    //Get start and exit chambers using the hexagon object
				Hexagon start = dungeon.getStart();
				Hexagon exit = dungeon.getExit();
	
			    // Add the starting chamber to the queue with a priority of 0
				newQueue.add(start, 0.0);
				//mark the start chamber as enqueued
				start.markEnqueued();
	
				// set exitFound as false as we have not found it yet
				boolean exitFound = false;
			    // Find shortest path using a while loop
				while (!newQueue.isEmpty() && exitFound == false) {
					
					// set curr as the node with the smallest priority and mark it as dequeued.
					Hexagon curr = newQueue.removeMin();
					curr.markDequeued();
				
					if (curr.equals(exit)) {
					// If the curr chamber is equal to the exit, then break the loop as we have found the exit
						exitFound = true;
						break;
					}
					
					//boolean variable for if there is a dragon in any neighboring chambers.
					boolean neighbouringDragon = false;

					//Iterating through the 6 neighboring chambers of the current chamber to check if any contain a dragon.
					for (int i = 0; i < 6; i++) {
					Hexagon neighbour = curr.getNeighbour(i);
					if (neighbour != null && neighbour.isDragon()) {
					neighbouringDragon = true;
					break;
						}
					}
				    // continue to iterate through the while loop if the curr contains a dragon or a neighbouring chamber that has a dragon
					if (curr.isDragon() || neighbouringDragon) {
						continue;
					}
					// call the SearchForExit method
				SearchForExit(curr, newQueue, dungeon);
					}
				// if exit is still not found then no path in the dungeon has been found
				if(exitFound == false) {
					System.out.println("No path found");	
		}
				else {
					  // otherwise, call the .size method to print out the length of the path
					  counter = newQueue.size();
			    	  System.out.println("Path of length " + counter + " found");
			    	}
				
		}
		// catch any exceptions thrown
		catch (Exception e) {
			System.out.println("Exception thrown");
		}
	
	}
	
	private static void SearchForExit(Hexagon curr, DLPriorityQueue<Hexagon> newQueue, Dungeon dungeon) {
		// method to find the exit chamber 
		
	    // Consider each one of the neighbouring chambers to the current one that are not null and not of type wall and have not been marked as dequeued
		for (int i = 0; i < 6; i++) {
			Hexagon neighbor = curr.getNeighbour(i);
			if (neighbor != null && neighbor.isWall() == false && neighbor.isMarkedDequeued() == false) {
				
		      	// set D be equal to distance from curr to the initial chamber plus 1
				int D = curr.getDistanceToStart() + 1;
				
		      	// set the distance of neighbour to the initial chamber to D if the distance of neighbour to initial chamber is greater than D
				if (D < neighbor.getDistanceToStart()) {
					neighbor.setDistanceToStart(D);
					neighbor.setPredecessor(curr);
					
						// update the neighbors priority in the priority queue if neighbour is marked enqueued and distance from it to the initial chamber was modified
						if (neighbor.isMarkedEnqueued())
							newQueue.updatePriority(neighbor, curr.getDistanceToStart() + curr.getDistanceToExit(dungeon));
				}
				if (neighbor.isMarkedEnqueued() == false) {
					// else add curr to the queue with priority equal to its distance to the initial chamber plus its distance to the exit
					newQueue.add(neighbor, neighbor.getDistanceToStart() + neighbor.getDistanceToExit(dungeon));
					neighbor.markEnqueued();
				}
										
			}
			
		}
			
	}
}

