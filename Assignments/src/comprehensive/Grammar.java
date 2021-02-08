package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Grammar {
	
	private HashMap<String, NonTerminal> nonTerminals = new HashMap<String, NonTerminal>();
	private PriorityQueue<String> phraseQueue = new PriorityQueue<String>();

	public Grammar(String filename) throws FileNotFoundException {
		Scanner scn;
		scn = new Scanner(new File(filename));

		while (scn.hasNext()) {
			if (scn.next().contains("{")) {
				String key = scn.next();
				NonTerminal tempRef = new NonTerminal(key);

				while (!scn.next().contains("}")) {
					String line = scn.nextLine();
					tempRef.addProdLine(line);
				}

				nonTerminals.put(key, tempRef);
			}
		}

	}

	public String generatePhrase() {

		String phrase = "";
		addTokensToQueue("<start>");
		while (!phraseQueue.isEmpty()) {
			buildPhrase(phrase);
		}

		return phrase;
	}

	private String buildPhrase(String phrase) {
		String token = phraseQueue.poll();

		// a token can be a terminal(word or character), a pure nonTerminal, or an
		// impure nonTerminal
		if (isPureNonTerminal(token)) {
			String expansion = nonTerminals.get(token).expand();
			addTokensToQueue(expansion);
		} else if (containsNonTerminal(token)) { // if the terminal is surround by chars: "self-<adjective>"
			int firstBracket = token.indexOf('<');
			int secondBracket = token.indexOf('>');
			String key = token.substring(firstBracket, secondBracket);
			String expansion = nonTerminals.get(key).expand();
			expansion = token.replaceAll(key, expansion);
			addTokensToQueue(expansion);
		} else { // if token is a terminal
			if (phrase.isEmpty()) { // the first token should not add a space
				phrase = token;
			} else {
				phrase = token + " " + phrase;
			}
		}

		return phrase;
	}

	/*
	 * Helper method that scans through a phrase or expansion and adds tokens to
	 * priority queue
	 * 
	 */
	private void addTokensToQueue(String expansion) {

		Scanner scn = new Scanner(expansion);
		while (scn.hasNext()) {
			phraseQueue.add(scn.next());
		}
	}

	private boolean isPureNonTerminal(String token) {
		if ((token.charAt(0) == '<') && (token.charAt(token.length() - 1) == '>')) {
			return true;
		} else {
			return false;
		}
	}

	public boolean containsNonTerminal(String token) {
		if (token.contains("<") && token.contains(">")) {
			int diff = token.indexOf('>') - token.indexOf('<') ;
			if (!(diff > 1))  {
				return true;
			}
		}
		return false;
	}

}
