import java.util.*;

public class WordLadderPath {
    public static List<String> findShortestPath(String startWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return new ArrayList<>();

        Queue<List<String>> queue = new LinkedList<>();
        queue.offer(Arrays.asList(startWord));

        Set<String> visited = new HashSet<>();
        visited.add(startWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> levelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                List<String> path = queue.poll();
                String lastWord = path.get(path.size() - 1);

                if (lastWord.equals(endWord)) return path;

                char[] arr = lastWord.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char original = arr[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        arr[j] = c;
                        String newWord = new String(arr);

                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            List<String> newPath = new ArrayList<>(path);
                            newPath.add(newWord);
                            queue.offer(newPath);
                            levelVisited.add(newWord);
                        }
                    }
                    arr[j] = original;
                }
            }
            visited.addAll(levelVisited);
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        String startWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        List<String> result = findShortestPath(startWord, endWord, wordList);
        if (result.isEmpty()) {
            System.out.println("No transformation possible.");
        } else {
            System.out.println("Shortest transformation path: " + result);
        }
    }
}
