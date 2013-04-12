package mods.scourgecraft;

import java.util.EnumSet;


import mods.scourgecraft.blocks.ScourgeBlock;
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
import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(
        modid = ScourgeCraftCore.modid,
        name = "ScourgeCraft Core",
        version = "0.0.1"
)
@NetworkMod(
        channels = {"MetallurgyCore"},
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class
)
public class ScourgeCraftCore
{
	public static final String modid = "ScourgeCraft";
	private static final int oreTotalsPerType = 20;
	private static final int oreBlocksPerType = 3;
	private static final int oreItemsPerType = 3;
	private static final int oreToolsPerType = 5;
	private static final int oreArmorsPerType = 4;
	
    @Mod.Instance("ScourgeCraftCore")
    public static ScourgeCraftCore instance;
    
    //Ores
    Block[] oresBlocks = new Block[OresEnum.names.length * oreBlocksPerType];
    int[] oresBlocksIDs = new int[OresEnum.names.length * oreBlocksPerType];
    
    Item[] oresItems = new Item[OresEnum.names.length * oreItemsPerType];
    int[] oresItemsIDs = new int[OresEnum.names.length * oreItemsPerType];
    
    Item[] oresTools = new Item[OresEnum.names.length * oreToolsPerType];
    int[] oresToolsIDs = new int[OresEnum.names.length * oreToolsPerType];
    
    Item[] oresArmors = new Item[OresEnum.names.length * oreArmorsPerType];
    int[] oresArmorsIDs = new int[OresEnum.names.length * oreArmorsPerType];

    private static final int StartId = 500;
    
    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
    	
    	for (int var3 = 0; var3 < OresEnum.names.length; var3++)
    	{
    		oresBlocksIDs[(var3 * oreBlocksPerType) + 0] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Ore", StartId + (var3 * oreTotalsPerType) + 0).getInt();
    		oresBlocksIDs[(var3 * oreBlocksPerType) + 1] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Block", StartId + (var3 * oreTotalsPerType) + 1).getInt();
    		oresBlocksIDs[(var3 * oreBlocksPerType) + 2] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Brick", StartId + (var3 * oreTotalsPerType) + 2).getInt();
    		oresItemsIDs[(var3 * oreItemsPerType) + 0] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Dust", StartId + (var3 * oreTotalsPerType) + 3).getInt();
    		oresItemsIDs[(var3 * oreItemsPerType) + 1] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Ingot", StartId + (var3 * oreTotalsPerType) + 4).getInt();
    		oresItemsIDs[(var3 * oreItemsPerType) + 2] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Nugget", StartId + (var3 * oreTotalsPerType) + 5).getInt();
    		

        	if (OresEnum.defaultHasTools[var3]) {
    			oresToolsIDs[(var3 * oreToolsPerType) + 0] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Axe", StartId + (var3 * oreTotalsPerType) + 6).getInt();
    			oresToolsIDs[(var3 * oreToolsPerType) + 1] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Hoe", StartId + (var3 * oreTotalsPerType) + 7).getInt();
    			oresToolsIDs[(var3 * oreToolsPerType) + 2] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " PickAxe", StartId + (var3 * oreTotalsPerType) + 8).getInt();
    			oresToolsIDs[(var3 * oreToolsPerType) + 3] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Spade", StartId + (var3 * oreTotalsPerType) + 9).getInt();
    			oresToolsIDs[(var3 * oreToolsPerType) + 4] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Sword", StartId + (var3 * oreTotalsPerType) + 10).getInt();
    		}
    		
