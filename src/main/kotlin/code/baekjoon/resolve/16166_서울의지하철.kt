import java.util.StringTokenizer
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    var startLineList = mutableListOf<Int>()

    val list = arrayListOf<ArrayList<Long>>()
    val nodes = arrayListOf<MutableSet<Int>>()
    var destination = 0L
    var destinationLines = mutableListOf<Int>()
    var visited: Array<Boolean>
    var min = 11

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
    checkDestination(list, nodes, destination, destinationLines)
    for (start in startLineList) {
        visited = Array(N) { false }
        dfs(start, 0, visited, nodes, destinationLines, min)
    }

    if (min == 11) {
        print(-1)
    } else {
        println(min)
    }
}

fun checkDestination(
    list: ArrayList<ArrayList<Long>>,
    nodes: ArrayList<MutableSet<Int>>,
    destination: Long,
    destinationLines: MutableList<Int>
) {
    var map = HashMap<Long, MutableSet<Int>>()

    for (i in 0 until list.size) {
        for (subwayStop in list[i]) {
            val lineList = map.getOrDefault(subwayStop, mutableSetOf())
            if (lineList.isEmpty()) {
                map[subwayStop] = mutableSetOf(i)
            } else {
                for (l in lineList) {
                    if (i != l) {
                        nodes[i].add(l)
                        nodes[l].add(i)
                    }
                }
                lineList.add(i)
                map[subwayStop] = lineList
            }
            if (subwayStop == destination) {
                destinationLines.add(i)
            }
        }
    }
}

fun dfs(
    startLine: Int,
    count: Int,
    visited: Array<Boolean>,
    nodes: ArrayList<MutableSet<Int>>,
    destinationLines: MutableList<Int>,
    min: Int
) {
    var minz = min
    if (destinationLines.contains(startLine)) {
        minz = min.coerceAtMost(count)
        visited[startLine] = false
        return
    }
    var line = startLine
    visited[line] = true
    if (nodes[line].isNotEmpty()) {
        for (nextLine in nodes[line]) {
            if (!visited[nextLine]) {
                visited[nextLine] = true
                dfs(nextLine, count + 1, visited, nodes, destinationLines, minz)
                visited[nextLine] = false
            }
        }
        visited[line] = false
    }
}
