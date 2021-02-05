    public static boolean isBalanced2(String input) {
        Stack<Character> s = new Stack<Character>();
        for (char ch: input.toCharArray()) {
            if (isLeftBracket(ch))
                s.push(ch);

            if (isRightBracket(ch)) {
                if (s.empty()) return false;
                char top = s.pop();
                if (bracketsMatch(top, ch))
                    return false;
            }
        }
        return s.empty();
    }

    private static boolean isLeftBracket(char ch) {
        return ( ch == '(' || ch == '[' || ch == '{' || ch == '<' );
    }

    private static boolean isRightBracket(char ch) {
        return ( ch == ')' || ch == ']' || ch == '>' || ch == '}' );
    }

    private static boolean bracketsMatch( char left, char right ) {
        return ((left == ')' && right != '(') || (left == '}' && right != '{') || (left == ']' && right != '[') || (left == '>' && right != '<'));
    }
