package mods.scourgecraft.items;

import mods.scourgecraft.ScourgeCraftCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ScourgeArmor extends ItemArmor {
	
	private int ingotID;
	
	public ScourgeArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, int par5) {
		this(par1, par2EnumArmorMaterial, par3, par4);

		this.ingotID = par5;
	}
	
	public ScourgeArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);

		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(ScourgeCraftCore.modid + ":" + this.getUnlocalizedName().replaceAll("item.", ""));
	}
	
	/**
     * Return whether this item is repairable in an anvil.
     */
	@Override
    public boolean getIsRepairable(ItemStack var1, ItemStack var2)
    {
        return this.ingotID == var2.itemID;
    }
	
}
