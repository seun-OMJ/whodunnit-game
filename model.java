import java.util.ArrayList;
import java.util.Collections;

public class model {
    private boolean gameOver;
   private ArrayList<Card> people;
   private ArrayList<Card> places;
   private ArrayList<Card> weapons;
   private Card ansCriminal;
   private Card ansPlace;
   private Card ansWeapon;
   private ArrayList<IPlayer> players;
    public model(ArrayList<Card> people,ArrayList<Card> places,ArrayList<Card>weapons){
        gameOver = false;
        this.people = new ArrayList<>();
        for(int i = 0;i<people.size();i++){this.people.add(people.get(i));}
        this.places = new ArrayList<>();
        for(int i = 0;i<places.size();i++){this.places.add(places.get(i));}
        this.weapons = new ArrayList<>();
        for(int i = 0;i<weapons.size();i++){this.weapons.add(weapons.get(i));}
    }
    public void SetPlayer(ArrayList<IPlayer>players) {
        System.out.println("printing all suspects\n");
        for (int i = 0; i < people.size(); i++) {
            System.out.println(i + ". " + people.get(i).getValue());
        }
        System.out.println("\nprinting all places\n");
        for (int i = 0; i < places.size(); i++) {
            System.out.println(i + ". " + places.get(i).getValue());
        }
        System.out.println("\nprinting all weapons\n");
        for(int i = 0;i<weapons.size();i++){
            System.out.println(i+" "+weapons.get(i).getValue());
        }
        this.players = (ArrayList<IPlayer>) players.clone();
        for(int i = 0; i<players.size();i++){
            players.get(i).setUp(this.players.size(),i,people,places,weapons);
        }

    }
    public void setCards(){
        Collections.shuffle(people);
        Collections.shuffle(places);
        Collections.shuffle(weapons);
        ansCriminal = people.remove(0);
        ansPlace = places.remove(0);
        ansWeapon = weapons.remove(0);

        ArrayList<Card> total = new ArrayList<Card>();
        for(int i = 0;i<people.size();i++){total.add(people.get(i));}
        for(int i =0 ;i<places.size();i++){total.add( places.get(i));}
        for(int i =0;i<weapons.size();i++){total.add( weapons.get(i));}
        Collections.shuffle(total);

        int i = 0;
        int j = 0;
        while(i<total.size()){
            if(j==players.size()){
                j =0;
            }
            else{
                players.get(j).setCard(total.get(i));
                j++;
                i++;
            }

        }
    }

    public void runGame(){
        ArrayList<IPlayer>temp = new ArrayList<IPlayer>();
        for(int i = 0;i<players.size();i++){temp.add(players.get(i));}
        int i = 0;
        while(gameOver==false){
            Guess g;
            if(temp.size()==1){
                System.out.println("player "+temp.get(0).getIndex()+" wins\n");
                gameOver = true;
            }
            else if(( i>=temp.size())&&(temp.size()!=1)){i=0;}
            else if(i<temp.size()){
               g= temp.get(i).getGuess();

               if(g.getChoice()){
                   if((g.getWeapon()==ansWeapon)&&(g.getSuspect()==ansCriminal)&&(g.getLocation())==ansPlace){
                       System.out.println("Game is won: \"player "+i+" won the game\"\n");
                       System.out.println("suspect: "+ansCriminal.getValue()+"\nweapon: "+ansWeapon.getValue()+"\n location: "+ansPlace.getValue());
                       gameOver= true;
                   }
                   else{
                       System.out.println("Bad accusation: player "+i+" made a bad accusation and is out of the game\n");
                       temp.remove(i);
                       i++;

                   }
               }
               else if(!g.getChoice()) {
                   int j = i+1;
                   Card c = null;
                  while(j!=i&& c==null){
                      if(j==players.size()){
                          j =0;
                      }
                      else{
                          c = players.get(j).canAnswer(g,temp.get(i));
                          j++;
                      }
                  }

                  if(c==null){
                      temp.get(i).receiveInfo(null,c);
                  }
                  else if(c!=null){
                      j=j-1;
                      temp.get(i).receiveInfo(players.get(j),c );
                  }

                  i++;
               }
               }

        }
    }
}
