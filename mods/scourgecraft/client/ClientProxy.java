package mods.scourgecraft.client;

import mods.scourgecraft.ChestType;
import mods.scourgecraft.CommonProxy;
import mods.scourgecraft.entities.TileEntityIronChest;
import net.minecraft.client.renderer.ChestItemRenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderInformation()
    {
        ChestItemRenderHelper.instance = new ScourgeChestRenderHelper();
    }

    @Override
    public void registerTileEntitySpecialRenderer(ChestType typ)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(typ.clazz, new TileEntityScourgeChestRenderer());
    }

    @Override
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null && te instanceof TileEntityIronChest)
        {
            return GUIChest.GUI.buildGUI(ChestType.values()[ID], player.inventory, (TileEntityIronChest) te);
        }
        else
        {
            return null;
        }
    }
}