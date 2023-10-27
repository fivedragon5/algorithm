import java.util.StringTokenizer

fun main(args: Array<String>) {
    val st = StringTokenizer(readLine())

    val T = st.nextToken().toInt()

    repeat(T) {
        val N = readLine()!!.toInt()
        val cafes = mutableListOf<Pair<Int, Int>>()

        cafes.add(-1 to 0)
        for (i in 1..N) {
            val (x, y) = readLine()!!.split(" ").map { it.toInt() }
            cafes.add(x to y)
        }

        cafes.sortWith(compareBy({ it.first }, { it.second }))

        var idx = 1

        while (idx <= N) {
            if (cafes[idx].first == cafes[idx - 1].first) {
                idx++
            } else if (cafes[idx].second == cafes[idx - 1].second) {
                idx++
            } else {
                val startPos = idx
                val x = cafes[idx].first

                while (++idx <= N && x == cafes[idx].first) {}

                val endPos = idx
                cafes.subList(startPos, endPos).reverse()
            }
        }

        val M = readLine()!!.split(" ").map { it.toInt() }

        for (i in 1 until M.size) {
            val k = M[i]
            println("${cafes[k].first} ${cafes[k].second}")
        }
    }
}

/*
2
17
3 3
5 3
11 2
9 2
2 1
3 1
5 1
0 0
1 0
2 0
9 0
11 -1
9 -3
6 -1
7 -1
7 -3
5 -1
3 5 14 17
4
0 0
0 1
1 1
1 0
5 1 4 1 3 1
 */

