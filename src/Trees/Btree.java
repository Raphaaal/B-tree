package Trees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Btree {

	int order;
	Node<Integer> root;

	Btree(Integer i, int order) {
		this.order = order;
		TreeMap<Integer, List<Node<Integer>>> temp = new TreeMap<>();
		temp.put(i, null);
		this.root = new Node<Integer>(temp, null, this);
	}

	public Node<Integer> search(Integer k) {
		return root.search(k);
	}

	public void insert(Integer k) {
		Node<Integer> insertionNode = this.searchForInsertion(k);
		if(insertionNode.data.containsKey(k))
			throw new IllegalArgumentException("Valeur déjà présente dans l'arbre");
		insertionNode.addData(k);
	}

	public Node<Integer> searchForInsertion(int k) {
		while(root.parent != null) 
			root = root.parent;
		Node<Integer> searchResult = root.search(k);
		return searchResult;
	}

	public Node<Integer> search(int k) {
		while(root.parent != null) 
			root = root.parent;
		Node<Integer> searchResult = root.search(k);
		if(searchResult.data.containsKey(k))
			return searchResult;
		return null;
	}
	
	public void deletion(Integer k) {
		   
        Node<Integer> nodeKey=searchForInsertion(k);
        int tmp=0;
        if(nodeKey.data.get(k)==null)
            nodeKey.data.remove(k);
        else {
            tmp=nodeKey.data.get(k).get(0).data.lastKey();

            while(nodeKey.data.get(tmp) !=null && nodeKey.data.get(tmp).get(1)!=null) {
                tmp=nodeKey.data.get(k).get(1).data.lastKey();
           
            }
            Node<Integer> nodeParent=searchForInsertion(k);
            nodeParent.data.get(k).get(0).data.remove(tmp);
   
            List <Node <Integer>> child=nodeParent.data.get(k);
            nodeKey.data.remove(k);
            nodeKey.data.remove(tmp);
            nodeKey.data.put(tmp,child);
        }
    }

	@Override
	public String toString() {
		String s ="";
		System.out.println("");
		while(root.parent != null) 
			root = root.parent;
		s += "\t\t\t\t\t\t\t" +  root.toString() + "\n";

		Set<Node<Integer>> childrenNodesBefore = new HashSet<>();
		Set<Node<Integer>> childrenNodesAfter = new HashSet<>();

		for(Integer i : root.data.keySet()) {
			if(root.data.get(i) != null) {
				for(Node<Integer> n : root.data.get(i)) {
					childrenNodesBefore.add(n);
				}
			}
		}
		for(Node<Integer> n : childrenNodesBefore)
			s += "\t\t\t\t" + n.toString();
		s+= "\n";

		do {
			List<Node<Integer>> childrenList = new ArrayList<>();
			for(Node<Integer> cn : childrenNodesBefore) {
				childrenList.addAll(cn.getChildren());
			}

			Iterator<Node<Integer>> itcn= childrenList.iterator();
			LinkedList<Node<Integer>> l = new LinkedList<>();
			if(itcn.hasNext()) {
				Node<Integer> valmin = itcn.next();
				l.addFirst(valmin);
				Node<Integer> val = itcn.next();
				while(itcn.hasNext()) {
					if(valmin.compareTo(val) > 0 ) {
						valmin=val;
						l.addFirst(val);
					}
					else
						l.addLast(val);
					val=itcn.next();
				}
			}
			for (int i = 0; i < l.size(); ++i)
				s+= l.get(i) + "\t";
			s+= "\n";

			childrenNodesBefore.clear();
			for(Node<Integer> cn : childrenList) 
				childrenNodesBefore.add(cn);

		} while (!childrenNodesBefore.isEmpty()) ;

		return s;
	}





	public static void main(String[] args) {
		System.out.println("==================");
		System.out.println("=B-TREE INSERTION=");
		System.out.println("==================");

		Btree btree = new Btree(10, 5);
		btree.insert(15);
		btree.insert(30);
		btree.insert(27);
		btree.insert(35);
		btree.insert(40);
		btree.insert(45);
		btree.insert(37);
		btree.insert(20);
		btree.insert(50);
		btree.insert(55);
		btree.insert(46);
		btree.insert(71);
		btree.insert(66);
		btree.insert(74);
		btree.insert(85);
		btree.insert(90);
		btree.insert(79);
		btree.insert(78);
		btree.insert(95);
		btree.insert(25);
		btree.insert(81);
		btree.insert(68);
		btree.insert(60);
		btree.insert(65);
		
		System.out.println(btree);

		System.out.println("================");
		System.out.println("=====SEARCH=====");
		System.out.println("================");
		System.out.println("");
		System.out.println("Searching for 27...");
		System.out.println(btree.search(27));
		System.out.println("");
		System.out.println("Searching for 1000...");
		System.out.println(btree.search(1000));
		System.out.println("");
		System.out.println("================");
		System.out.println("=====DELETION=====");
		System.out.println("================");
		System.out.println("");
		System.out.println("Deleting 55 in the lower-right corner (simple deletion)...");
		btree.deletion(55);
		System.out.println(btree);
		System.out.println("");
		System.out.println("Deleting 46 at the top (simple deletion)...");
		btree.deletion(46);
		System.out.println(btree);



	}

}
