trait Food { val name: String }
trait Fruit extends Food
trait Cereal extends Food

case class Apple(name: String) extends Fruit
case class Orange(name: String) extends Fruit
case class Muesli(name: String) extends Cereal

case class FoodBowl[+F <: Food](food: F) {
  def eat: String = s"food ${food.name}"
}

val apple = Apple("apple")
val bowlOfApple = FoodBowl(apple)
//val foodBowl: bowlOfApple.F = bowlOfApple.food

abstract class FoodBowl2 {
  type FOOD <: Food
  val food: FOOD
  def eat: String = s"food ${food.name}"
}

class AppleBowl(val food: Apple) extends FoodBowl2 {
  override type FOOD = Apple
}

val appleBowl = new AppleBowl(Apple("app"))
val apple4: appleBowl.FOOD = appleBowl.food
// type projection
val apple5: AppleBowl#FOOD = appleBowl.food

object BowlOfFood {
  def apply[F <: Food](f: F) = new FoodBowl2 {
    override type FOOD = F
    override val food: FOOD = f
  }
}

// path dependent
val appleBowl1 = BowlOfFood(Apple("app"))
val a: appleBowl1.FOOD = appleBowl1.food

val orangeBowl1 = BowlOfFood(Orange("or"))
val o: orangeBowl1.FOOD = orangeBowl1.food
