import org.w3c.dom.html.HTMLTableRowElement;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        ArrayList<IPlayer>players = new ArrayList<>();
        ArrayList<Card>suspects = new ArrayList<>();
        ArrayList<Card>weapons = new ArrayList<>();
        ArrayList<Card>places = new ArrayList<>();
        System.out.println("WELCOME TO \"WHODUNNIT?\"\n");

        suspects.add(new Card("suspect","agnes"));
        suspects.add(new Card("suspect","thanos"));
        suspects.add(new Card("suspect","loki"));
        suspects.add(new Card("suspect","whiplash"));
        suspects.add(new Card("suspect","ella"));
        suspects.add(new Card("suspect","hydra"));
        suspects.add(new Card("suspect","egor"));
        suspects.add(new Card("suspect","killmonger"));

        weapons.add(new Card("weapon","stones"));
        weapons.add(new Card("weapon","bombs"));
        weapons.add(new Card("weapon","spells"));
        weapons.add(new Card("weapon","guns"));
        weapons.add(new Card("weapon","electricity"));
        weapons.add(new Card("weapon","vibranium"));

        places.add(new Card("place","wakanda"));
        places.add(new Card("place","vormir"));
        places.add(new Card("place","lagos"));
        places.add(new Card("place","asgard"));
        places.add(new Card("place","sekovia"));


        model mod = new model(suspects,places,weapons);
        int A = 0;
        boolean accepted = false;
        while(!accepted){
            Scanner scan = new Scanner(System.in);
            System.out.println("\nHow many players do you want(ENTER A POSITIVE INTEGER):");
            A = scan.nextInt();
            if((A>0)&&(A%1==0)){
                accepted = true;
            }
        }
        for(int i = 0;i<A-1;i++){
            players.add(new comPlayer());
        }
        players.add(new humanPlayer());


        System.out.println("\nSetting players\n");
        mod.SetPlayer(players);
        System.out.println("\nDealing cards\n");
        mod.setCards();
        System.out.println("\ngame starting\n");
        mod.runGame();
        System.out.println("game ended\n");

    }
}
