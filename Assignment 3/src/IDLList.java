// Name: Gundeep Singh Saluja
//CWID: 20005427

import java.util.ArrayList;

public class IDLList<E> {

	private static class Node<E> {
		E data;
		Node<E> next;
		Node<E> prev;

		Node(E item) {
			this.data = item;
		}

		Node(E item, Node<E> prev, Node<E> next) {
			this.data = item;
			this.prev = prev;
			this.next = next;
		}
	}

	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;

	public IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<Node<E>>();
	}

	public boolean add(int index, E item) {
		if (index < 0 || index > size)
			System.out.println("Index out of bounds!");
		if (index == 0)
			add(item);
		else if (index == size)
			append(item);
		else {
			Node<E> temp = this.indices.get(index);
			Node<E> new_node = new Node<E>(item, temp.prev, temp);
			temp.prev.next = new_node;
			temp.prev = new_node;
			indices.add(index, new_node);
			size++;
		}
		System.out.println(item + " added at position " + index);
		return true;

	}

	public boolean add(E item) {
		if (size == 0) {
			Node<E> newnode = new Node<E>(item);
			head = newnode;
			tail = newnode;
			indices.add(newnode);
		} else {
			Node<E> newnode = new Node<E>(item, null, head);
			head.prev = newnode;
			head = newnode;
			indices.add(0, newnode);
		}
		size++;
		System.out.println(item + " prepended to the linked list");
		return true;
	}

	public boolean append(E item) {
		if (size == 0) {
			Node<E> newnode = new Node<E>(item);
			head = newnode;
			tail = newnode;
			indices.add(newnode);
		} else {
			Node<E> newnode = new Node<E>(item, tail, null);
			tail.next = newnode;
			tail = newnode;
			indices.add(newnode);
		}
		System.out.println(item + " appended to the linked list");
		size++;
		return true;
	}

	public E get(int index) {
		System.out.print("The item at index " + index + " is: ");
		return indices.get(index).data;
	}

	public E getHead() {
		System.out.print("The first item of linked list is: ");
		return head.data;
	}

	public E getLast() {
		System.out.print("The last item of the linked list is: ");
		return tail.data;
	}

	public int size() {
		System.out.print("The size of the linked list is: ");
		return indices.size();
	}

	public E remove() {
		E item;

		if (size == 0)
			throw new IndexOutOfBoundsException("The linked list is already empty!");

		else if (size == 1) {
			item = head.data;
			head = null;
			tail = null;
		} else {
			item = head.data;
			head.next.prev = null;
			head = head.next;
		}

		indices.remove(0);
		size--;
		System.out.print("First item of the linked list removed which was ");
		return item;
	}

	public E removeLast() {
		E item;

		if (size == 0)
			throw new IndexOutOfBoundsException("The linked list is already empty!");

		else if (size == 1) {
			item = tail.data;
			head = null;
			tail = null;
		}

		else {
			item = tail.data;
			tail.prev.next = null;
			tail = tail.prev;
		}

		indices.remove(size - 1);
		size--;
		System.out.print("Last item of the linked list removed which was ");
		return item;
	}

	public E removeAt(int index) {
		E item_at_index;

		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
				
		else if (index == 0) {
			item_at_index = head.data;
			head.next.prev = null;
			head = head.next;
		}
		
		else if (size == 1) {
			item_at_index = head.data;
			head = null;
			tail = null;
		}

		else if (index == size - 1) {
			item_at_index = tail.data;
			tail.prev.next = null;
			tail = tail.prev;

		} else {
			Node<E> temp = indices.get(index);
			item_at_index = temp.data;
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
		}

		indices.remove(index);
		size--;
		System.out.print("Item at index " + index + " removed from the linked list which was ");
		return item_at_index;
	}

	public boolean remove(E item) {
		for (int i = 0; i < indices.size(); i++) {
			if (indices.get(i).data == item) {
				removeAt(i);
				System.out.println(item + " removed from the linked list!");
				return true;
			}
		}
		System.out.println(item + " doesn't exist in the linked list!");
		return false;
	}

	public String toString() {
		String ll_str = "";
		for (int i = 0; i < size; i++) {
			if (i != size - 1)
				ll_str = ll_str + (indices.get(i).data + ", ");
			else
				ll_str = ll_str + (indices.get(i).data);
		}
		ll_str += "";
		System.out.print("The linked list is: ");
		return ll_str;
	}
}
