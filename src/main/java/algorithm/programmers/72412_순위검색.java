package algorithm.programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Lesson72412 {
    static int[] solution(String[] info, String[] query) {
        int[] answer = {};
        List<Developer> developersList = new ArrayList<>();

        for (String result : info) {
            String[] splitStr = result.split(" ");
            System.out.println(splitStr[4]);
            Developer developer = new Developer(splitStr[0], splitStr[1], splitStr[2], splitStr[3], Integer.parseInt(splitStr[4]));
            developersList.add(developer);
        }
        
        //점수순 정렬
        developersList.stream().sorted(Comparator.comparing(Developer::getPoint)).collect(Collectors.toList());

        for (String search : query) {
            String[] searchStr = search.split(" and | ");
            int point = Integer.parseInt(searchStr[4]);
            int startPointer = startPointer(point, developersList);
        }

        return answer;
    }
    
    // point 이상부터 탐색할것
    // filter 사용해보기
    // '-'처리 filter로 가능한가
    static int startPointer(int point, List<Developer> developers) {
        int result = 0;
        List<Developer> a = developers.stream().filter(vo -> vo.getLanguage().equals("java")).collect(Collectors.toList());
        System.out.println(a.toString());
        System.out.println("===============");
        return 0;
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
                    '}';
        }
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        //result 1,1,1,1,2,4
        System.out.println("===START===");
        System.out.println(solution(info, query));
        System.out.println("===END===");
    }
}
