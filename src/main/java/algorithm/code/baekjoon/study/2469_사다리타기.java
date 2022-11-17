package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem2469 {

    /**
     * 풀이)
     *  숨겨저 있는 사다리를 기준으로 시작선부터 숨겨저있는 줄전까지의 순서를 구하고
     *  최종순서에서부터 숨겨저 있는 줄까지의 순서를 구한다음 비교해서 사다리를 생성해준다.
     *
     * 설명)
     * ladders[][] : 사다리 위치
     * questionMarkLine : 숨겨저 있는 라인 
     * movePlayer() : 사람들 이동
     *
     * 첫 줄에는 참가한 사람의 수 k가 나온다(3 ≤ k ≤ 26).
     * 그 다음 줄에는 가로 막대가 놓일 전체 가로 줄의 수를 나타내는 n이 나온다(3 ≤ n ≤ 1,000).
     * 그리고 세 번째 줄에는 사다리를 타고 난 후 결정된 참가자들의 최종 순서가 길이 k인 대문자 문자열로 들어온다.
     * k와 n, 그리고 도착순서 문자열이 나타난 다음, 이어지는 n개의 줄에는 앞서 설명한 바와 같이 ‘*’와 ‘-’ 문자로 이루어진 길이 k-1인 문자열이 주어진다.
     * 그 중 감추어진 가로 줄은 길이가 k-1인 ‘?’ 문자열로 표시되어 있다.
     *
     * 사다리를 놓아도 순서를 맞출 수 없는경우 k-1 만큼 "x"를 출력 해준다. *
     */
    static int k,n;
    static String ladders[][];
    static int questionMarkLine;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        String resultPlayers = st.nextToken();
        int[] playersToAscii = new int[k];
        int[] resultPlayersToAscii = new int[k];

        for (int i = 0; i < resultPlayers.length(); i++) {
            playersToAscii[i] = i;
            resultPlayersToAscii[i] = resultPlayers.charAt(i) - 65;
        }

        ladders = new String[n][k-1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            ladders[i] = st.nextToken().split("");
            if ("?".equals(ladders[i][0])) questionMarkLine = i;
        }

        for (int i = 0; i < questionMarkLine; i++) {
            playersToAscii = movePlayer(playersToAscii, i, 0);
        }

        for (int i = n; i > questionMarkLine + 1; i--) {
            resultPlayersToAscii = movePlayer(resultPlayersToAscii, i, 1);
        }

//        System.out.println(Arrays.toString(playersToAscii));
//        System.out.println(Arrays.toString(resultPlayersToAscii));

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < k - 1 ; i++) {
            if(playersToAscii[i] != resultPlayersToAscii[i] && playersToAscii[i] != resultPlayersToAscii[i+1]) {
                System.out.println("x".repeat(k-1));
                return;
            }
            else if(playersToAscii[i] != resultPlayersToAscii[i]) {
                sb.append("-");
                sb.append("*");
                i++;
            }
            else {
                sb.append("*");
            }
        }

        if(sb.length() > k-1) {
            sb.delete(k-1, sb.length());
        }

        System.out.println(sb.toString());

    }

    //isRevers 0 or 1 (down : 0, up : 1)
    static int[] movePlayer(int[] player, int curentStep, int isRevers) {
        int[] movedPlayer = new int[k];

        for (int i = 0; i < k - 1; i++) {
            if ("-".equals(ladders[curentStep-isRevers][i])) {
                movedPlayer[i+1] = player[i];
                movedPlayer[i] = player[i+1];
                i++;
            }
            else {
                movedPlayer[i] = player[i];
            }
        }

        if ("*".equals(ladders[curentStep-isRevers][k-2])) movedPlayer[k-1] = player[k-1];

        return movedPlayer;
    }
}

/**
 10
 5
 ACGBEDJFIH
 *-***-***
 -*-******
 ?????????
 -**-***-*
 **-*-*-*-

 **-*-***-
*/