/**
 * 1:i, 5:V, 10:X, 50:L, 500:C, 1000:M
 * 4:IV
 */
val values1 = hashMapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
val values2 = linkedMapOf(
    1000 to "M", 900 to "CM", 500 to "D", 400 to "CD",
    100 to "C", 90 to "XC", 50 to "L", 40 to "XL",
    10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I"
)
val answerList = mutableListOf<String>()
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()

    repeat(T) {
        val input = readLine()!!
        if (input[0].isDigit()) {
            val arabic = input.toInt()
            val roman = arabiaToRoma(arabic)
            answerList.add(roman)
        } else {
            val roma = input
            val arabic = romaToArabic(roma)
            answerList.add(arabic.toString())
        }
    }

    for (answer in answerList) {
        println(answer)
    }
}

fun romaToArabic(roman: String): Int {

    var result = 0
    var prevValue = 0
    for (i in roman.indices.reversed()) {
        val value = values1[roman[i]]!!
        if (value >= prevValue) {
            result += value
        } else {
            result -= value
        }
        prevValue = value
    }

    return result
}

fun arabiaToRoma(arabic: Int): String {
    var num = arabic
    var roman = ""
    for (value in values2.keys) {
        while (num >= value) {
            roman += values2[value]
            num -= value
        }
    }

    return roman
}

/*
3
999
MMXVI
MMMCLXXVIII

CMXCIX
2016
3178
 */

