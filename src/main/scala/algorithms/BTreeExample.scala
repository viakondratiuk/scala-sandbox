package algorithms

/**
 - All leaves are at the same level.
 - B-Tree is defined by the term minimum degree ‘t‘. The value of ‘t‘ depends upon disk block size.
 - Every node except the root must contain at least t-1 keys. The root may contain a minimum of 1 key.
 - All nodes (including root) may contain at most (2*t – 1) keys.
 - Number of children of a node is equal to the number of keys in it plus 1.
 - All keys of a node are sorted in increasing order.
   The child between two keys k1 and k2 contains all keys in the range from k1 and k2.
  - B-Tree grows and shrinks from the root which is unlike Binary Search Tree.
    Binary Search Trees grow downward and also shrink from downward.
  - Like other balanced Binary Search Trees, the time complexity to search, insert, and delete is O(log n).
  - Insertion of a Node in B-Tree happens only at Leaf Node.

 */


class BTree(t: Int) {
  class Node {
    var n: Int = 0
    var keys: Array[Int] = new Array[Int](2 * t - 1)
    var children: Array[Option[Node]] = new Array[Option[Node]](2 * t)
    var leaf: Boolean = true
  }

  var root: Option[Node] = Some(new Node)

  def search(k: Int, node: Option[Node] = None): Option[(Node, Int)] = {
    val x = node.getOrElse(root.get)
    var i = 0

    while (i < x.n && k > x.keys(i)) {
      i += 1
    }

    if (i < x.n && k == x.keys(i)) {
      Some((x, i))
    } else if (x.leaf || x.children(i).isEmpty) {
      None
    } else {
      search(k, x.children(i))
    }
  }
}

/**

           [10, 20, 30]
         /      |      \
   [5, 6]    [15, 16]   [25, 26]


 */
object BTreeExample extends App {
  val bTree = new BTree(2) // Initialize B-Tree with minimum degree 2

  // Manually populating the B-Tree for this example:
  val root = bTree.root.get
  root.n = 3
  root.keys = Array(10, 20, 30) ++ Array.fill[Int](2 * 2 - 1 - 3)(0) // Root has keys 10, 20, 30
  root.leaf = false // Root is not a leaf

  // Creating children of the root
  val child1 = new bTree.Node
  child1.n = 2
  child1.keys = Array(5, 6) ++ Array.fill[Int](2 * 2 - 1 - 2)(0)
  child1.leaf = true
  child1.children = Array.fill[Option[bTree.Node]](2 * 2)(None)

  val child2 = new bTree.Node
  child2.n = 2
  child2.keys = Array(15, 16) ++ Array.fill[Int](2 * 2 - 1 - 2)(0)
  child2.leaf = true
  child2.children = Array.fill[Option[bTree.Node]](2 * 2)(None)

  val child3 = new bTree.Node
  child3.n = 2
  child3.keys = Array(25, 26) ++ Array.fill[Int](2 * 2 - 1 - 2)(0)
  child3.leaf = true
  child3.children = Array.fill[Option[bTree.Node]](2 * 2)(None)

  // Assigning children to the root
  root.children = Array(Some(child1), Some(child2), Some(child3)) ++ Array.fill[Option[bTree.Node]](2 * 2 - 3)(None)

  // Test the search operation
  val searchKeys = List(15, 100) // List of keys to search for

  searchKeys.foreach { searchKey =>
    bTree.search(searchKey) match {
      case Some((node, index)) =>
        println(s"Key $searchKey found at index $index in node with keys [${node.keys.mkString(", ")}].")
      case None =>
        println(s"Key $searchKey not found in the B-Tree.")
    }
  }
}
