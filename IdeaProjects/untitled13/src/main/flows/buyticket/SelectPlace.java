package main.flows.buyticket;

import main.beans.Passenger;
import main.pages.PassengersSelectPage;
import main.pages.WagonSelectPage;
import org.openqa.selenium.WebDriver;
import main.beans.Trip;
import main.flows.FlowUnitExecutionException;

/**
 * @author Serj Sintsov
 * @since 11/5/13 8:58 PM
 */
public class SelectPlace extends BaseFlowUnit
{
    private WagonSelectPage wagonSelectPage;
    private PassengersSelectPage passengersSelectPage;

    private Trip      trip;
    private Passenger passenger;

    public SelectPlace(Trip trip, Passenger passenger, WebDriver driver)
    {
        wagonSelectPage = new WagonSelectPage(driver);
        passengersSelectPage = new PassengersSelectPage(driver);

        this.trip = trip;
        this.passenger = passenger;
    }

    @Override
    public void doExecute() throws FlowUnitExecutionException
    {
        wagonSelectPage.selectWagon(trip);
        passengersSelectPage.selectPassenger(passenger);
    }

    @Override
    public boolean isMandatory()
    {
        return true;
    }
}
