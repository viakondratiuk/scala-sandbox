package algorithms

/**
  - What is a Binary Search Tree (BST) and how is it structured?
      Left subtree is less than parent and right is greater.
      In-order traversal generates sorted order.

  - What are the time complexities of various operations (insert, delete, search) in a BST
    and how do they vary with the tree's structure?
      Insert, delete, search - O(log_N), worst case O(N)

  - How does a BST become unbalanced and what are the consequences of this imbalance?
      If insertion happens in sorted ot most sorted way, it leads to linked list degradation

  - What are the methods for traversing a BST?
      In-order Traversal: Visits left subtree, node, then right subtree. It retrieves keys in a sorted order.
      Pre-order Traversal: Visits node, then left subtree, then right subtree. It's useful for creating a copy of the tree.
      Post-order Traversal: Visits left subtree, then right subtree, then node. It's used to delete the tree or collect garbage.

  - How do insertion and deletion operations work in a BST, and what are the special cases that need to be handled?
      Insertion: Start at the root and compare the key to be inserted. Traverse left or right based on
        the comparison until a suitable position is found. Insert the node.
      Deletion:
        - Node is a leaf: Simply remove the node.
        - Node has one child: Remove the node and replace it with its child.
        - Node has two children: Find the node's in-order successor (or predecessor), replace the node's value with
          that of the in-order successor, then delete the in-order successor.
 */

case class Node(key: Int, value: String) {
  var left: Option[Node] = None
  var right: Option[Node] = None
}

object BST {
  def insert(root: Option[Node], node: Node): Option[Node] = root match {
    case None =>
      Some(node)
    case Some(r) =>
      if (r.key > node.key) {
        r.left = insert(r.left, node)
      } else if (r.key < node.key) {
        r.right = insert(r.right, node)
      }
      root
  }

  def get(root: Option[Node], key: Int): Option[Node] = root match {
    case None => None
    case Some(r) =>
      if (key < r.key) {
        get(r.left, key)
      } else if (key > r.key) {
        get(r.right, key)
      } else {
        root
      }
  }

  def inOrderPrint(root: Option[Node]): Unit = root match {
    case None =>
    case Some(r) =>
      inOrderPrint(r.left)
      print(r.key + ", ")
      inOrderPrint(r.right)
  }

  def preOrderPrint(root: Option[Node]): Unit = root match {
    case None =>
    case Some(r) =>
      print(r.key + ", ")
      preOrderPrint(r.left)
      preOrderPrint(r.right)
  }
}

/**
      3
     / \
    1   7
       / \
      5   9
*/

object BSTMain extends App {
  var root: Option[Node] = None
  val nodes = List((3, "Kyiv"), (7, "Dnipro"), (1, "Lviv"), (5, "Mariupol"), (9, "Zhytomyr"))

  nodes.foreach { case(key, value) =>
    root = BST.insert(root, Node(key, value))
  }

  println("In-order traversal:")
  BST.inOrderPrint(root)

  println("Pre-order traversal:")
  BST.preOrderPrint(root)
}
