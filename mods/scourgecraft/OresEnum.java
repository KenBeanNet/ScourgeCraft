package mods.scourgecraft;

import mods.scourgecraft.items.ScourgeCraftEnumToolMaterial;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;

public class OresEnum {

	public static String[] names = new String[] { "Zinc", "Silver", "Platinum" };
	public static int[] expValues = new int[] {3, 3, 3};
    private final int[] harvestLevels = new int[] {3, 3, 3};
    private final int[] pickLevels = new int[] {3, 3, 3};
    private int[] metalLevels = new int[] {5, 6};
    public static int[] defaultVeinCount = new int[] {6, 5, 3};
    public static int[] defaultOreCount = new int[] {5, 5, 3};
    public static int[] defaultOreHeight = new int[] {128, 128, 128};
    public static boolean[] defaultHasTools = new boolean[] { false, true, true };
    public static boolean[] defaultHasArmors = new boolean[] { false, true, true };
    public static ScourgeCraftEnumToolMaterial[] toolEnum = new ScourgeCraftEnumToolMaterial[] { ScourgeCraftEnumToolMaterial.Steel, ScourgeCraftEnumToolMaterial.Steel, ScourgeCraftEnumToolMaterial.Steel };
    public static EnumArmorMaterial[] defaultArmorEnums = new EnumArmorMaterial[] { null, EnumHelper.addArmorMaterial("Silver", 30, new int[] {4, 7, 5, 4}, 30), EnumHelper.addArmorMaterial("Silver", 30, new int[] {4, 7, 5, 4}, 30) };
}
