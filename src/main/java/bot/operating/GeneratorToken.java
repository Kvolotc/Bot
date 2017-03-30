package bot.operating;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;



public class GeneratorToken {

	private static final String[] ALPHABET = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	private static final int INDEX_OF_LOWERCASE_LETTER = 1;
	private static final int DIVIDER = 2;
	
	public static String generateToken() {
		
		Random random = new Random();
		Set<String> token = new HashSet<>();
		String latter;
			
		while(token.size() !=5) {
			int rand = random.nextInt(ALPHABET.length);
			
			if(rand % DIVIDER == INDEX_OF_LOWERCASE_LETTER) {
				latter = ALPHABET[rand];
				token.add(latter);
			}
			else{
				latter = ALPHABET[rand].toUpperCase();
				token.add(latter);
			}
			
		}
		
		return token.stream().collect(Collectors.joining());
		
	}
	
	private GeneratorToken() {
		
	}
}
