package Trees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class Btree {

	int order;
	Node<Integer> root;

	// TO DO : créer un autre constructueur ou le btree peut être loadé directement avec les valeurs à la chaine
	Btree(Node<Integer> root, int order) {
		this.order = order;
		this.root = root;
	}

	public Node<Integer> search(Integer k) {
		//Node<Integer> result = null;
		return root.search(k);
	}

	public void insert(Integer k) {
		Node<Integer> insertionNode = this.searchForInsertion(k);

		if(insertionNode.data.containsKey(k))
			throw new IllegalArgumentException("Valeur déjà présente dans l'arbre");
		/*
		if(root.data.size()<order-1)
			root.addData(k);
		else
		 */
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

	/*
	@Override
	public String toString() {
		String s="";
		List<Node<Integer>> levelNodes = new ArrayList<>();
		// A calculer a un temps t
		int sublevelNbNodes = 4;
		int counter = sublevelNbNodes;
		List<List<Node<Integer>>> levelNodesLists = new ArrayList<>();
		while(root.parent != null) 
			root = root.parent;
		s+=" ";
		s+="\t\t\t\t\t" + root.data.keySet() +"\n";

		while(root != null && root.data.values()!= null ) {
			levelNodes.clear();
			levelNodesLists.clear();

			for (int h =0; h < counter; ++h) {
				System.out.println("test");

				Iterator<List<Node<Integer>>> it = root.data.values().iterator();
				List<Node<Integer>> val = (List<Node<Integer>>) it.next();
				while(it.hasNext()) {
					levelNodesLists.add(val);
					val=(List<Node<Integer>>) it.next();
				}
				levelNodesLists.add(val);
				
				for (List<Node<Integer>> l : levelNodesLists)
					levelNodes.addAll(l);
				
				for(Node<Integer> root : levelNodes) {
					for (Integer key : root.data.keySet()) {
						if(root.data.get(key) !=null) {
							if(root.data.get(key).get(0) !=null) {
								++sublevelNbNodes;
								levelNodes.add(root.data.get(key).get(0));
							}
						}
					}
					Integer maxKey = root.data.lastKey();
					if (root.data.get(maxKey) != null) {
						if(root.data.get(maxKey).get(1) !=null) {
							++sublevelNbNodes;
							levelNodes.add(root.data.get(maxKey).get(1));
						}
					}
				}
				
				for(int i = 0; i< levelNodes.size(); ++i) {
					s+= levelNodes.get(i).toString() + "\t";
				}

				if(levelNodes.get(h).data.firstEntry().getValue().get(0) != null)
					root = levelNodes.get(h).data.firstEntry().getValue().get(0);
				else if (levelNodes.get(h).data.firstEntry().getValue().get(1) != null)
					root = levelNodes.get(h).data.firstEntry().getValue().get(1);
				else
					root = null;
				
			}

			s+= "\n";
			counter = sublevelNbNodes;
			sublevelNbNodes = 0;
		}
		return s;
	}
	*/


	
	@Override
	public String toString() {
		String s="";
		Node<Integer> rootLeft=null;
		Node<Integer> rootRight=null;
		while(root.parent != null) 
			root = root.parent;
		s+=" ";
		s+="\t\t\t\t" + root.data.keySet() +"\n";

		for (Integer key : root.data.keySet()) {
			if(root.data.get(key) !=null) {


				if(root.data.get(key).get(0) !=null) {
					rootLeft=root.data.get(key).get(0);
					s+=rootLeft.recursivePrint();
				}


				//if(root.data.get(key).get(1) !=null) { 
				//	rootRight=root.data.get(key).get(1);
				//	s+=rootRight.recursivePrint();
				//}



			}
		}
		Integer maxKey = root.data.lastKey();
		if (root.data.get(maxKey) != null) {

			if(root.data.get(maxKey).get(1) !=null) {
				rootRight=root.data.get(maxKey).get(1);
				s+=rootRight.recursivePrint();
			}
		}
		return s;
	}
	 

	public static void main(String[] args) {
		Node<Integer> root = new Node<Integer>(new TreeMap<Integer, List<Node<Integer>>>(), null);
		root.data.put(10, null);
		Btree btree = new Btree(root, 5);


		btree.insert(15);
		btree.insert(30);
		btree.insert(27);
		btree.insert(35);
		btree.insert(40);
		btree.insert(45);
		btree.insert(37);

		btree.search(20);

		btree.insert(20);

		btree.insert(55);

		btree.insert(1);

		btree.insert(7);


		btree.insert(16);
		btree.insert(26);



		btree.insert(74);
		/*
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

		 */
		System.out.println(btree);


	}

}
