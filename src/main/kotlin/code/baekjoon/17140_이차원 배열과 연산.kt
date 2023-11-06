import java.util.StringTokenizer

/**
 * 백준 17140번 문제
 * @see https://www.acmicpc.net/problem/17140
 * 이차원 배열과 연산
 * 1 <= r, c, k <= 100, 3 X 3 크기의 배열
 * 1 <= Array[x][y] <= 100
 */
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {

    var st = StringTokenizer(readLine())

    val r = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val list = mutableListOf<MutableList<Int>>()

    for (i in 0 until 3) {
        st = StringTokenizer(readLine())
        val temp = mutableListOf<Int>()
        for (j in 0 until 3) {
            temp.add(st.nextToken().toInt())
        }
        list.add(temp)
    }
}

private fun test() {

}
