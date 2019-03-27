package project6;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * This class is a Binary Search Tree class that is used to store MovieList objects. 
 * Inherits properties from Collection<E> and Iterable<E> Interface. 
 * @author Seulmin Ryu 
 */
public class BST<E extends Comparable <E>> implements Collection<E>, Iterable<E>{ 
	/**
	 * Node class that actually stores all the MovieList objects inside. 
	 * includes attributes data, which stores the object itself, left, which stores the reference to the node left of the node, 
	 * and right, which stores the reference to the node right of the node. 
	 * Also includes constructor Node(E data), which creates the Node object with just data attribute filled with 
	 * the value of the parameter, left and right attributes as null. 
	 * Also includes constructor Node (E data, Node<E> left, Node<E> right), which creates the Node object with 
	 * data left and right attributes filled with the values of the parameter.  
	 * @author Seulmin Ryu
	 */
	private static class Node <E extends Comparable <E>> {
		private E data; 
		private Node<E> left; 
		private Node<E> right; 
		
		public Node(E data) {
			this.data = data; 
			this.left = null; 
			this.right = null; 
		}
		public Node (E data, Node<E> left, Node<E> right) {
			this.data = data; 
			this.left = left; 
			this.right = right;
		}
	}
	
	//Create a node for root of the tree, left node, and right node. 
	private Node<E> root = null; 
	private Node<E> left = null; 
	private Node<E> right = null; 
	private int size = 0; 
	
	public BST() {
		//Empty Constructor 
	}
	
	/**
	 * Method that returns the reference to the element stored in the BST with a value that is equivalent to the value 
	 * passed as the parameter or returns null if no equal element exists in the BST. 
	 * @param value the element to be searched in the BST. 
	 * @return E element that is the reference to the element equal to the parameter value or null if not such element exists. 
	 */
	public E get(E value) { 
		Node<E> temp = root; 
		while (temp != null) {
			//Found the value being searched. 
			if (temp.data.compareTo(value) == 0) {
				return (E) temp.data; 
			}
			//Value being searched is less than that of the node, so check the left node. 
			else if (temp.data.compareTo(value) > 0) {
				temp = temp.left; 
			}
			//Value being searched is greater than that of the node, so check the right node. 
			else {
				temp = temp.right; 
			}
		}
		return null; 
 	}
	
	/**
	 * Returns a string representation of elements inside this collection in the order returned by the iterator. 
	 * @return string representation of the elements of the BST collection. 
	 */
	public String toString() {
		 if (size == 0) {
			 return "";
		 }
		 String string = ""; 
		 Iter iter = new Iter(); 
		 //Iterate through the collection 
		 while (iter.hasNext()) {
			 string = string + "[" + String.valueOf(iter.next()) + "],"; 
		 }
		 return string; 
	}
	
