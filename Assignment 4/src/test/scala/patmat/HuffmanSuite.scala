package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("times(\"list of 'a', 'b', 'a'\")") {
    val testList:List[Char] = List('a', 'b', 'a')
    def matchResult: Boolean ={
      val resList =  times(testList)
      resList.contains(('a', 2)) && resList.contains(('b', 1))
    }
    new TestTrees {
      assert(matchResult)
    }
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("test until") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(until(singleton,combine)(leaflist) == List(Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4),List('e','t','x'),7)))
  }

  test("test createCodeTree on List of 'e','t','x','t','x','x','x'") {
    val charList = List('e','t','x','t','x','x','x')
    assert(createCodeTree(charList) == Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4),List('e','t','x'),7))
  }

  test("test decode 00011 on List of 'e','t','x','t','x','x','x'") {
    val charList = List('e','t','x','t','x','x','x')
    assert(decode(createCodeTree(charList),List(0,0,0,1,1)) == List('e','t','x'))
  }


  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

}
