import java.lang.StringBuilder
import java.util.ArrayList
import java.util.Scanner

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val count = nextInt()
    var index = 1
    val answer = StringBuilder()
    var startNumber = 0;
    val stack = ArrayList<Int>()
    while (index <= count) {
        val currentNumber = nextInt()
        if (startNumber < currentNumber) {
            for (i in startNumber + 1..currentNumber) {
                stack.add(i)
                answer.append("+\n")
            }
            startNumber = currentNumber
        }
        if (stack.last() == currentNumber) {
            stack.removeLast()
            answer.append("-\n")
        } else {
            println("NO")
            return
        }
        index++
    }
    println(answer.toString())
}
