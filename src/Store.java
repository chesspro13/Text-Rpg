//package src;

import java.io.*;
import java.util.*;

public class Store
{
	Player player;
	GameEvent gameEvent;
	UiEvent uiEvent;
	InteractionEvent interactionEvent;
	Scanner scanner;

	StoreState storeState;
	StoreState lastState;

	boolean forceDebug;

	public Store(Player player, GameEvent gameEvent, UiEvent uiEvent, InteractionEvent interactionEvent)
	{
		this.player = player;
		this.gameEvent = gameEvent;
		this.uiEvent = uiEvent;
		this.interactionEvent = interactionEvent;

		storeState = storeState.Welcome;
		lastState = storeState.Welcome;
	}

	public void startStore(boolean forceDebug)
	{
		this.forceDebug = forceDebug;
//		try
//		{
//			//scanner = new Scanner(new File(StoryLine.store1.getFileLocation()));
//			update();
//		}
//		catch (FileNotFoundException e)
//		{
//			e.printStackTrace();
//			int h = 4 / 0;
//			return;
//		}

		update();
	}

	private void update()
	{

		switch(storeState)
		{
			case Welcome:
				uiEvent.printWithPause("Welcome to my store");
				if(forceDebug)
				{
					player.buyItem(Weapons.Mace);
					player.buyItem(Armor.Cloth);
				}
				switch(interactionEvent.getOptionInput("What would you like to do?", new String[]{"Buy", "Sell", "Leave"}))
				{
					case 1:
						lastState = storeState;
						this.storeState = storeState.Buying;
						break;
					case 2:
						lastState = storeState;
						this.storeState = storeState.Selling;
						break;
					case 3: //bake
						lastState = storeState;
						this.storeState = storeState.Leaving;
						break;
				}
				break;
			case Buying:
				buying();
				break;
			case Selling:
				selling();
				break;
			case Leaving:
				uiEvent.printWithPause("Good fucking job. You win the game.");
				this.storeState = null;
				break;
		}

		boolean temp = false;
		do
		{
			//temp = followScript(script);
		}while( temp );
	}

