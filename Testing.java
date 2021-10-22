import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Testing {
    @Test
    public void test1(){
        Card a =new Card("suspect","personA");
        Card b =new Card("place","warehouse");
        Card c =new Card("weapon","forklift");
        IPlayer d = new comPlayer();
        Guess e = new Guess(c,a,b,false);
        IPlayer com = new comPlayer();
        assert (com.canAnswer(e,d)==null);
    }
    @Test
    public void test2(){
        Card a =new Card("suspect","personA");
        Card b =new Card("place","warehouse");
        Card c =new Card("weapon","forklift");
        IPlayer d = new comPlayer();
        Guess e = new Guess(c,a,b,false);
        IPlayer com = new comPlayer();
        com.setCard(a);
        assert (com.canAnswer(e,d).getValue()==a.getValue());
    }
    @Test
    public void test3(){
        Card a =new Card("suspect","personA");
        Card b =new Card("place","warehouse");
        Card c =new Card("weapon","forklift");
        IPlayer d = new comPlayer();
        Guess e = new Guess(c,a,b,false);
        IPlayer com = new comPlayer();
        com.setCard(a);
        com.setCard(b);
        assert (com.canAnswer(e,d).getValue()==a.getValue()||com.canAnswer(e,d).getValue()==b.getValue());
    }
    @Test
    public void test4(){
        ArrayList<Card> suspects = new ArrayList<>();
        ArrayList<Card>weapons = new ArrayList<>();
        ArrayList<Card>places = new ArrayList<>();

        suspects.add(new Card("suspect","agnes"));
        suspects.add(new Card("suspect","thanos"));

        weapons.add(new Card("weapon","stones"));
        weapons.add(new Card("weapon","bombs"));

        places.add(new Card("place","wakanda"));
        places.add(new Card("place","vormir"));

        IPlayer com = new comPlayer();
        com.setUp(1,0,suspects,places,weapons);
        com.setCard(suspects.get(0));
        com.setCard(weapons.get(0));
        com.setCard(places.get(0));
        Guess g = com.getGuess();

        assert((g.getLocation().getValue()!=places.get(0).getValue())&&(g.getWeapon().getValue()!=weapons.get(0).getValue())&&g.getSuspect().getValue()!=suspects.get(0).getValue());
    }
    @Test
    public void test5(){
        ArrayList<Card> suspects = new ArrayList<>();
        ArrayList<Card>weapons = new ArrayList<>();
        ArrayList<Card>places = new ArrayList<>();

        suspects.add(new Card("suspect","agnes"));
        suspects.add(new Card("suspect","thanos"));

        weapons.add(new Card("weapon","stones"));
        weapons.add(new Card("weapon","bombs"));

        places.add(new Card("place","wakanda"));
        places.add(new Card("place","vormir"));

        IPlayer com = new comPlayer();
        com.setUp(1,0,suspects,places,weapons);
        com.setCard(suspects.get(0));
        com.setCard(weapons.get(0));
        com.setCard(places.get(0));
        Guess g = com.getGuess();

        assert((g.getLocation().getValue()==places.get(1).getValue())&&(g.getWeapon().getValue()==weapons.get(1).getValue())&&g.getSuspect().getValue()==suspects.get(1).getValue());

    }
    @Test
    public void test6(){
        ArrayList<Card> suspects = new ArrayList<>();
        ArrayList<Card>weapons = new ArrayList<>();
        ArrayList<Card>places = new ArrayList<>();

        suspects.add(new Card("suspect","agnes"));
        suspects.add(new Card("suspect","thanos"));

        weapons.add(new Card("weapon","stones"));
        weapons.add(new Card("weapon","bombs"));

        places.add(new Card("place","wakanda"));
        places.add(new Card("place","vormir"));
        places.add(new Card("place","asgard"));

        IPlayer com = new comPlayer();
        com.setUp(1,0,suspects,places,weapons);
        com.setCard(suspects.get(0));
        com.setCard(weapons.get(0));
        com.setCard(places.get(0));
        Guess f = com.getGuess();
        assert(f.getChoice()==false);
        com.receiveInfo(null,places.get(2));
        Guess g = com.getGuess();
        assert(g.getChoice()==true);
    }
    @Test
    public void test7(){
        Card a =new Card("suspect","personA");
        Card b =new Card("place","warehouse");
        Card c =new Card("weapon","forklift");
        IPlayer d = new comPlayer();
        Guess e = new Guess(c,a,b,false);
        IPlayer com = new humanPlayer();
        com.setCard(a);
        com.setCard(b);
        Card f = com.canAnswer(e,d);
        assert (f.getValue()==a.getValue()||f.getValue()==b.getValue());

    }

}
