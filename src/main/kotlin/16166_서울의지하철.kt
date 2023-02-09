import java.util.StringTokenizer

val list = arrayListOf<ArrayList<Int>>()
var destination: Int = 0

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()

    var startSubwayLine: Int = 0
    var startIndex: Int = 0
    var visited = BooleanArray(N) { false }

    for(i in 0 until N) {
        val temp = readLine().split(" ").map { it.toInt() }
        list.add(ArrayList())
        for (j in 1 .. temp[0]) {
            if (temp[j] == 0) {
                startSubwayLine = i;
                startIndex = j
            }
            list.get(i).add(temp[j])
        }
    }

    destination = StringTokenizer(readLine()).nextToken().toInt()
    val destinationLine = checkDestination()

    println("도착 라인 : ${destinationLine}")

    bfs(startSubwayLine, startIndex)

}

fun checkDestination(): Int {
    for(i in 0 until list.size) {
        for (subwayStop in list.get(i)) {
            if (subwayStop == destination) return i
        }
    }
    return -1
}

fun bfs(startLine: Int, startIndex: Int) {
    for (i in 0 until list.size) {
        println(list.get(i))
    }

    var currentLine = startLine
    var currentIndex = startIndex
}

