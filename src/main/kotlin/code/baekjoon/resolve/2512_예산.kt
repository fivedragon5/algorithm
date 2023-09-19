package code.baekjoon.resolve

import java.util.Scanner

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val count = nextInt()
    val budgets = ArrayList<Int>()
    var maxBudget = 0;
    for (i in 1 .. count) {
        val budget = nextInt()
        budgets.add(budget)
        maxBudget = Math.max(budget, maxBudget)
    }
    val totalBudget = nextInt()

    var left = 0
    var right = maxBudget

    while (left <= right) {
        val mid = (left + right) / 2
        val sumBudget = sumBudgets(budgets, mid)
        if (sumBudget > totalBudget) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    println(right)
}

private fun sumBudgets(budgets: List<Int>, maxBudget: Int): Int {
    var sum = 0
    for (budget in budgets) {
        sum += if (budget > maxBudget) {
            maxBudget
        } else {
            budget
        }
    }
    return sum
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

5
100 100 100 100 100
50
 */
