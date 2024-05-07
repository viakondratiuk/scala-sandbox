package algorithms.dp
import scala.collection.mutable


case class TreeNode(value: Int, children: List[TreeNode])

object MWIS {
  def bottomUp(root: TreeNode): (Int, Set[Int]) = {
    // Node to include and exclude values ++ sets
    val resultMap = scala.collection.mutable.Map.empty[TreeNode, ((Int, Set[Int]), (Int, Set[Int]))]

    // Node and state of processed children
    val stack = scala.collection.mutable.Stack[(TreeNode, Boolean)]()
    stack.push((root, false))

    while (stack.nonEmpty) {
      val (node, visited) = stack.pop()
      if (node.children.isEmpty || visited) {
        // Calculate the sums and sets when all children have been processed or node has no children
        val initial = ((0, Set.empty[Int]), (0, Set.empty[Int]))
        val results = node.children.foldLeft(initial) { (acc, child) =>
          val (childInc, childExc) = resultMap(child)
          ((acc._1._1 + childExc._1, acc._1._2 ++ childExc._2),
            (acc._2._1 + math.max(childInc._1, childExc._1),
              if (childInc._1 > childExc._1) acc._2._2 ++ childInc._2 else acc._2._2 ++ childExc._2))
        }
        resultMap(node) = ((results._1._1 + node.value, results._1._2 + node.value), results._2)
      } else {
        // Mark the current node as visited and push it back to the stack
        stack.push((node, true))
        // Push all children to the stack to be processed
        node.children.foreach(child => stack.push((child, false)))
      }
    }

    val ((includeRootSum, includeRootSet), (excludeRootSum, excludeRootSet)) = resultMap(root)
    if (includeRootSum > excludeRootSum) (includeRootSum, includeRootSet)
    else (excludeRootSum, excludeRootSet)
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
  """
    |        7
    |       / \
    |      6   5
    |     /|  / \
    |    4 3 2   1
    |
    |""".stripMargin

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
