    public static String revWord(String word) {
        Stack<Character> s1 = new Stack<Character>();
        Stack<Character> s2 = new Stack<Character>();

        for (char c: word.toCharArray()) {
            s1.push(c);
        }
        StringBuilder out = new StringBuilder();
        while (!s1.isEmpty()) {
            if (s1.peek() != ' ')
                s2.push(s1.pop());
            else {
                while (!s2.isEmpty())
                    out.append(s2.pop());
                out.append(s1.pop());
            }
        }
        while (!s2.isEmpty())
            out.append(s2.pop());
        return out.toString();
    }

    public static String revWord2(String word) {
        Stack<String> s = new Stack<String>();
        for (int i = 0; i < word.length(); i++) {
            StringBuilder temp = new StringBuilder();
            for (;i < word.length() && word.charAt(i) != ' '; i++) {
                temp.append(word.charAt(i));
            }
            temp.append(' ');
            s.push(temp.toString());
        }
        StringBuilder out = new StringBuilder();
        while (!s.isEmpty())
            out.append(s.pop());
        return out.toString();
    }
