package algorithms


/**
 - Looks for local minimum and can miss global one
    - Typical problem: coin change for less coins is not optimal
      - Greedy
        - You have coins [4,3,1] and want 6
        - Greedy would take 4, 1, 1
        - Optimal is 3,3
      - Better is dynamic programming
  - Can be faster than other approaches
  - Complexity is determined on how algorithm works
  - Simple and efficient
  - Used to solve tasks when local optimum is also a global one, when global solution can be build from incremental local solutions
  - Takes immediate decision without looking for entire set
 */

case class Activity(start: Int, end: Int)

object ActivitySelection {
  def selectActivities(activities: Array[Activity]): Array[Activity] = {
    val sortedActivities = activities.sortBy(_.end)
    var lastSelectedEnd = -1
    val selectedActivities = sortedActivities.filter { activity =>
      if (activity.start >= lastSelectedEnd) {
        lastSelectedEnd = activity.end
        true
      } else {
        false
      }
    }
    selectedActivities
  }
}

object GreedyScheduling extends App {
    val activities = Array(Activity(1, 4), Activity(3, 5), Activity(0, 6), Activity(5, 7),
      Activity(3, 9), Activity(5, 9), Activity(6, 10), Activity(8, 11),
      Activity(8, 12), Activity(2, 14), Activity(12, 16))
    val selectedActivities = ActivitySelection.selectActivities(activities)
    println("Selected activities (start, end):")
    selectedActivities.foreach(activity => println(s"(${activity.start}, ${activity.end})"))
}
