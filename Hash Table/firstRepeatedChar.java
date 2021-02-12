package HashTaples;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        System.out.println(firstRepeated(word));
    }

    public static char firstRepeated(String word) {

        Set<Character> s = new HashSet<Character>();
        char[] chars = word.toCharArray();
        for (char ch: chars) {
            if (s.contains(ch))
                return ch;

            s.add(ch);
        }
        // All characters repeated
        return Character.MIN_VALUE;
    }
    
        public static char firstRepeatedUsingHash(String word) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        char[] chars = word.toCharArray();
        for (char ch: chars)
            if (freq.containsKey(ch))
                freq.put(ch, freq.get(ch) + 1);
            else
                freq.put(ch, 1);

        for (char ch: chars)
            if(freq.get(ch) > 1)
                return ch;

        // All characters repeated
        return Character.MIN_VALUE;
    }
}
