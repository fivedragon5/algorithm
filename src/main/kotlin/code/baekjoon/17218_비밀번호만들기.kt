fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val firstWord = readLine()
    val secondWord = readLine()
    val dp = Array(firstWord.length + 1) { IntArray(secondWord.length + 1) { 0 } }
    for (i in 1..firstWord.length) {
        for (j in 1..secondWord.length) {
            if (firstWord[i - 1] == secondWord[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }
    var i = firstWord.length
    var j = secondWord.length
    val sb = StringBuilder()
    while (i > 0 && j > 0) {
        if (firstWord[i - 1] == secondWord[j - 1]) {
            sb.append(firstWord[i - 1])
            i--
            j--
        } else {
            if (dp[i - 1][j] > dp[i][j - 1]) {
                i--
            } else {
                j--
            }
        }
    }
    println(sb.reverse())
}
