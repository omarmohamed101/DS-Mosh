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
}
