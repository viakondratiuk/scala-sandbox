package algorithms


/**
  - Self-balancing binary search tree
  - Either node red or black
  - Root is always black
  - Red nodes can't have red children
  - Every path has the same number of black nodes
     The rule that every path from a node to its descendant NULL nodes must have the same number
     of black nodes is particularly crucial. It's often referred to as maintaining the same "black height.
  - All leafs are black
  - After insert and delete rotations and color change, to keep tree balanced
  - O(log_N)


   https://www.educative.io/answers/what-is-a-red-black-tree
   https://www.youtube.com/watch?v=EmxWC18iuSc&ab_channel=NiemaMoshiri

    Compare:
    RBT insert is recolor and most 2 rotations, faster than AVL
    AVL faster search because more strictly balanced
    RBT height is bigger than AVL, but no path is twice bigger than smallest

    Use cases:
    RBT - insert and delete frequent(map, set)
    AVL - search frequent
 */


object RBTMain extends App {

}
