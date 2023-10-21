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
    private ArrayList<String> keyRing; 
    
    private int health;
    private int hunger;

    /**
     * Constructor for objects of class Player
     * Contains Player's health and hunger values
     * 
     * Creates the backpack and keyRing arrays to hold items
     */
    public Player()
    {
        health = 100;
        hunger = 150;
        
        backpack = new ArrayList<>();
        keyRing = new ArrayList<>();
    }

    public void addItem(String item)
    {
        backpack.add(item);
    }
    
    public void eat(String item)
    {
        int itemNumber = 0;
        
        for (int index = 0; index < backpack.size(); index++) {
            if (backpack.get(index) == item) {
                index = itemNumber;
            }
        }
        
        System.out.println("You ate the " + item);
        
        backpack.remove(itemNumber);
    }
    
    public void lowerHunger(int lower)
    {
        hunger -= lower;
        
        if (hunger <= 50) {
            System.out.println("Your stomach growls...");
        }
        
        if (hunger <= 0) {
            health -= 1;
            System.out.println("Your starving.. you lost 1 hp");
        }
    }
    
    public void raiseHunger(int raise)
    {
        hunger += raise;
        
        if (hunger >= 100) {
            System.out.println("You feel alot better");
        }
    }
}
