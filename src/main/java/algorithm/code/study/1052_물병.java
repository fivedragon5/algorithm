package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem1052 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int buyWaterCount = 0;
        int oneCount = 100000000;
        String waterBinaryStr = Integer.toBinaryString(N);

        while(oneCount > K) {
            oneCount = 0;
            waterBinaryStr = Integer.toBinaryString(N + buyWaterCount);

            for (int i = waterBinaryStr.length()-1; i >= 0; i--) {

                if (waterBinaryStr.charAt(i) == '1') {
                    oneCount++;
                    if (oneCount > K) {
                        buyWaterCount++;
                        break;
                    }
                }
            }
        }
        System.out.println(buyWaterCount);
    }
}

/*
3 1

1

13 2

3

1000000 5

15808

10000000 1
 */
