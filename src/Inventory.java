//package src;

import java.util.*;

public class Inventory
{
	LinkedList<Weapons> weapons;
	LinkedList<Armor> armor;
	LinkedList<Items> items;
	
	Player player;
	
	GameEvent gameEvent;
	UiEvent uiEvent;
	InteractionEvent interactionEvent;

	
	StoreState tab;

	public Inventory( Player player, GameEvent gameEvent, UiEvent uiEvent, InteractionEvent interactionEvent )
	{
		this.player = player;
		
		this.gameEvent = gameEvent;
		this.uiEvent = uiEvent;
		this.interactionEvent = interactionEvent;
		
		weapons = new LinkedList();
		armor = new LinkedList();
		items = new LinkedList();
	}

	public LinkedList<Weapons> getWeapons()
	{
		return weapons;
	}

	public LinkedList<Armor> getArmor()
	{
		return armor;
	}

	public LinkedList<Items> getItems()
	{
		return items;
	}

	public void view()
	{
		tab = StoreState.WeaponsTab;
		int choice;
		String header;
		String [] endOptions;
		
		while( true )
		switch( tab )
		{
			//TODO: Add more information to inventory
			case WeaponsTab:
				header = "<Weapon> Armor Items\tYour money: $" + player.getMoney();
				String [] 	weaponsArray = new String[weapons.size()+ 3];
				for(int i = 0; i < weapons.size(); i++){
					//System.out.println(weapons.get(i).getName() + "\t" + weapons.get(i).getCost());
					weaponsArray[i] = weapons.get(i).getName() + "\t" + weapons.get(i).getDescription();
				}
				endOptions = new String[]{
					"Tab left",
					"Tab Right",
					"Leave"
				};
				choice = interactionEvent.getOptionInput( header, weapons, endOptions, 1 );
				System.out.println( weaponsArray[choice] );
				if( choice == weaponsArray.length - 3 )
					tab = tabLeft( tab );
				else if( choice == weaponsArray.length - 2 )
					tab = tabRight( tab );
				else if( choice == weaponsArray.length - 1 )
					return;
				else
					viewItem( weapons.get( choice ) );
				break;
			case ArmorTab:
				header = "Weapon <Armor> Items\tYour money: $" + player.getMoney();
				String [] 	armorArray = new String[armor.size()+ 3];
				for(int i = 0; i < armor.size(); i++){
					//System.out.println(weapons.get(i).getName() + "\t" + weapons.get(i).getCost());
					armorArray[i] = armor.get(i).getName() + "\t" + armor.get(i).getDescription();
				}
				endOptions = new String[]{
					"Tab left",
					"Tab Right",
					"Leave"
				};
				choice = interactionEvent.getOptionInput( header, weapons, endOptions, 1 );
				System.out.println( armorArray[choice] );
				if( choice == armorArray.length - 3 )
					tab = tabLeft( tab );
				else if( choice == armorArray.length - 2 )
					tab = tabRight( tab );
				else if( choice == armorArray.length - 1 )
					return;
		}
	}
	
	private StoreState tabRight(StoreState tab)
	{
		switch( tab )
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
		switch( tab )
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

	private void viewItem(Weapons weapon)
	{
		String temp = "Name: " + weapon.getName()
			+ "\nAttack: " + weapon.getAttack()
			+ "\nDescription: " + weapon.getDescription();
		
		switch( interactionEvent.getOptionInput(temp, new String[]{"Equip", "Drop", "exit"}) )
		{
			case 1:
				player.equip( weapon );
				uiEvent.printWithPause( weapon.name() + " is equiped!\nAttack: " + (player.getAttack() + weapon.getAttack()));
				break;
			case 2:
				weapon = null;
				break;
			case 3:
				return;
		}
	}

	public void addItem(Weapons weapon)
	{
		weapons.add( weapon );
		uiEvent.printWithPause( weapon.name() + " added to your inventory!");
	}

	public void addItem(Armor armor)
	{
		this.armor.add( armor );
		uiEvent.printWithPause( armor.name() + " added to your inventory!");
	}

	public void removeWeapon(int index)
	{
		weapons.remove(index);
	}

	public void removeArmor(int index)
	{
		armor.remove(index);
	}

	public void removeItem(int index)
	{
		items.remove(index);
	}
	
	public Weapons getWeapon( int index )
	{
		return weapons.get( index );
	}

	public Armor getArmor( int index )
	{
		return armor.get( index );
	}

	public Items getItem( int index )
	{
		return items.get( index );
	}
}