	private void buying()
	{
		StoreState tab = StoreState.WeaponsTab;
		LinkedList items = new LinkedList();
		LinkedList<String []> w = new LinkedList();

		while(true)
		{
			String temp;
			int choice;

			switch(tab)
			{
				case WeaponsTab:
					temp = "<Weapons> Armor Items\tYour money: $" + player.getMoney();

					w.add(new String [] { Weapons.Stick.getName(), Weapons.Stick.getCost() + "\t" + Weapons.Stick.getAttack() + "\t" + Weapons.Stick.getHandsRequired() + "\t" + Weapons.Stick.getDescription()});
					w.add(new String [] { Weapons.WoodenSword.getName(), Weapons.WoodenSword.getCost() + "\t" + Weapons.WoodenSword.getAttack() + "\t" + Weapons.WoodenSword.getHandsRequired() + "\t" + Weapons.WoodenSword.getDescription()});
					w.add(new String [] { Weapons.Dagger.getName(), Weapons.Dagger.getCost() + "\t" + Weapons.Dagger.getAttack() + "\t" + Weapons.Dagger.getHandsRequired() + "\t" + Weapons.Dagger.getDescription()});
					w.add(new String []	{ Weapons.ShortSword.getName(), Weapons.ShortSword.getCost() + "\t" + Weapons.ShortSword.getAttack() + "\t" + Weapons.ShortSword.getHandsRequired() + "\t" + Weapons.ShortSword.getDescription()});
					w.add(new String []	{ Weapons.Broadsword.getName(), Weapons.Broadsword.getCost() + "\t" + Weapons.Broadsword.getAttack() + "\t" + Weapons.Broadsword.getHandsRequired() + "\t" + Weapons.Broadsword.getDescription()});
					w.add(new String []	{ Weapons.Bow.getName(), Weapons.Bow.getCost() + "\t" + Weapons.Bow.getAttack() + "\t" + Weapons.Bow.getHandsRequired() + "\t" + Weapons.Bow.getDescription()});
					w.add(new String []	{ Weapons.Staff.getName(), Weapons.Staff.getCost() + "\t" + Weapons.Staff.getAttack() + "\t" + Weapons.Staff.getHandsRequired() + "\t" + Weapons.Staff.getDescription()});
					w.add(new String []	{ Weapons.Mace.getName(), Weapons.Mace.getCost() + "\t" + Weapons.Mace.getAttack() + "\t" + Weapons.Mace.getHandsRequired() + "\t" + Weapons.Mace.getDescription()});
					w.add(new String []	{ Weapons.Hammer.getName(), Weapons.Hammer.getCost() + "\t" + Weapons.Hammer.getAttack() + "\t" + Weapons.Hammer.getHandsRequired() + "\t" + Weapons.Hammer.getDescription()});
					w.add(new String []	{ Weapons.Crossbow.getName(), Weapons.Crossbow.getCost() + "\t" + Weapons.Crossbow.getAttack() + "\t" + Weapons.Crossbow.getHandsRequired() + "\t" + Weapons.Crossbow.getDescription()});
					w.add(new String []	{ Weapons.Greatsword.getName(), Weapons.Greatsword.getCost() + "\t" + Weapons.Greatsword.getAttack() + "\t" + Weapons.Greatsword.getHandsRequired() + "\t" + Weapons.Greatsword.getDescription()});
					String[] endOptions = new String[]{
						"View Inventory",
						"Tab left",
						"Tab Right",
						"Leave"
					};

					System.out.println("fgghhcdfhhvethh");
					///*String [] output = 

					choice = interactionEvent.getOptionInput(temp, w, endOptions, 'a');
					/*System.out.println(choice);
					if(choice == weaponArray.length - 3)
					{
						player.viewInventory();
						storeState = lastState;
					}
					else if(choice == weaponArray.length - 2)
						tab = tabLeft(tab);
					else if(choice == weaponArray.length - 1)
						tab = tabRight(tab);
					else if(choice == weaponArray.length)
					{
						storeState = StoreState.Welcome;
						return;
					}
					else	
						switch(choice)
						{
							case 1:	if(!player.buyItem(Weapons.Stick))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 2:	if(!player.buyItem(Weapons.WoodenSword))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 3:	if(!player.buyItem(Weapons.Dagger))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 4:	if(!player.buyItem(Weapons.ShortSword))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;	
							case 5:	if(!player.buyItem(Weapons.Broadsword))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 6:	if(!player.buyItem(Weapons.Bow))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 7:	if(!player.buyItem(Weapons.Staff))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 8:	if(!player.buyItem(Weapons.Mace))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 9:	if(!player.buyItem(Weapons.Hammer))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 10:	if(!player.buyItem(Weapons.Crossbow))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 11:	if(!player.buyItem(Weapons.Greatsword))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;

						}*/
					break;
				case ArmorTab:
					temp = "Weapons <Armor> Items\tYour money: $" + player.getMoney();
					String [] armorArray = new String[]{
						Armor.Leaf.getName() + "\t" + Armor.Leaf.getCost() + "\t" + Armor.Leaf.getDeffence() + "\t" + Armor.Leaf.getDescription(),
						Armor.Cloth.getName() + "\t" + Armor.Cloth.getCost() + "\t" + Armor.Cloth.getDeffence() + "\t" + Armor.Cloth.getDescription(),
						"View Inventory",
						"Tab left",
						"Tab Right",
						"Leave"
					}; 
					choice = interactionEvent.getOptionInput(temp, armorArray);
					System.out.println(choice);
					if(choice == armorArray.length - 3)
						player.viewInventory();
					else if(choice == armorArray.length - 2)
						tab = tabLeft(tab);
					else if(choice == armorArray.length - 1)
						tab = tabRight(tab);
					else if(choice == armorArray.length)
					{
						storeState = StoreState.Welcome;
						return;
					}
					else	
						switch(choice)
						{
							case 1:	if(!player.buyItem(Armor.Leaf))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 2:	if(!player.buyItem(Armor.Cloth))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;


						}
					break;
				case ItemsTab:
					temp = "Weapons Armor <Items>\tYour money: $" + player.getMoney();
					String [] itemsArray = new String[]{
						"View Inventory",
						"Tab left",
						"Tab Right",
						"Leave"
					};
					choice = interactionEvent.getOptionInput(temp, itemsArray);
					System.out.println(choice);
					if(choice == itemsArray.length - 3)
						player.viewInventory();
					else if(choice == itemsArray.length - 2)
						tab = tabLeft(tab);
					else if(choice == itemsArray.length - 1)
						tab = tabRight(tab);
					else if(choice == itemsArray.length)
					{
						storeState = StoreState.Welcome;
						return;
					}
					else	
						switch(choice)
						{
							case 1:	if(!player.buyItem(Armor.Leaf))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;
							case 2:	if(!player.buyItem(Armor.Cloth))
								{	uiEvent.printWithPause("You do not have enough money for this item.");	}	break;


						}
					break;
			}
		}
	}

