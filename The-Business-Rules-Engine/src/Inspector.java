import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inspector {
	private final List<Action> conditionalActionList;
	public Inspector(Action conditionalActionList) {
		this.conditionalActionList = Arrays.asList(conditionalActionList);
	}
	
	public List<Report> inspect(final Facts facts){
		final List<Report> reportList = new ArrayList<>();
		for(Action conditionalAction : conditionalActionList) {
			final boolean conditionResult = conditionalAction.evaluate(facts);
			reportList.add(new Report(conditionalAction, facts, conditionResult));
		}
		return reportList;
	}
}
