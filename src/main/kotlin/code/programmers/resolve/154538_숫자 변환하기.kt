/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/154538?language=kotlin
 * 코딩테스트 연습 > 연습문제 > 숫자 변환하기
 */
fun main() {
//    val result1 = solution(10, 40, 5)
//    val result2 = solution(10, 40, 30)
//    val result3 = solution(2, 5, 4)
//    println("result1: $result1")
//    println("result2: $result2")
//    println("result3: $result3")
}

var ADD_NUMBER = 0
var TARGET_NUMBER = 0
var hashSet = HashSet<Int>()

private fun solution(x: Int, y: Int, n: Int): Int {
    ADD_NUMBER = n
    TARGET_NUMBER = y
    hashSet.add(x)
    return bfs()
}

private fun bfs(): Int {
    var count = 0
    while (hashSet.isNotEmpty()) {
        if (hashSet.contains(TARGET_NUMBER)) {
            return count
        }
        val newHashSet = HashSet<Int>()
        for (number in hashSet) {
            val mul3 = number * 3
            val mul2 = number * 2
            val plus = number + ADD_NUMBER
            if (mul3 <= TARGET_NUMBER) newHashSet.add(mul3)
            if (mul2 <= TARGET_NUMBER) newHashSet.add(mul2)
            if (plus <= TARGET_NUMBER) newHashSet.add(plus)
        }
        hashSet = newHashSet
        count++
    }
    return -1
}
/**
 * 10 40 5 2
 * 10 40 30 1
 * 2 5 4 -1
 */
