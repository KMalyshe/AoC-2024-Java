import java.util.*;
import java.util.stream.Collectors;

public class Day5 {

    public static void main(String[] args) {
        InputHelper helper = new InputHelper();
        List<String> input = new ArrayList<>(helper.parseInput("day5.txt"));
        List<String> rules = new ArrayList<>();
        List<String> updates = new ArrayList<>();

        boolean readingUpdates = false;
        for (String line : input) {
            if (line.isBlank()) {
                readingUpdates = true;
                continue;
            }
            if (!readingUpdates) {
                rules.add(line);
            } else
                updates.add(line);
        }

        Set<RulePair> rulePairs = new HashSet<>();
        for (String rule : rules) {
            String[] splitRule = rule.split("\\|");
            rulePairs.add(new RulePair(splitRule[0], splitRule[1]));
        }

        int count = 0;
        List<String[]> invalidUpdates = new ArrayList<>();
        for (String update : updates) {
            String[] updateSplit = update.split(",");
            boolean valid = true;
            for (int i = updateSplit.length - 1; i > 0; i--) {
                for (int j = i - 1; j >= 0; j--) {
                    if (!valid)
                        break;
                    int streamHelper = i;
                    Set<RulePair> filteredSet = rulePairs.stream()
                            .filter(RulePair -> RulePair.prio.equals(updateSplit[streamHelper]))
                            .collect(Collectors.toSet());

                    for (RulePair rule : filteredSet) {
                        if (rule.last.equals(updateSplit[j])) {
                            valid = false;
                        }
                        if (!valid)
                            break;
                    }
                }
                if (!valid)
                    break;
            }
            if (valid)
                count += Integer.valueOf(updateSplit[updateSplit.length / 2]);
            else
                invalidUpdates.add(updateSplit);
        }

        int count2 = 0;
        for (String[] invalidUpdateSplit : invalidUpdates) {
            List<Integer> result = fixUpdate(rulePairs, invalidUpdateSplit);
            count2 += result.get(result.size() / 2);
        }
        System.out.println(count);
        System.out.println(count2);
    }

    private static List<Integer> fixUpdate(Set<RulePair> rules, String[] updateSplit) {
        List<Integer> fixedUpdate = new ArrayList<>();
        Set<String> allPages = new HashSet<>(Arrays.asList(updateSplit));
        Map<String, List<String>> adjList = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        for (String page : allPages) {
            adjList.put(page, new ArrayList<>());
            inDegree.put(page, 0);
        }
        for (RulePair rule : rules) {
            if (allPages.contains(rule.prio) && allPages.contains(rule.last)) {
                adjList.get(rule.prio).add(rule.last);
                inDegree.put(rule.last, inDegree.get(rule.last) + 1);
            }
        }
        Queue<String> queue = new LinkedList<>();
        for (String page : inDegree.keySet()) {
            if (inDegree.get(page) == 0) {
                queue.add(page);
            }
        }
        while (!queue.isEmpty()) {
            String current = queue.poll();
            fixedUpdate.add(Integer.valueOf(current));

            for (String neighbor : adjList.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return fixedUpdate;
    }

}

class RulePair {
    String prio;
    String last;

    public RulePair(String prio, String last) {
        this.prio = prio;
        this.last = last;
    }
}