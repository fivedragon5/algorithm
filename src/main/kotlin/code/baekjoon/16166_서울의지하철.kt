import java.util.StringTokenizer

var list = arrayListOf<ArrayList<Int>>()
var nodes = arrayListOf<MutableSet<Int>>()
var destination: Int = 0
var destinationLines = mutableListOf<Int>()
var visited: Array<Boolean> = emptyArray()
var min = 12

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    var startLineList = mutableListOf<Int>()

    for(i in 0 until N) {
        val temp = readLine().split(" ").map { it.toInt() }
        list.add(ArrayList())
        nodes.add(mutableSetOf())
        for (j in 1 .. temp[0]) {
            if (temp[j] == 0) {
                startLineList.add(i)
            }
            list.get(i).add(temp[j])
        }
    }

    destination = StringTokenizer(readLine()).nextToken().toInt()
    checkDestination()
    for (start in startLineList) {
        visited = Array(N) { false }
        dfs(start, 0)
    }


    if (min == 12) {
        print(-1)
    }
    else {
        println(min)
    }
}

fun checkDestination() {
    var map = HashMap<Int, MutableSet<Int>>()

    for(i in 0 until list.size) {
        for (subwayStop in list.get(i)) {
            val lineList = map.getOrDefault(subwayStop, mutableSetOf())
            if (lineList.isEmpty()) {
                map[subwayStop] = mutableSetOf(i)
            }
            else {
                for (l in lineList) {
                    nodes[i].add(l)
                    nodes[l].add(i)
                }
                map.get(subwayStop)!!.add(i)
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
    for (nextLine in nodes[line]) {
        if (!visited[nextLine]) {
            visited[nextLine] = true
            dfs(nextLine, count + 1)
            visited[nextLine] = false
        }
    }
    visited[line] = false
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
 */

