package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Comparator;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size = 0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{	
		TrieNode 	itr = root;
		int 		len = word.length();
		
		if(word.isEmpty()) {
			itr.setEndsWord(true);
		}
		
		String lowercase_word = word.toLowerCase();
		if(!isWord(lowercase_word)) {
			for(int i = 0; i < len ; i++) {
				char c = lowercase_word.charAt(i);
				TrieNode node = itr.getChild(c);
				if(node == null) {
					node = new TrieNode(String.valueOf(c));
					node = itr.insert(c);
				}
				itr = node;
			}
			itr.setEndsWord(true);
			size += 1;
			return true;
		}
		else {
			return false;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		TrieNode 	itr 	= root;
		String lowercase_s 	= s.toLowerCase();
		int 		len 	= lowercase_s.length();
		if(itr!=null && (!lowercase_s.isEmpty())) {
			for(int i = 0; i < len; i++) {
				char c = lowercase_s.charAt(i);
				TrieNode node = itr.getChild(c);
				if(node != null) {
					itr = node;
				}
				else {
					return false;
				}
			}
			if(itr.endsWord()) {
				return true;
			}
		}
		
		return false;
	}

	 // This method should implement the following algorithm:
	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
	 //    empty list
	 // 2. Once the stem is found, perform a breadth first search to generate completions
	 //    using the following algorithm:
	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
	 //       of the list.
	 //    Create a list of completions to return (initially empty)
	 //    While the queue is not empty and you don't have enough completions:
	 //       remove the first Node from the queue
	 //       If it is a word, add it to the completions list
	 //       Add all of its child nodes to the back of the queue
	 // Return the list of completions

	
	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
	public List<String> predictCompletions(String prefix, int numCompletions) 
	{
    	 TrieNode itr = root;
    	 List<String> output = new LinkedList<String>();
    	 
    	 if(numCompletions > 0) {
    		 for(char c: prefix.toCharArray()) {
        		 if(!itr.getValidNextCharacters().contains(c)) {
        			 return output;
        		 }
        		 itr = itr.getChild(c);
        	 }
        	 output.addAll(itr.BFS());
        	 
        	 // TODO: Change this sort as it is inefficient to sort
        	 // may be while searching add strings in priority queue
        	 Collections.sort(output,new Comparator<String>()
        	 {
        		 @Override
        		 public int compare(String s1,String s2)
        		 {
        			 return s1.length() - s2.length();
        	     }
        	 });
        	 
        	 if(numCompletions < output.size()) 
        		 return output.subList(0, numCompletions);
        	 else
        		 return output;
    	 }
    	 
    	 return output;
	}
     
     // For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
}