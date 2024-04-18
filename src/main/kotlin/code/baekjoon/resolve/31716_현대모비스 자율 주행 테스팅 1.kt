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

    if (!validTrack(lineArray, K)) {
        return -1L
    }
    // 첫번째 라인의 처음으로 등장하는 장애물 index
    val firstLineOfFirstObject = firstLine.indexOf('#')
    // 두번째 라인의 처음으로 등장하는 장애물 index
    val secondLineOfFirstObject = secondLine.indexOf('#')

    // 시작 라인 정하기
    val startLine =
        if (firstLineOfFirstObject == -1) 0
        else if (secondLineOfFirstObject == -1) 1
        else if (firstLineOfFirstObject >= secondLineOfFirstObject) 0
        else 1

    var currentLine = startLine

    for (i in 1 until N) {
        if (lineArray[currentLine][i] == '#') {
            currentLine = getAnotherLine(currentLine)
            // 장애물로 경로가 막혀있을 경우
            if (lineArray[currentLine][i] == '#' || lineArray[currentLine][i-1] == '#') return -1L
            moveCount += 2L
        } else {
            moveCount += 1L
        }
    }

    val moveNext =  if (startLine == currentLine) {
        1L
    } else {
        2L
    }

    return moveCount * K + moveNext * (K - 1)
}

private fun getAnotherLine(line: Int): Int {
    return if (line == 1) 0 else 1
}

private fun validTrack(lineArray: Array<CharArray>, K: Long): Boolean {
    val length = lineArray[0].size

    if (lineArray[0][0] == '#' && lineArray[1][0] == '#') {
        return false
    }
    if (lineArray[0][length-1] == '#' && lineArray[1][length-1] == '#') {
        return false
    }
    if (K == 1L) {
        return true
    }

    if (lineArray[0][0] == '#' && lineArray[1][length-1] == '#') {
        return false
    }
    if (lineArray[1][0] == '#' && lineArray[0][length-1] == '#') {
        return false
    }
    return true
}

/*
1 2
.
.

3 2
.#.
#.#

4 10

3 2
..#
.#.

12 2
.##...#...##
#...#...#...

12 2
..#...#...##
#...#...#...

16 34

4 3
...#
.#..

4 10 16

5 2
.#...
...#.

5 12 19

4 2
#...
..#.

4 10

5 2
..#..
....#

19 5 12 17

5 2
....#
..#..

12

4 2
...#
#...

9 2
.#...#...
...#...#.

11 24
 */
