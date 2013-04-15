package mods.scourgecraft.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mods.scourgecraft.HitEntityListener;
import mods.scourgecraft.ScourgeCraftCore;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ScourgeItemSword extends ItemSword {
	
	public String subText;
	/** The material this tool is made from. */
    protected ScourgeEnumToolMaterial toolMaterial;
    private List hlList;
	private int ingotID;
	private int weaponDamage;
	
	public ScourgeItemSword(int par1, ScourgeEnumToolMaterial par2, int par3) {
		this(par1, par2);
		this.ingotID = par3;
	}
	
	public ScourgeItemSword(int par1, ScourgeEnumToolMaterial par2) {
		super(par1, EnumToolMaterial.EMERALD);
		
		this.hlList = new ArrayList();
		this.toolMaterial = par2;
		this.maxStackSize = 1;
		this.setMaxDamage(par2.getMaxUses());
		this.setCreativeTab(CreativeTabs.tabTools);
		this.subText = null;
		this.weaponDamage = 4 + par2.getDamageVsEntity();
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(ScourgeCraftCore.modid + ":" + this.getUnlocalizedName().replaceAll("item.", ""));
	}
	
	
	public void setSubText(String var1)
    {
        this.subText = var1;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        if (this.subText != null)
        {
            String[] var5 = this.subText.split("-");
            int var6 = var5.length;

            for (int var7 = 0; var7 < var6; ++var7)
            {
                String var8 = var5[var7];
                var3.add("\u00a7" + var8);
            }
        }
    }
    
    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack var1, Block var2)
    {
        return var2.blockID != Block.web.blockID ? 1.5F : 15.0F;
    }

    public void addHitListener(HitEntityListener var1)
    {
        this.hlList.add(var1);
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3)
    {
        Iterator var4 = this.hlList.iterator();

        while (var4.hasNext())
        {
            HitEntityListener var5 = (HitEntityListener)var4.next();
            var5.hitEntity(var1, var2, var3);
        }

        var1.damageItem(1, var3);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack var1, int var2, int var3, int var4, int var5, EntityLiving var6)
    {
        var1.damageItem(2, var6);
        return true;
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return this.weaponDamage;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack var1)
    {
        return EnumAction.block;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack var1)
    {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
        return var1;
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block var1)
    {
        return var1.blockID == Block.web.blockID;
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
