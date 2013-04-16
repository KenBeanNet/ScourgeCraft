package mods.scourgecraft;

import java.util.EnumSet;


import mods.scourgecraft.blocks.ScourgeBlock;
import mods.scourgecraft.blocks.ScourgeBlockChest;
import mods.scourgecraft.items.ItemChest;
import mods.scourgecraft.items.ScourgeArmor;
import mods.scourgecraft.items.ScourgeItem;
import mods.scourgecraft.items.ScourgeItemAxe;
import mods.scourgecraft.items.ScourgeItemHoe;
import mods.scourgecraft.items.ScourgeItemPickAxe;
import mods.scourgecraft.items.ScourgeItemSpade;
import mods.scourgecraft.items.ScourgeItemSword;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(
        modid = ScourgeCraftCore.modid,
        name = "ScourgeCraft Core",
        version = "0.0.1"
)
@NetworkMod(
        channels = {"ScourgeCraft"},
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class
)
public class ScourgeCraftCore
{
    @Mod.Instance("ScourgeCraft")
    public static ScourgeCraftCore instance;
    
	public static final String modid = "ScourgeCraft";
	private static final int blockTotalsPerType = 5;
	private static final int itemTotalsPerType = 15;
	private static final int oreBlocksPerType = 3;
	private static final int oreItemsPerType = 3;
	private static final int oreToolsPerType = 5;
	private static final int oreArmorsPerType = 4;
	
    
    @SidedProxy(clientSide = "mods.scourgecraft.client.ClientProxy", serverSide = "mods.scourgecraft.CommonProxy")
    public static CommonProxy proxy;
    
    //Ores
    Block[] oresBlocks = new Block[OresType.names.length * oreBlocksPerType];
    int[] oresBlocksIDs = new int[OresType.names.length * oreBlocksPerType];
    
    Item[] oresItems = new Item[OresType.names.length * oreItemsPerType];
    int[] oresItemsIDs = new int[OresType.names.length * oreItemsPerType];
    
    Item[] oresTools = new Item[OresType.names.length * oreToolsPerType];
    int[] oresToolsIDs = new int[OresType.names.length * oreToolsPerType];
    
    Item[] oresArmors = new Item[OresType.names.length * oreArmorsPerType];
    int[] oresArmorsIDs = new int[OresType.names.length * oreArmorsPerType];

    private static final int StartIdBlock = 500;
    private static final int StartIdItem = 4000;
    
    public static ScourgeBlockChest chestBlock;
    public static int chestBlockId;
    
    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
    	
    	//Ores Load
    	for (int var3 = 0; var3 < OresType.names.length; var3++)
    	{
    		if (OresType.defaultIsMetal[var3]) {
    			oresBlocksIDs[(var3 * oreBlocksPerType) + 0] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Ore", StartIdBlock + (var3 * blockTotalsPerType) + 0).getInt();
    		}
    		oresBlocksIDs[(var3 * oreBlocksPerType) + 1] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Block", StartIdBlock + (var3 * blockTotalsPerType) + 1).getInt();
    		oresBlocksIDs[(var3 * oreBlocksPerType) + 2] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Brick", StartIdBlock + (var3 * blockTotalsPerType) + 2).getInt();
    		oresItemsIDs[(var3 * oreItemsPerType) + 0] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Dust", StartIdItem + (var3 * itemTotalsPerType) + 0).getInt();
    		oresItemsIDs[(var3 * oreItemsPerType) + 1] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Ingot", StartIdItem + (var3 * itemTotalsPerType) + 1).getInt();
    		if (OresType.defaultIsMetal[var3])	{
    			oresItemsIDs[(var3 * oreItemsPerType) + 2] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Nugget", StartIdItem + (var3 * itemTotalsPerType) + 2).getInt();
    		}
    		

        	if (OresType.defaultHasTools[var3]) {
    			oresToolsIDs[(var3 * oreToolsPerType) + 0] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Axe", StartIdItem + (var3 * itemTotalsPerType) + 3).getInt();
    			oresToolsIDs[(var3 * oreToolsPerType) + 1] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Hoe", StartIdItem + (var3 * itemTotalsPerType) + 4).getInt();
    			oresToolsIDs[(var3 * oreToolsPerType) + 2] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " PickAxe", StartIdItem + (var3 * itemTotalsPerType) + 5).getInt();
    			oresToolsIDs[(var3 * oreToolsPerType) + 3] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Spade", StartIdItem + (var3 * itemTotalsPerType) + 6).getInt();
    			oresToolsIDs[(var3 * oreToolsPerType) + 4] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Sword", StartIdItem + (var3 * itemTotalsPerType) + 7).getInt();
    		}
    		
