package code.leetcode.daily;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/product-of-the-last-k-numbers/description/?envType=daily-question&envId=2025-02-14
 *
 * 제한)
 *  0 <= num <= 100
 *  1 <= k <= 4 * 10^4
 *  At most 4 * 104 calls will be made to add and getProduct.
 *  The product of the stream at any point in time will fit in a 32-bit integer.
 *
 * 문제)
 *  ProductOfNumbers Class 구현
 *  1. add(int num)
 *   - List<Integer> 에 add
 *  2. getProduct(int k)
 *   - 가장 최근에 넣은 원소 k개를 곱해서 반환
 *
 * 풀이)
 *  add(num)
 *      1. list(1) = list(0) * num
 *  getProduct(k)
 *      1. return list.get(list.size() - 1) / list.get(list.size() - k - 1)
 */

public class Question_20250214 {
    public static void main(String args[]) throws IOException {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);
        productOfNumbers.add(0);
        productOfNumbers.add(2);
        productOfNumbers.add(5);
        productOfNumbers.add(4);
        System.out.println(productOfNumbers.getProduct(2));
        System.out.println(productOfNumbers.getProduct(3));
        System.out.println(productOfNumbers.getProduct(4));
        productOfNumbers.add(8);
        System.out.println(productOfNumbers.getProduct(2));
    }

    static class ProductOfNumbers {
        List<Integer> list;
        public ProductOfNumbers() {
            list = new ArrayList<>();
            list.add(1);
        }

        public void add(int num) {
            if (num == 0) {
                list = new ArrayList<>();
                list.add(1);
            } else {
                list.add(list.get(list.size() - 1) * num);
            }
        }

        public int getProduct(int k) {
            if (list.size() <= k) {
                return 0;
            }
            return list.get(list.size() - 1) / list.get(list.size() - k - 1);
        }
    }
}
