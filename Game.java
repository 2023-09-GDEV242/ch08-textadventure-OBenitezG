/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * @student Obdulio Benitez Garcia
 * @class GDEV-242-99V
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;

    
    /**
     * Method main
     * Plays the game without the use of BlueJ
     *
     */
    public static void main(String[] args) {
        Game start = new Game();
        start.play();
    }   

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     * Creates all the items and places them in a room.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, courtyard,
        parkinglot, lobby, bathroom, breakroom, classroom, hallway, 
        library, meetingroom, lectureroom,balcony;
        Item apple, disk, book, paper, gummies, muffin, water, coffie, crackers, key;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        courtyard = new Room("in a spacious courtyard");
        parkinglot = new Room("in a vacant parking lot");
        lobby = new Room("in a desolate lobby");
        bathroom = new Room("in the bathrooms");
        breakroom = new Room("in the brakroom");
        classroom = new Room("in the classroom");
        hallway = new Room("in the hallway");
        library = new Room("in the library");
        meetingroom = new Room("in the meeting room");
        lectureroom = new Room("in the lecture room");
        balcony = new Room("outside the library");

        //create the items
        apple = new Item("apple", "shiny red apple", 1);
        disk = new Item("disk", "old floppy disk", 0.1);
        book = new Item("book", "dusty book", 1);
        paper = new Item("paper", " stack of paper", 0.2);
        gummies = new Item("fruit gummies", "Whelche's fruit gummie snack", 0.1);
        muffin = new Item("muffin", "toasted corn muffin", 0.1);
        water = new Item("water bottle", "Poland Spring water bottle", 0.5);
        coffie = new Item("coffie", "luke warm cup of coffe", 0.1);
        crackers = new Item("crackers", "ritz crackers", 0.1);
        key = new Item("key", "key with a tag that says meeting room", 0.1);
        
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);
        pub.setExit("south", courtyard);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        courtyard.setExit("west", parkinglot);
        courtyard.setExit("south", lobby);
        courtyard.setExit("north", pub);
        
        parkinglot.setExit("east", courtyard);
        
        lobby.setExit("west", bathroom);
        lobby.setExit("east", breakroom);
        lobby.setExit("south", hallway);
        lobby.setExit("north", courtyard);
        
        bathroom.setExit("east", lobby);
        bathroom.setExit("south", classroom);
        
        breakroom.setExit("west", lobby);
        breakroom.setExit("south", library);
        
        classroom.setExit("north", bathroom);
        classroom.setExit("east", hallway);
        
        hallway.setExit("north", lobby);
        hallway.setExit("south", lectureroom);
        hallway.setExit("east", library);
        hallway.setExit("west", classroom);
        
        library.setExit("north", breakroom);
        library.setExit("south", balcony);
        library.setExit("east", meetingroom);
        library.setExit("west", hallway);
        
        meetingroom.setExit("west", library);
        
        lectureroom.setExit("north", hallway);
        
        balcony.setExit("north", library);
        
        // initialise room items
        outside.setItem(apple);
        
        lab.setItem(disk);
        
        bathroom.setItem(key);
        
        office.setItem(book);
        office.setItem(paper);
        
        breakroom.setItem(gummies);
        breakroom.setItem(muffin);
        breakroom.setItem(water);
        
        classroom.setItem(coffie);
        
        lectureroom.setItem(coffie);
        lectureroom.setItem(water);
        lectureroom.setItem(muffin);
        
        meetingroom.setItem(crackers);
        lectureroom.setItem(coffie);
        lectureroom.setItem(water);
        lectureroom.setItem(muffin);
        
        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        currentRoom.getLongDescription();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            //Exercise 8.14:
            case LOOK:
                lookAround(command);
                break;

            //Exercise 8.15:  
            case EAT:
                eatSomething(command);
                break;
                
            case GRAB:
                grabSomething(command);
                break;
                
            case BACKPACK:
                checkBackpack(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            if (nextRoom.getShortDescription() == "in the meeting room") {
                if (player.hasItem("key") == false) {
                    System.out.println("Door is locked");
                    return;
                } else {
                    currentRoom = nextRoom;
                    player.lowerHunger(10);
                    currentRoom.getLongDescription();
                }
            } else {
                currentRoom = nextRoom;
                player.lowerHunger(10);
                currentRoom.getLongDescription();
            }
        }
    }

    /** 
     *  Grabs an item by removing them from the rooms array and adding it to the players array
     *
     * @param:   The command
     * @return: if the item was grabbed or not
     */
    private void grabSomething(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Grab what?");
            return;
        }
        
        String item = command.getSecondWord();

        if (!currentRoom.hasItem(item)) {
            System.out.println("There is no item");
            return;
        }
        
        currentRoom.removeItem(item);
        
        player.addItem(item);
    }
    
    /**
     * Allows the player to check what they have in their backpack
     *
     * @return An array of stored items
     */
    private void checkBackpack(Command command) 
    {
        player.insideBackpack();
    }
    
    /**
     * Exercise 8.14:
     * Looks around the current room
     * 
     * @param     The command word used to run this method
     * @return    A description of the current room
     */
    private void lookAround(Command command)
    {
        currentRoom.getLongDescription();
    }
    
    /**
     * Exercise 8.15:
     * Eats an item
     *
     * @param     The command word used to run this method
     * @return    A description of eating an item
     */
    private void eatSomething(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Eat what?");
            return;
        }
        
        String item = command.getSecondWord();
        
        System.out.println(player.eat(item));
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
