package mods.scourgecraft.items;

import mods.scourgecraft.ScourgeCraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ScourgeItemPickAxe extends ItemPickaxe {

	/** The material this tool is made from. */
    protected ScourgeEnumToolMaterial toolMaterial;
	private int ingotID;
	
	public ScourgeItemPickAxe(int par1, ScourgeEnumToolMaterial par2, int par3) {
		this(par1, par2);
		this.ingotID = par3;
	}
	
	public ScourgeItemPickAxe(int par1, ScourgeEnumToolMaterial par2) {
		super(par1, EnumToolMaterial.EMERALD);
		this.toolMaterial = par2;
		this.setMaxDamage(par2.getMaxUses());
		this.efficiencyOnProperMaterial = par2.getEfficiencyOnProperMaterial();
		this.damageVsEntity = 2 + par2.getDamageVsEntity();
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(ScourgeCraftCore.modid + ":" + this.getUnlocalizedName().replaceAll("item.", ""));
	}
	
	/**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block var1)
    {
        return var1 == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (var1 != Block.blockDiamond && var1 != Block.oreDiamond ? (var1 != Block.blockGold && var1 != Block.oreGold ? (var1 != Block.blockIron && var1 != Block.oreIron ? (var1 != Block.blockLapis && var1 != Block.oreLapis ? (var1 != Block.oreRedstone && var1 != Block.oreRedstoneGlowing ? (var1.blockMaterial == Material.rock ? true : var1.blockMaterial == Material.iron) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack var1, Block var2)
    {
        if (var2 != null && (var2.blockMaterial == Material.iron || var2.blockMaterial == Material.rock))
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
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return this.damageVsEntity;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack var1, ItemStack var2)
    {
        return this.ingotID == var2.itemID;
    }
}