	private void selling()
	{
		Inventory inventory = player.getInventory();

		LinkedList<Weapons> weapons;
		LinkedList<Armor> armor;
		LinkedList<Item> items;

		StoreState tab = StoreState.WeaponsTab;
		String temp;
		int choice;
		String [] endItems;
		int arraySize;

		while(true)
		{
			weapons = inventory.getWeapons();
			armor = inventory.getArmor();
			items = inventory.getItems();
			switch(tab)
			{
				case WeaponsTab:
					temp = "<Weapon> Armor Items\tYour money: $" + player.getMoney();
					arraySize = weapons.size() + 3;
					String [] 	weaponsArray = new String[ arraySize ];
					if(weapons.size() > 0)
						for(int i = 0; i < weapons.size(); i++)
						{
							//System.out.println(weapons.get(i).getName() + "\t" + weapons.get(i).getCost());
							weaponsArray[i] = weapons.get(i).getName() + "\t" + weapons.get(i).getCost();
						}
					endItems = new String[]{
						"Tab left",
						"Tab Right",
						"Leave"
					};
					choice = interactionEvent.getOptionInput(temp, weapons, endItems, 1);
					choice--;
					System.out.println(choice + " == " + weaponsArray[choice]);
					if(choice == arraySize - 3)
						tab = tabLeft(tab);
					else if(choice == arraySize - 2)
						tab = tabRight(tab);
					else if(choice == arraySize - 1)
					{
						storeState = StoreState.Welcome;
						return;
					}
					if(weapons.size() > 0)
					{
						int confirm = interactionEvent.getOptionInput("Are you sure?", new String[]{"yes", "no"});
						if(--confirm == 0)
							player.sellWeapon(choice);
					}
					break;
				case ArmorTab:
					temp = "Weapon <Armor> Items\tYour money: $" + player.getMoney();
					arraySize = armor.size() + 3;
					String [] 	armorArray = new String[ arraySize ];
					if(armor.size() > 0)
						for(int i = 0; i < armor.size(); i++)
						{
							//System.out.println(weapons.get(i).getName() + "\t" + weapons.get(i).getCost());
							armorArray[i] = armor.get(i).getName() + "\t" + armor.get(i).getCost();
						}
					endItems = new String[]{
						"Tab left",
						"Tab Right",
						"Leave"
					};
					choice = interactionEvent.getOptionInput(temp, 1, armor, endItems);
					choice--;
					System.out.println(choice + " == " + armorArray[choice]);
					if(choice == arraySize - 3)
						tab = tabLeft(tab);
					else if(choice == arraySize - 2)
						tab = tabRight(tab);
					else if(choice == arraySize - 1)
					{
						storeState = StoreState.Welcome;
						return;
					}
					if(armor.size() > 0)
					{
						int confirm = interactionEvent.getOptionInput("Are you sure?", new String[]{"yes", "no"});
						if(--confirm == 0)
							player.sellArmor(choice);
					}
					break;
				case ItemsTab:
					temp = "Weapon Armor <Items>\tYour money: $" + player.getMoney();
					arraySize = items.size() + 3;
					String [] 	itemsArray = new String[ arraySize ];
					if(items.size() > 0)
						for(int i = 0; i < items.size(); i++)
						{
							//System.out.println(weapons.get(i).getName() + "\t" + weapons.get(i).getCost());
							itemsArray[i] = items.get(i).getName() + "\t" + items.get(i).getCost();
						}
					endItems = new String[]{
						"Tab left",
						"Tab Right",
						"Leave"
					};
					choice = interactionEvent.getOptionInput(temp, items, 1, endItems);
					choice--;
					System.out.println(choice + " == " + itemsArray[choice]);
					if(choice == arraySize - 3)
						tab = tabLeft(tab);
					else if(choice == arraySize - 2)
						tab = tabRight(tab);
					else if(choice == arraySize - 1)
					{
						storeState = StoreState.Welcome;
						return;
					}
					if(items.size() > 0)
					{
						int confirm = interactionEvent.getOptionInput("Are you sure?", new String[]{"yes", "no"});
						if(--confirm == 0)
							player.sellItem(choice);
					}
					break;
			}
		}
	}



	private StoreState tabRight(StoreState tab)
	{
		switch(tab)
		{
			case WeaponsTab:
				return StoreState.ArmorTab;
			case ArmorTab:
				return StoreState.ItemsTab;
			case ItemsTab:
				return StoreState.WeaponsTab;
			default:
				return StoreState.WeaponsTab;
		}
	}

	private StoreState tabLeft(StoreState tab)
	{
		switch(tab)
		{
			case WeaponsTab:
				return StoreState.ItemsTab;
			case ArmorTab:
				return StoreState.WeaponsTab;
			case ItemsTab:
				return StoreState.ArmorTab;
			default:
				return StoreState.WeaponsTab;
		}
	}

	public boolean followScript(Scanner script)
	{
		if(script.hasNext())
			switch(script.next())
			{

			}
		return true;
	}
}


