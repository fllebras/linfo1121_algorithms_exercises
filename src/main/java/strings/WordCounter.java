package strings;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implement the class WordCounter that counts the number of occurrences
 * of each word in a given piece of text.
 * Feel free to use existing java classes.
 */
public class WordCounter implements Iterable<String> {

    public Map<String,Integer> counter;

    public WordCounter() {
        counter = new TreeMap<String, Integer>();
    }

    /**
     * Add the word so that the counter of the word is increased by 1
     */
    public void addWord(String word) {
        if(this.counter.containsKey(word)){
            this.counter.replace(word,this.counter.get(word)+1);
        }else{
            this.counter.put(word,1);
        }
    }

    /**
     * Return the number of times the word has been added so far
     */
    public int getCount(String word) {
        Integer r=this.counter.get(word);
        if(r==null){
            return 0;
        }
        return r;
    }

    // iterate over the words in ascending lexicographical order
    @Override
    public Iterator<String> iterator() {
        return this.counter.keySet().iterator();
    }
}