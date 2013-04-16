package mods.scourgecraft;

import cpw.mods.fml.common.registry.GameRegistry;

import mods.scourgecraft.items.ItemChestChanger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public enum ChestChangerType {
    IRONGOLD(ChestType.IRON, ChestType.WOOD, "ironGoldUpgrade", "Iron to Gold Chest Upgrade", "mmm", "msm", "mmm");

    private ChestType source;
    private ChestType target;
    public String itemName;
    public String descriptiveName;
    private ItemChestChanger item;
    private String[] recipe;

    private ChestChangerType(ChestType source, ChestType target, String itemName, String descriptiveName, String... recipe)
    {
        this.source = source;
        this.target = target;
        this.itemName = itemName;
        this.descriptiveName = descriptiveName;
        this.recipe = recipe;
    }

    public boolean canUpgrade(ChestType from)
    {
        return from == this.source;
    }

    public int getTarget()
    {
        return this.target.ordinal();
    }

    public ItemChestChanger buildItem(Configuration cfg, int id)
    {
        int itemId = cfg.getItem(itemName, id).getInt(id);
        item = new ItemChestChanger(itemId, this);
        GameRegistry.registerItem(item, itemName);
        return item;
    }

    public void addRecipes()
    {
        for (String sourceMat : source.getMatList())
        {
            for (String targetMat : target.getMatList())
            {
                Object targetMaterial = ChestType.translateOreName(targetMat);
                Object sourceMaterial = ChestType.translateOreName(sourceMat);
                ChestType.addRecipe(new ItemStack(item), recipe, 'm', targetMaterial, 's', sourceMaterial, 'G', Block.glass, 'O', Block.obsidian);
            }
        }
    }

    public static void buildItems(Configuration cfg, int defaultId)
    {
        for (ChestChangerType type : values())
        {
            type.buildItem(cfg, defaultId++);
        }
    }

    public static void generateRecipes()
    {
        for (ChestChangerType item : values())
        {
            item.addRecipes();
        }
    }
}