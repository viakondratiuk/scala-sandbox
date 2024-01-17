package algorithms

/**
  - AVL Tree vs. Regular BST?
      Guarantee O(log_N) by keeping balance -1, 0, 1

  - Balance Factor in AVL Tree
      The balance factor of a node is the height of its left subtree minus the height of its right subtree.
      It's crucial for maintaining the tree's balance

  - Rotation Operations in AVL Trees
      Right Rotation (RR)
      Left Rotation (LL)
      Left-Right Rotation (LR)
      Right-Left Rotation (RL)
      https://www.educative.io/answers/common-avl-rotation-techniques

  - Insertions in AVL Tree
      Like BST but with possible rotations

  - Deletions in AVL Tree
      Like BST

  - Height Maintenance and O(log n) Operations
      With rebalancing keeps height O(log_N)

  - Applications of AVL Trees
      AVL trees find applications in areas where frequent insertions and deletions occur,
      and a balanced tree is necessary for quick search operations

  - Complexities of AVL Tree Operations
      O(log_N)
 */


class NodeAVL(var key: Int, var height: Int = 1) {
  var left: Option[NodeAVL] = None
  var right: Option[NodeAVL] = None
}

class AVLTree {
  private var root: Option[NodeAVL] = None

  // Utility function to get the height of the tree
  private def height(node: Option[NodeAVL]): Int = node.map(_.height).getOrElse(0)

  // Utility function to get the balance factor of the node
  private def getBalance(node: Option[NodeAVL]): Int = {
    if (node.isEmpty) 0
    else height(node.get.left) - height(node.get.right)
  }

  // Right rotate
  private def rightRotate(y: NodeAVL): NodeAVL = {
    val x = y.left.get
    val T2 = x.right
    x.right = Some(y)
    y.left = T2

    // Update heights
    y.height = math.max(height(y.left), height(y.right)) + 1
    x.height = math.max(height(x.left), height(x.right)) + 1

    x
  }

  // Left rotate
  private def leftRotate(x: NodeAVL): NodeAVL = {
    val y = x.right.get
    val T2 = y.left
    y.left = Some(x)
    x.right = T2

    // Update heights
    x.height = math.max(height(x.left), height(x.right)) + 1
    y.height = math.max(height(y.left), height(y.right)) + 1

    y
  }

  // Insert a node
  def insert(key: Int): Unit = {
    root = insertRec(root, key)
  }

  private def insertRec(node: Option[NodeAVL], key: Int): Option[NodeAVL] = {
    node match {
      case None => Some(new NodeAVL(key))
      case Some(n) =>
        if (key < n.key) {
          n.left = insertRec(n.left, key)
        } else if (key > n.key) {
          n.right = insertRec(n.right, key)
        } else { // Duplicate keys not allowed
          return node
        }

        // Update height of this ancestor node
        n.height = 1 + math.max(height(n.left), height(n.right))

        // Get the balance factor
        val balance = getBalance(Some(n))

        // If unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && key < n.left.get.key) {
          return Some(rightRotate(n))
        }

        // Right Right Case
        if (balance < -1 && key > n.right.get.key) {
          return Some(leftRotate(n))
        }

        // Left Right Case
        if (balance > 1 && key > n.left.get.key) {
          n.left = Some(leftRotate(n.left.get))
          return Some(rightRotate(n))
        }

        // Right Left Case
        if (balance < -1 && key < n.right.get.key) {
          n.right = Some(rightRotate(n.right.get))
          return Some(leftRotate(n))
        }

        Some(n)
    }
  }

  def preOrder(node: Option[NodeAVL] = root): Unit = {
    node match {
      case None =>
      case Some(n) =>
        println(s"Node: ${n.key}, Height: ${n.height}")
        preOrder(n.left)
        preOrder(n.right)
    }
  }
}

/**

      30
     /  \
   20   40
   / \    \
 10  25   50

*/
object AVLMain extends App {
  val tree = new AVLTree()

  tree.insert(10)
  tree.insert(20)
  tree.insert(30)
  tree.insert(40)
  tree.insert(50)
  tree.insert(25)

  // Preorder traversal of the constructed AVL tree is
  tree.preOrder()
}
