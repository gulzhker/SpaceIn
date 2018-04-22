package main.pages;

import main.pages.blocks.WagonTypeBlock;
import org.openqa.selenium.WebDriver;
import main.beans.Trip;

import java.util.List;

/**
 * @author Aliaksei Boole
 */
public class WagonSelectPage extends AbstractPage
{

    private List<WagonTypeBlock> wagonTypeBlocks;

    public WagonSelectPage(WebDriver driver)
    {
        super(driver);
    }

    public void selectWagon(Trip trip)
    {
        for (WagonTypeBlock wagonTypeBlock : wagonTypeBlocks)
        {
            if (wagonTypeBlock.isGoodCost(trip))
            {
                wagonTypeBlock.clickSelect();
                break;
            }
        }
    }
}
