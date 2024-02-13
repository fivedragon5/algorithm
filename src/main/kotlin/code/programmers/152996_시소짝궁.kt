/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/152996
 * 코딩테스트 연습 > 연습문제 > 시소짝궁
 */
fun main() {
    val result = solution(intArrayOf(100, 180, 360, 100, 270))
    println("result1: $result")
}

private fun solution(weights: IntArray): Long {
    var answer: Long = 0
    val originalWeight = IntArray(1001) // 길이가 1001 인 배열 선언
    val multipleWeight = IntArray(4001) // 배수 배열 4001 길이 선언
    for (weight in weights) {
        val count = originalWeight[weight]
        val s2 = weight * 2
        val s3 = weight * 3
        val s4 = weight * 4
        if (count > 0) {
            answer += count
            answer += multipleWeight[s2] - count
            answer += multipleWeight[s3] - count
            answer += multipleWeight[s4] - count
        } else {
            answer += multipleWeight[s2]
            answer += multipleWeight[s3]
            answer += multipleWeight[s4]
        }
        originalWeight[weight]++
        multipleWeight[s2]++
        multipleWeight[s3]++
        multipleWeight[s4]++
    }
    return answer
}
