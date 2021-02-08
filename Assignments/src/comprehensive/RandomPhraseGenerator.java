package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;

public class RandomPhraseGenerator {
	//  java comprehensive/RandomGenerator poetic_sentence.g 5
	//.parseInt();
	public static void main(String[] args) throws FileNotFoundException {
		//get user input
		//create grammar
		//return a randomly generated phrase
		if (args.length >= 1) {
			System.out.println("The grammar file is:  " + args[0]);
		if (args.length >= 2) {
			System.out.println("The number of phrases to generate is: " + args[1]);
		}
		
		Grammar grammar = new Grammar(args[0]);
		int numToGo = Integer.parseInt(args[1]);
		while (numToGo > 0) {
			grammar.generatePhrase();
			numToGo --;
		}
		
			
		}
	}

}