    		if (OresType.defaultHasArmors[var3]) {
    			oresArmorsIDs[(var3 * oreArmorsPerType) + 0] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Helmet", StartIdItem + (var3 * itemTotalsPerType) + 8).getInt();
    			oresArmorsIDs[(var3 * oreArmorsPerType) + 1] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Plate", StartIdItem + (var3 * itemTotalsPerType) + 9).getInt();
    			oresArmorsIDs[(var3 * oreArmorsPerType) + 2] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Legs", StartIdItem + (var3 * itemTotalsPerType) + 10).getInt();
    			oresArmorsIDs[(var3 * oreArmorsPerType) + 3] = config.get(OresType.names[var3] + " IDs", OresType.names[var3] + " Boots", StartIdItem + (var3 * itemTotalsPerType) + 11).getInt();
    		}
    	}
    	
    	//Chests Load
    	chestBlockId = config.getBlock("Scourge Chests", 750).getInt();
    	
    	
    	config.save();
    }
    
    @Mod.Init
    public void load(FMLInitializationEvent var1)
    {
    	//Ores
    	for (int var3 = 0; var3 < OresType.names.length; var3++)
    	{
    		if (OresType.defaultIsMetal[var3])
    			oresBlocks[(var3 * oreBlocksPerType) + 0] = new ScourgeBlock(oresBlocksIDs[(var3 * oreBlocksPerType) + 0]).setHardness(2.0F).setResistance(0.1F).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Ore");
    		oresBlocks[(var3 * oreBlocksPerType) + 1] = new ScourgeBlock(oresBlocksIDs[(var3 * oreBlocksPerType) + 1]).setHardness(5.0F).setResistance(0.1F).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Block");
    		oresBlocks[(var3 * oreBlocksPerType) + 2] = new ScourgeBlock(oresBlocksIDs[(var3 * oreBlocksPerType) + 2]).setHardness(5.0F).setResistance(10.0F).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Brick");
    		oresItems[(var3 * oreItemsPerType) + 0] = new ScourgeItem(oresItemsIDs[(var3 * oreItemsPerType) + 0]).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Dust");
    		oresItems[(var3 * oreItemsPerType) + 1] = new ScourgeItem(oresItemsIDs[(var3 * oreItemsPerType) + 1]).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Ingot");
    		if (OresType.defaultIsMetal[var3])
    			oresItems[(var3 * oreItemsPerType) + 2] = new ScourgeItem(oresItemsIDs[(var3 * oreItemsPerType) + 2]).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Nugget");
    		
    		if (OresType.defaultHasTools[var3]) {
    			oresTools[(var3 * oreToolsPerType) + 0] = new ScourgeItemAxe(oresToolsIDs[(var3 * oreToolsPerType) + 0], OresType.toolEnum[var3]).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Axe");
    			oresTools[(var3 * oreToolsPerType) + 1] = new ScourgeItemHoe(oresToolsIDs[(var3 * oreToolsPerType) + 1], OresType.toolEnum[var3]).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Hoe");
    			oresTools[(var3 * oreToolsPerType) + 2] = new ScourgeItemPickAxe(oresToolsIDs[(var3 * oreToolsPerType) + 2], OresType.toolEnum[var3]).setUnlocalizedName(OresType.names[var3].toLowerCase() + "PickAxe");
    			oresTools[(var3 * oreToolsPerType) + 3] = new ScourgeItemSpade(oresToolsIDs[(var3 * oreToolsPerType) + 3], OresType.toolEnum[var3]).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Spade");
    			oresTools[(var3 * oreToolsPerType) + 4] = new ScourgeItemSword(oresToolsIDs[(var3 * oreToolsPerType) + 4], OresType.toolEnum[var3]).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Sword");
    		}
    		
    		if (OresType.defaultHasArmors[var3]) {
    			oresArmors[(var3 * oreArmorsPerType) + 0] = new ScourgeArmor(oresArmorsIDs[(var3 * oreArmorsPerType) + 0], OresType.defaultArmorEnums[var3], 0, 0).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Helmet");
    			oresArmors[(var3 * oreArmorsPerType) + 1] = new ScourgeArmor(oresArmorsIDs[(var3 * oreArmorsPerType) + 1], OresType.defaultArmorEnums[var3], 0, 1).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Plate");
    			oresArmors[(var3 * oreArmorsPerType) + 2] = new ScourgeArmor(oresArmorsIDs[(var3 * oreArmorsPerType) + 2], OresType.defaultArmorEnums[var3], 0, 2).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Legs");
    			oresArmors[(var3 * oreArmorsPerType) + 3] = new ScourgeArmor(oresArmorsIDs[(var3 * oreArmorsPerType) + 3], OresType.defaultArmorEnums[var3], 0, 3).setUnlocalizedName(OresType.names[var3].toLowerCase() + "Boots");
    		}
    	}
    	
    	//Chests
    	chestBlock = new ScourgeBlockChest(chestBlockId);
    	
    	
    	gameRegisters();
    	languageRegisters();
    	recipeRegisters();
    }
    
    private void gameRegisters()
    {
    	//Ores
    	for (int var3 = 0; var3 < OresType.names.length; var3++)
    	{
    		if (OresType.defaultIsMetal[var3])
    			GameRegistry.registerBlock(oresBlocks[(var3 * oreBlocksPerType) + 0], "block" + oresBlocks[(var3 * oreBlocksPerType) + 0].getUnlocalizedName2());
    		GameRegistry.registerBlock(oresBlocks[(var3 * oreBlocksPerType) + 1], "block" + oresBlocks[(var3 * oreBlocksPerType) + 1].getUnlocalizedName2());
    		GameRegistry.registerBlock(oresBlocks[(var3 * oreBlocksPerType) + 2], "block" + oresBlocks[(var3 * oreBlocksPerType) + 2].getUnlocalizedName2());
    		GameRegistry.registerItem(oresItems[(var3 * oreItemsPerType) + 0], "item" + oresItems[(var3 * oreItemsPerType) + 0].getUnlocalizedName().replaceAll("item.", ""));
    		GameRegistry.registerItem(oresItems[(var3 * oreItemsPerType) + 1], "item" + oresItems[(var3 * oreItemsPerType) + 1].getUnlocalizedName().replaceAll("item.", ""));
    		if (OresType.defaultIsMetal[var3])
    			GameRegistry.registerItem(oresItems[(var3 * oreItemsPerType) + 2], "item" + oresItems[(var3 * oreItemsPerType) + 2].getUnlocalizedName().replaceAll("item.", ""));
    		
    		if (OresType.defaultHasTools[var3]) {
    			GameRegistry.registerItem(oresTools[(var3 * oreToolsPerType) + 0], "tool" + oresTools[(var3 * oreToolsPerType) + 0].getUnlocalizedName().replaceAll("item.", ""));
        		GameRegistry.registerItem(oresTools[(var3 * oreToolsPerType) + 1], "tool" + oresTools[(var3 * oreToolsPerType) + 1].getUnlocalizedName().replaceAll("item.", ""));
        		GameRegistry.registerItem(oresTools[(var3 * oreToolsPerType) + 2], "tool" + oresTools[(var3 * oreToolsPerType) + 2].getUnlocalizedName().replaceAll("item.", ""));
        		GameRegistry.registerItem(oresTools[(var3 * oreToolsPerType) + 3], "tool" + oresTools[(var3 * oreToolsPerType) + 3].getUnlocalizedName().replaceAll("item.", ""));
        		GameRegistry.registerItem(oresTools[(var3 * oreToolsPerType) + 4], "tool" + oresTools[(var3 * oreToolsPerType) + 4].getUnlocalizedName().replaceAll("item.", ""));
    		}
    		
    		if (OresType.defaultHasArmors[var3]) {
    			GameRegistry.registerItem(oresArmors[(var3 * oreArmorsPerType) + 0], "armor" + oresArmors[(var3 * oreArmorsPerType) + 0].getUnlocalizedName().replaceAll("item.", ""));
    			GameRegistry.registerItem(oresArmors[(var3 * oreArmorsPerType) + 1], "armor" + oresArmors[(var3 * oreArmorsPerType) + 1].getUnlocalizedName().replaceAll("item.", ""));
    			GameRegistry.registerItem(oresArmors[(var3 * oreArmorsPerType) + 2], "armor" + oresArmors[(var3 * oreArmorsPerType) + 2].getUnlocalizedName().replaceAll("item.", ""));
    			GameRegistry.registerItem(oresArmors[(var3 * oreArmorsPerType) + 3], "armor" + oresArmors[(var3 * oreArmorsPerType) + 3].getUnlocalizedName().replaceAll("item.", ""));
    		}
    	}
    	
    	GameRegistry.registerBlock(chestBlock, ItemChest.class, "BlockIronChest");
    	for (ChestType typ : ChestType.values())
        {
            GameRegistry.registerTileEntityWithAlternatives(typ.clazz, "IronChest."+typ.name(), typ.name());
            LanguageRegistry.instance().addStringLocalization(typ.name() + ".name", "en_US", typ.friendlyName);
            proxy.registerTileEntitySpecialRenderer(typ);
        }
    	NetworkRegistry.instance().registerGuiHandler(instance, proxy);
    	proxy.registerRenderInformation();
    	MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void languageRegisters()
    {
    	//Ores
    	for (int var3 = 0; var3 < OresType.names.length; var3++)
    	{
    		if (OresType.defaultIsMetal[var3])
    			LanguageRegistry.addName(oresBlocks[(var3 * oreBlocksPerType) + 0], OresType.names[var3] + " Ore");
    		LanguageRegistry.addName(oresBlocks[(var3 * oreBlocksPerType) + 1], OresType.names[var3] + " Block");
    		LanguageRegistry.addName(oresBlocks[(var3 * oreBlocksPerType) + 2], OresType.names[var3] + " Brick");
    		LanguageRegistry.addName(oresItems[(var3 * oreItemsPerType) + 0], OresType.names[var3] + " Dust");
    		LanguageRegistry.addName(oresItems[(var3 * oreItemsPerType) + 1], OresType.names[var3] + " Ingot");
    		if (OresType.defaultIsMetal[var3])
    			LanguageRegistry.addName(oresItems[(var3 * oreItemsPerType) + 2], OresType.names[var3] + " Nugget");
    		
    		if (OresType.defaultHasTools[var3]) {
    			LanguageRegistry.addName(oresTools[(var3 * oreToolsPerType) + 0], OresType.names[var3] + " Axe");
        		LanguageRegistry.addName(oresTools[(var3 * oreToolsPerType) + 1], OresType.names[var3] + " Hoe");
        		LanguageRegistry.addName(oresTools[(var3 * oreToolsPerType) + 2], OresType.names[var3] + " PickAxe");
        		LanguageRegistry.addName(oresTools[(var3 * oreToolsPerType) + 3], OresType.names[var3] + " Spade");
        		LanguageRegistry.addName(oresTools[(var3 * oreToolsPerType) + 4], OresType.names[var3] + " Sword");
    		}
    		
    		if (OresType.defaultHasArmors[var3]) {
    			LanguageRegistry.addName(oresArmors[(var3 * oreArmorsPerType) + 0], OresType.names[var3] + " Helmet");
        		LanguageRegistry.addName(oresArmors[(var3 * oreArmorsPerType) + 1], OresType.names[var3] + " Plate");
        		LanguageRegistry.addName(oresArmors[(var3 * oreArmorsPerType) + 2], OresType.names[var3] + " Legs");
        		LanguageRegistry.addName(oresArmors[(var3 * oreArmorsPerType) + 3], OresType.names[var3] + " Boots");
    		}
    	}
    	
    }

	private void recipeRegisters()
	{
		//Ores
    	for (int var3 = 0; var3 < OresType.names.length; var3++)
    	{
    		if (OresType.defaultIsMetal[var3]) {
    			//Ore to Ingot
    			GameRegistry.addSmelting(oresBlocksIDs[(var3 * oreBlocksPerType) + 0], new ItemStack(oresItems[(var3 * oreItemsPerType) + 1], 1), 0.4F);
    			MinecraftForge.setBlockHarvestLevel(oresBlocks[(var3 * oreBlocksPerType) + 0], var3, "pickaxe", OresType.harvestLevels[var3]);
    		}
    		
    		if (OresType.defaultHasTools[var3]) {
    			MinecraftForge.setToolClass(oresTools[(var3 * oreToolsPerType) + 2], "pickaxe", OresType.pickLevels[var3]);
    		}
    		
    		MinecraftForge.setBlockHarvestLevel(oresBlocks[(var3 * oreBlocksPerType) + 1], var3, "pickaxe", OresType.harvestLevels[var3]);
    		MinecraftForge.setBlockHarvestLevel(oresBlocks[(var3 * oreBlocksPerType) + 2], var3, "pickaxe", OresType.harvestLevels[var3]);
    		
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(oresBlocks[(var3 * oreBlocksPerType) + 1], 1, 0), new Object[] {"XXX", "XXX", "XXX", 'X', oresItems[(var3 * oreItemsPerType) + 1]}));
            GameRegistry.addRecipe(new ItemStack(oresItems[(var3 * oreItemsPerType) + 1], 9), new Object[] {"X", 'X', new ItemStack(oresBlocks[(var3 * oreBlocksPerType) + 1], 1, 0)});	
    		//Dust to Ingot
    		GameRegistry.addSmelting(oresItems[(var3 * oreItemsPerType) + 0].itemID, new ItemStack(oresItems[(var3 * oreItemsPerType) + 1], 1), 1.0F);
    	}
    	
    	//Chests
    	ChestType.registerBlocksAndRecipes(chestBlock);
	}
    
}