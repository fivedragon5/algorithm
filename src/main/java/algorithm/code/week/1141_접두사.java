package algorithm.code.week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
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

        Arrays.sort(words);

        ArrayList<ArrayList<String>> list = new ArrayList<>();
        int index = 0;
        list.add(new ArrayList());
        String beforeWord = words[0];
        list.get(0).add(beforeWord);

        for (int i = 1; i < words.length; i++) {
            String currentWord = words[i];
            if (currentWord.startsWith(beforeWord)) {
                list.get(index).add(currentWord);
            }
            else {
                list.add(new ArrayList());
                list.get(++index).add(currentWord);
                beforeWord = currentWord;
            }
        }

        int count = list.size();

        for (ArrayList<String> wordList : list) {
            System.out.println(wordList.toString());
            int max = 0;
            for (int i = 0; i < wordList.size(); i++) {
                int c = 0;
                for (int j = i; j < wordList.size(); j++) {
                    if (!wordList.get(i).startsWith(wordList.get(j)) && !wordList.get(j).startsWith(wordList.get(i))) {
                        c++;
                    }
                }
                max = Math.max(c, max);
            }
            if (max > 1) {
                count = count + max - 1;
            }
            else {
                count += max;
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

4

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

 */

