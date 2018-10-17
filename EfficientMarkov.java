import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(int order) {
		super(order);
		myMap= new HashMap<String,ArrayList<String>>();//
	}
	
	public EfficientMarkov() {
		this(3);
	}

	
	
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
	
	
	@Override
	public ArrayList<String> getFollows(String key){
			ArrayList<String> follows = myMap.get(key);
			if (!(myMap.containsKey(key))){
				throw new NoSuchElementException(key+" not in map");
			}
		
		return follows;
	}
	

}
