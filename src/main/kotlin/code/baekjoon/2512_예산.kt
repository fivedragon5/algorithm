package code.baekjoon

import java.util.ArrayList
import java.util.Scanner

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val count = nextInt()
    val budgets = ArrayList<Int>()
    var budgetSum = 0
    for (i in 1 .. count) {
        val budget = nextInt()
        budgets.add(budget)
        budgetSum += budget
    }
    val totalBudget = nextInt()
    val sortBudgets = budgets.sorted()
    var answer = sortBudgets.last()

    var left = 0
    var right = count - 1

    // TODO : 이분탐색으로 풀어보기
    println(answer)
}
/*
4
120 110 140 150
485

127

5
70 80 30 40 100
450

100
 */
