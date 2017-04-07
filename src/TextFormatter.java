
import java.util.*;
import org.apache.http.conn.ssl.*;public class TextFormatter
{
	private static final int NUM_OF_SPACES_IN_TAB = 4;
	
	private String[] weapons;
	private String output;
	private String newString;
	private int maxLetters;
	private double numOfSpaces;
	private double remainder;
	private int size;
	
	public String format( String [] weapons )
	{
	    
		this.weapons = weapons;
		
		//for( int i = 0; i < weapons.length; i++ )
		//{
			output = weapons[0];
		size = weapons[0].length();
		System.out.println(maxLetters + ":" + size);
//		numOfSpaces = (double)(maxLetters - size);
		
		for( int j = size; j < maxLetters; j++ )
			output += " ";
		output += weapons[1];
		//}
		return output;
	}
	
	public void getMax(LinkedList<String[]> array)
	{
		for( int i = 0; i < array.size(); i++)
			maxLetters = getMaxCount( maxLetters, array.get(i)[0].length() );
		maxLetters += 4;
	}
	
	private int getMaxCount(int max, int newNum)
	{
		if( newNum % 5 == 0)
			newNum++;
		if( max < newNum )
			max = newNum;
		return max;
	}
	
	private double roundToFive( double num )
	{
		double whole = num / NUM_OF_SPACES_IN_TAB;
		double extra = 0;
		return 0;
	}
}
