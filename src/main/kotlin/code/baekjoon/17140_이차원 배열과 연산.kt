import java.util.StringTokenizer

/**
 * 백준 17140번 문제
 * @see https://www.acmicpc.net/problem/17140
 * 이차원 배열과 연산
 * 1 <= r, c, k <= 100, 3 X 3 크기의 배열
 * 1 <= Array[x][y] <= 100
 */
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {

    var st = StringTokenizer(readLine())

    val r = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val list = mutableListOf<MutableList<Int>>()

    for (i in 0 until 3) {
        st = StringTokenizer(readLine())
        val temp = mutableListOf<Int>()
        for (j in 0 until 3) {
            temp.add(st.nextToken().toInt())
        }
        list.add(temp)
    }

    // 행의 갯수 >= 열의 갯수 일때 리스트의 행을 하나씩 countNumber에 넣고 행을 갱신
    // 행의 갯수 < 열의 갯수 일때 리스트의 열을 하나씩 countNumber에 넣고 열을 갱신
    var time = 0
    while (time <= 100) {
        var max = 0
        var isReverse = false
        if (list.size > r - 1 && list[0].size > c - 1) {
            if (list[r - 1][c - 1] == k) {
                println(time)
                return
            }
        }

        if (list.size >= list[0].size && !isReverse) {
            isReverse = false
            val tempList = mutableListOf<MutableList<Int>>()
            for (i in list) {
                val sortedList = countNumber(i)
                tempList.add(sortedList)
                max = maxOf(max, sortedList.size)
            }
            list.clear()
            list.addAll(tempList)
        } else {
            isReverse = !isReverse
            val tempList = mutableListOf<MutableList<Int>>()
            for (i in 0 until list[0].size) {
                val temp = mutableListOf<Int>()
                for (j in 0 until list.size) {
                    temp.add(list[j][i])
                }
                val sortedList = countNumber(temp)
                tempList.add(sortedList)
                max = maxOf(max, sortedList.size)
            }
            list.clear()
            list.addAll(tempList)
        }
        for (i in list) {
            while (i.size < max) {
                i.add(0)
            }
        }
        time++
    }
    println(-1)
}

private fun countNumber(list: List<Int>): MutableList<Int> {
    // 정렬한 리스트를 순회하면서 숫자의 개수를 카운팅
    val map = mutableMapOf<Int, Int>()
    for (i in list) {
        // 0은 무시
        if (i == 0) continue
        map[i] = map.getOrDefault(i, 0) + 1
    }
    // 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러가지면 수가 커지는 순으로 정렬
    val sortedMap = map.toList().sortedWith(compareBy({ it.second }, { it.first })).toMap()
    val tempList = mutableListOf<Int>()
    for (i in sortedMap) {
        tempList.add(i.key)
        tempList.add(i.value)
    }
    return tempList
}
/*
1 2 2
1 2 1
2 1 3
3 3 3

0
 */

