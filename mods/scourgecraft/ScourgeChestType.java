package mods.scourgecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.scourgecraft.entities.chests.TileEntityIronChest;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.ShapedOreRecipe;

public enum ScourgeChestType {

	IRON(54, 9, true, "Iron Chest", "ironchest.png", 0, Arrays.asList("ingotIron", "ingotRefinedIron"), TileEntityIronChest.class, "mmmmPmmmm", "mGmG3GmGm");
	
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
        this.modelTexture = "/mods/scourgecraft/textures/model/" + modelTexture;
        this.textureRow = textureRow;
        this.clazz = clazz;
        this.recipes = recipes;
        this.matList = new ArrayList<String>();
        matList.addAll(mats);
    }
    
    
}
