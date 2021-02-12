package HashTaples;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        System.out.println(findFirstRepeated(word));
    }

    public static char findFirstRepeated(String word) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        char[] chars = word.toCharArray();
        for (char ch: chars)
            if (freq.containsKey(ch))
                freq.put(ch, freq.get(ch) + 1);
            else
                freq.put(ch, 1);

        for (char ch: chars)
            if(freq.get(ch) == 1)
                return ch;

        // All characters repeated
        return Character.MIN_VALUE;
    }
}
