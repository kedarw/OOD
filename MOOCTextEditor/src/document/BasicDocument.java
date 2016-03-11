package document;

import java.util.Arrays;
import java.util.List;

/**
 * A naive implementation of the Document abstract class.
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document
{
	/** Create a new BasicDocument object
	 *
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
	}


	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 *
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		return (this.getTokens("[a-zA-Z]+").size());
	}

	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of
	 * characters in the document, even if they don't end with a punctuation mark.
	 *
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
		String temp = this.getText();
		int length = temp.length();

		if(length > 0) {
			if (temp.charAt(length-1) != '?' &&  temp.charAt(length-1) != '.' && temp.charAt(length-1) != '!') {
				return(this.getTokens("[.|!|?]+").size()+1);
			}
		}

		return(this.getTokens("[.|!|?]+").size());
	}

	/**
	 * Get the number of syllables in the document.
	 * Words are defined as above.	Syllables are defined as:
	 * a contiguous sequence of vowels, except for a lone "e" at the
	 * end of a word if the word has another set of contiguous vowels,
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
		int syllables = 0;
		List<String> words = this.getTokens("[a-zA-Z]+");

		for(String word: words) {
			syllables += countSyllables(word);
		}

	return syllables;
	}


	/* The main method for testing this class.
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{

		testCase(new BasicDocument("This is a test.  How many???  "
			+ "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);

		testCase(new BasicDocument(""), 0, 0, 0);

		testCase(new BasicDocument("sentence, with, lots, of, commas.!	"
			+ "(And some poaren)).	The output is: 7.5."), 15, 11, 4);

		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);

		testCase(new BasicDocument("Segue"), 2, 1, 1);

		testCase(new BasicDocument("Sentence"), 2, 1, 1);
		testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
			 32, 15, 1);

		Document d = new BasicDocument("If you are single, never lost your spouse, and not the head of a household, you pay taxes "
				+ "according to the following table:");

		System.out.println("Flesch score: " + d.getFleschScore());

		Document d1 = new BasicDocument("There is hereby imposed on the taxable income of every individual (other than a surviving "
				+ "spouse as defined in section 2(a) or the head of a household as defined in section 2(b)) who is not a married "
				+ "individual (as defined in section 7703) a tax determined in accordance with the following table:");

		System.out.println("Flesch score: " + d1.getFleschScore());

		Document d2 = new BasicDocument("How are you?");

		System.out.println("Flesch score: " + d2.getFleschScore());
	}

}
