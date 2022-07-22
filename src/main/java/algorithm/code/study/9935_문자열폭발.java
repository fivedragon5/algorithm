package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem9935 {
    /**
     mirkovC4nizCC44
     C4
     mirkovniz

     12ab112ab2ab
     12ab
     FRULA

     anananananananananT
     ant
     */

    static String str;
    static String boomStr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        str = st.nextToken();
        st = new StringTokenizer(br.readLine());
        boomStr = st.nextToken();

        //solve();
        solve2();

    }

    public static void solve() {
        int strLen = str.length();

        while(true) {
            str = str.replaceAll(boomStr,"");
            int temp = str.length();
            if(strLen == temp) break;
            else strLen = temp;
        }

        str = strLen > 0 ? str : "FRULA";
        System.out.println(str);
    }
    public static void solve2() {
        int boomLen = boomStr.length();
        char ch = boomStr.charAt(0);
        for (int i = 0; i <= str.length() - boomLen; i++) {
            if(str.charAt(i) == ch) {
                String temp = str.substring(i,i+boomLen);
                System.out.println(temp);
                if(temp.equals(boomStr)) {
                    str = str.substring(0,i) + str.substring((i+boomLen), str.length());
                    i -= 2;
                }
            }
        }
        str = str.length() > 0 ? str : "FRULA";
        System.out.println(str);
    }
}
