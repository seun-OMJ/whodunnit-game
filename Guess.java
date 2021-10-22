public class Guess {
    private Card weapon;
    private Card suspect;
    private Card location;
    private boolean choice;
    public Guess(Card weapon,Card suspect, Card location, boolean choice){
        this.weapon = weapon;
        this.suspect = suspect;
        this.location = location;
        this.choice = choice;
    }

    public Card getWeapon(){
        return weapon;
    }
    public Card getSuspect(){
        return suspect;
    }
    public Card getLocation(){
        return location;
    }
    public boolean getChoice(){
        return choice;
    }
}
