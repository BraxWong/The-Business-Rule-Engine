import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class InspectorTest {
	@Test
	public void inspectOneConditionEvaluatesTrue() {
		final Facts facts = new Facts();
		facts.setFact("jobTitle","CEO");
		final Action conditionalAction = new JobTitleCondition();
		final Inspector inspector = new Inspector(conditionalAction);
		
		final List<Report> reportList = inspector.inspect(facts);
		
		assertEquals(1,reportList.size());
		assertEquals(true,reportList.get(0).isPositive());
	}
	
	private static class JobTitleCondition implements Action {
		@Override
		public void perform(Facts facts) {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean evaluate(Facts facts) {
			return "CEO".equals(facts.getFact("jobTitle"));
		}

		@Override
		public void execute(Facts facts) {
			System.out.println("It does nothing so far.");
			
		}
	}
}
