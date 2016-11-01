package patterns.state.parser_state_machine;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Enter a Float: ");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String input = r.readLine();
        int pos = 0;
        Parser parser = new Parser();

        while (input != null && pos < input.length()) {
            char ch = input.charAt(pos++);
            try {
                if (parser.isDigit(ch))
                    parser.state.digit(parser.getNumericValue(ch));
                else if (ch == '.')
                    parser.state.dot();
                else if (ch == '+')
                    parser.state.plus();
                else if (ch == '-')
                    parser.state.minus();
                else if (ch == 'e' || ch == 'E')
                    parser.state.e();

            } catch (IllegalStateException e) {
                System.out.println("Illegal Format");
            }
            input = r.readLine();
        }
    }
}
