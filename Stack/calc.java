public static int calculate(String s) {
    Stack<Integer> ss = new Stack<Integer>();
    int top = 0, num = 0, sign = '+';
    char[] array = s.toCharArray();

    for (int i = 0; i <= array.length; i++) {

        while (i < array.length && array[i] != ' ' && Character.isDigit(array[i])) {
            num = num * 10 + array[i++] - '0';
        }
        if (i < array.length && array[i] == ' ') {
            continue;
        }
        else {
            if (sign == '+') {
                ss.push(num);
            }
            else if (sign == '-') {
                ss.push(-num);
            }
            else if (sign == '*') {
                ss.push( ss.pop() * num );
            }
            else {
                ss.push( ss.pop() / num );
            }
            if (i < array.length) {
                sign = array[i];
            }
            num = 0;
        }
    }
    int result = 0;
    while (!ss.isEmpty()) {
        result += ss.pop();
    }
    return result;
}
