/**
 * https://www.acmicpc.net/problem/2141
 * 알고리즘 분류 : 정렬, 누적합
 * f(x) = ||
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
            return
        }
    }
}
/*
f(x) = |x-1| * 3 + |x-2| * 5 + |x-3| * 3
f(1) = 5 + 6 = 11
f(2) = 3 + 3 = 6
f(3) = 6 + 5 = 11
f(1) > f(2), f(3) > f(2)
3
1 3
2 5
3 3

2

4
1 1
2 1
3 1
4 1

f(x) = |x-1| * 1 + |x-2| * 1 + |x-3| * 1 + |x-4| * 1
f(1) = 0 + 1 + 2 + 3 = 6
f(2) = 1 + 0 + 1 + 2 = 4
f(3) = 2 + 1 + 0 + 1 = 4
f(4) = 3 + 2 + 1 + 0 = 6
 */

