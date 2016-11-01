package patterns.state.parser_state_machine;

public class AbstractState implements State {

    @Override
    public void digit(int next) {
        throw new IllegalStateException();
    }

    @Override
    public void e() {
        throw new IllegalStateException();
    }

    @Override
    public void plus() {
        throw new IllegalStateException();
    }

    @Override
    public void minus() {
        throw new IllegalStateException();
    }

    @Override
    public void dot() {
        throw new IllegalStateException();
    }
}
