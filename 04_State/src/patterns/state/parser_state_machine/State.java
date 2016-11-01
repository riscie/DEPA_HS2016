package patterns.state.parser_state_machine;

public interface State {
    public void digit(int next);

    public void e();

    public void plus();

    public void minus();

    public void dot();

}
