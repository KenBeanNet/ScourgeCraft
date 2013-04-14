package mods.scourgecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.scourgecraft.blocks.ScourgeBlockChest;
import mods.scourgecraft.entities.chests.TileEntityDiamondChest;
import mods.scourgecraft.entities.chests.TileEntityIronChest;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.ShapedOreRecipe;

public enum ScourgeChestType {

	DIAMOND(108, 12, true, "Diamond Chest", "diamondchest.png", 2, Arrays.asList("gemDiamond"), TileEntityDiamondChest.class, "GGGmPmGGG", "GGGG4Gmmm");

	int size;
    private int rowLength;
    public String friendlyName;
    private boolean tieredChest;
    private String modelTexture;
    private int textureRow;
    public Class<? extends TileEntityIronChest> clazz;
    private String[] recipes;
    private ArrayList<String> matList;

    ScourgeChestType(int size, int rowLength, boolean tieredChest, String friendlyName, String modelTexture, int textureRow, List<String> mats,
            Class<? extends TileEntityIronChest> clazz, String... recipes)
    {
        this.size = size;
        this.rowLength = rowLength;
        this.tieredChest = tieredChest;
        this.friendlyName = friendlyName;
        this.modelTexture = "/mods/ironchest/textures/model/" + modelTexture;
        this.textureRow = textureRow;
        this.clazz = clazz;
        this.recipes = recipes;
        this.matList = new ArrayList<String>();
        matList.addAll(mats);
    }
    
    public String getModelTexture()
    {
        return modelTexture;
    }

    public int getTextureRow()
    {
        return textureRow;
    }

    public static TileEntityIronChest makeEntity(int metadata)
    {
        // Compatibility
        int chesttype = validateMeta(metadata);
        if (chesttype == metadata)
        {
            try
            {
                TileEntityIronChest te = values()[chesttype].clazz.newInstance();
                return te;
            }
            catch (InstantiationException e)
            {
                // unpossible
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // unpossible
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void registerTranslations()
    {
    }

    public static void registerBlocksAndRecipes(ScourgeBlockChest blockResult)
    {
        ItemStack previous = new ItemStack(Block.chest);
        for (ScourgeChestType typ : values())
        {
            generateRecipesForType(blockResult, previous, typ);
            ItemStack chest = new ItemStack(blockResult, 1, typ.ordinal());
            if (typ.isValidForCreativeMode()) GameRegistry.registerCustomItemStack(typ.friendlyName, chest);
            if (typ.tieredChest) previous = chest;
        }
    }

    public static void generateRecipesForType(ScourgeBlockChest blockResult, Object previousTier, ScourgeChestType type)
    {
        for (String recipe : type.recipes)
        {
            String[] recipeSplit = new String[] { recipe.substring(0, 3), recipe.substring(3, 6), recipe.substring(6, 9) };
            Object mainMaterial = null;
            for (String mat : type.matList)
            {
                mainMaterial = translateOreName(mat);
                addRecipe(new ItemStack(blockResult, 1, type.ordinal()), recipeSplit,
                        'm', mainMaterial, 'P', previousTier, /* previous tier of chest */
                        'G', Block.glass, 'C', Block.chest,
                        '0', new ItemStack(blockResult, 1, 0), /* Iron Chest */
                        '1', new ItemStack(blockResult, 1, 1), /* Gold Chest */
                        '2', new ItemStack(blockResult, 1, 2), /* Diamond Chest */
                        '3', new ItemStack(blockResult, 1, 3), /* Copper Chest */
                        '4', new ItemStack(blockResult, 1, 4)/* Silver Chest */
                );
            }
        }
    }

    public static Object translateOreName(String mat)
    {
        if (mat == "ingotIron")
        {
            return Item.ingotIron;
        }
        else if (mat == "ingotGold")
        {
            return Item.ingotGold;
        }
        else if (mat == "gemDiamond")
        {
            return Item.diamond;
        }
        else if (mat == "blockGlass")
        {
            return Block.glass;
        }
        else if (mat == "obsidian")
        {
            return Block.obsidian;
        }
        return mat;
    }

    public static void addRecipe(ItemStack is, Object... parts)
    {
        ShapedOreRecipe oreRecipe = new ShapedOreRecipe(is, parts);
        GameRegistry.addRecipe(oreRecipe);
    }

    public int getRowCount()
    {
        return size / rowLength;
    }

    public int getRowLength()
    {
        return rowLength;
    }

    public boolean isTransparent()
    {
        return this == CRYSTAL;
    }

    public List<String> getMatList()
    {
        return matList;
    }

    public static int validateMeta(int i)
    {
        if (i < values().length && values()[i].size > 0)
        {
            return i;
        }
        else
        {
            return 0;
        }
    }

    public boolean isValidForCreativeMode()
    {
        return validateMeta(ordinal()) == ordinal();
    }

    public boolean isExplosionResistant()
    {
        return this == OBSIDIAN;
    }

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    @SideOnly(Side.CLIENT)
    public void makeIcons(IconRegister par1IconRegister)
    {
        if (isValidForCreativeMode())
        {
            icons = new Icon[3];
            int i = 0;
            for (String s : sideNames)
            {
                icons[i++] = par1IconRegister.registerIcon(String.format("ironchest:%s_%s",name().toLowerCase(),s));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side)
    {

        return icons[sideMapping[side]];
    }

    private static String[] sideNames = { "top", "front", "side" };
    private static int[] sideMapping = { 0, 0, 2, 1, 2, 2, 2 };
}
