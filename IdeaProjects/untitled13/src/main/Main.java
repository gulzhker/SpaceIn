package main;

import main.NotNullValidator;
import main.Sms;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import main.beans.Customer;
import main.beans.Passenger;
import main.beans.PassengersList;
import main.beans.Trip;
import main.beans.impl.PropCustomer;
import main.beans.impl.PropPassengersList;
import main.beans.impl.PropTrip;
import main.flows.FlowExecutionException;
import main.flows.UserFlow;
import main.flows.UserFlowFactory;

/**
 * @author Aliaksei Boole
 */
public class Main {
    static {
        LogFactory.getFactory()
                .setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
    }


    public static void main(String[] args) {
        Customer customer = PropCustomer.INSTANCE;
        Trip trip = PropTrip.INSTANCE;
        PassengersList passengersList = PropPassengersList.INSTANCE;

        WebDriver driver = getWebDriver();

        try {
            NotNullValidator.checkCustomer(customer);
            NotNullValidator.checkPassengerList(passengersList);
            NotNullValidator.checkTrip(trip);

            while (passengersList.hasNext()) {
                driver.get("http://poezd.rw.by");
                Passenger passenger = passengersList.getNext();
                NotNullValidator.checkPassenger(passenger);
                UserFlow searchAndBuyFlow = UserFlowFactory.createSearchAndBuyFlow(customer, trip, passenger, driver);
                searchAndBuyFlow.play();
            }

        } catch (FlowExecutionException e) {
            Sms.send(customer, "Rwts finish work with error!");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static WebDriver getWebDriver() {
        Capabilities caps = configWebDriver();
        return new PhantomJSDriver(caps);
    }

    private static Capabilities configWebDriver() {
        DesiredCapabilities dCaps = new DesiredCapabilities();
        dCaps.setCapability("takesScreenshot", true);
        dCaps.setCapability(
                PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/53 (KHTML, like Gecko) Chrome/15.0.87"
        );
        dCaps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "path_to_phantom_js"
        );
        return dCaps;
    }

}
