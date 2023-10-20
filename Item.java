import java.util.HashMap;

/**
 * Exercise 8.20/8.21:
 * Adding items into the game
 *
 * @author Obdulio Benitez Garcia; G00294642
 * @version GDEV-242-99V; Super Text Adventure
 */
public class Item
{
    private String description;
    private double weight;

    /**
     * Constructor for objects of class Item
     * 
     * @param description: Allows a description to be passed
     * @param weight:      Allows a weight to be assigned to the item
     */
    public Item(String description, double weight)
    {
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Return a description of just the item
     * 
     * @return: The short description of the room
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * Return a description of the item and it's weight
     * 
     * @return: The long description of the room
     */
    public String getLongDescription()
    {
        return "There is a " + description + " looking to weigh about " + weight + "lbs.";
    }
}
