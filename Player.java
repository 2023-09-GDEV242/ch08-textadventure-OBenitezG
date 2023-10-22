import java.util.ArrayList;

/**
 * Advanced Requirement:
 * Eat changes game state
 * Health System
 *
 * @author Obdulio Benitez Garcia; G00294642
 * @version GDEV-242-99V; Super Text Adventure
 */
public class Player
{
    private ArrayList<String> backpack;
    
    private int health;
    private int hunger;

    /**
     * Constructor for objects of class Player
     * Contains Player's health and hunger values
     * 
     * Creates the backpack array to hold items
     */
    public Player()
    {
        // The player stats
        health = 100;
        hunger = 150;
        
        //creates the backpack array
        backpack = new ArrayList<>();
    }
    
    public void insideBackpack()
    {
        if (backpack.size() < 1){
            System.out.println("You have nothing in your backpack");
        } else {
            System.out.println(backpack.toString());
        }
    }
    
    public boolean hasItem(String item) 
    {
        boolean itemCheck = false;
        
        for (int index = 0; index < backpack.size(); index++) {
            if (backpack.get(index).equals(item)) {
                itemCheck = true;
            }
        }
        
        return itemCheck;
    }

    public void addItem(String item)
    {
        backpack.add(item);
    }
    
    public String eat(String item)
    {
        int itemNumber = -1;

        if ("book".equals(item) || "disk".equals(item) || "key".equals(item)) {
            return "You can't eat that!";
        } else {
            for (int index = 0; index < backpack.size(); index++) {
                if (backpack.get(index).equals(item)) {
                    itemNumber = index;
                }
            }

            if (itemNumber == -1) {
                return "That item is not in your backpack";
            }

            backpack.remove(itemNumber);
            raiseHunger(50);

            return "You ate the " + item;
            }
    }
    
    public void lowerHunger(int lower)
    {
        hunger -= lower;
        
        if (hunger <= 50) {
            System.out.println("Your stomach growls...");
        }
        
        if (hunger <= 0) {
            health -= 1;
            System.out.println("You're starving.. you lost 1 hp");
        }
    }
    
    public void raiseHunger(int raise)
    {
        hunger += raise;
    }
}
