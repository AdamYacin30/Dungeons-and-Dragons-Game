// DLinkedNode java Class prepared by Adam Yassine
public class DLinkedNode<T> {

	// initialize instance variables
	private T dataItem;
	private double priority;
	private DLinkedNode<T> next;
	private DLinkedNode<T> prev;
	
	// 
	public DLinkedNode(T data, double prio) {
		this.dataItem = data;
		this.priority = prio;
	}
	
	// create a new node
	public DLinkedNode() {
		dataItem = null;
		priority = 0;	
	}
	
	// Getter Methods
	public double getPriority() {
		return priority;
	} 
	
	public T getDataItem() {
		return dataItem;
	} 
	public DLinkedNode<T> getNext() {
		return next;
	} 
	
	public DLinkedNode<T> getPrev() {
		return prev;
	}
	
	
	// Setter methods
	public void setDataItem (T dataItem) {
		this.dataItem = dataItem;
	}
	
	public void setNext(DLinkedNode<T> next) {
		this.next = next;
	}
	
	public void setPrev(DLinkedNode<T> prev) {
		this.prev = prev;
	}
	
	public void setPriority(double priority) {
		this.priority = priority;
	}

}
