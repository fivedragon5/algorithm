package algorithm.programmers;

import java.util.*;

class Lesson42579 {
    static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        LinkedHashMap<String, Integer> genresSum = new LinkedHashMap<>();
        HashMap<String, List<Integer>> songIndex = new HashMap<>();
        List<Integer> tempList = new ArrayList<>();
        List<Integer> bestList = new ArrayList<>();

        for(int i = 0 ; i < plays.length ; i++) {
            genresSum.put(genres[i], genresSum.getOrDefault(genres[i], 0) + plays[i]);
            if(songIndex.get(genres[i]) == null) {
                tempList = new ArrayList<>();
            }
            else {
                tempList = songIndex.get(genres[i]);
            }
            tempList.add(plays[i]);
            songIndex.put(genres[i], tempList);
        }

        genresSum = sortByValue(genresSum);
        List<int[]> b = Arrays.asList(plays);

        for(String key : genresSum.keySet()) {
            List<Integer> genreSong = songIndex.get(key);
            genreSong.sort(Comparator.reverseOrder());
            System.out.println(genreSong.toString() + "fivetest!");

            for(int i = 0; i < genreSong.size() ; i ++) {
                int c = genreSong.get(i);
                //int index = Arrays.asList(plays).indexOf(genreSong.get(i));
                int index = b.indexOf(c);
                System.out.println(genreSong.get(i) + " fivetest@" + index);
                bestList.add(index);
                if(i > 0) {
                    break;
                }
            }
        }

        System.out.println(bestList.toString());

        return answer;
    }

    static private LinkedHashMap<String, Integer> sortByValue(LinkedHashMap<String, Integer> map) {
        List<String> keyList = new ArrayList<>(map.keySet());
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        keyList.sort((o1, o2) -> map.get(o2) - map.get(o1));

        for(String key : keyList) {
            result.put(key, map.get(key));
        }

        return result;
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop", "a", "a"};
        int[] plays = {500, 600, 150, 800, 2500, 100, 200}; // {4, 1, 3, 0}
        System.out.println("===START===");
        System.out.println(solution(genres, plays));
        System.out.println("===END===");
    }
}
