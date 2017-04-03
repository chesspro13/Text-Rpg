//package src;

public class Item
{
	private Weapons weapon;
	
	private String name;
	private int value;
	private String description;
	
	public Item( Weapons weapon )
	{
		this.name = weapon.getName();
		this. value = weapon.getCost();
		this.description = weapon.getDescription();
	}

	public String getName()
	{
		return name;
	}

	public int getValue()
	{
		return value;
	}

	public String getDescription()
	{
		return description;
	}
	
	
}
