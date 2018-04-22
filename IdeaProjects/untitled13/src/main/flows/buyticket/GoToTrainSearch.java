package main.flows.buyticket;

import main.beans.Customer;
import main.pages.HomePage;
import main.pages.RulesPage;
import org.openqa.selenium.WebDriver;
import main.flows.FlowUnitExecutionException;

/**
 * @author Serj Sintsov
 * @since 11/5/13 8:58 PM
 */
public class GoToTrainSearch extends BaseFlowUnit
{
    private HomePage homePage;
    private RulesPage rulesPage;

    private Customer customer;

    public GoToTrainSearch(Customer customer, WebDriver driver)
    {
        homePage = new HomePage(driver);
        rulesPage = new RulesPage(driver);

        this.customer = customer;
    }

    @Override
    public void doExecute() throws FlowUnitExecutionException
    {
        homePage.login(customer, true);

        if (!homePage.isLogin())
        {
            throw new FlowUnitExecutionException("Cannot login customer [%s]", customer);
        }

        rulesPage.confirmWithRules();
    }

    @Override
    public boolean isMandatory()
    {
        return true;
    }
}
