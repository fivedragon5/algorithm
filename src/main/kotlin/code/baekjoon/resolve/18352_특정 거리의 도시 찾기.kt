import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 *
 * 제한)
 * 2 <= N <= 300,000
 * 1 <= M <= 1,000,000
 * 1 <= K <= 300,000
 * 1 <= X <= N
 */
data class Edge(val to: Int, val cost: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, k, x) = br.readLine().split(" ").map { it.toInt() }

    val graph = Array(n + 1) { mutableListOf<Edge>() }
    repeat(m) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        graph[a].add(Edge(b, 1))
    }

    val dist = IntArray(n + 1) { Int.MAX_VALUE }
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
    pq.offer(0 to x)
    dist[x] = 0

    while (pq.isNotEmpty()) {
        val (d, v) = pq.poll()
        if (d > dist[v]) continue
        for (e in graph[v]) {
            if (dist[e.to] > dist[v] + e.cost) {
                dist[e.to] = dist[v] + e.cost
                pq.offer(dist[e.to] to e.to)
            }
        }
    }

    var count = 0
    for (i in 1..n) {
        if (dist[i] == k) {
            println(i)
            count++
        }
    }
    if (count == 0) {
        println(-1)
    }
}
