/**
 * https://www.acmicpc.net/problem/2141
 * 알고리즘 분류 : 정렬, 누적합
 */

import java.util.StringTokenizer

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {

    var st = StringTokenizer(readLine())

    val townCount = st.nextToken().toInt()
    val list = mutableListOf<Pair<Long, Long>>()
    var totalPeopleCount = 0L

    for (i in 0 until townCount) {
        st = StringTokenizer(readLine())
        val location = st.nextToken().toLong()
        val peopleCount = st.nextToken().toLong()
        totalPeopleCount += peopleCount
        list.add(location to peopleCount)
    }

    val sortedTowns = list.sortedBy { it.first } // 마을 정렬
    val middle = (totalPeopleCount + 1) / 2 // 인구 수 기준 중간값에 가까운 마을에 설치
    var stack = 0L

    for (town in sortedTowns) {
        stack += town.second
        if (stack >= middle) {
            println(town.first)
            return@with
        }
    }
}
/*
3
1 3
2 5
3 3

2

2
1 3
2 3

1
 */

