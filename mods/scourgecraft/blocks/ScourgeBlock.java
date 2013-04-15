package mods.scourgecraft.blocks;

import java.util.Random;

import mods.scourgecraft.ScourgeCraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class ScourgeBlock extends Block {

	
	public ScourgeBlock(int id) {
		super(id, Material.iron);
		
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void registerIcons(IconRegister reg)
	{
		this.blockIcon = reg.registerIcon(ScourgeCraftCore.modid + ":" + this.getUnlocalizedName2());
	}
	
	 /**
     * Returns the ID of the items to drop on destruction.
     */
	@Override
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.blockID;
    }
	
	/**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
	@Override
    public int damageDropped(int var1)
    {
        return var1;
    }
}
