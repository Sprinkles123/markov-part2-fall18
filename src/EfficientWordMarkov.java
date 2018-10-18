import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram, ArrayList<String>>myWordgram;
	
	public EfficientWordMarkov(int order) {
		super(order);
		myWordgram= new HashMap<WordGram, ArrayList<String>>();//
	}
	
	public EfficientWordMarkov() {
		this(2);
	}

	
	
	@Override
	public void setTraining(String text) {
	 myWords = text.split("\\s+");
		myWordgram.clear();
		
		for(int index=0; index<myWords.length- myOrder + 1 ;) {
			WordGram key = new WordGram(myWords,index, myOrder);
			 if(!(myWordgram.containsKey(key))) {myWordgram.put(key, new ArrayList<String>()); }
			 if ( index==myWords.length- myOrder  ) {myWordgram.get(key).add(PSEUDO_EOS);}//
			 else {myWordgram.get(key).add(myWords[index+myOrder +1 ]);}			
			index++;
		}
	}		
	
	
	@Override
	public ArrayList<String> getFollows(WordGram key) {
			ArrayList<String> follows = myWordgram.get(key);
			if (!(myWordgram.containsKey(key))){
				throw new NoSuchElementException(key+" not in map");
			}
		
		return follows;
	}
	

}
	
	

