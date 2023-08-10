fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val K = readLine().toInt()
    if (K >= N) {
        println("0")
        return
    }
    val list = readLine()
        .split(" ")
        .map { it.toInt() }
        .toSet()
        .toList()
        .sorted()
    var answer = 0
    val gapList = mutableListOf<Int>()

    for (i in 0 until  list.size - 1) {
        gapList.add(list[i+1] - list[i])
    }

    val sortedGapList = gapList.sorted()

    for(i in 0 .. sortedGapList.size - K)
        answer += sortedGapList[i]

    println(answer)
}
