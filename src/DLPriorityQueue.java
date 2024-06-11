// DLPriorityQueue java class prepared by Adam Yassine
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {

	private DLinkedNode<T> front; // first node of the doubly linked list
	private DLinkedNode<T> rear; // last node of the doubly linked list
	private int count; // number of data items in the queue

	public DLPriorityQueue() {
		// create an empty queue by setting the front and rear to null and count to zero
		front = null;
		rear = null;
		count = 0;
	}

	public void add(T dataItem, double priority) {
		// method to add a dataItem given the priority

		// create a new node
		DLinkedNode<T> newNode = new DLinkedNode<>(dataItem, priority);
		// set a current node variable to front;
		DLinkedNode<T> currNode = front;
		// if the queue is empty set front and rear to the new node
		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			while (currNode != null && priority > currNode.getPriority()) {
				// set currNode to the next node if given priority is greater than the priority of currNode
				currNode = currNode.getNext();
			}

			if (currNode == front && currNode != null) {
				// use/call the addFront method if the currNode is located in the front of the queue
				addFront(dataItem, priority, newNode);
			} else if (currNode != null) {
				// use/call the addMiddle method if the currNode is located somewhere of the queue such as in the middle
				addMiddle(dataItem, priority, newNode, currNode);
			} else {
				// use/call the addRear method if the currNode is located in the rear/last index of the queue
				addRear(dataItem, priority, newNode, currNode);
			}
		}
		// add count by one for every data item added to the queue
		count++;
	}

	private void addFront(T dataItem, double priority, DLinkedNode<T> newNode) {
		// method to add new node to the front of the queue

		//set the next node of newNode to be the front node
		newNode.setNext(front);
		
		// set the previous node of front to be newNode		
		front.setPrev(newNode);
		// set front equal to the new node
		front = newNode;
	}

	private void addMiddle(T dataItem, double priority, DLinkedNode<T> newNode, DLinkedNode<T> currNode) {
		// method to add new node somewhere in the middle of the queue

		//set the next node of newNode to be the currNode
		newNode.setNext(currNode);
		
		// set the previous newNode before the previous node of currNode
		newNode.setPrev(currNode.getPrev());
		
		// set the next node of the previous node of currNode to be newNode 
		currNode.getPrev().setNext(newNode);
		
		// set the previous node of currNode to be newNode		
		currNode.setPrev(newNode);
	}

	private void addRear(T dataItem, double priority, DLinkedNode<T> newNode, DLinkedNode<T> currNode) {
		// method to add new node to the rear of the queue

		//set the next node of rear to be the newNode
		rear.setNext(newNode);
		
		// set the previous node of newNode to be rear		
		newNode.setPrev(rear);
		
		//set the next node of newNode to null
		newNode.setNext(null);
		
		// set rear equal to newNode
		rear = newNode;
	}

	public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
		// method to change the priority of the data item to the new given priority

		// set currNode equal to the front
		DLinkedNode<T> currNode = front;
		while (currNode != null && !dataItem.equals(currNode.getDataItem())) {
			// set currNode to go to the next node while currNode is not null and does not equal the given dataItem
			currNode = currNode.getNext();

		}
		if (currNode == null) {
			// throw an exception if currNode is null
			throw new InvalidElementException("invalid element");
		}

		else if (currNode == front) {
			// call the updateFront method if currNode is in the front of the queue
			updateFront(dataItem, newPriority, currNode);
			
		} else if (currNode == rear) {
			// call the updateRear method if currNode is in the rear of the queue
			
			updateRear(dataItem, newPriority, currNode);
		} else {
			// call the updateMiddle method if currNode is somewhere in the middle of the queue
			
			updateMiddle(dataItem, newPriority, currNode);

		}

	}

	private void updateFront(T dataItem, double newPriority, DLinkedNode<T> currNode) throws InvalidElementException {
		// method to update front of the queue to the currNode
		if (currNode == front) {
			currNode = currNode.getNext();
			front = currNode;
			if (front == null) {
				rear = null;
			}
			add(dataItem, newPriority);
			count--;
		}
	}

	private void updateRear(T dataItem, double newPriority, DLinkedNode<T> curr) throws InvalidElementException {
		// method to update rear of the queue to the currNode
		if (curr == rear) {
			curr = curr.getPrev();
			curr.setNext(null);
			rear = curr;
			add(dataItem, newPriority);
			count--;
		}
	}

	private void updateMiddle(T dataItem, double newPriority, DLinkedNode<T> curr) throws InvalidElementException {
		// method to update a node that's in the middle of the queue to the currNode
		curr.getNext().setPrev(curr.getPrev());
		curr.getPrev().setNext(curr.getNext());
		add(dataItem, newPriority);
		count--;
	}

	public T removeMin() throws EmptyPriorityQueueException {
		// method to remove and return the node with the smallest priority
		if (isEmpty())
			// throw an exception if the queue is empty
			throw new EmptyPriorityQueueException("Priority Queue is empty");
		
		// create a generic variable that invokes the data item of the front
		T minData = front.getDataItem();
		front = front.getNext();

		if (front == null) {
			// set rear to null if the front is also null
			rear = null;
		} else {
			// set the previous of the front to null which should remove the smallest
			// dataItem
			front.setPrev(null);

		}
		// subtract the count by 1 for every node
		count--;
		// return the dataItem with the smallest priority
		return minData;

	}

	public boolean isEmpty() {
		if (rear == null && front == null) {
			// return true if the queue is empty (if front and rear are null)
			return true;
		} else {
			// return false if otherwise not empty
			return false;
		}
	}

	public int size() {
		// return the number of data items in the priority queue;
		return count;
	}

	public String toString() {
		// create a stringBuilder object to create the string of the queue
		StringBuilder stringBuilder = new StringBuilder();
		DLinkedNode<T> currNode = front;
		while (currNode != null) {
			// append the the dataItem to the string builder and iterate through the queue until you reach the last node
			stringBuilder.append(currNode.getDataItem());

			currNode = currNode.getNext();
		}
		// return the string representation of the queue
		return stringBuilder.toString();
	}

	public DLinkedNode<T> getRear() {
		// return rear
		return rear;
	}
}
