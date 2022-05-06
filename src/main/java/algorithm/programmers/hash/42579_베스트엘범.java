package algorithm.programmers.hash;

import java.util.*;

class Lesson42579 {
    static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, Integer> playsSum = new HashMap<>();
        HashMap<String, TreeMap<Integer, Integer>> genresPlays = new HashMap<>();
        TreeMap<Integer, Integer> tempTreeMap = new TreeMap<>();

        //1. HashMap (genre, 장르별 합)
        for(int i = 0 ; i <plays.length ; i ++){
            playsSum.put(genres[i], playsSum.getOrDefault(genres[i] ,0) + plays[i]);
            tempTreeMap = genresPlays.getOrDefault(genres[i], new TreeMap<Integer, Integer>());
            tempTreeMap.put(i, plays[i]);
            genresPlays.put(genres[i], tempTreeMap);
        }
        
        //2. 장르 합의 순위
        List<String> genresRank = sortByValue(playsSum);
        List<Integer> answerList = new LinkedList<>();

        for(String genre : genresRank) {
            List<Integer> keyList = sortByValue2(genresPlays.get(genre));
            int i = 0;
            for(int key : keyList) {
               answerList.add(key);
               if(i >= 1) {
                   break;
               }
               i++;
            }
        }

        answer = answerList.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    static private List<String> sortByValue(HashMap<String, Integer> map) {
        List<String> keyList = new ArrayList<>(map.keySet());
        keyList.sort((o1, o2) -> map.get(o2) - map.get(o1));
        return keyList;
    }

    static private List<Integer> sortByValue2(TreeMap<Integer, Integer> map) {
        List<Integer> keyList = new ArrayList<>(map.keySet());
        keyList.sort((o1, o2) -> map.get(o2) - map.get(o1));
        return keyList;
    }

    public static void main(String[] args) {
        String[] genres = {"d", "classic", "pop", "classic", "classic", "pop", "a", "b", "b", "c" , "d", "d"};
        int[] plays = {10000, 500, 600, 150, 800, 2500, 100, 100, 100, 10000, 200, 10000}; // {8, 4, 1, 3, 0, 7, 6, 5}
        System.out.println("===START===");
        System.out.println(solution(genres, plays));

        String[] genres2 = {"classic", "pop", "classic", "classic", "pop", "classic"};
        int[] plays2 = {500, 600, 150, 800, 2500, 800}; // {4, 1, 3, 5}
        System.out.println(solution(genres2, plays2));

        System.out.println("===END===");
    }
}
