package ohtuesimerkki;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Reader readerStub = new Reader() {
    	 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  

    @Test
    public void validPlayerFound() {
    	Player semenko = stats.search("Semenko");
    	assertEquals(semenko.getName(), "Semenko");
    }
    
    @Test
    public void invalidPlayerNotFound() {
    	Player eiKukaan = stats.search("rörör");
    	assertEquals(eiKukaan, null);
    }
    
    @Test
    public void listHasAllPlayers() {
    	List<Player> players = stats.team("EDM");
    	assertEquals(players.size(), 3);
    }
    
    @Test
    public void listsAllTopPlayers() {
    	List<Player> tops = stats.topScorers(3);
    	assertEquals(tops.size(), 4);
    }
}
