package mods.scourgecraft.client;

import java.util.Map;

import mods.scourgecraft.ChestType;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.entities.TileEntityIronChest;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ChestItemRenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;

import com.google.common.collect.Maps;

public class ScourgeChestRenderHelper extends ChestItemRenderHelper {
    private Map<Integer, TileEntityIronChest> itemRenders = Maps.newHashMap();

    public ScourgeChestRenderHelper()
    {
        for (ChestType typ : ChestType.values())
        {
            itemRenders.put(typ.ordinal(), (TileEntityIronChest) ScourgeCraftCore.chestBlock.createTileEntity(null, typ.ordinal()));
        }
    }

    @Override
    public void renderChest(Block block, int i, float f)
    {
        if (block == ScourgeCraftCore.chestBlock)
        {
            TileEntityRenderer.instance.renderTileEntityAt(itemRenders.get(i), 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else
        {
            super.renderChest(block, i, f);
        }
    }
}