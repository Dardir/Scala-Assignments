package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    println()
    val str : String = "())("
    println("balance of "+ str+" is "+ balance(str.toList))
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if (c ==0 || c==r) 1 else pascal(c-1,r-1) + pascal(c,r-1)
    }




  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def countOfOpenedparentheses(i: Int, chars: List[Char]):Int = {
        if(chars.isEmpty || i<0) i
        else if(chars.head == '(') countOfOpenedparentheses(i+1,chars.tail)
        else if(chars.head == ')') countOfOpenedparentheses(i-1,chars.tail)
        else countOfOpenedparentheses(i,chars.tail)
      }

      if(chars.isEmpty)true
      else if(countOfOpenedparentheses(0,chars) == 0) true
      else false

    }


  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      def countForTwoNumbers(x:Int,y:Int,money:Int):Int = {
        if( x== -1 || y== -1) 0
        else if((money - (x + y))<=0) 0
        else{
          var count = 0
          val z = money - (x+y)
          if(z%x == 0) count = count + 1
          if(z%y == 0) count = count + 1
          if(z%(x+y) == 0) count = count + 1
          count + countForTwoNumbers(x,y,z)
        }
      }
      if(coins.isEmpty)0
      else {
        var count = 0
        if(money%coins.head == 0) count = count + 1
        def nextCoin: Int= {
          coins match {
            case xs::Nil => -1
            case _ => coins.tail.head
          }
        }

        count + countForTwoNumbers(coins.head,nextCoin,money) + countChange(money, coins.tail)
      }
    }

  }
