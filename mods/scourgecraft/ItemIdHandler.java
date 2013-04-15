package mods.scourgecraft;

public class ItemIdHandler {

	private static int startId = 500;
	private int currentId = startId;
	
	public int getNextID()
	{
		return currentId++;
	}
}
