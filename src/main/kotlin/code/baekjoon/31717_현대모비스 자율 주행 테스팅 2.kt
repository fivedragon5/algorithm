/**
 * https://www.acmicpc.net/problem/31717
 * 1 <= M, K <= 200,000
 * 1<= 트랙의 길이 <= 500,000
 * Problem
 */

import java.util.StringTokenizer
import kotlin.math.min

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {

    var st = StringTokenizer(readLine())

    val M = st.nextToken().toInt() // 트랙의 의 수
    val K = st.nextToken().toInt() // 선택할 트랙의 수

    val trackNumberOrder = mutableListOf<Int>() // 트랙의 순서
    val tracks : MutableList<Pair<CharArray, CharArray>> = mutableListOf() // 트랙

    st = StringTokenizer(readLine())

    for (i in 0 until K) {
        trackNumberOrder.add(st.nextToken().toInt() - 1)
    }

    for (i in 0 until M) {
        st = StringTokenizer(readLine())
        val firstLine = st.nextToken().toString()
        st = StringTokenizer(readLine())
        val secondLine = st.nextToken().toString()
        tracks.add(firstLine.toCharArray() to secondLine.toCharArray())
    }

    var memo = Array(tracks.size) {
        IntArray(2) {-1}
    }
    val firstStartCount = countTracksMinRoute(tracks, trackNumberOrder, 0, memo)
    val secondStartCount = countTracksMinRoute(tracks, trackNumberOrder, 1, memo)

    println(min(firstStartCount, secondStartCount))
}

// 각 트랙의 최소값 계산하기
private fun countTracksMinRoute(
    tracks: List<Pair<CharArray, CharArray>>,
    trackNumberOrder: List<Int>,
    startLine: Int,
    memo: Array<IntArray>,
): Long {

    var moveCount = 0L
    var currentLine = startLine

    val trackEndArray = IntArray(tracks.size){-1}

    for (order in trackNumberOrder) {
        val track = tracks[order]
        if (trackEndArray[order] == -1) {
            val min = getTrackMinRoute(track, trackEndArray, currentLine, order)
            if (min.first == -1 && min.second == -1) return -1
            memo[order][0] = min.first
            memo[order][1] = min.second
        }

        if (currentLine == 0) {
            moveCount += memo[order][0]
        } else {
            moveCount += memo[order][1]
        }

        if (trackEndArray[order] == 0) {
            currentLine = 0
        } else if(trackEndArray[order] == 1) {
            currentLine = 1
        }
    }

    return moveCount
}

private fun getTrackMinRoute(track: Pair<CharArray, CharArray>,
                             trackEndArray: IntArray,
                             trackNumber: Int,
                             startLine: Int,
): Pair<Int, Int> {

    var moveCount = 1 // 움직인 횟수

    val trackLength = track.first.size

    if (!validTrack(track)) {
        return -1 to -1
    }

    // 첫번째 라인의 처음으로 등장하는 장애물 index
    val firstLineOfFirstObject = track.first.indexOf('#')
    // 두번째 라인의 처음으로 등장하는 장애물 index
    val secondLineOfFirstObject = track.second.indexOf('#')

    if (firstLineOfFirstObject == -1 && secondLineOfFirstObject == -1) {
        trackEndArray[trackNumber] = 2
        return trackLength to trackLength
    }

    var currentLine = startLine

    for (i in 1 until trackLength) {
        if (getCurrentLine(track, currentLine)[i] == '#') {
            currentLine = getAnotherLine(currentLine)
            // 장애물로 경로가 막혀있을 경우
            if (getCurrentLine(track, currentLine)[i] == '#'
                || getCurrentLine(track, currentLine)[i-1] == '#') {
                return -1 to -1
            }
            moveCount += 2
        } else {
            moveCount += 1
        }
    }

    trackEndArray[trackNumber] = currentLine

    return if (startLine == 0) {
        if (track.second[0] == '#') moveCount to -1
        else moveCount to moveCount + 1
    } else {
        if (track.first[0] == '#') -1 to moveCount
        else moveCount + 1 to moveCount
    }
}

// 트랙 유효성 검사
private fun validTrack(track: Pair<CharArray, CharArray>): Boolean {
    val length = track.first.size

    if (track.first[0] == '#' && track.second[0] == '#') {
        return false
    }

    if (track.first[length - 1] == '#' && track.second[length - 1] == '#') {
        return false
    }

    return true
}

private fun getAnotherLine(line: Int): Int {
    return if (line == 1) 0 else 1
}

private fun getCurrentLine(track: Pair<CharArray, CharArray>, line: Int): CharArray {
    return if (line == 0) track.first else track.second
}

/*
2 3
1 2 1
#..
...
..
#.

9

-1 3  / 1
2 -1  / 0
 */
