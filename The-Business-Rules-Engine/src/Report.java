
public class Report {
	private final Action conditionAction;
	private final Facts facts;
	private final boolean isPositive;
	
	public Report(final Action conditionAction, final Facts facts, final boolean isPositive) {
		this.facts = facts;
		this.conditionAction = conditionAction;
		this.isPositive = isPositive;
	}
	public Action getConditionalAction() {
		return conditionAction;
	}
	
	public Facts getFacts() {
		return facts;
	}
	
	public boolean isPositive() {
		return isPositive;
	}
	@Override
	public String toString() {
		return "Report{" + "conditionalAction=" + conditionAction + ", facts=" + facts + ", result= " + isPositive + '}';
	}
}
