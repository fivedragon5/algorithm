/**
 * https://www.acmicpc.net/problem/1463
 *
 * 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3. 1을 뺀다.
 *
 * 1 <= N <= 10^6
 */

import java.util.StringTokenizer
import kotlin.math.min

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val dp = IntArray(10000000, { Int.MAX_VALUE })
    dp[1] = 0
    dp[2] = 1
    for (i in 3 .. N) {
        getDp(dp, i)
    }
    println(dp[N])
}

private fun getDp(dp:IntArray, number: Int) {
    dp[number] = min(dp[number-1] + 1, dp[number])
    if (number % 3 == 0) dp[number] = min(dp[number/3] + 1, dp[number])
    if (number % 2 == 0) dp[number] = min(dp[number/2] + 1, dp[number])
}
/*
2
1

10
3
 */

