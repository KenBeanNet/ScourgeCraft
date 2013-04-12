package mods.scourgecraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.scourgecraft.ScourgeCraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ScourgeItemAxe extends ItemAxe {

	/** The material this tool is made from. */
    protected ScourgeCraftEnumToolMaterial toolMaterial;
	private int ingotID;
	
	public ScourgeItemAxe(int par1, ScourgeCraftEnumToolMaterial par2, int par3) {
		this(par1, par2);
		this.ingotID = par3;
	}
	
	public ScourgeItemAxe(int par1, ScourgeCraftEnumToolMaterial par2) {
		super(par1, EnumToolMaterial.EMERALD);
		this.toolMaterial = par2;
		this.setMaxDamage(par2.getMaxUses());
		this.efficiencyOnProperMaterial = par2.getEfficiencyOnProperMaterial();
		this.ingotID = 0;
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
	@Override
    public float getStrVsBlock(ItemStack var1, Block var2)
    {
        if (var2 != null && var2.blockMaterial == Material.wood)
        {
            return this.efficiencyOnProperMaterial;
        }
        else
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
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return 2 + this.toolMaterial.getDamageVsEntity();
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack var1, ItemStack var2)
    {
        return this.ingotID == var2.itemID;
    }
}
