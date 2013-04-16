package mods.scourgecraft.items;

import mods.scourgecraft.ChestType;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemChest extends ItemBlock {

    public ItemChest(int id)
    {
        super(id);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int i)
    {
        return ChestType.validateMeta(i);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return ChestType.values()[itemstack.getItemDamage()].name();
    }
}