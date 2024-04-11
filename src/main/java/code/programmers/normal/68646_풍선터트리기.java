package code.programmers.normal;

class Lesson68646 {
    /*
        풀이방법
        1.boolean[]을 a 배열의 길이만큼 생성
        2.a배열을 반복을 돌려 현재 index를 기준으로 작은 값들의 최소값을 계속 갱신과 동시에 자신이 최소값 인지 확인
        3. 2번 과정을 반대로 진행
        4. 2,3번 과정을 하면서 boolean[]도 갱신 해준다
        5. 최소값일경우 터트릴 수 있는 풍선의 갯수을 증가시켜줌과 동시에 boolean[]이 이미 true인 경우 카운트 증가X
     */
//    static int solution(int[] a) {
//        int answer = 2;
//        int currentNumber = a[0];
//        int leftMinNumber = currentNumber;
//        int[] sortArray = a.clone();
//        Arrays.sort(sortArray);
//
//        List<Integer> list = new ArrayList<>();
//
//        for(int i = 0; i < a.length; i++) {
//            list.add(sortArray[i]);
//        }
//
//        int listIndex = list.indexOf(currentNumber);
//        list.remove(listIndex);
//
//
//        for(int i = 1; i < a.length-1; i ++) {
//            currentNumber = a[i];
//
//            listIndex = list.indexOf(currentNumber);
//            list.remove(listIndex);
//
//            int rightMinNumber = list.get(0);
//
//            //System.out.println(leftMinNumber + " | " + currentNumber + " | " + rightMinNumber);
//            //System.out.println(list.toString());
//
//            if(leftMinNumber < currentNumber && rightMinNumber < currentNumber) {
//                if(leftMinNumber > currentNumber) {
//                    leftMinNumber = currentNumber;
//                }
//            }
//            else {
//                answer++;
//            }
//        }
//        return answer;
//    }
    static int solution2(int[] a) {
        int answer = 2;
        boolean[] isBoom = new boolean[a.length];
        isBoom[0] = true;
        isBoom[a.length-1] = true;
        int currentNumber = a[0];
        int leftMin = a[0];
        int rightMin = a[a.length-1];

        for(int i = 1; i < a.length-1; i++) {
            currentNumber = a[i];
            if(currentNumber < leftMin) {
                leftMin = currentNumber;
                isBoom[i] = true;
                answer++;
            }
        }

        for(int i = a.length-2 ; i > 0; i--) {
            currentNumber = a[i];
            if(currentNumber < rightMin) {
                rightMin = currentNumber;
                if(!isBoom[i]) {
                    isBoom[i] = true;
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] a = {9,-1,-5}; // 3
        //int[] a = {-16,27,65,-2,58,-92,-71,-68,-61,-33}; // 6
        System.out.println("===START===");
        System.out.println(solution2(a));
        System.out.println("===END===");
    }
}
