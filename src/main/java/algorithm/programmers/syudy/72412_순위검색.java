package algorithm.programmers.syudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Lesson72412 {
    static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        List<Developer> developersList = new ArrayList<>();
        int index = 0;

        for (String result : info) {
            String[] splitStr = result.split(" ");
            Developer developer = new Developer(splitStr[0], splitStr[1], splitStr[2], splitStr[3], Integer.parseInt(splitStr[4]));
            developersList.add(developer);
        }
        
        //점수순 정렬
        developersList.stream().sorted(Comparator.comparing(Developer::getPoint)).collect(Collectors.toList());

        System.out.println(developersList.toString());

        for (String search : query) {
            String[] searchStr = search.split(" and | ");
            int point = Integer.parseInt(searchStr[4]);
            int filteredDevelopers = startPointer(developersList, point, searchStr[0], searchStr[1], searchStr[2], searchStr[3]);
            answer[index++] = filteredDevelopers;
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }
    
    // point 이상부터 탐색할것
    // filter 사용해보기
    // '-'처리 filter로 가능한가
    static int startPointer(List<Developer> developers, int point, String language, String position, String rank, String soulFood) {
        int result = 0;
        //System.out.println("언어 : " + language + " / 포지션 : " + position + " / 직급 : " + rank + " / 소울푸드 :" + soulFood);
        //(효율성 TEST FAIL)
        //filter하기전 list의 탐색 범위를 줄인뒤 탐색하기
        List<Developer> filterDevelopers =
                developers.stream()
                        .filter(vo -> (language.equals("-") || vo.getLanguage().equals(language)))
                        .filter(vo -> (position.equals("-") || vo.getPosition().equals(position)))
                        .filter(vo -> (rank.equals("-") || vo.getRank().equals(rank)))
                        .filter(vo -> (soulFood.equals("-") || vo.getSoulFood().equals(soulFood)))
                        //.filter(vo -> vo.getPoint() >= point)
                        .collect(Collectors.toList());
        return filterDevelopers.size();
    }
    
    //개발자 클래스
    static class Developer {
        String language;
        String position;
        String rank;
        String soulFood;
        int point;

        Developer(String language, String position, String rank, String soulFood, int point) {
            this.language = language;
            this.position = position;
            this.rank = rank;
            this.soulFood = soulFood;
            this.point = point;
        }

        public String getLanguage() {
            return language;
        }

        public String getPosition() {
            return position;
        }

        public String getRank() {
            return rank;
        }

        public String getSoulFood() {
            return soulFood;
        }

        public int getPoint() {
            return point;
        }

        @Override
        public String toString() {
            return "Developer{" +
                    "language='" + language + '\'' +
                    ", position='" + position + '\'' +
                    ", rank='" + rank + '\'' +
                    ", soulFood='" + soulFood + '\'' +
                    ", point=" + point +
                    '}' + "\n";
        }
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query =
                {
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"
                };
        //result 1,1,1,1,2,4
        System.out.println("===START===");
        System.out.println(solution(info, query));
        System.out.println("===END===");
    }
}
