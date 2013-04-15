package mods.scourgecraft.entities.chests;

import mods.scourgecraft.ScourgeChestType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityIronChest extends TileEntity implements IInventory {
    private int ticksSinceSync = -1;
    public float prevLidAngle;
    public float lidAngle;
    private int numUsingPlayers;
    private ScourgeChestType type;
    public ItemStack[] chestContents;
    private ItemStack[] topStacks;
    private byte facing;
    private boolean inventoryTouched;
    private boolean hadStuff;

    public TileEntityIronChest()
    {
        this(ScourgeChestType.IRON);
    }

    protected TileEntityIronChest(ScourgeChestType type)
    {
        super();
        this.type = type;
        this.chestContents = new ItemStack[getSizeInventory()];
        this.topStacks = new ItemStack[8];
    }

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}
}