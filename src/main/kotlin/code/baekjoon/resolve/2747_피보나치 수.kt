/**
 * https://www.acmicpc.net/problem/2747
 * 1 <= N <= 45
 */

import java.util.StringTokenizer

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val fibonacci = mutableListOf(0,1)
    for (i in 2 .. N) {
        fibonacci.add(fibonacci[i-2] + fibonacci[i-1])
    }
    println(fibonacci[N])
}
