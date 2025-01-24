import java.util.*;
import java.util.stream.Collectors;
public class Day5 {
    
    public static void main(String[] args) {
        InputHelper helper = new InputHelper();
        ArrayList<String> input = new ArrayList<String>(helper.parseInput("day5.txt"));
        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> updates = new ArrayList<>();

        boolean readingUpdates = false;
        for (String line : input) {
            if (line.isBlank()) {
                readingUpdates = true;
                continue;
            }
            if (!readingUpdates) {
                rules.add(line);
            }
            else updates.add(line);
        }
        
        Set<RulePair> rulePairs = new HashSet<>();
        for (String rule : rules) {
            String[] splitRule = rule.split("\\|");
            rulePairs.add(new RulePair(splitRule[0], splitRule[1]));
        }

        int count = 0;
        for (String update : updates) {
            String[] updateSplit = update.split(",");
            boolean valid = true;
            for (int i = updateSplit.length-1; i>0; i--) {
                for (int j = i-1; j >= 0; j--) {
                    if (!valid) break;
                    // "waaaahh you cant use i"
                    // local variables referenced from a lambda expression must be final or effectively final
                    int streamHelper = i;
                    Set<RulePair> filteredSet = rulePairs.stream()
                                    .filter(RulePair -> RulePair.prio.equals(updateSplit[streamHelper]))
                                    .collect(Collectors.toSet());
                    
                    
                    for (RulePair rule : filteredSet) {
                        if (rule.last.equals(updateSplit[j])) {
                            valid = false;
                        }
                        if (!valid) break;
                    }
                }
                if (!valid) break;
            }
            if (valid) count+=Integer.valueOf(updateSplit[updateSplit.length/2]);
        }
        System.out.println(count);
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