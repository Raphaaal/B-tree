package Trees;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Node<Integer> {

	// A prendre d'au dessus
	private int treeOrder = 5;

	private int sizeMax = treeOrder-1 ;

	// La liste est de taille deux : index 0 pour le pointeur à gauche, index 1 pour le pointeur à droite
	public TreeMap<Integer, List<Node<Integer>>> data = new TreeMap<>();

	public Node<Integer> parent = null;

	public Node(TreeMap<Integer,List<Node<Integer>>> data, Node parent) {
		this.data = data;
		this.parent = parent;
	}



	public Node<Integer> search(Integer k) {
		if (this.data.keySet().contains(k))
			return this;
		else {
			Iterator<Integer> it = this.data.keySet().iterator();
			Integer val = it.next();
			Node<Integer> result=null;
			while((int)k > (int)val && it.hasNext()) {
				val = it.next();
			}
			if(val != this.data.lastKey()) {
				try {
					result = this.data.get(val).get(0).search(k);
				}catch(NullPointerException e){return this;}

			}
			else {
				try {
					result = this.data.get(val).get(1).search(k);
				}catch(NullPointerException e){return this;}
			}
			return result;
		}
	}


	/*
	public Node<Integer> search(Integer k) {
		if (this.data.keySet().contains(k))
			return this;
		else {
			Iterator<Integer> it = this.data.keySet().iterator();
			Integer val = it.next();
			Node<Integer> result=null;
			while((int)k < (int)val && it.hasNext()) {
				try {
					result = this.data.get(val).get(0).search(k);
				}catch(NullPointerException e){return this;}
				val = it.next();
				return result;
			}
			try {
				result = this.data.get(val).get(1).search(k);
			}catch(NullPointerException e){return this;}
			return result;
		}
	}
	 */

	public void addData(Integer i) {
		data.put(i, null);
		if(data.size() > sizeMax)  {
			//Integer key = findMiddle(data);
			addChild();
		}
	}

	public void addData2(Integer i, List<Node<Integer>> childPointers) {
		data.put(i, childPointers);
		if(data.size() > sizeMax)  {
			//Integer key = findMiddle(data);
			addChild();
		}
	}

	public Integer findMiddle(Map<Integer, List <Node <Integer>>> data) {
		Set<Integer> ks = data.keySet();
		int size = ks.size();
		Iterator<Integer> it=ks.iterator();		
		int curseur=0;
		while(curseur < size/2) {
			it.next();
			++curseur;
		}
		return it.next();
	}

	public void addChild(){
		Iterator<Integer> it = data.keySet().iterator();		
		Integer val = it.next();

		Integer middle = findMiddle(data);
		TreeMap<Integer, List<Node<Integer>>> newChildMap = new  TreeMap<>();
		//Map<Integer, List<Node<Integer>>> childRightMap = new  TreeMap<>();

		List<Node<Integer>> middlePointers = new ArrayList<>();


		while((int)val <= (int)middle) {
			newChildMap.put(val, null);
			it.remove();
			val = it.next();
		}

		newChildMap.remove(middle);

		// Pour sauter le middle
		/*
		val = it.next();
		int curseur=0;
		curseur =(data.keySet().size()/2) +1;
		 */

		/*
		while (curseur <= data.keySet().size() ) {
			childRightMap.put(val,null);
			if (it.hasNext())
				val = it.next();
			++curseur;

		}



		Set<Integer> ks = data.keySet();
		Iterator<Integer> itData=ks.iterator();
		while(itData.hasNext()) {
			if(itData.next() middle)
				itData.remove();
		}
		 */



		// Create the pointers for child (list)

		Node <Integer> newChild = new Node<Integer>(newChildMap, this.parent);
		//Node <Integer> childRight = new Node<Integer>(childRightMap, this.parent);

		middlePointers.add(0,newChild);
		middlePointers.add(1,this);

		if(this.parent != null) 
			// Gerer la max size
			this.parent.addData2(middle,middlePointers);
		else {
			TreeMap<Integer, List<Node<Integer>>> middleMap = new TreeMap<>();
			middleMap.put(middle, middlePointers);
			Node<Integer> newRoot = new Node<Integer>(middleMap, null);
			this.parent = newRoot;
			newChild.parent = newRoot;

		}
		/*
		System.out.println(this);
		System.out.println(this.parent);
		System.out.println(newChild);
		 */


	}

	public String recursivePrint() {

		String s="";
		Node<Integer> rootLeft=null;
		Node<Integer> rootRight=null;

		s+=" ";
		s+="\t" + this.data.keySet() ;

		for (Integer key : this.data.keySet()) {
			if(this.data.get(key) !=null) {

				System.out.println(this.data.keySet());

				if(this.data.get(key).get(0) !=null) {
					rootLeft=this.data.get(key).get(0);
					s+=rootLeft.recursivePrint();
				}
				//if(this.data.get(key).get(1) !=null) {
				//	rootRight=this.data.get(key).get(1);
				//s+=rootRight.recursivePrint();
				//}
			}

		}
		Integer maxKey = this.data.lastKey();
		if (this.data.get(maxKey) != null) {
			if(this.data.get(maxKey).get(1) !=null) {
				rootRight=this.data.get(maxKey).get(1);
				s+=rootRight.recursivePrint();
			}
		}
		return s;
	}

	@Override
	public String toString() {
		if(this.parent != null)
			return "" + data.keySet()  + " parent = " + parent.data.keySet();
		return "" + data.keySet()  + " parent = null" ;
	}



}
