package mods.scourgecraft;

import java.util.logging.Level;

import mods.scourgecraft.blocks.ScourgeBlockChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "IronChest", name = "Iron Chests", dependencies = "required-after:Forge@[7.0,);required-after:FML@[5.0.5,)")
@NetworkMod(channels = { "IronChest" }, versionBounds = "[5.2,)", clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class ScourgeChest {
    public static ScourgeBlockChest ironChestBlock;
    @SidedProxy(clientSide = "cpw.mods.ironchest.client.ClientProxy", serverSide = "cpw.mods.ironchest.CommonProxy")
    public static ChestCommonProxy proxy;
    @Instance("IronChest")
    public static ScourgeChest instance;
    public static boolean CACHE_RENDER = true;
    public static boolean OCELOTS_SITONCHESTS = true;
    private int blockId;

    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        Version.init(event.getVersionProperties());
        event.getModMetadata().version = Version.fullVersionString();
        Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
            cfg.load();
            blockId = cfg.getBlock("ironChests", 975).getInt(975);
            ChestChangerType.buildItems(cfg, 19501);
            CACHE_RENDER = cfg.get(Configuration.CATEGORY_GENERAL, "cacheRenderingInformation", true).getBoolean(true);
            OCELOTS_SITONCHESTS = cfg.get(Configuration.CATEGORY_GENERAL, "ocelotsSitOnChests", true).getBoolean(true);
        }
        catch (Exception e)
        {
            FMLLog.log(Level.SEVERE, e, "IronChest has a problem loading it's configuration");
        }
        finally
        {
            cfg.save();
        }
    }

    @Init
    public void load(FMLInitializationEvent evt)
    {
        ironChestBlock = new ScourgeBlockChest(blockId);
        GameRegistry.registerBlock(ironChestBlock, ItemIronChest.class, "BlockIronChest");
        for (ScourgeChestType typ : ScourgeChestType.values())
        {
            GameRegistry.registerTileEntityWithAlternatives(typ.clazz, "IronChest."+typ.name(), typ.name());
            LanguageRegistry.instance().addStringLocalization(typ.name() + ".name", "en_US", typ.friendlyName);
            proxy.registerTileEntitySpecialRenderer(typ);
        }
        for (ChestChangerType typ : ChestChangerType.values())
        {
            LanguageRegistry.instance().addStringLocalization("item." + typ.itemName + ".name", "en_US", typ.descriptiveName);
        }
        ScourgeChestType.registerBlocksAndRecipes(ironChestBlock);
        ChestChangerType.generateRecipes();
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        proxy.registerRenderInformation();
        if (OCELOTS_SITONCHESTS)
        {
            MinecraftForge.EVENT_BUS.register(new OcelotsSitOnChestsHandler());
        }
        MinecraftForge.EVENT_BUS.register(this);
    }

    @PostInit
    public void modsLoaded(FMLPostInitializationEvent evt)
    {
    }

}