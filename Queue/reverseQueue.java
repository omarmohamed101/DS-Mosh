public static void reverse(Queue<Integer> q) {
        Stack<Integer> s = new Stack<Integer>();
        while (!q.isEmpty())
            s.push(q.remove());

        while (!s.empty())
            q.add(s.pop());
}