	/**
	 * Produce tree like string representation of this BST.  
	 * @return string containing tree-like representation of this BST. 
	 * @author Joanna Klukowska 
	 */
	public String toStringTreeFormat() {
		StringBuilder s = new StringBuilder();
		preOrderPrint(root, 0, s);
		return s.toString();
	}
	/**
	 * Uses pre-order traversal to produce a tree-like representation of this BST.
	 * @param tree the root of the current subtree. 
	 * @param level level(depth) of the current recursive call in the tree to 
	 * determine the indentation of each item. 
	 * @param output the string that accumulated the string representation of this BST. 
	 * @author Joanna Klukowska 
	 */
	private void preOrderPrint(Node<E> tree, int level, StringBuilder output) {
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++) {
					spaces += " ";
				}
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.data);
			preOrderPrint(tree.left, level + 1, output);
			preOrderPrint(tree.right , level + 1, output);
		}
		else { 
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++) {
					spaces += " ";
				}
				spaces += "|--";
			}
			output.append(spaces);
			output.append("null");
		}
	}
		
	
	/**
	 * Method that returns the Iterator object of the elements of the tree. 
	 * @return Iterator object that contains elements of the tree in preorder traversal order. 
	 */
	public Iterator<E> preorderIterator() {
		return new Iter("Preorder");
	}
	
	/**
	 * Method that returns the Iterator object of the elements of the tree. 
	 * @return Iterator object that contains elements of the tree in postorder traversal order. 
	 */
	public Iterator<E> postorderIterator() {
		return new Iter("Postorder");
	}
	
	/**
	 * Method that returns the least element in this set greater than or equal to the given element, or null if there is no such element.
	 * @param e element to be searched in the BST. 
	 * @return current element inside the BST that is greater than or equal to the element to be searched, 
	 * or null if there is no such element. 
	 */
	public E ceiling(E e) {
		//Is the element being searched null? 
		if (e == null) {
			return null; 
		}
		Iter iter = new Iter();
		int index = 0;
		//Iterate through the collection. 
		while (index <= iter.list.size()-1) {
			E current = iter.list.get(index);
			//The first element greater than or equal to the inorder collection of the BST. 
			if (current.compareTo(e) >= 0) {
				return current;
			}
			index++;
		}
		return null; 
	}
	
	/**
	 * Method that returns a shallow copy of this BST instance.
	 * @return tree shallow copy of this BST instance. 
	 */
	public Object clone() {
		BST<E> tree = this; 
		return tree; 
	}
	
	/**
	 * Method that returns the lowest element in this BST. 
	 * @return node.data the lowest element in the BST. 
	 */
	public E first() {
		//Start at the root. 
		Node<E> node = root; 
		//Shift all the way to the left. 
        while (node.left != null) { 
            node = node.left; 
        } 
        return node.data;
	}
	
	/**
	 * Method that returns the greatest element in the BST less than or equal to the given element, or null if there is no such element.
	 * @param e element to be searched in the BST. 
	 * @return greatest element in the BST less than or equal to the given element, or null. 
	 */
	public E floor(E e) {
		Iter iter = new Iter();
		int index = 0;
		//Iterate through the BST collection. 
		while (index <= iter.list.size()-1) {
			//Get new element from each iteration. 
			E current = iter.list.get(index);
			//Is the element from the collection greater than the element being searched? 
			if (current.compareTo(e) > 0) {
				if (index > 0) {
					return iter.list.get(index-1);
				}
				else {
					return null; 
				}
			}
			//When the element from the collection is equal to the element being searched. 
			else if (current.compareTo(e) == 0) {
				return current; 
			}
			index++;
		}
		return iter.list.get(iter.list.size() - 1);
	}
	
	
	/**
	 * Method that returns the least element in the BST strictly greater than the given element, or null if there is no such element.
	 * @param e element to be searched in the BST. 
	 * @return current the least element in the BST strictly greater than the given element, or null. 
	 */
	public E higher(E e) {
		Iter iter = new Iter();
		int index = 0;
		while (index <= iter.list.size()-1) {
			E current = iter.list.get(index);
			//Is it greater than the element being searched? 
			if (current.compareTo(e) > 0) {
				return current;
			}
			index++;
		}
		return null;
	}
	
	/**
	 * Method that returns the highest element currently in the BST.
	 * @return node.data the greatest element in the BST. 
	 */
	public E last() {
		Node<E> node = root; 
		//Go all the way to the right. 
        while (node.right != null) { 
            node = node.right; 
        } 
        return node.data;
	}
	
	/**
	 * Method that returns the greatest element in the BST strictly less than the given element, or null if there is no such element.
	 * @param e element to be searched in the BST. 
	 * @return the greatest element in the BST that is strictly less than the element to be searched. 
	 */
	public E lower(E e) {
		Iter iter = new Iter();
		int index = 0;
		while (index <= iter.list.size()-1) {
			E current = iter.list.get(index);
			//First instance of the element of the collection being greater than the element being searched. 
			if (current.compareTo(e) >= 0) {
				if (index > 0) {
					return iter.list.get(index-1);
				}
				else {
					return null; 
				}
			}
			index++;
		}
		return iter.list.get(iter.list.size() - 1);
	}
	
	/**
	 * Method that ensures that the BST contains the element given by adding it to the BST collection. 
	 * @param e element that is being checked to see if the element is in the collection. 
	 * @return true if element is being added. 
	 * @return false if object e is null.
	 */
	public boolean add(E e) {
		try {
			//Does the collection have this element? 
			if (this.contains(e)) {
				return false;
			}
			root = add(e, root); 
			size++;
			return true; 
		}
		catch (IllegalArgumentException E) {
			return false; 
		}
	}
	/**
	 * Recursive helper method that actually adds the element given into the collection. 
	 * @param e element that is being added to the BST collection. 
	 * @param node the node that will have the element being added. 
	 * @return node with the element that is being added. 
	 */
	private Node<E> add(E e, Node<E> node) {
		if (node == null) {
			Node<E> n = new Node<E>(e); 
			return n; 
		}
		if (node.data.compareTo(e) > 0) {
			node.left = add(e, node.left);
		}
		else if (node.data.compareTo(e) < 0) {
			node.right = add(e, node.right); 
		}
		return node;
	}
	
	/**
	 * Method that removes all of the elements from this collection. 
	 */
	public void clear() {
		root = null; 
		size = 0; 
	}
	
	/**
	 * Checks whether or not the BST collection has the Object given in the parameter. 
	 * @param o specified object that is being searched within tree to see if it exists. 
	 * @return true if object given exists in the BST. 
	 * @return false if object given is not in the BST. 
	 */
	public boolean contains(Object o) {
		//Is the object being searched null?
		if (o == null) {
			return false; 
		}
		//Is the collection empty? 
		if (this.isEmpty()) {
			return false; 
		}
		E data = (E) o;
		return contains(data, root); 
	}
	/**
	 * Recursive helper method that checks if the object given exists in the BST. 
	 * @param o object that is being checked inside the BST collection. 
	 * @param node nodes within the BST that is being checked to see if the object given exists. 
	 * @return true if object given exists. 
	 * @return false if object does not exist. 
	 */
	public boolean contains(E o, Node<E> node) {
		if (node == null) {
			return false; 
		}
		else if (node.data.compareTo(o) > 0) {
			return contains(o, node.left);
		}
		else if (node.data.compareTo(o) < 0) {
			return contains(o, node.right);
		}
		else {
			return true;
		}
	}
	
	/**
	 * Method that checks to see if collection contains all of the elements in the collection given in parameter. 
	 * @param c collection that has the elements being searched within the Binary Search Tree. 
	 * @return true if elements inside the given collection c exists in the Binary Search Tree being searched. 
	 * @return false if elements inside the given collection c does not exist in the Binary Search Tree being searched. 
	 */
	public boolean containsAll(Collection <?> c) {
		for (Object o: c) {
			if (!(this.contains(o))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method that checks whether object given in parameter and object within the collection are equivalent or not. 
	 * @param o object that is being compared to the objects inside the collection.
	 * @return true if objects are equal to each other. 
	 * @return false if objects are not equal to each other. 
	 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof BST) {
			BST tree = (BST) o;
			if (this.size == tree.size) {
				Iterator treeItr = this.iterator();
				Iterator otherItr = tree.iterator(); 
				
				while (treeItr.hasNext()) {
					//Do the next elements match? 
					Object obj1 = treeItr.next();
					Object obj2 = otherItr.next(); 
					
					if (!(obj1.equals(obj2))) {
						return false; 
					}
				}
				return true;
			}
		}
		return false; 
	}
	
	/**
	 * Method that checks whether the Binary Search Tree is empty by checking if the size of the tree is 0. 
	 * @return true if the size of the BST is 0, 
	 * @return false if BST is not empty.
	 */
	public boolean isEmpty() {
		return size == 0; 
	}
	
	/**
	 * Iterator class that is used to create Iterator objects whenever the iterator() method is called. 
	 * The default constructor traverses through the BST using inorder traversal, and adds the values of the node in ArrayList list. 
	 * The constructor that takes in a string as a parameter will traverse through the BST using preorder or postorder traversal, 
	 * Depending on whatever is specified in the parameter, and adds the values of the node in ArrayList list. 
	 * Overrides hasNext and Next methods coming from Iterator class. 
	 * Also contains inorder, postorder, and preorder methods that traverses through the tree accordingly. 
	 * @author Seulmin Ryu 
	 */
	private class Iter implements Iterator<E>{
		Node<E> node; 
		ArrayList<E> list = new ArrayList<E>();   
		int i = 0;
		
		public Iter() {
			node = root;
			i = 0;
			inorder(node);
		}
		public Iter(String s) {
			node = root;
			i = 0;
			if (s.equals("Postorder")) {
				postorder(node);
			}
			else if (s.equals("Preorder")){
				preorder(node);
			}
		}
		@Override
		public boolean hasNext() {
			return i <= list.size() - 1;
		}
		@Override
		public E next() {
			if (!hasNext()) {
				return null;
			}
			E data = list.get(i);
			i++;
			return data;
		}
		
		public void inorder(Node<E> n) {
			if (n == null) {
				return; 
			}
			inorder(n.left); 
			list.add(n.data);
			inorder(n.right);
		}
		public void postorder(Node<E> n) {
			if (n == null) {
				return; 
			}
			postorder(n.left);
			postorder(n.right);
			list.add(n.data);
		}
		public void preorder(Node<E> n) {
			if (n == null) {
				return;
			}
			list.add(n.data);
			preorder(n.left);
			preorder(n.right);
		}
 	}
	
	/**
	 * Iterator method that comes from the iterable interface. 
	 * @return iterator object that iterated through the BST with inorder traversal. 
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iter(); 
	}
	
	
	
	/**
	 * Method that removes a single instance of the specified element from this BST collection, if it exists. 
	 * @return true if the object that is being searched exists in the collection. 
	 * @return false if the object that is being searched does not exist in the collection. 
	 */
	public boolean remove(Object o) {
		try {
			//Does the collection not have the object in the parameter? 
			if (!this.contains(o)) {
				return false;
			}
			root = RecRemove(o, root); 
			size--;
			return true;
		}
		catch (IllegalArgumentException E) {
			return false; 
		}	 
	}
	/**
	 * Recursive method that actually removes the object specified from the BST collection. 
	 * @param element element that needs to be removed from the collection. 
	 * @param node node within the collection that might contain the element that is being searched. 
	 * @return node that used to contain the element that is being searched. 
	 */
	private Node<E> RecRemove(Object element, Node<E> node) {
        if (node == null) {
            return null; 
        }
        else if (node.data.compareTo((E) element) > 0) {
            node.left = RecRemove(element, node.left); 
        }
        else if (node.data.compareTo((E) element) < 0) {
        		node.right = RecRemove(element, node.right); 
        }
        else {
	        	if (node.left == null && node.right == null) {
	        		return null; 
	        	}
	        	else if (node.left == null) {
	        		return node.right; 
	        	}
	        	else if (node.right == null) {
	        		return node.left;
	        	}
	        //If it has both children, first get predecessor.
	        node.data = getpredecessor(node.left);
	        node.left = RecRemove(node.data,node.left);
	        return node;
        }
        return node; 
    }
	/**
	 * Method that gets the predecessor of the BST collection, which allows the tree to replace the removed value with 
	 * the value that being returned in this method. 
	 * @param n nodes in the collection. 
	 * @return the element that is the predecessor of the node of the element being removed. 
	 */
	private E getpredecessor(Node<E> n) {
		if (n.right == null) {
			return null; 
		}
		else {
			while (n.right != null) {
				n = n.right;  //Moving the node all the way to the right 
			}
			return n.data; 
		}
	}
	
	/**
	 * Method that returns the number of elements in the BST collection. 
	 */
	public int size() {
		return size(root); 
	}
	/**
	 * Recursive method that actually counts how many nodes are in the BST. 
	 * @param n nodes in the BST. 
	 * @return integer value of the number of nodes in the BST. 
	 */
	private int size(Node<E> n) {
		if (n == null) {
			return 0;
		}
		//Count the number of nodes recursively. 
		return size(n.left) + size(n.right) + 1; 
	}
	
	/**
	 * Returns an array of objects containing all of the elements in this BST collection.
	 * @return array with all of the elements in the BST. 
	 */
	public Object[] toArray() {
		Object[] array = new Object[size]; 
		int i = 0;
		
		for (E movie : this) {
			array[i++] = movie;
		}
		return array;
	}
	
	/**
	 * Returns an array containing all of the elements in the collection; 
	 * the runtime type of the returned array is the same as that of the specified array.
	 * @return an array that contains all the elements in the collection. 
	 */
	public <T> T[] toArray(T[] a) {
		if (a.length < size)
            a = (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        
		int i = 0;
        Object[] result = a;
        
        Iterator<E> treeItr = this.iterator();
        for (E element = root.data; element != null; element = (E) treeItr.next())
            result[i++] = element;

        if (a.length > size)
            a[size] = null;

        return a;
	}
	
	/**
	 * Adds all of the elements in the specified collection to this collection.
	 * Not implemented. 
	 */
	public boolean addAll (Collection <? extends E> c) {
		throw new UnsupportedOperationException("Not implemented."); 
	}
	
	/**
	 * Removes all of this collection’s elements that are also contained in the specified collection.
	 * Not implemented. 
	 */
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not implemented.");
	}
	
	/**
	 * Retains only the elements in this collection that are contained in the specified collection. 
	 * Not implemented. 
	 */
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not implemented.");
	}
	
}
