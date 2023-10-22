import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * @student Obdulio Benitez Garcia
 * @class GDEV-242-99V
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;              // stores items of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Sets in what room an item will be placed in
     * The door is the key with the item being it's value
     *
     * @param item: The item object that is being put in the room.
     */
    public void setItem(Item item) 
    {
        items.add(item);
    }
    
    /**
     * Removes an item from the room's array
     *
     * @param item: The item object that is being removed from the room.
     */
    public void removeItem(String itemName)
    {
        int itemNumber = 0;
        
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getName() == itemName) {
                index = itemNumber;
            }
        }
        
        items.remove(itemNumber);
        
        System.out.println("You took the " + itemName 
                        + " and put it into your backpack");
    }
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public void getLongDescription()
    {
        if (items.size() < 1) {
            System.out.println("You are " + description + ".\n" + getExitString());
        } else {
            System.out.println("You are " + description + ".\n");
            for (Item item: items) {
                System.out.println(item.getLongDescription());
            }
            System.out.println(getExitString());
        }
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Checks if the room has an item
     * 
     * @param:  The item we want to check for
     * @return: If the item was found or not
     */
    public boolean hasItem(String item)
    {
        boolean found = false;
        
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getName().equals(item)) {
                found = true;
            }
        }
        
        return found;
    }
}

