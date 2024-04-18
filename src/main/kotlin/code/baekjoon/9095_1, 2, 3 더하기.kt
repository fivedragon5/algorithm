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
    dp[4] = 7

    for (number in list) {
        val result = getNumber(dp, number)
        println(result)
    }
}

private fun getNumber(dp: IntArray, number: Int): Int {
    return if (dp[number] == -1) {
        getNumber(dp, number - 1) * 2 + 1
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

