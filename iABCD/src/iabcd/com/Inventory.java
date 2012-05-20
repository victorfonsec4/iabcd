package iabcd.com;

public class Inventory 
{
	private int numItens;
	private int itemWidth = 68;
	private int itemHeight = 68;
	
	
	Inventory()
	{
		numItens = 0;
	}
	
	void addItem()
	{
		numItens++;
	}

	public int getNumItens() {
		return numItens;
	}

	public void setNumItens(int numItens) {
		this.numItens = numItens;
	}

	public int getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(int itemWidth) {
		this.itemWidth = itemWidth;
	}

	public int getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(int itemHeight) {
		this.itemHeight = itemHeight;
	}
	

}
