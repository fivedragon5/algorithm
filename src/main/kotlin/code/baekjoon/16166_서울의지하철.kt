import java.util.StringTokenizer

var list = arrayListOf<ArrayList<Long>>()
var nodes = arrayListOf<MutableSet<Int>>()
var destination: Long = 0L
var destinationLines = mutableListOf<Int>()
var visited: Array<Boolean> = emptyArray()
var min = 11

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    var startLineList = mutableListOf<Int>()

    for(i in 0 until N) {
        st = StringTokenizer(readLine())
        val stationCount = st.nextToken().toInt()
        list.add(ArrayList())
        nodes.add(mutableSetOf())
        for (j in 1 .. stationCount) {
            val station = st.nextToken().toLong()
            if (station == 0L) {
                startLineList.add(i)
            }
            list[i].add(station)
        }
    }

    destination = StringTokenizer(readLine()).nextToken().toLong()
    checkDestination()
    for (start in startLineList) {
        visited = Array(N) { false }
        dfs(start, 0)
    }

    if (min === 11) {
        print(-1)
    }
    else {
        println(min)
    }
}

fun checkDestination() {
    var map = HashMap<Long, MutableSet<Int>>()

    for(i in 0 until list.size) {
        for (subwayStop in list[i]) {
            val lineList = map.getOrDefault(subwayStop, mutableSetOf())
            if (lineList.size === 0) {
                map[subwayStop] = mutableSetOf(i)
            }
            else {
                for (l in lineList) {
                    if (i !== l) {
                        nodes[i].add(l)
                        nodes[l].add(i)
                    }
                }
                lineList.add(i)
                map.put(subwayStop, lineList)
            }
            if (subwayStop === destination) {
                destinationLines.add(i)
            }
        }
    }
}

fun dfs(startLine: Int, count: Int) {
    if (destinationLines.contains(startLine)) {
        min = min.coerceAtMost(count)
        visited[startLine] = false
        return
    }
    var line = startLine
    visited[line] = true
    if (nodes[line].size != 0) {
        for (nextLine in nodes[line]) {
            if (!visited[nextLine]) {
                visited[nextLine] = true
                dfs(nextLine, count + 1)
                visited[nextLine] = false
            }
        }
        visited[line] = false
    }
}
/*
3
3 0 2 3
4 2 5 7 10
2 10 8
8

3
3 0 2 3
5 2 0 7 10 8
2 10 8
8

3
1 0
2 0 3
2 3 8
8

5
7 12 1 2 3 1 12 8
2 12 13
7 13 0 9 4 99 99 22
7 13 8 10 7 99 13 22
2 13 22
22

5
7 12 1 2 3 1 12 8
2 12 13
7 13 0 9 4 99 99 99
7 13 8 10 7 99 13 22
3 13 22 4294967296
22

3
3 6 99 4294967295
4 2 5 7 10
2 10 8
55

1
1 0
0
 */

