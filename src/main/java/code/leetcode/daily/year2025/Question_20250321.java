package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description/?envType=daily-question&envId=2025-03-21
 *
 * 제한)
 *  n == recipes.length == ingredients.length
 *  1 <= n <= 100
 *  1 <= ingredients[i].length, supplies.length <= 100
 *  1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 *  recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
 *  All the values of recipes and supplies combined are unique.
 *  Each ingredients[i] does not contain any duplicate values.
 *
 * 문제)
 *  1. n개의 레시피 정보가 있습니다.
 *  2. i번째 레시피 recipes[i]의 재료는 ingredients[i] 이며 레시피도 재료가 될 수 있습니다.
 *  3. supplies는 재료 배열이며 무한하게 공급 됩니다.
 *  4. 이때 만들 수 있는 레시피 목록을 반환하기
 *
 * 풀이)
 *  위상정렬, 차수, BFS
 *
 *  graph<레시피, List<상위레시피>>
 *  ingredientMap<레시피, List<재료>>
 *  indegree<레피시, 차수>
 *
 *  1. recipes를 반복하면서 아래 데이터를 Set 해준다
 *   - graph Map에 List를 초기화
 *   - ingredientMap Map에 필요한 재료를 Set
 *   - 차수를 모두 0으로 설정
 *
 *  2. 주어진 ingredients의 길이만큼 반복
 *   - recipe에 필요한 재료들중에 다른 레시피가 필요한 경우 차수를 1 올려서 indegree Map에 저장해준다
 *   - graph에 레시피간 관계를 단방향 간선으로 표시 bread -> sandwich
 *
 *  3. Queue를 선언하고 차수가 0인것들의 Recipe를 Queue에 넣어준다
 *
 *  4. Queue를 하나씩 빼면서 현재 주어진 재료들로 Queue에서 뺀 레시피를 만들 수 있는지 확인 (반복)
 *   - 만들 수 있을 경우
 *      - 정답 배열에 추가
 *      - 해당 레시피를 재료 테이블(Set)에 추가
 *      - graph에서 해당 레시피로 만들 수 있는 다른 레시피들의 차수를 -1
 *      - 차수를 -1 했을 때 차수가0이 되는 경우 Queue에 추가
 */

public class Question_20250321 {
    public static void main(String args[]) throws IOException {
//        String[] recipes = {"bread"};
//        List<List<String>> ingredients = new ArrayList<>(Arrays.asList(
//                Arrays.asList("yeast","flour")
//        ));
//
//        String[] supplies = {"yeast","flour","corn"};
//        System.out.println(findAllRecipes(recipes, ingredients, supplies)); // bread

        String[] recipes2 = {"bread","sandwich"};
        List<List<String>> ingredients2 = new ArrayList<>(Arrays.asList(
                Arrays.asList("yeast","flour"),
                Arrays.asList("bread","meat")
        ));

        String[] supplies2 = {"yeast","flour","meat"};
        System.out.println(findAllRecipes(recipes2, ingredients2, supplies2)); // "bread","sandwich"

        String[] recipes3 = {"bread","sandwich","burger"};
        List<List<String>> ingredients3 = new ArrayList<>(Arrays.asList(
                Arrays.asList("yeast","flour"),
                Arrays.asList("bread","meat"),
                Arrays.asList("sandwich","meat","bread")
        ));

        String[] supplies3 = {"yeast","flour","meat"};
        System.out.println(findAllRecipes(recipes3, ingredients3, supplies3)); // "bread","sandwich","burger"
    }

    public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> answer = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, List<String>> ingredientMap = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        for (int i = 0; i < recipes.length; i++) {
            graph.put(recipes[i], new ArrayList<>());
            ingredientMap.put(recipes[i], ingredients.get(i));
            indegree.put(recipes[i], 0);
        }

        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            for (String ingredient : ingredients.get(i)) {
                if (graph.containsKey(ingredient)) {
                    graph.get(ingredient).add(recipe);
                    indegree.put(recipe, indegree.get(recipe) + 1);
                }
            }
        }
        Set<String> supplieSet = Arrays.stream(supplies).collect(Collectors.toSet());
        Queue<String> queue = new LinkedList<>();
        for (String key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }

        while (!queue.isEmpty()) {
            String currentRecipe = queue.poll();
            boolean isCanMake = true;
            for (String ingredient : ingredientMap.get(currentRecipe)) {
                if (!supplieSet.contains(ingredient)) {
                    isCanMake = false;
                    break;
                }
            }
            if (isCanMake) {
                answer.add(currentRecipe);
                supplieSet.add(currentRecipe);
                for (String parentRecipe : graph.get(currentRecipe)) {
                    int x = indegree.get(parentRecipe) - 1;
                    indegree.put(parentRecipe, x);
                    if (x == 0) {
                        queue.offer(parentRecipe);
                    }
                }
            }
        }
        return answer;
    }
}
