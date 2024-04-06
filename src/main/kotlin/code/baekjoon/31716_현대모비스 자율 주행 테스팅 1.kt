/**
 * https://www.acmicpc.net/problem/31716
 * 1 <= N <= 100,000
 * 1 <= K <= 10^9
 * 10000000000
 * Problem
 * 1. 시범 주행은 시범 주행 트랙의 첫 번째 열에서 시작하며, 마지막 열에 도달하면 끝난다.
 * 2. 시작하는 칸이나 끝나는 칸은 자유롭게 정할 수 있다.
 * 3. 시작하는 칸과 끝나는 칸은 모두 도로여야 한다.
 * 4. 완주하는 것이 가능하다면 완주하기 위해 필요한 최소 이동 횟수를 구하여라.
 * 5. 완주가 불가능할 경우, -1 반환
 */

import java.util.StringTokenizer

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {

    var st = StringTokenizer(readLine())

    val N = st.nextToken().toInt()
    val K = st.nextToken().toLong()

    st = StringTokenizer(readLine())
    val firstLine = st.nextToken().toString()
    st = StringTokenizer(readLine())
    val secondLine = st.nextToken().toString()

    val minCount = countMinRoute(firstLine, secondLine, N, K)
    println(minCount)
}

private fun countMinRoute(firstLine: String, secondLine: String, N: Int, K: Long): Long {
    val lineArray = arrayOf(
        firstLine.toCharArray(),
        secondLine.toCharArray(),
    )
    var moveCount = 0L // 움직인 횟수

    if (!validTrack(lineArray, N, K)) {
        return -1
    }

    val startLine = if (N == 1) {
        return 1 * K
    } else {
        // 첫번째 라인의 처음으로 등장하는 장애물 index
        val firstLineOfFirstObject = firstLine.indexOf('#')
        // 두번째 라인의 처음으로 등장하는 장애물 index
        val secondLineOfFirstObject = secondLine.indexOf('#')
        if (firstLineOfFirstObject == -1) 0
        else if (secondLineOfFirstObject == -1) 1
        else if (firstLineOfFirstObject >= secondLineOfFirstObject) 0
        else 1
    }
    var currentLine = startLine

    for (i in 1 until N) {
        if (lineArray[currentLine][i] == '#') {
            currentLine = getAnotherLine(currentLine)
            // 장애물로 경로가 막혀있을 경우
            if (lineArray[currentLine][i] == '#' || lineArray[currentLine][i-1] == '#') return -1
            moveCount += 2L
        } else {
            moveCount += 1L
        }
    }
    if (startLine == currentLine) {
        moveCount = (moveCount + 1) * K - 1
    } else {
        moveCount = moveCount * K + K + K - 2
    }
    return moveCount
}

private fun getAnotherLine(line: Int): Int {
    return if (line == 1) 0 else 1
}

private fun validTrack(lineArray: Array<CharArray>, N: Int, K: Long): Boolean {

    if (lineArray[0][0] == '#' && lineArray[1][0] == '#') {
        return false
    }
    if (lineArray[0][N-1] == '#' && lineArray[1][N-1] == '#') {
        return false
    }
    if (K == 1L) {
        return true
    }

    if (lineArray[0][0] == '#' && lineArray[1][N-1] == '#') {
        return false
    }
    if (lineArray[1][0] == '#' && lineArray[0][N-1] == '#') {
        return false
    }
    return true
}

/*
3 2
#..
..#

3 1
...
.#.

5 1
..#..
#...#

13

5 3
..#..
#...#

20

4 3
..#.
#...

16

4 2
...#
#.#.

-1

3 1
#..
..#

3

5 1
..#..
....#

19

5 2
....#
..#..

12

4 2
...#
#...
 */
