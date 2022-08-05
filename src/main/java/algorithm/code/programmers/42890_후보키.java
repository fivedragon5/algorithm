package algorithm.code.programmers;

import java.util.*;

class Lesson42890 {

    static List<List<Integer>> combinationList = new LinkedList<>();
    static List<List<String>>[] candidateCombination;

    static int solution(String[][] relation) {
        int answer = 0;
        int rowsCount = relation.length;
        int columnCount = relation[0].length;

        System.out.println("r : " + rowsCount + "/ c : " + columnCount);

        List<Integer> nonCandidateColumn = new LinkedList<>();
        candidateCombination = new List[columnCount];

        for (int i = 0; i < columnCount; i++) {
            candidateCombination[i] = new LinkedList<>();
        }

        for (int i = 0; i < columnCount; i++) {
            if (isCandidateColumn(i, relation)) {
                answer++;
            }
            else {
                nonCandidateColumn.add(i);
            }
        }

        //List to Array
        int[] nonCandidateArray = new int[nonCandidateColumn.size()];

        for (int i = 0; i < nonCandidateArray.length; i++) {
            nonCandidateArray[i] = nonCandidateColumn.get(i);
        }

        boolean[] visited = new boolean[nonCandidateArray.length];

        for (int i = 2; i <= nonCandidateArray.length; i++) {
            combination(nonCandidateArray, visited, 0, i);
            System.out.println(combinationList.toString());
            isCandidateColumn(relation, i);
            combinationList.clear();
        }

        for (int i = 2; i < candidateCombination.length; i++) {
            System.out.println(candidateCombination[i].toString());
        }


        return answer;
    }
    
    //1개의 컬럼
    static boolean isCandidateColumn(int column, String[][] relation) {

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < relation.length; i ++) {
            if (map.getOrDefault(relation[i][column], 0) == 0) {
                map.put(relation[i][column], 1);
            }
            else {
                return false;
            }
        }
        return true;
    }

    //2개이상의 컬럼
    static boolean isCandidateColumn(String[][] relation, int pickCount) {
        HashMap<String, Integer> map = new HashMap<>();

        for (int j = 0; j < combinationList.size(); j++) {
            boolean isFlag = true;
            for (int i = 0; i < relation.length; i ++) {
                StringBuffer sb = new StringBuffer();
                for (int index : combinationList.get(j)) {
                    sb.append(relation[i][index]);
                }
                if (map.getOrDefault(sb.toString(), 0) == 0) {
                    map.put(sb.toString(), 1);
                }
                else {
                    isFlag = false;
                    break;
                }
            }
            if (isFlag) {
                StringBuffer indexStr = new StringBuffer();
                for (int index : combinationList.get(j)) {
                    indexStr.append(index);
                }
                //list를 String으로 append한다음 add 할것
                //candidateCombination[pickCount].add(combinationList.get(j));
            }
        }

        return true;
    }

    static void combination(int[] indexList, boolean[] visited, int start, int r) {
        if (r <= 0) {
            combinationListAdd(indexList, visited);
            return;
        }
        for (int i = start; i < indexList.length; i ++) {
            visited[i] = true;
            combination(indexList, visited, i+1, r-1);
            visited[i] = false;
        }
    }

    static void combinationListAdd(int[] indexList, boolean[] visited) {
        List<Integer> list = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < indexList.length; i++) {
            if (visited[i]) {
                list.add(indexList[i]);
            }
        }

        if(list.size() > 2) {
            //최소성 테스트 로직 추가

        }

        combinationList.add(list);
    }

    public static void main(String[] args) {
        String[][] relation = {
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        };

        System.out.println("===START===");
        System.out.println(solution(relation));
        System.out.println("===END===");
    }
}
