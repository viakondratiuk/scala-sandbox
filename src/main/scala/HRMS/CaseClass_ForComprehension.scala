package HRMS

object CaseClass_ForComprehension extends App {
  //A. What problem this concept can solve?
  //B. How it can solve it?
  //C. What is the proper place of this concept in a bigger picture?

  /** Case Classes **/
  //A. Abstraction to provide access to immutable data.
  // - Short initialization val covidPL = CovidCountryStats("PL", 776, 15366) because of apply
  // - Pattern Matching
  // - hashcode, equals
  // - Product
  // - Copy
  // - Serializable

  //B.
  case class CovidCountryStats(countryCode: String, deaths: Int, confirmedCases: Int)
  val covidPL = CovidCountryStats("PL", 776, 15366)
  covidPL match {
    case CovidCountryStats("PL", x, y) => println("Death rate for Poland is " + x.toFloat / y.toFloat)
    case _ => println("Unknown country")
  }

  //C. When you need simple wrapper for immutable data with power functionality - take Case Class

  /** For Comprehension **/
  //A. We have imperative way of using loops, declarative would be to use map, flatMap, filter
  // but it can be complex, so for-comprehension comes as syntax sugar
  // if we want to use it for custom collection we should implement those 4 methods
  // - foreach - generator
  // - filter - is if guard
  // - yield - is as map

  //B.
  val result = List(1,2,3)
  for {
    res <- result
    if res == 10
  } yield res

  //C. When we need to iterate with nested map, flatMap we can simplify code by such syntax sugar
}
