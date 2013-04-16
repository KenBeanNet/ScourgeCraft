package mods.scourgecraft.client;

import mods.scourgecraft.ChestType;
import mods.scourgecraft.containers.ContainerChestBase;
import mods.scourgecraft.entities.TileEntityIronChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

import org.lwjgl.opengl.GL11;


public class GUIChest extends GuiContainer {
    public enum GUI {
        IRON(184, 202, "/mods/scourgecraft/textures/gui/ironcontainer.png", ChestType.IRON),
        GOLD(184, 256, "/mods/ironchest/textures/gui/goldcontainer.png", ChestType.GOLD),
        BRASS(238, 256, "/mods/ironchest/textures/gui/diamondcontainer.png", ChestType.BRASS),
        ELECTRUM(184, 184, "/mods/ironchest/textures/gui/coppercontainer.png", ChestType.ELECTRUM),
        SILVER(184, 238, "/mods/ironchest/textures/gui/silvercontainer.png", ChestType.SILVER),
        PLATINUM(238, 256, "/mods/ironchest/textures/gui/diamondcontainer.png", ChestType.PLATINUM);
        
        private int xSize;
        private int ySize;
        private String guiTexture;
        private ChestType mainType;

        private GUI(int xSize, int ySize, String guiTexture, ChestType mainType)
        {
            this.xSize = xSize;
            this.ySize = ySize;
            this.guiTexture = guiTexture;
            this.mainType = mainType;
        }

        protected Container makeContainer(IInventory player, IInventory chest)
        {
            return new ContainerChestBase(player, chest, mainType, xSize, ySize);
        }

        public static GUIChest buildGUI(ChestType type, IInventory playerInventory, TileEntityIronChest chestInventory)
        {
            return new GUIChest(values()[chestInventory.getType().ordinal()], playerInventory, chestInventory);
        }
    }

    public int getRowLength()
    {
        return type.mainType.getRowLength();
    }

    private GUI type;

    private GUIChest(GUI type, IInventory player, IInventory chest)
    {
        super(type.makeContainer(player, chest));
        this.type = type;
        this.xSize = type.xSize;
        this.ySize = type.ySize;
        this.allowUserInput = false;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        // new "bind tex"
        mc.renderEngine.bindTexture(type.guiTexture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}