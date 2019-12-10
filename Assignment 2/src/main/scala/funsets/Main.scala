package funsets

import scala.sys.BooleanProp

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  println (contains(union(singletonSet(1),singletonSet(2)),2 ) )
  println (contains(union(singletonSet(1),singletonSet(2)),3 ) )
  printSet(union(singletonSet(1),singletonSet(2)))
  println (contains(intersect((x:Int) => x>2,(x:Int) => x<4),3))
  printSet(intersect((x:Int) => x>2,(x:Int) => x<4))
  println (contains(diff((x:Int) => x>2,(x:Int) => x<4),5))
  println (contains(filter((x:Int) => x>0,(x:Int) => x%2 == 0),3))
  println (forall((x:Int) => x>5, (x:Int) => x>0))
  println (forall((x:Int) => x>5, (x:Int) => x<100))
}
