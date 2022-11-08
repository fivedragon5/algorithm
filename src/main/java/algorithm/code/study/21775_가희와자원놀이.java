package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Problem21775 {

    /**
     * 풀이)
     * 각턴을 수행한 사람의 정보를 가지고있는 배열을 순회하면서 출력
     * 1. 각 턴을 진행할때마다 해당 player가 acquire들고 있는지 확인
     * 2. next, relase 카드는 그대로 id출력
     * 3. acquire카드일경우 공용 공간에서 카드의 소유자 update
     *
     * 설명)
     * 1. 공동 공간 카드의 소유자를 표시해주는 배열 int[2*10^9] : commonCards
     * 2. 공동 공간 카드의 소유자를 표시해주는 map(cardNumber,playerNumber) Map<Integer,Integer> : commonCards
     *
     * 플레이어가 들고있는 acquire 카드 정보 int[N+1][2] : getAcquire
     *  - getAcquire[N][0] : acquire카드의 Id (출력용)
     *  - getAcquire[N][1] : acquire카드의 number

     문제 조건)
     1 ≤ N ≤ T ≤ 5×10^5
     1 ≤ 턴을 수행하는 사람의 번호 ≤ N
     1 ≤ acquire나 release 연산에서 등장하는 수 ≤ 2×10^9
     자원 카드 n을 획득한 사람이 다시 acquire n을 수행하는 경우는 주어지지 않습니다.

     ***획득하지 않은 자원 카드를 release 하는 경우는 주어지지 않습니다.***
     1 ≤ 더미에 있는 연산 카드의 id ≤ 5×10^5
     더미에 있는 연산 카드의 id는 모두 다릅니다.
     */

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        HashMap<Integer, Integer> commonCards = new HashMap<>();
        int[][] getAcquire = new int[N+1][2]; // acquire카드를 누가 소유하고 있는지 확인 (0 카드 없음)

        int[] turns = new int[T];

        for (int i = 0; i < T; i++) {
            turns[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < T; i++) {
            int turn = turns[i]; // 현재 차례

            // 현재 순서의 사람이 acquire 카드를 들고 있을 경우 손에있는 acquire카드를 바로 사용 한다.
            if (getAcquire[turn][0] != 0) {
                System.out.println(getAcquire[turn][0]);
                int getCardNumber = getAcquire[turn][1];
                if (!commonCards.containsKey(getCardNumber)) {
                    //공용에 있을경우 카드를 가지고 온다.
                    commonCards.put(getCardNumber, turn);
                    getAcquire[turn][0] = 0;
                    getAcquire[turn][1] = 0;
                }
            }

            // 현재 순서의 사람이 acquire 카드를 들고 있지 않을 경우 연산 카드 뭉치에서 한장 사용한다.
            else {
                st = new StringTokenizer(br.readLine());
                int id = Integer.parseInt(st.nextToken());
                sb.delete(0, sb.length());
                sb.append(st.nextToken());

                //우선 id를 출력한다.
                System.out.println(id);

                if ("acquire".equals(sb.toString())) {
                    int number = Integer.parseInt(st.nextToken());
                    // 선택된 숫자가 공용 공간에 있을 경우
                    if (!commonCards.containsKey(number)) {
                        commonCards.put(number, turn);
                    }
                    // 선택된 숫자가 공용 공간에 없을 경우
                    else {
                        getAcquire[turn][0] = id;
                        getAcquire[turn][1] = number;
                    }
                }
                else if ("release".equals(sb.toString())) {
                    int number = Integer.parseInt(st.nextToken());
                    commonCards.remove(number);
                }
            }
        }
    }

// 메모리, 시간 초과
//    public static void main(String args[]) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int T = Integer.parseInt(st.nextToken());
//        st = new StringTokenizer(br.readLine());
//        StringBuffer sb = new StringBuffer();
//
//        int[] commonCards = new int[Integer.MAX_VALUE]; // 공용 자원 카드의 소유권 (0:공용, N:N이 소유)
//        int[][] getAcquire = new int[N+1][2]; // acquire카드를 누가 소유하고 있는지 확인 (0 카드 없음)
//
//        int[] turns = new int[T];
//
//        for (int i = 0; i < T; i++) {
//            turns[i] = Integer.parseInt(st.nextToken());
//        }
//
//        for (int i = 0; i < T; i++) {
//            int turn = turns[i]; // 현재 차례
//
//            // 현재 순서의 사람이 acquire 카드를 들고 있을 경우 손에있는 acquire카드를 바로 사용 한다.
//            if (getAcquire[turn][0] != 0) {
//                System.out.println(getAcquire[turn][0]);
//                int currentCard = (int) commonCards[getAcquire[turn][1]];
//                if (currentCard == 0) {
//                    //공용에 있을경우 카드를 가지고 온다.
//                    commonCards[currentCard] = turn;
//                    getAcquire[turn][0] = 0;
//                }
//            }
//
//            // 현재 순서의 사람이 acquire 카드를 들고 있지 않을 경우 연산 카드 뭉치에서 한장 사용한다.
//            else {
//                st = new StringTokenizer(br.readLine());
//                int id = Integer.parseInt(st.nextToken());
//                sb.delete(0, sb.length());
//                sb.append(st.nextToken());
//
//                //우선 id를 출력한다.
//                System.out.println(id);
//
//                if ("acquire".equals(sb.toString())) {
//                    int number = Integer.parseInt(st.nextToken());
//                    // 선택된 숫자가 공용 공간에 있을 경우
//                    if (commonCards[number] == 0) {
//                        commonCards[number] = turn;
//                    }
//                    // 선택된 숫자가 공용 공간에 없을 경우
//                    else {
//                        getAcquire[turn][0] = id;
//                        getAcquire[turn][1] = number;
//                    }
//                }
//                else if ("release".equals(sb.toString())) {
//                    int number = Integer.parseInt(st.nextToken());
//                    commonCards[number] = 0;
//                }
//            }
//        }
//    }
}

/**
 2 10
 1 1 2 2 1 1 2 2 2 2
 1 next
 2 acquire 2000000000
 3 acquire 2000000000
 4 next
 5 release 2000000000
 6 release 2000000000
 7 next
 8 acquire 2000000000
 9 next
 10 next

*/