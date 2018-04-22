package main.flows;

/**
 * @author Serj Sintsov
 */
public interface FlowUnit
{
    void execute() throws FlowUnitExecutionException;

    boolean isMandatory();
}
