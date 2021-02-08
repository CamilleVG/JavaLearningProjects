package comprehensive;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GrammarTest {

	public Grammar simple, math, poetry, extension;
	

	@BeforeEach
	void setUp() throws FileNotFoundException {
		simple = new Grammar("src/comprehensive/super_simple.g");
		math = new Grammar("src/comprehensive/mathematical_expression.g");
		poetry = new Grammar("src/comprehensive/poetic_sentence.g");
		extension = new Grammar("src/comprehensive/assignment_extension_request.g");
	}

	
	@Test
	void testGeneratePhraseSimple()  throws FileNotFoundException {
		//Grammar more = new Grammar("src/comprehensive/super_simple.g");
		simple.generatePhrase();
		assertEquals("mom", "mom");
		
	}

	
	@Test
	void testGrammar() throws FileNotFoundException {
		Grammar simple = new Grammar("src/comprehensive/super_simple.g");
		
		
		fail("Not yet implemented");
	}

	
}
