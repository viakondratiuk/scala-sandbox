object Implicit2 extends App {

  class Yaml(s: String) {
    override def toString = s
  }

  class Config[Type] {
    val tabSize = 2
  }

  class Validator[Type] {
  }

  def parse[S <: String](str: S)(implicit c: Config[String], v: Validator[String]): Yaml =
    new Yaml(str)

  def simplifiedParse[S <: String : Config : Validator](str: S): Yaml =
    new Yaml(str)


  implicit val generalConfig: Config[String] = new Config[String] {
    override val tabSize = 4
  }

  implicit val generaValidationRules: Validator[String] = new Validator[String] {
  }

  val parsedYaml: Yaml = parse("""    name: "name"    """)
  val anotherParsedYaml: Yaml = simplifiedParse("""    name: "anotherName"    """)

  println(parsedYaml)
  println(anotherParsedYaml)
}
