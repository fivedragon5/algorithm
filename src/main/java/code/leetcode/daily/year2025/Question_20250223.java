package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/description/?envType=daily-question&envId=2025-02-23
 *
 * 제한)
 * 1 <= preorder.length <= 30
 * 1 <= preorder[i] <= preorder.length
 * All the values of preorder are unique.
 * postorder.length == preorder.length
 * 1 <= postorder[i] <= postorder.length
 * All the values of postorder are unique.
 * It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
 *
 * 문제)
 *  1. 두개의 배열이 주어집니다. (preorder, postorder)
 *   - preorder : 어떤 이진트리의 전위순회 결과
 *   - postorder : 어떤 이진트리의 후위순회 결과
 *  2. 2개의 배열을 이용해서 원래의 이진트리를 복원 하기
 *  3. 만약 정답이 여러개일 경우 아무거나 반환해도 가능
 *   - 전위 순회 : 뿌리 -> 왼쪽 자식 -> 오른쪽 자신
 *   - 후위 순회 : 왼쪽 자식 -> 오른쪽 자식 -> 뿌리
 *
 * 풀이)
 *  1. preorder[preIndex] 로 TreeNode 생성
 *  2. 생성한 노드의 값과 postorder[postIndex] 값이 다르다면 leftNode 생성, rightNode 생성
 *  3. 위 처리가 끝날때(left-node 일 경우) postIndex 증가
 */


public class Question_20250223 {

    private static int preorderIndex = 0;
    private static int postorderIndex = 0;

    public static void main(String args[]) throws IOException {
        int[] preorder = new int[]{1,2,4,5,3,6,7};
        int[] postorder = new int[]{4,5,2,6,7,3,1};
        System.out.println(constructFromPrePost(preorder, postorder));

//        int[] preorder2 = new int[]{1};
//        int[] postorder2 = new int[]{1};
//        System.out.println(constructFromPrePost(preorder2, postorder2));
    }

    // 전위 순회 : 뿌리 -> 왼쪽 자식 -> 오른쪽 자신
    // 후위 순회 : 왼쪽 자식 -> 오른쪽 자식 -> 뿌리

    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        TreeNode treeNode = buildTree(preorder, postorder);
        return treeNode;
    }

    private static TreeNode buildTree(int[] preorder, int[] postorder) {
        TreeNode treeNode = new TreeNode(preorder[preorderIndex++]);
        if (treeNode.val != postorder[postorderIndex]) {
            treeNode.left = buildTree(preorder, postorder);
        }

        if (treeNode.val != postorder[postorderIndex]) {
            treeNode.right = buildTree(preorder, postorder);
        }
        postorderIndex++;
        return treeNode;
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
