package main.flows;

import com.google.common.collect.Lists;
import main.beans.Customer;
import main.beans.Passenger;
import main.flows.buyticket.GoToTrainSearch;
import main.flows.buyticket.PurchaseOrder;
import main.flows.buyticket.SearchTrain;
import main.flows.buyticket.SelectPlace;
import org.openqa.selenium.WebDriver;
import main.beans.Trip;

/**
 * @author Serj Sintsov
 */
public class UserFlowFactory
{
    public static UserFlow createSearchAndBuyFlow(Customer customer, Trip trip, Passenger passenger,
                                                  WebDriver webDriver)
    {
        UserFlow searchAndBuyFlow = new UserFlow();

        searchAndBuyFlow.setActions(Lists.<FlowUnit>newArrayList(
                new GoToTrainSearch(customer, webDriver),
                new SearchTrain(trip, webDriver),
                new SelectPlace(trip, passenger, webDriver),
                new PurchaseOrder(customer, webDriver)
        ));

        return searchAndBuyFlow;
    }
}
