package mods.scourgecraft.items;

import mods.scourgecraft.ScourgeCraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ScourgeItemHoe extends ItemPickaxe {

	/** The material this tool is made from. */
    protected ScourgeEnumToolMaterial toolMaterial;
	private int ingotID;
	
	public ScourgeItemHoe(int par1, ScourgeEnumToolMaterial par2, int par3) {
		this(par1, par2);
		this.ingotID = par3;
	}
	
	public ScourgeItemHoe(int par1, ScourgeEnumToolMaterial par2) {
		super(par1, EnumToolMaterial.EMERALD);
		
		this.maxStackSize = 1;
		this.setMaxDamage(par2.getMaxUses());
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(ScourgeCraftCore.modid + ":" + this.getUnlocalizedName().replaceAll("item.", ""));
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (!var2.canPlayerEdit(var4, var5, var6, var7, var1))
        {
            return false;
        }
        else
        {
            UseHoeEvent var11 = new UseHoeEvent(var2, var1, var3, var4, var5, var6);

            if (MinecraftForge.EVENT_BUS.post(var11))
            {
                return false;
            }
            else if (var11.getResult() == Result.ALLOW)
            {
                var1.damageItem(1, var2);
                return true;
            }
            else
            {
                int var12 = var3.getBlockId(var4, var5, var6);
                int var13 = var3.getBlockId(var4, var5 + 1, var6);

                if ((var7 == 0 || var13 != 0 || var12 != Block.grass.blockID) && var12 != Block.dirt.blockID)
                {
                    return false;
                }
                else
                {
                    Block var14 = Block.tilledField;
                    var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), var14.stepSound.getStepSound(), (var14.stepSound.getVolume() + 1.0F) / 2.0F, var14.stepSound.getPitch() * 0.8F);

                    if (var3.isRemote)
                    {
                        return true;
                    }
                    else
                    {
                        var3.setBlock(var4, var5, var6, var14.blockID);
                        var1.damageItem(1, var2);
                        return true;
                    }
                }
            }
        }
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack var1, ItemStack var2)
    {
        return this.ingotID == var2.itemID;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }
}