import java.util.Scanner
import kotlin.math.abs

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val t = nextInt()
    val list = mutableListOf<Int>()
    val rows = mutableListOf<Int>()
    val cols = mutableListOf<Int>()
    for (i in 1..6) {
        val a = nextInt()
        val b = nextInt()
        list.add(b)
        if (a == 1 || a == 2) {
            rows.add(b)
        } else {
            cols.add(b)
        }
    }
    val squareOne = list[0] * list[1]
    val squareTwo = list[3] * list[4]
    val maxSquare = rows.maxOrNull()!! * cols.maxOrNull()!!

    val answer = if (maxSquare == squareOne || maxSquare == squareTwo) {
        abs(squareOne - squareTwo)
    } else {
        squareOne + squareTwo
    }
    println(answer * t)
}
