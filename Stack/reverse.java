public static String reverse(String word) {
        if (word == null)
            throw new IllegalArgumentException();
        Stack<Character> s = new Stack<Character>();
        for (int i = 0; i < word.length(); i++)
            s.push(word.charAt(i));
        StringBuffer res = new StringBuffer();
        while (!s.empty())
            res.append(s.pop());
        return res.toString();
}
