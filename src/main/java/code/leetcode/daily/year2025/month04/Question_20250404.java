package code.leetcode.daily.year2025.month04;

import java.io.IOException;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/description/?envType=daily-question&envId=2025-04-04
 *
 * 제한)
 *  The number of nodes in the tree will be in the range [1, 1000].
 *  0 <= Node.val <= 1000
 *  The values of the nodes in the tree are unique.
 *
 * 문제)
 *  1. 이진트리의 루트가 주어질 때, 이 트리의 가장 깊은 리프노드들의 최소 공통 조상 반환하기
 *
 * 풀이)
 *  1. 주어진 노드의 깊이를 계산
 *  2. 가장 깊은 리프 노트 찾기
 *  3. 최소 공통 조상을 찾기
 */

public class Question_20250404 {
    public static void main(String args[]) throws IOException {
    }

    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        int maxDepth = getMaxDepth(root);
        return dfs(root, maxDepth, 0);
    }

    private static TreeNode dfs(TreeNode root, int maxDepth, int length) {
        if (root == null) return null;
        if (maxDepth - 1 == length) return root;
        TreeNode left = dfs(root.left, maxDepth, length + 1);
        TreeNode right = dfs(root.right, maxDepth, length + 1);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    private static int getMaxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getMaxDepth(root.left), getMaxDepth(root.right));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
