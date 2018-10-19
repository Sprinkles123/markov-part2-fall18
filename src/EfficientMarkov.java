import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;


public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	/**
	 * Construct a 	EfficientMarkov object with the specified order.
	 * Calls the private variable called myMap and initialize with a Hashmap.
	 * using a string as a key input and an ArrayList of strings as a value input.
	 * @param order size of this markov generator
	 */
	public EfficientMarkov(int order) {
		super(order);
		myMap= new HashMap<String,ArrayList<String>>();//
	}
	/**
	 * Default EfficientMarkov constructor has order 3
	 */
	public EfficientMarkov() {
		this(3);
	}

	
	/**
	 * Fills the hashmap with keys and their associated values for each myOrder sequence of characters of by iterating through a loop
	 * Also clears the hashmap 
	 * @param text the string we are using to add hash values and keys to the hash map
	 */
	@Override
	
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		
		for(int index=0; index<myText.length()- myOrder + 1 ;) {
			String key= myText.substring(index, index + myOrder );
			 if(!(myMap.containsKey(key))) {myMap.put(key, new ArrayList<String>()); }
			 if ( index==myText.length()- myOrder  ) {myMap.get(key).add(PSEUDO_EOS);}//
			 else {myMap.get(key).add(myText.substring(index+ myOrder, index+myOrder +1 ));}			
			index++;
		}
	}		
	
	/**
	 * looks up the key in a map and returns the associated value. throws  an exception when map does not contain key. 
	 * @param key is the key values from the Hashmap created in the previous method
	 * @return follows the value associated the the key in the Hashmap
	 */
	@Override
	public ArrayList<String> getFollows(String key){
			ArrayList<String> follows = myMap.get(key);
			if (!(myMap.containsKey(key))){
				throw new NoSuchElementException(key+" not in map");
			}
		
		return follows;
	}
	

}
