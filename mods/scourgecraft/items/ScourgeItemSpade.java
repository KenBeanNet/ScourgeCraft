package mods.scourgecraft.items;

import mods.scourgecraft.ScourgeCraftCore;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ScourgeItemSpade extends ItemSpade {

	/** The material this tool is made from. */
    protected ScourgeEnumToolMaterial toolMaterial;
	private int ingotID;
	
	public ScourgeItemSpade(int par1, ScourgeEnumToolMaterial par2, int par3) {
		this(par1, par2);
		this.ingotID = par3;
	}
	
	public ScourgeItemSpade(int par1, ScourgeEnumToolMaterial par2) {
		super(par1, EnumToolMaterial.EMERALD);
		
        this.efficiencyOnProperMaterial = 4.0F;
        this.toolMaterial = par2;
        this.setMaxDamage(par2.getMaxUses());
        this.efficiencyOnProperMaterial = par2.getEfficiencyOnProperMaterial();
        this.damageVsEntity = 2 + par2.getDamageVsEntity();
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(ScourgeCraftCore.modid + ":" + this.getUnlocalizedName().replaceAll("item.", ""));
	}
	
	/**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack var1, Block var2)
    {
        for (int var3 = 0; var3 < blocksEffectiveAgainst.length; ++var3)
        {
            if (blocksEffectiveAgainst[var3] == var2)
            {
                return this.efficiencyOnProperMaterial;
            }
        }

        return 1.0F;
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return this.damageVsEntity;
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block var1)
    {
        return var1 == Block.snow;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack var1, ItemStack var2)
    {
        return this.ingotID == var2.itemID;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }
}
