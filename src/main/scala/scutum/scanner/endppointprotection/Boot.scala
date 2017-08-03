package scutum.scanner.endppointprotection

import com.google.gson.Gson


object Boot {
  def main(args: Array[String]): Unit = {
    val json = new Gson().toJson(Person("hey", 1))
    println(json)
  }


  case class Person(name: String, id: Int)
}
