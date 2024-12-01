fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {

    val T = readLine().toInt()

    for (i in 0 until T) {
        val n = readLine().toInt()
        val cafeList = mutableListOf<Pair<Int, Int>>()

        repeat(n) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            cafeList.add(Pair(x, y))
        }

        val answers = readLine().split(" ").map { it.toInt() }
        val result = rearrangeCafes(cafeList, answers.drop(1))
        println(result)
    }
}


fun rearrangeCafes(cafeList: List<Pair<Int, Int>>, answers: List<Int>): List<Pair<Int, Int>> {
    val sortedCafes = cafeList.sortedWith(compareBy({ it.first }, { it.second }))
    val rearrangedCafes = mutableListOf<Pair<Int, Int>>()
    var currentX = sortedCafes[0].first
    var sameXGroup = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until sortedCafes.size) {
        val cafe = sortedCafes[i]
        if (cafe.first == currentX) {
            rearrangedCafes.addAll(sameXGroup)
            rearrangedCafes.add(cafe)
            sameXGroup.sortBy { it.second }
        } else {
            sameXGroup.sortBy { it.second }
            rearrangedCafes.addAll(sameXGroup)
            sameXGroup.clear()
            sameXGroup.add(cafe)
            currentX = cafe.first
        }
    }


    val result = mutableListOf<Pair<Int, Int>>()
    for (i in answers) {
        if (i - 1 < rearrangedCafes.size) {
            result.add(rearrangedCafes[i - 1])
        }
    }

    return result
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

