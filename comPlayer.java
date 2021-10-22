import java.util.ArrayList;
import java.util.Random;

public class comPlayer implements  IPlayer {
    private int index;
    private boolean makeAccusation;
    private int totalPlayers;
    private ArrayList<Card> people;
    private ArrayList<Card> places;
    private ArrayList<Card> weapons;
    private ArrayList<Card> dealtCards;
    private ArrayList<Card> notAnswer;


    public comPlayer(){
        index=0;
        dealtCards = new ArrayList<Card>();
        this.weapons = new ArrayList<>();
        this.places = new ArrayList<>();
        this.notAnswer = new ArrayList<>();
        this.people = new ArrayList<>();
        this.makeAccusation=false;

    }


    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,
                       ArrayList<Card> places, ArrayList<Card> weapons){
        this.index = index;
        totalPlayers = numPlayers;
        for(int i = 0;i<ppl.size();i++){people.add(ppl.get(i));}
        for(int i = 0;i<places.size();i++){this.places.add( places.get(i));}
        for(int i = 0;i<weapons.size();i++){this.weapons.add(weapons.get(i));}

    }


    public void setCard (Card c){
        dealtCards.add(c);
    }


    public int getIndex(){return index;}


    public Card canAnswer(Guess g, IPlayer ip){
        ArrayList<Card> possibleResult = new ArrayList<Card>();
        Card result = null;
        for(int i = 0;i<dealtCards.size();i++){
            if((dealtCards.get(i)==g.getLocation())||(dealtCards.get(i)==g.getSuspect())||(dealtCards.get(i)==g.getWeapon())){
                possibleResult.add(dealtCards.get(i));
            }
        }
        if (possibleResult.size()>1){

            result = possibleResult.get((int)Math.random()*(possibleResult.size()-0+1));
        }
        else if (possibleResult.size()==1){
            result = possibleResult.get(0);
        }
        if(result==null){
           System.out.println("player "+ this.index+" could not answer player "+ip.getIndex()+"'s guess");
        }
        else if(result!=null){
            System.out.println("player "+index+" answered to player "+ip.getIndex()+"'s guess");
        }
        return result;}


    public Guess getGuess(){
        Card suspect = null;
        Card weapon = null;
        Card place = null;
        boolean suspectFound = false;
        int i = 0;
        while ((suspectFound==false)&&(i<people.size())){
            if(notAnswer.contains(people.get(i))){
                i++;
            }
            else if(dealtCards.contains(people.get(i))){
                i++;
            }
            else{
                suspectFound = true;
                suspect = people.get(i);
            }
        }
        boolean weaponFound = false;
        int j = 0;
        while ((weaponFound==false)&&(j<weapons.size())){
            if(notAnswer.contains(weapons.get(j))){
                j++;
            }
            else if(dealtCards.contains(weapons.get(j))){
                j++;
            }
            else{
                weaponFound = true;
                weapon = weapons.get(j);
            }
        }
        boolean placeFound = false;
        int k = 0;
        while ((placeFound==false)&&(k<places.size())){
            if(notAnswer.contains(places.get(k))){
                k++;
            }
            else if(dealtCards.contains(places.get(k))){
                k++;
            }
            else{
                placeFound = true;
                place = places.get(k);
            }
        }
        boolean accusation = false;
        if(makeAccusation==true){
            System.out.println("player "+this.index+" made an accusation");
            accusation = true;
        }
        else if((people.size()+places.size()+weapons.size())-(dealtCards.size()+notAnswer.size())==3){
            System.out.println("player "+this.index+" made an accusation");
            accusation=true;
        }
        else if(makeAccusation==false){
        System.out.println("player "+this.index+" made a guess");
        }
        Guess G = new Guess(weapon,suspect,place,accusation);
        return G;}


    public void receiveInfo(IPlayer ip, Card c){
        if(c!=null){
            notAnswer.add(c);}
        else{
            makeAccusation = true;
        }
    }
}
