package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem2671 {
    /** String <= 150
     10010111
     10011001100110010110011111111111111111111101
     */
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 잠수함 소리의 패턴 (100~1~|01)~
        // * 0의 갯수로 파악 (2개|1개)

        //0  : (01)~ 패턴
        //1  : (100~1~) 패턴1 처음 1을 만났을 경우

        //2  : (100~1~) 패턴2-1 바로다음 0을 만났을 경우
        //3  : (100~1~) 패턴2-2 바로다음 두번째0을 만났을 경우

        //4  : (100~1~) 패턴2-3 다음0을 만나기 전까지(SUBMARINE 처리)

        //5  : (SUBMARINE 처리)
        char currentPattern = '6';

        String sound = st.nextToken();

        for (int i = 0; i < sound.length(); i++) {
            char ch = sound.charAt(i);

            if (currentPattern == '0') {
                if (ch == '1') currentPattern = '5';
                //패턴 2개가 중복될경우 예외처리 추가
                else if (sound.charAt(i-1) == '0'
                        && sound.charAt(i-2) == '1'
                        && sound.charAt(i-3) == '1'
                        && 0 <= (i-3)) currentPattern = '3';
                else break;
            }
            else if(currentPattern == '1') {
                if (ch == '0') currentPattern = '2';
                else break;
            }
            else if(currentPattern == '2') {
                if (ch == '0') currentPattern = '3';
                else break;
            }
            else if(currentPattern == '3') {
                if (ch == '1') currentPattern = '4';
            }

            else if(currentPattern == '4') {
                if (ch == '0') currentPattern = '0';
            }

            else {
                currentPattern = ch;
            }
        }
        System.out.println(currentPattern == '5' || currentPattern == '4' ? "SUBMARINE" : "NOISE" );
    }
}
