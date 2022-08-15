
public interface Action {
	boolean evaluate(Facts fact);
	void perform(Facts fact);
	void execute(Facts facts);
}
