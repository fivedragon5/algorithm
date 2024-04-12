/**
 * https://www.acmicpc.net/problem/31717
 * 1 <= M, K <= 200,000
 * Problem
 */

import java.util.StringTokenizer
import javax.swing.text.html.HTML.Attribute.N
import sun.security.krb5.internal.KDCOptions.with

fun main2(args: Array<String>) = with(System.`in`.bufferedReader()) {

    var st = StringTokenizer(readLine())

    val M = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    for (i in 0 until M) {
        // 트랙 저장
        st = StringTokenizer(readLine())
        val firstLine = st.nextToken().toString()
        st = StringTokenizer(readLine())
        val secondLine = st.nextToken().toString()
    }

    val minCount = countMinRoute(firstLine, secondLine, N, K)
    println(minCount)
}

// 각 트랙의 최소값 계산하기
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

// 트랙 완주 확인
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
