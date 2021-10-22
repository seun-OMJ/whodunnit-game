import java.util.ArrayList;
import java.util.Scanner;

public class humanPlayer implements IPlayer{
    private int index;
    int totalPlayers;
    ArrayList<Card> people;
    ArrayList<Card> places;
    ArrayList<Card> weapons;
    private ArrayList<Card> dealtCards;


    public humanPlayer(){
        dealtCards = new ArrayList<Card>();
        people = new ArrayList<Card>();
        places = new ArrayList<Card>();
        weapons = new ArrayList<Card>();
    }


    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,
                       ArrayList<Card> places, ArrayList<Card> weapons){
        this.index = index;
        totalPlayers = numPlayers;
        for(int i = 0;i<ppl.size();i++){people.add(ppl.get(i));}
        for(int i = 0;i<places.size();i++){this.places.add( places.get(i));}
        for(int i = 0;i<weapons.size();i++){this.weapons.add( weapons.get(i));}
    }


    public void setCard (Card c){
        dealtCards.add(c);
        System.out.println("you received card "+c.getValue()+" of type "+c.getType());
    }


    public int getIndex(){return index;}


    public Card canAnswer(Guess g, IPlayer ip){
        System.out.println("player "+ip.getIndex()+" is asking you a question about guess: "+g.getSuspect().getValue()+", "+g.getLocation().getValue()+", "+g.getWeapon().getValue());
        ArrayList<Card> possibleResult = new ArrayList<Card>();
        Card result = null;
        for(int i = 0;i<dealtCards.size();i++){
            if((dealtCards.get(i)==g.getLocation())||(dealtCards.get(i)==g.getSuspect())||(dealtCards.get(i)==g.getWeapon())){
                possibleResult.add(dealtCards.get(i));
            }
        }
        if(possibleResult.size()>1){
            System.out.println("which card do you want to reveal:");
            for(int i=0;i<possibleResult.size();i++){
                System.out.println(i+". "+possibleResult.get(i).getValue()+" type-"+possibleResult.get(i).getType());
            }
            int suspect = 0;
            boolean found = false;
            while(found == false){
                Scanner A = new Scanner(System.in);
                System.out.println("pick a card to show from 0 to "+(possibleResult.size()-1));
                suspect = A.nextInt();
                if(suspect>=0&&suspect<=possibleResult.size()-1){
                    found = true;
                }
            }

            result = possibleResult.get(suspect);
        }
        else if(possibleResult.size()==1){
            result = possibleResult.get(0);
            System.out.println("you only have one card to show: "+result.getValue());

        }
        if(result == null){
            System.out.println("you have no cards to show");
        }
        return result;}


    public Guess getGuess(){
        System.out.println("your turn");
        boolean foundSuspect= false;
        int suspect = 0;
        System.out.print("\nHere are all the suspects: ");
        for(int i=0;i<people.size();i++){
            System.out.print(i+". "+people.get(i).getValue()+", ");
        }
        while(foundSuspect == false){
            Scanner A = new Scanner(System.in);
            System.out.println("pick a suspect from 0 to "+(people.size()-1));
            suspect = A.nextInt();
            if(suspect>=0&&suspect<=people.size()-1){
                foundSuspect = true;
            }
        }
        boolean foundPlace= false;
        int place = 0;
        System.out.print("\nHere are all the locations: ");
        for(int i=0;i<places.size();i++){
            System.out.print(i+". "+places.get(i).getValue()+", ");}
        while(foundPlace == false){
            Scanner A = new Scanner(System.in);
            System.out.println("\npick a place from 0 to "+(places.size()-1));
            place = A.nextInt();
            if((place>=0)&&(place<=people.size()-1)){
                foundPlace = true;
            }
        }
        boolean foundWeapon= false;
        int weapon = 0;
        System.out.print("\nHere are all the weapons: ");
        for(int i=0;i<weapons.size();i++){
            System.out.print(i+". "+weapons.get(i).getValue()+", ");}
        while(foundWeapon == false){
            Scanner A = new Scanner(System.in);
            System.out.println("\npick a weapon from 0 to "+(weapons.size()-1));
            weapon = A.nextInt();
            if(weapon>=0&&weapon<=people.size()-1){
                foundWeapon = true;
            }
        }
        boolean choiceMade= false;
        String choice = null;
        boolean accusation = false;
        while(choiceMade == false){
            Scanner A = new Scanner(System.in);
            System.out.println("is this an accusation? (y/n)");
            choice= A.nextLine();
            if((choice.compareToIgnoreCase("y")==0)||(choice.compareToIgnoreCase("n")==0)){
                choiceMade = true;
            }
        }
        if(choice.compareToIgnoreCase("y")==0){
            accusation = true;
        }

        Guess made  = new Guess(weapons.get(weapon), people.get(suspect),places.get(place),accusation);
        return made;}

    public void receiveInfo(IPlayer ip, Card c){
        if(ip!=null&&c!=null){
        System.out.println("player "+ip.getIndex()+" has card"+c.getValue());
        }
        else{
            System.out.println("no one could answer");
        }

    }
}
