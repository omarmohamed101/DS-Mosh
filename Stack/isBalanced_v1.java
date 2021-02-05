
public static boolean isBalanced(String input) {
        Stack<Character> s = new Stack<Character>();
        for (char ch: input.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{' || ch == '<')
                s.push(ch);

            if (ch == ')' || ch == ']' || ch == '>' || ch == '}') {
                if (s.empty()) return false;
                char top = s.pop();
                if ((ch == ')' && top != '(') || (ch == '}' && top != '{') || (ch == ']' && top != '[') || (ch == '>' && top != '<'))
                    return false;
            }
        }
        return s.empty();
}
