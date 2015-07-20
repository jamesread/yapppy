package com.example.yappy;

import java.util.ArrayList;
import java.util.Iterator;

public class BogoTree<T> implements Iterable<T> {
	private class Node {
		private T value;
		private ArrayList<T> children = new ArrayList<T>();
		
		public Node(T t) {
			this.value = t; 
		}
	}
	
	public Node root;
	
	public void add(T t) {
		if (root == null) {
			root = new Node(t); 
		} 
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}
	
}