    		if (OresEnum.defaultHasArmors[var3]) {
    			oresArmorsIDs[(var3 * oreArmorsPerType) + 0] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Helmet", StartId + (var3 * oreTotalsPerType) + 11).getInt();
    			oresArmorsIDs[(var3 * oreArmorsPerType) + 1] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Plate", StartId + (var3 * oreTotalsPerType) + 12).getInt();
    			oresArmorsIDs[(var3 * oreArmorsPerType) + 2] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Legs", StartId + (var3 * oreTotalsPerType) + 13).getInt();
    			oresArmorsIDs[(var3 * oreArmorsPerType) + 3] = config.get(OresEnum.names[var3] + " IDs", OresEnum.names[var3] + " Boots", StartId + (var3 * oreTotalsPerType) + 14).getInt();
    		}
    	}
    	
    	
    	config.save();
    }
    
    @Mod.Init
    public void load(FMLInitializationEvent var1)
    {
    	//Ores
    	for (int var3 = 0; var3 < OresEnum.names.length; var3++)
    	{
    		oresBlocks[(var3 * oreBlocksPerType) + 0] = new ScourgeBlock(oresBlocksIDs[(var3 * oreBlocksPerType) + 0]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Ore");
    		oresBlocks[(var3 * oreBlocksPerType) + 1] = new ScourgeBlock(oresBlocksIDs[(var3 * oreBlocksPerType) + 1]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Block");
    		oresBlocks[(var3 * oreBlocksPerType) + 2] = new ScourgeBlock(oresBlocksIDs[(var3 * oreBlocksPerType) + 2]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Brick");
    		oresItems[(var3 * oreItemsPerType) + 0] = new ScourgeItem(oresItemsIDs[(var3 * oreItemsPerType) + 0]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Dust");
    		oresItems[(var3 * oreItemsPerType) + 1] = new ScourgeItem(oresItemsIDs[(var3 * oreItemsPerType) + 1]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Ingot");
    		oresItems[(var3 * oreItemsPerType) + 2] = new ScourgeItem(oresItemsIDs[(var3 * oreItemsPerType) + 2]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Nugget");
    		
    		if (OresEnum.defaultHasTools[var3]) {
    			oresTools[(var3 * oreToolsPerType) + 0] = new ScourgeItemAxe(oresToolsIDs[(var3 * oreToolsPerType) + 0], OresEnum.toolEnum[var3]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Axe");
    			oresTools[(var3 * oreToolsPerType) + 1] = new ScourgeItemHoe(oresToolsIDs[(var3 * oreToolsPerType) + 1], OresEnum.toolEnum[var3]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Hoe");
    			oresTools[(var3 * oreToolsPerType) + 2] = new ScourgeItemPickAxe(oresToolsIDs[(var3 * oreToolsPerType) + 2], OresEnum.toolEnum[var3]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "PickAxe");
    			oresTools[(var3 * oreToolsPerType) + 3] = new ScourgeItemSpade(oresToolsIDs[(var3 * oreToolsPerType) + 3], OresEnum.toolEnum[var3]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Spade");
    			oresTools[(var3 * oreToolsPerType) + 4] = new ScourgeItemSword(oresToolsIDs[(var3 * oreToolsPerType) + 4], OresEnum.toolEnum[var3]).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Sword");
    		}
    		
    		if (OresEnum.defaultHasArmors[var3]) {
    			oresArmors[(var3 * oreArmorsPerType) + 0] = new ScourgeArmor(oresArmorsIDs[(var3 * oreArmorsPerType) + 0], OresEnum.defaultArmorEnums[var3], 0, 0).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Helmet");
    			oresArmors[(var3 * oreArmorsPerType) + 1] = new ScourgeArmor(oresArmorsIDs[(var3 * oreArmorsPerType) + 1], OresEnum.defaultArmorEnums[var3], 0, 1).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Plate");
    			oresArmors[(var3 * oreArmorsPerType) + 2] = new ScourgeArmor(oresArmorsIDs[(var3 * oreArmorsPerType) + 2], OresEnum.defaultArmorEnums[var3], 0, 2).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Legs");
    			oresArmors[(var3 * oreArmorsPerType) + 3] = new ScourgeArmor(oresArmorsIDs[(var3 * oreArmorsPerType) + 3], OresEnum.defaultArmorEnums[var3], 0, 3).setUnlocalizedName(OresEnum.names[var3].toLowerCase() + "Boots");
    		}
    	}
    	
    	gameRegisters();
    	languageRegisters();
    }
    
    private void gameRegisters()
    {
    	//Ores
    	for (int var3 = 0; var3 < OresEnum.names.length; var3++)
    	{
    		GameRegistry.registerBlock(oresBlocks[(var3 * oreBlocksPerType) + 0], "block" + oresBlocks[(var3 * oreBlocksPerType) + 0].getUnlocalizedName2());
    		GameRegistry.registerBlock(oresBlocks[(var3 * oreBlocksPerType) + 1], "block" + oresBlocks[(var3 * oreBlocksPerType) + 1].getUnlocalizedName2());
    		GameRegistry.registerBlock(oresBlocks[(var3 * oreBlocksPerType) + 2], "block" + oresBlocks[(var3 * oreBlocksPerType) + 2].getUnlocalizedName2());
    		GameRegistry.registerItem(oresItems[(var3 * oreItemsPerType) + 0], "item" + oresItems[(var3 * oreItemsPerType) + 0].getUnlocalizedName().replaceAll("item.", ""));
    		GameRegistry.registerItem(oresItems[(var3 * oreItemsPerType) + 1], "item" + oresItems[(var3 * oreItemsPerType) + 1].getUnlocalizedName().replaceAll("item.", ""));
    		GameRegistry.registerItem(oresItems[(var3 * oreItemsPerType) + 2], "item" + oresItems[(var3 * oreItemsPerType) + 2].getUnlocalizedName().replaceAll("item.", ""));
    		
    		if (OresEnum.defaultHasTools[var3]) {
    			GameRegistry.registerItem(oresTools[(var3 * oreToolsPerType) + 0], "tool" + oresTools[(var3 * oreToolsPerType) + 0].getUnlocalizedName());
        		GameRegistry.registerItem(oresTools[(var3 * oreToolsPerType) + 1], "tool" + oresTools[(var3 * oreToolsPerType) + 1].getUnlocalizedName());
        		GameRegistry.registerItem(oresTools[(var3 * oreToolsPerType) + 2], "tool" + oresTools[(var3 * oreToolsPerType) + 2].getUnlocalizedName());
        		GameRegistry.registerItem(oresTools[(var3 * oreToolsPerType) + 3], "tool" + oresTools[(var3 * oreToolsPerType) + 3].getUnlocalizedName());
        		GameRegistry.registerItem(oresTools[(var3 * oreToolsPerType) + 4], "tool" + oresTools[(var3 * oreToolsPerType) + 4].getUnlocalizedName());
    		}
    		
    		if (OresEnum.defaultHasArmors[var3]) {
    			GameRegistry.registerItem(oresArmors[(var3 * oreArmorsPerType) + 0], "armor" + oresArmors[(var3 * oreArmorsPerType) + 0].getUnlocalizedName());
    			GameRegistry.registerItem(oresArmors[(var3 * oreArmorsPerType) + 1], "armor" + oresArmors[(var3 * oreArmorsPerType) + 1].getUnlocalizedName());
    			GameRegistry.registerItem(oresArmors[(var3 * oreArmorsPerType) + 2], "armor" + oresArmors[(var3 * oreArmorsPerType) + 2].getUnlocalizedName());
    			GameRegistry.registerItem(oresArmors[(var3 * oreArmorsPerType) + 3], "armor" + oresArmors[(var3 * oreArmorsPerType) + 3].getUnlocalizedName());
    		}
    	}
    }
    
    private void languageRegisters()
    {
    	//Ores
    	for (int var3 = 0; var3 < OresEnum.names.length; var3++)
    	{
    		LanguageRegistry.addName(oresBlocks[(var3 * oreBlocksPerType) + 0], OresEnum.names[var3] + " Ore");
    		LanguageRegistry.addName(oresBlocks[(var3 * oreBlocksPerType) + 1], OresEnum.names[var3] + " Block");
    		LanguageRegistry.addName(oresBlocks[(var3 * oreBlocksPerType) + 2], OresEnum.names[var3] + " Brick");
    		LanguageRegistry.addName(oresItems[(var3 * oreItemsPerType) + 0], OresEnum.names[var3] + " Dust");
    		LanguageRegistry.addName(oresItems[(var3 * oreItemsPerType) + 1], OresEnum.names[var3] + " Ingot");
    		LanguageRegistry.addName(oresItems[(var3 * oreItemsPerType) + 2], OresEnum.names[var3] + " Nugget");
    		
    		if (OresEnum.defaultHasTools[var3]) {
    			LanguageRegistry.addName(oresTools[(var3 * oreToolsPerType) + 0], OresEnum.names[var3] + " Axe");
        		LanguageRegistry.addName(oresTools[(var3 * oreToolsPerType) + 1], OresEnum.names[var3] + " Hoe");
        		LanguageRegistry.addName(oresTools[(var3 * oreToolsPerType) + 2], OresEnum.names[var3] + " PickAxe");
        		LanguageRegistry.addName(oresTools[(var3 * oreToolsPerType) + 3], OresEnum.names[var3] + " Spade");
        		LanguageRegistry.addName(oresTools[(var3 * oreToolsPerType) + 4], OresEnum.names[var3] + " Sword");
    		}
    		
    		if (OresEnum.defaultHasArmors[var3]) {
    			LanguageRegistry.addName(oresArmors[(var3 * oreArmorsPerType) + 0], OresEnum.names[var3] + " Helmet");
        		LanguageRegistry.addName(oresArmors[(var3 * oreArmorsPerType) + 1], OresEnum.names[var3] + " Plate");
        		LanguageRegistry.addName(oresArmors[(var3 * oreArmorsPerType) + 2], OresEnum.names[var3] + " Legs");
        		LanguageRegistry.addName(oresArmors[(var3 * oreArmorsPerType) + 3], OresEnum.names[var3] + " Boots");
    		}
    	}
    }
    
    
    
}