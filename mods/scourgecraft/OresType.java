package mods.scourgecraft;

import mods.scourgecraft.items.ScourgeEnumToolMaterial;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;

public class OresType {

	public static String[] names = new String[] { "Copper", "Tin", "Manganese", "Bronze", "Hepatizon", "Damascus Steel", "Angmallen", "Steel", "Eximite", "Meutoite", "Desichalkos", 
		"Prometheum", "Deep Iron", "Infuscolium", "Oureclase", "Aredrite", "Astral Silver", "Carmot", "Mithril", "Rubracium", "Orichalcum", "Adamantine", "Atlarus",
		"Black Steel", "Quicksilver", "Haderoth", "Celenegil", "Tartarite", "Ignatius", "Shadow Iron", "Midasium", "Vyroxeres", "Ceruclase", "Kalendrite", "Vulcanite", 
		"Sanguinite", "Lemurite", "Adluorite", "Shadow Steel", "Inolashite", "Amordrine", "Zinc", "Silver", "Platinum", "Brass", "Electrum" };
	public static int[] expValues = new int[] { 1, 1, 3, 2, 8, 5, 9, 6, 3, 3, 10, 1, 2, 4, 3, 3, 11, 15, 4, 2, 5, 6, 7, 4, 9, 6, 25, 13, 1, 2, 3, 3, 3, 4, 5, 6, 3, 4, 4, 6, 24, 3, 5, 20, 4, 11 };
    public static final int[] harvestLevels = new int[] { 1, 1, 2, 1, 1, 1, 1, 2, 3, 3, 4, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 5, 5, 2, 4, 4, 5, 6, 1, 1, 2, 2, 2, 3, 4, 5, 0, 0, 2, 4, 4, 0, 1, 2, 1, 1 };
    public static final int[] pickLevels = new int[] { 1, 0, 0, 2, 2, 3, 2, 3, 3, 0, 4, 1, 2, 0, 3, 3, 4, 4, 4, 0, 5, 6, 6, 2, 4, 4, 5, 6, 1, 1, 3, 3, 3, 4, 5, 6, 0, 0, 2, 4, 4, 0, 0, 2, 0, 1 };
    public static int[] metalLevels = new int[] { 1, 2, 4, 2, 3, 5, 5, 5, 5, 6, 7, 1, 2, 3, 5, 5, 6, 7, 8, 9, 10, 10, 11, 4, 9, 9, 11, 12, 1, 2, 4, 5, 6, 8, 10, 11, 2, 6, 3, 7, 9, 1, 2, 5, 2, 4 };
    public static final int[] dungeonLootChances = new int[] {120, 100, 45, 80, 80, 60, 55, 45, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 18, 4, 25, 10};
    public static final int[] dungeonLootAmounts = new int[] {6, 4, 2, 3, 3, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 3, 1, 3, 2 };
    public static int[] defaultVeinCount = new int[] { 12, 10, 4, 0, 0, 0, 0, 0, 6, 3, 0, 5, 5, 5, 4, 4, 3, 3, 3, 2, 2, 1, 1, 0, 0, 0, 0, 0, 9, 7, 5, 4, 4, 3, 2, 2, 6, 3, 0, 0, 0, 6, 5, 3, 0, 0 };
    public static int[] defaultOreCount = new int[] { 6, 7, 4, 0, 0, 0, 0, 0, 4, 3, 0, 6, 4, 3, 3, 3, 3, 3, 3, 3, 4, 4, 3, 0, 0, 0, 0, 0, 6, 6, 6, 7, 4, 4, 3, 3, 6, 4, 0, 0, 0, 5, 5, 3, 0, 0 };
    public static boolean[] defaultIsMetal = new boolean[] { true, true, true, false, false, false, false, false, true, true, false, true, true,
    	true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, true, true, true, true, true, true, true, 
    	true, true, true, false, false, false, true, true, true, false, false };
    public static int[] defaultOreHeight = new int[] { 128, 128, 128, 0, 0, 0, 0, 0, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 0, 0, 0, 0, 0, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 0, 0, 0, 128, 128, 128, 0, 0 };
    public static boolean[] defaultHasTools = new boolean[] { true, false, false, true, true, true, true, true, true, false, true, true, true, 
    	false, true, true, true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
    	true, false, false, true, true, true, false, true, true, true, true };
    public static boolean[] defaultHasArmors = new boolean[] { true, false, false, true, true, true, true, true, true, false, true, true, 
    	true, false, true, true, true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, 
    	true, true, true, false, false, true, true, true, false, true, true, true, true };
    public static int[] numRails = new int[] {4, 0, 0, 10, 14, 26, 22, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static ScourgeEnumToolMaterial[] toolEnum = new ScourgeEnumToolMaterial[] { ScourgeEnumToolMaterial.Copper, ScourgeEnumToolMaterial.Copper, ScourgeEnumToolMaterial.Copper,
    	 ScourgeEnumToolMaterial.Bronze, ScourgeEnumToolMaterial.Hepatizon, ScourgeEnumToolMaterial.DamascusSteel, ScourgeEnumToolMaterial.Angmallen,
    	 ScourgeEnumToolMaterial.Steel, ScourgeEnumToolMaterial.Copper, ScourgeEnumToolMaterial.Copper, ScourgeEnumToolMaterial.Steel,  ScourgeEnumToolMaterial.Prometheum,
    	 ScourgeEnumToolMaterial.DeepIron, null, ScourgeEnumToolMaterial.Oureclase, ScourgeEnumToolMaterial.Aredrite, ScourgeEnumToolMaterial.AstralSilver, ScourgeEnumToolMaterial.Carmot,
    	 ScourgeEnumToolMaterial.Mithril, null, ScourgeEnumToolMaterial.Orichalcum, ScourgeEnumToolMaterial.Adamantine, ScourgeEnumToolMaterial.Atlarus, ScourgeEnumToolMaterial.BlackSteel,
    	 ScourgeEnumToolMaterial.Quicksilver, ScourgeEnumToolMaterial.Haderoth, ScourgeEnumToolMaterial.Celenegil, ScourgeEnumToolMaterial.Tartarite, ScourgeEnumToolMaterial.BlackSteel,
    	 ScourgeEnumToolMaterial.Ignatius,  ScourgeEnumToolMaterial.ShadowIron, ScourgeEnumToolMaterial.Midasium, ScourgeEnumToolMaterial.Vyroxeres, ScourgeEnumToolMaterial.Ceruclase, 
    	 ScourgeEnumToolMaterial.Kalendrite, ScourgeEnumToolMaterial.Vulcanite, ScourgeEnumToolMaterial.Sanguinite, null, ScourgeEnumToolMaterial.ShadowSteel, ScourgeEnumToolMaterial.Inolashite,
    	 ScourgeEnumToolMaterial.Amordrine, null, ScourgeEnumToolMaterial.Silver, ScourgeEnumToolMaterial.Platinum, ScourgeEnumToolMaterial.Brass, ScourgeEnumToolMaterial.Electrum};
    public static EnumArmorMaterial[] defaultArmorEnums = new EnumArmorMaterial[] { EnumHelper.addArmorMaterial("Copper", 10, new int[] {2, 3, 2, 1}, 5), null, null, EnumHelper.addArmorMaterial("Bronze", 13, new int[] {2, 4, 3, 3}, 9), 
    	EnumHelper.addArmorMaterial("Hepatizon", 14, new int[] {2, 4, 3, 3}, 22), EnumHelper.addArmorMaterial("Damascus Steel", 20, new int[] {3, 6, 5, 3}, 18), EnumHelper.addArmorMaterial("Angmallen", 18, new int[] {3, 6, 5, 3}, 30),
    	EnumHelper.addArmorMaterial("Steel", 22, new int[] {3, 6, 5, 3}, 18), EnumHelper.addArmorMaterial("Eximite", 24, new int[] {4, 6, 5, 4}, 25), null, EnumHelper.addArmorMaterial("Desichalkos", 30, new int[] {4, 7, 5, 4}, 30),
    	EnumHelper.addArmorMaterial("Prometheum", 9, new int[] {2, 3, 2, 1}, 16), EnumHelper.addArmorMaterial("Deep Iron", 14, new int[] {2, 6, 5, 2}, 14), null, EnumHelper.addArmorMaterial("Oureclase", 20, new int[] {3, 6, 5, 4}, 18),
    	EnumHelper.addArmorMaterial("Aredrite", 24, new int[] {3, 6, 5, 4}, 18), EnumHelper.addArmorMaterial("Astral Silver", 10, new int[] {2, 6, 5, 2}, 30), EnumHelper.addArmorMaterial("Carmot", 12, new int[] {3, 6, 5, 3}, 40),
    	EnumHelper.addArmorMaterial("Mithril", 28, new int[] {4, 6, 5, 4}, 18), null, EnumHelper.addArmorMaterial("Orichalcum", 34, new int[] {4, 7, 6, 4}, 20),  EnumHelper.addArmorMaterial("Adamantine", 38, new int[] {5, 7, 6, 4}, 22),
    	EnumHelper.addArmorMaterial("Atlarus", 40, new int[] {5, 7, 6, 4}, 22), EnumHelper.addArmorMaterial("Black Steel", 22, new int[] {3, 6, 5, 3}, 17), EnumHelper.addArmorMaterial("Quicksilver", 30, new int[] {4, 7, 5, 4}, 20),
    	EnumHelper.addArmorMaterial("Haderoth", 32, new int[] {4, 7, 5, 4}, 19), EnumHelper.addArmorMaterial("Celenegil", 36, new int[] {5, 7, 6, 4}, 50), EnumHelper.addArmorMaterial("Tartarite", 42, new int[] {5, 7, 6, 5}, 25),
    	EnumHelper.addArmorMaterial("Ignatius", 15, new int[] {2, 6, 5, 2}, 15), EnumHelper.addArmorMaterial("Shadow Iron", 16, new int[] {3, 6, 5, 2}, 3), EnumHelper.addArmorMaterial("Midasium", 8, new int[] {2, 5, 3, 3}, 35),
    	EnumHelper.addArmorMaterial("Vyroxeres", 23, new int[] {3, 6, 5, 4}, 16),  EnumHelper.addArmorMaterial("Ceruclase", 25, new int[] {3, 6, 5, 4}, 18), EnumHelper.addArmorMaterial("Kalendrite", 28, new int[] {4, 6, 5, 4}, 20),
    	EnumHelper.addArmorMaterial("Vulcanite", 34, new int[] {4, 7, 5, 4}, 20), EnumHelper.addArmorMaterial("Sanguinite", 36, new int[] {5, 7, 6, 4}, 25), null, null, EnumHelper.addArmorMaterial("Shadow Steel", 20, new int[] {3, 6, 5, 4}, 5), 
    	EnumHelper.addArmorMaterial("Inolashite", 30, new int[] {4, 7, 5, 4}, 25), EnumHelper.addArmorMaterial("Amordrine", 32, new int[] {4, 7, 5, 4}, 50), null, EnumHelper.addArmorMaterial("Silver", 5, new int[] {2, 4, 3, 2}, 20),
    	EnumHelper.addArmorMaterial("Platinum", 11, new int[] {3, 6, 5, 3}, 50), EnumHelper.addArmorMaterial("Brass", 3, new int[] {2, 5, 3, 3}, 18), EnumHelper.addArmorMaterial("Electrum", 9, new int[] {2, 6, 5, 2}, 30)};

}
