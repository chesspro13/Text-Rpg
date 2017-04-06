
import java.util.*;public class TextFormatter
{
	private static final int NUM_OF_SPACES_IN_TAB = 5;
	
	private LinkedList<String[]> weapons;
	private String [] output;
	private String newString;
	private int maxLetters;
	private double numOfTabs;
	private int size;
	
	public String[] format( LinkedList<String []> weapons )
	{
		this.weapons = weapons;
		output = new String[weapons.size()];
		
		for( int i = 0; i < weapons.size(); i++ )
		{
		maxLetters = getMaxCount( maxLetters, weapons.get(i)[0].length() );
		}
		
		for( int i = 0; i < weapons.size(); i++ )
		{
			output[i] = weapons.get(i)[0];
			size = weapons.get(i)[0].length();
			numOfTabs = maxLetters - size;
			numOfTabs = Math.ceil(numOfTabs);

			for( int j = 0; j < numOfTabs; j++ )
			{
				output[i] += "\t";
			}
			output[i] += weapons.get(i)[1] + "\t";
			output[i] += weapons.get(i)[2] + "\t";
			output[i] += weapons.get(i)[3] + "\t";
			output[i] += weapons.get(i)[4];
		}
		return output;
	}
	
	private int getMaxCount(int max, int newNum)
	{
		if( newNum % 5 == 0)
			newNum++;
		if( max < newNum )
			max = newNum;
		return max;
	}
}
