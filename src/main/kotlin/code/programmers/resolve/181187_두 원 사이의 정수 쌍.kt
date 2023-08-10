import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

fun main() {
    val result = solution(2, 3) // 20
    println(result)
}

private fun solution(r1: Int, r2: Int): Long {
    var answer = 0L

    for (i in 1 .. r2) {
        val max = floor(sqrt(1.0 * r2 * r2 - 1.0 * i * i))
        val min = ceil(sqrt(1.0 * r1 * r1 - 1.0 * i * i))
        answer += max.toLong() - min.toLong() + 1L
    }

    return answer * 4
}
