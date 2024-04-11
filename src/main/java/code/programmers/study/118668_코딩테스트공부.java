package code.programmers.study;

/**
 * 0 ≤ alp,cop ≤ 150
 * 1 ≤ problems.length ≤ 100
 * problems[n].length = 5
 * problems의 원소 [alp_req, cop_req, alp_rwd, cop_rwd, cost]의 형태로 이루어져 있습니다.
 * 0 ≤ alp_req, cop_req ≤ 150 문제를 푸는데 필요한 알고력,코딩력
 * 0 ≤ alp_rwd, cop_rwd ≤ 30 문제를 풀었을때 증가하는 알고력,코딩력
 * 1 ≤ cost ≤ 100 문제를 푸는데 드는 시간
 */
class Lesson118668 {
    static int solution(int alp, int cop, int[][] problems) {
        int maxAlpReq = 0;
        int maxCopReq = 0;

        for (int i = 0; i < problems.length; i++) {
            maxAlpReq = Math.max(maxAlpReq, problems[i][0]);
            maxCopReq = Math.max(maxCopReq, problems[i][1]);
        }

        System.out.println(maxAlpReq +":"+ maxCopReq);

        int second = 0;

        while (true) {
            if (alp >= maxAlpReq && cop >= maxCopReq) break;
        }

        return second;
    }

    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10,15,2,1,2},{20,20,3,3,4}};

        System.out.println("===START===");
        System.out.println(solution(alp, cop, problems));
        System.out.println("===END===");
    }
}
