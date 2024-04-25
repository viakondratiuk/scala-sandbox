package algorithms.dp
import scala.collection.mutable


case class TreeNode(value: Int, children: List[TreeNode])

object MWIS {
  def bottomUp(root: TreeNode): Int = {
    def postOrder(node: TreeNode): (Int, Int) = {
      val initial = (0, 0)
      val results = node.children.foldLeft(initial) { (acc, child) =>
        val (childInc, childExc) = postOrder(child)
        (acc._1 + childExc, acc._2 + math.max(childInc, childExc))
      }
      (results._1 + node.value, results._2)
    }

    val (includeRoot, excludeRoot) = postOrder(root)
    math.max(includeRoot, excludeRoot)
  }

  def topDown(root: TreeNode): Int = {
    val memo = mutable.Map.empty[TreeNode, (Int, Int)]

    def dp(node: TreeNode, includeNode: Boolean): Int = memo.get(node) match {
      case Some((inc, exc)) => if (includeNode) inc else exc
      case None =>
        if (node.children.isEmpty) {
          val result = if (includeNode) node.value else 0
          memo(node) = (node.value, 0)
          result
        } else {
          val include = node.value + node.children.map(dp(_, includeNode = false)).sum
          val exclude = node.children.map(child => math.max(dp(child, includeNode = true), dp(child, includeNode = false))).sum
          memo(node) = (include, exclude)
          if (includeNode) include else exclude
        }
    }

    math.max(dp(root, includeNode = true), dp(root, includeNode = false))
  }
}

object MWISExample extends App {
  val child1 = TreeNode(1, List())
  val child2 = TreeNode(2, List())
  val child3 = TreeNode(3, List())
  val child4 = TreeNode(4, List())
  val child5 = TreeNode(5, List(child2, child1))
  val child6 = TreeNode(6, List(child4, child3))
  val root = TreeNode(7, List(child6, child5))

  println(s"MWIS of (Top-Down): ${MWIS.topDown(root)}")
  println(s"MWIS of (Bottom-Up): ${MWIS.bottomUp(root)}")
}
