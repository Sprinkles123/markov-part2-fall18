import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram, ArrayList<String>>myWordgram;
	/**
	 * Construct a 	EfficientWordMarkov object with the specified order.
	 * Calls the private variable called myWordGram and initialize with a Hashmap 
	 * using a Wordgram oject as a key input and an ArrayList of strings as a value input.
	 * @param order size of this Markov generator
	 */
	public EfficientWordMarkov(int order) {
		super(order);
		myWordgram= new HashMap<WordGram, ArrayList<String>>();//
	}
	/**
	 * Default constructor has order 2
	 */
	public EfficientWordMarkov() {
		this(2);
	}

	
	/**
	 * Fills the Hashmap with keys and their associated values for each myOrder sequence of characters 
	 * of by iterating through a loop and also clears the Hashmap.
	 * @param text the string we are using to add hash values and keys to the hash map
	 */
	@Override
	public void setTraining(String text) {
	 myWords = text.split("\\s+");
		myWordgram.clear();
		
		for(int index=0; index<myWords.length- myOrder + 1 ;) {
			WordGram key = new WordGram(myWords,index, myOrder);
			 if(!(myWordgram.containsKey(key))) {myWordgram.put(key, new ArrayList<String>()); }
			 if ( index==myWords.length- myOrder  ) {myWordgram.get(key).add(PSEUDO_EOS);}//
			 else {myWordgram.get(key).add(myWords[index+myOrder ]);}			
			index++;
		}
	}		
	
	/**
	 * looks up the key in a map and returns the associated value. It throws  an exception when map does not contain key. 
	 * @param key the key values from the hashmap created in the previous method
	 * @return follows the value associated the the key in the hashmap
	 */
	@Override
	public ArrayList<String> getFollows(WordGram key) {
			ArrayList<String> follows = myWordgram.get(key);
			if (!(myWordgram.containsKey(key))){
				throw new NoSuchElementException(key+" not in map");
			}
		
		return follows;
	}
	

}
	
	

