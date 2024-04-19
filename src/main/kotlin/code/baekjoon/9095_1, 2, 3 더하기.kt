/**
 * https://www.acmicpc.net/problem/9095
 */

import java.util.StringTokenizer

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {

    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val list = mutableListOf<Int>()

    for (i in 0 until N) {
        st = StringTokenizer(readLine())
        list.add(st.nextToken().toInt())
    }
    val dp = IntArray(11, { -1 })
    dp[0] = 1
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4

    for (number in list) {
        val result = getDp(dp, number)
        println(result)
    }
}

private fun getDp(dp: IntArray, number: Int): Int {
    return if (dp[number] == -1) {
        val result = getDp(dp, number - 1) + getDp(dp, number - 2) + getDp(dp, number - 3)
        dp[number] = result
        result
    } else {
        dp[number]
    }
}
/*
3
4
7
10

7
44
274
 */

