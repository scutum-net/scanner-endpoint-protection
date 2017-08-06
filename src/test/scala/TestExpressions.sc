class Bar {
  var foo: String = "abc"
  def setFoo(in: String) = foo = in
}

val bar = new Bar
bar setFoo "sad"


object Set { def field(name: String): Of = new Of(name) }

class Of(val name: String) { def of(obj: AnyRef): To = new To(name, obj) }

class To(name: String, obj: AnyRef) {
  def to(value: Any): Unit = {
    val f = obj.getClass.getDeclaredField(name)
    f.setAccessible(true)
    f.set(obj, value)
  }
}


object & {
  def apply(x: Any, y: Any): Any = {
    y
  }
}
