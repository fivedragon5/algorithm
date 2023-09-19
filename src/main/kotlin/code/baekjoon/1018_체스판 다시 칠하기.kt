import java.util.StringTokenizer

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {

    var st = StringTokenizer(readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val list = mutableListOf<String>()

    for (i in 0 until N) {
        st = StringTokenizer(readLine())
        list.add(st.nextToken())
    }

    var min = 64

    for (i in 0 .. N - 8) {
        for (j in 0 .. M - 8) {
            min = calCount(i, j, list).coerceAtMost(min)
        }
    }

    println(min)
}

private fun calCount(row: Int, col: Int, map: List<String>): Int {
    var count = 0
    for (i in row until row + 8) {
        for (j in col until col + 8) {
            if ((i + j) % 2 == 0) {
                if (map[i][j] == 'B') {
                    count++
                }
            } else {
                if (map[i][j] == 'W') {
                    count++
                }
            }
        }
    }
    return count.coerceAtMost(64 - count)
}
/*
8 8
WBWBWBWB
BWBWBWBW
WBWBWBWB
BWBBBWBW
WBWBWBWB
BWBWBWBW
WBWBWBWB
BWBWBWBW
 */

