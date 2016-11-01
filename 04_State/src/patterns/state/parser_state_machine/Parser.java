package patterns.state.parser_state_machine;

public class Parser {

    private final State S0 = new S0();
    private final State S1 = new S1();
    private final State S2 = new S2();
    private final State S3 = new S3();
    private final State S4 = new S4();
    private final State S5 = new S5();
    private final State S6 = new S6();
    public State state = S0;


    public boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    public int getNumericValue(char ch) {
        return Character.getNumericValue(ch);
    }

    public class S0 extends AbstractState {
        @Override
        public void digit(int next) {
            System.out.print(next);
            state = S1;
        }

        @Override
        public void dot() {
            System.out.print(".");
            state = S2;
        }
    }

    public class S1 extends AbstractState {
        @Override
        public void digit(int next) {
            System.out.print(next);
        }

        @Override
        public void dot() {
            System.out.print(".");
            state = S3;
        }

        @Override
        public void e() {
            System.out.print("e");
            state = S4;
        }
    }

    public class S2 extends AbstractState {
        @Override
        public void digit(int next) {
            System.out.print(next);
            state = S3;
        }
    }

    public class S3 extends AbstractState {
        @Override
        public void digit(int next) {
            System.out.println("");
            System.out.println("Finished");
        }

        @Override
        public void e() {
            System.out.print("e");
            state = S4;
        }
    }

    public class S4 extends AbstractState {
        @Override
        public void digit(int next) {
            System.out.print(next);
            state = S6;
        }

        @Override
        public void plus() {
            System.out.print("+");
            state = S5;
        }

        @Override
        public void minus() {
            System.out.print("-");
            state = S5;
        }
    }

    public class S5 extends AbstractState {
        @Override
        public void digit(int next) {
            System.out.print(next);
            state = S6;
        }

    }

    public class S6 extends AbstractState {
        @Override
        public void digit(int next) {
            System.out.println("");
            System.out.println("Finished");
        }

    }
}
