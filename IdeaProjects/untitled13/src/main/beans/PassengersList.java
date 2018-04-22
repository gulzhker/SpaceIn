package main.beans;

import java.util.List;

/**
 * @author Aliaksei Boole
 */
public interface PassengersList
{

    List<Passenger> getPassengers();

    Passenger getNext();

    boolean hasNext();


}
