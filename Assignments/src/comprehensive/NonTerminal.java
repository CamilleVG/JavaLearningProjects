package comprehensive;

import java.util.Random;

/**
 * This class defines nonterminal objects.  Each non terminal has the num
 * of production lines it defines.
 * 
 * @author Camille van Ginkel
 *
 */

public class NonTerminal {
	
	String key;
	int numOfProdLines = 0;
	int size = 4;
	String[] prodLines = new String[size];;
	Random rng = new Random();
	
	
	public NonTerminal(String token) {
		key = token;
	}
	
	public void addProdLine(String line) {
		numOfProdLines ++;
		prodLines[numOfProdLines - 1] = line;
		//what data structure do we hold prod lines in?
		//we need to access a line randomly
		//we do not know how many lines there are
		//We could add each line to a String holder
		//then based on how many lines we add we create an array of that size
		//or we could try a binary heap or tree or something 
	}
	
	public String expand() {
		int index = rng.nextInt(numOfProdLines);
		return prodLines[index];
	}
	
	

}
