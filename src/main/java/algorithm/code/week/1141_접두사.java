package algorithm.code.week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 풀이)
 * 1. 입력받은 단어들은 글자길이 오름차순으로 정렬
 *  - 두 단어가 주어젔을때 두 단어중 길이가 짧은 단어가 긴 단어의 접두사가 될 수 있음
 * 2. 정렬한 단어들을 순회 하면서 현재 단어가 뒤에 있는 단어의 접두사가 될 수 있는지 확인
 *  - 2-1 어떤 단어도 접두사가 되지 않는 경우 카운트 증가
 *  - 2-2 접두사가 될 경우 다음 단어확인
 *
 * 제한)
 * 1 <= N <= 50
 * 1 <= word.length <= 50
 * 같은 단어가 있을 수 있음
 */
class Problem1141 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken();
        }

        Arrays.sort(words, Comparator.comparingInt(String::length));

        int count = 0;

        for (int i = 0 ; i < N ; i++) {
            String word = words[i];
            boolean isFlag = false;
            for (int j = i + 1 ; j < N ; j++) {
                if (words[j].startsWith(word)) {
                    isFlag = true;
                    break;
                }
            }
            if (!isFlag) {
                count++;
            }
        }

        System.out.println(count);
    }
}

/*
6
hello
hi
h
run
rerun
running

4

3
topcoder
topcoder
topcoding

2

6
h
he
h
hac
ha
running

3

7
ca
cade
caed
cae
coff
c
cb

8
cirt
c
criow
vimnb
bbm
bbmiis
vim
aosi

5
hi
hi
hk
h
zh

 */

