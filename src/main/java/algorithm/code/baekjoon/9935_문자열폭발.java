package algorithm.code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
     anT
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
        solve3();

    }
    
    //시간 초과
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
    //메모리 초과
    public static void solve2() {
        int boomLen = boomStr.length();
        StringBuffer sb = new StringBuffer(str);
        StringBuffer sbTemp = new StringBuffer(str);
        for (int i = 0; i <= sb.length() - boomLen; i++) {
            if(sb.charAt(i) == boomStr.charAt(0)) {

                if(boomStr.equals(sb.substring(i, i+boomLen))) {
                    sbTemp.delete(0,sbTemp.length());
                    sbTemp.append(sb);
                    sb.delete(0, sb.length());
                    sb.append(sbTemp.substring(0,i));
                    sb.append(sbTemp.substring((i+boomLen), sbTemp.length()));
                    i = i - boomStr.length() > 0 ? i - boomStr.length() : -1;
                }
            }
        }
        sb = sb.length() > 0 ? sb : sb.append("FRULA");
        System.out.println(sb);
    }

    public static void solve3() {

        char[] chArr = new char[str.length()];
        int index = 0;
        int boomStrLength = boomStr.length();

        for (int i = 0; i < str.length(); i++) {
            chArr[index] = str.charAt(i);
            if(str.charAt(i) == boomStr.charAt(boomStrLength-1)) {
                if(isEquals(index, chArr)) index = index - boomStrLength;
            }
            index++;
        }
        if(index > 0) System.out.println(String.valueOf(chArr, 0, index));
        else System.out.println("FRULA");

    }

    public static boolean isEquals(int index, char[] arr) {
        if (boomStr.length() > index + 1) return false;

        for (int i = 0; i < boomStr.length(); i++) {
            if (boomStr.charAt(i) != arr[index - boomStr.length() + i + 1]) {
                return false;
            }
        }
        return true;
    }
}
