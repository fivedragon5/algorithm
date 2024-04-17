/**
 * https://www.acmicpc.net/problem/24116
 * 5 <= N <= 40
 */

import java.util.StringTokenizer

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val dp = mutableListOf(0, 1)
    for (i in 2 .. N) {
        dp.add(dp[i-2] + dp[i-1])
    }
    println("${dp[N]} ${N-2}")
}
