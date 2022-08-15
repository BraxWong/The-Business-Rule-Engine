import static org.junit.Assert.assertEquals;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mock;
import org.junit.Test;

public class BusinessRuleEngine {
	
	private final List<Rule> rules;
	private final Facts facts;
	
	public BusinessRuleEngine(final Facts facts) {
		this.rules = new ArrayList<>();
		this.facts = facts;
	}
	
	public void addRule(final Rule rule) {
		this.rules.add(rule);
	}
	
	public int count() {
		return this.rules.size();
	}
	
	public void run() {
		this.rules.forEach(action -> action.perform(facts));
	}
	
	@Test
	public void shouldHaveNoRulesInitially() {
		final Facts facts = new Facts();
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(facts);
		assertEquals(0,businessRuleEngine.count());
	}
	
	@Test
	public void shouldAddTwoActions() {
		Rule rule = null;
		final Facts facts = new Facts();
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(facts);
		businessRuleEngine.addRule(rule);
		businessRuleEngine.addRule(rule);
		assertEquals(2,businessRuleEngine.count());
		
	}
	
	@Test
	public void shouldExecuteOnce() {
		final Facts facts = new Facts();
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(facts);
		final Rule mockRule = mock(Rule.class);
		
		businessRuleEngine.addRule(mockRule);
		businessRuleEngine.run();
		verify(mockRule).perform();
	}
	
	final Customer customer = new Customer("Mark", "CEO");
	
	businessRuleEngine.addAction(facts -> {
		var forecastedAmount = 0.0;
		var dealStage = Stage.valueOf(facts.getFact("stage"));
		var amount = Double.parseDouble(facts.getFact("amount"));
		if(dealStage == Stage.LEAD) {
			forecastedAmount = amount * 0.2;
		}
		else if(dealStage == Stage.EVALUATING) {
			forecastedAmount = amount * 0.5;
		}
		else if(dealStage == Stage.INTERESTED) {
			forecastedAmount = amount * 0.8;
		}
		else if(dealStage == Stage.CLOSED) {
			forecastedAmount = amount;
		}
		facts.addFact("forecastedAmount", String.valueOf(forecastedAmount));
	});
	
	@Test
	public void shouldPerformAnActionWithFacts() {
		final Action mockAction = mock(Action.class);
		final Facts mockFacts = mock(Facts.class);
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mocked Facts);
		businessRuleEngine.addAction(mockAction);
		businessRuleEngine.run();
		
		verify(mockAction).perofrm(mockFacts);
		
	}
	
	final Rule ruleSendEmailToSalesWhenCEO = RuleBuilder .when(facts -> "CEO".equals(facts.getFact("jobTitle"))) .then(facts -> {
        var name = facts.getFact("name");
        Mailer.sendEmail("sales@company.com", "Relevant customer!!!: " + name);
    });
}
