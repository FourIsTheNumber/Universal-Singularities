package wealthyturtle.uiesingularities.proxy;

import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.avaritia.crafting.CompressorManager;
import fox.spiteful.avaritia.crafting.Grinder;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import wealthyturtle.uiesingularities.UniversalSingularity;
import wealthyturtle.uiesingularities.UniversalSingularityItem;
import wealthyturtle.uiesingularities.UniversalSingularityWrapper;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.*;

import static fox.spiteful.avaritia.Config.craftingOnly;
import static java.io.File.separatorChar;

public class CommonProxy
{
	protected final List<UniversalSingularityItem> singularities = new ArrayList<>();

	private final Map<String, Set<String>> allowed = new HashMap<>();

	//Base Values were Generated by random.org [Min: 65, Max: 95]
	public CommonProxy()
	{
		final List<UniversalSingularity> universalSingularities = Arrays.asList(
				// Vanilla Singularities
				new UniversalSingularity("vanilla", Arrays.asList(
						new UniversalSingularityWrapper("coal", "blockCoal", 90, 0x282828, 0x0C0C0C),
						new UniversalSingularityWrapper("emerald", "blockEmerald", 81, 0x60DB83, 0x2CA746),
						new UniversalSingularityWrapper("diamond", "blockDiamond", 76, 0x4AEDD1, 0x30DBBD)
				)),
				// Applied Energistics Singularities
				/*
				new UniversalSingularity("appliedEnergistics", Arrays.asList(
						new UniversalSingularityWrapper("certus", "blockCertus", 71, 0xC6D4F8, 0xB9CAE7),
						new UniversalSingularityWrapper("fluix", "blockFluix", 67, 0x903BC8, 0x572FA3)
				)),
				*/
				// Big Reactors Singularities
				new UniversalSingularity("bigReactors", Arrays.asList(
						new UniversalSingularityWrapper("blutonium", "blockBlutonium", 68, 0x4642D6, 0x1B00E6),
						new UniversalSingularityWrapper("cyanite", "blockCyanite", 86, 0x5CAFDB, 0x0087EF),
						new UniversalSingularityWrapper("graphite", "blockGraphite", 94, 0x5D5D5D, 0x444444),
						new UniversalSingularityWrapper("ludicrite", "blockLudicrite", 88, 0xF001E8, 0xF103B1),
						new UniversalSingularityWrapper("yellorium", "blockYellorium", 75, 0xD9DB5C, 0xEBFF3D)
				)),
				// Botania Singularities
				/*
				new UniversalSingularity("botania", Arrays.asList(
						new UniversalSingularityWrapper("manasteel", "blockManasteel", 71, 0x7ACDFD, 0x2CAEF8),
						new UniversalSingularityWrapper("terrasteel", "blockTerrasteel", 91, 0x62D62A, 0x31AF04)
				)),
				*/
				// Draconic Evolution Singularities
				new UniversalSingularity("draconicEvolution", Collections.singletonList(
						new UniversalSingularityWrapper("awakenedDraconium", "blockAwakenedDraconium", 83, 0xFF7200, 0xFF6600)
				)),
				// Ender IO Singularities
				new UniversalSingularity("enderIO", Arrays.asList(
						new UniversalSingularityWrapper("conductiveIron", "blockConductiveIron", 65, 0xCA9D9D, 0xC39499),
						new UniversalSingularityWrapper("electricalSteel", "blockElectricalSteel", 91, 0x949494, 0x8D8D8D),
						new UniversalSingularityWrapper("energeticAlloy", "blockEnergeticAlloy", 83, 0xFFA638, 0xFF9C21),
						new UniversalSingularityWrapper("darkSteel", "blockDarkSteel", 77, 0x3C3C3C, 0x383838),
						new UniversalSingularityWrapper("pulsatingIron", "blockPulsatingIron", 74, 0x82FA9E, 0x69EA88),
						new UniversalSingularityWrapper("redstoneAlloy", "blockRedstoneAlloy", 89, 0xF25757, 0xD63C3C),
						new UniversalSingularityWrapper("soularium", "blockSoularium", 73, 0x5C4527, 0x5A3E25),
						new UniversalSingularityWrapper("vibrantAlloy", "blockVibrantAlloy", 74, 0xA7CA52, 0x98BB40)
				)),
				// ExtraTiC Singularities
				new UniversalSingularity("extraTiC", Arrays.asList(
						new UniversalSingularityWrapper("fairy", "blockFairy", 90, 0xFF83C3, 0xFF65B4),
						new UniversalSingularityWrapper("pokefennium", "blockPokefennium", 71, 0x436B73, 0x485361),
						new UniversalSingularityWrapper("red_aurum", "blockRed_aurum", 78, 0xFF4809, 0xFF3D09)
				)),
				// Extra Utilities Singularities
				new UniversalSingularity("extraUtilities", Collections.singletonList(
						new UniversalSingularityWrapper("unstable", "blockUnstable", 67, 0xC5C5C5, 0xB1B1B1)
				)),
				// Metallurgy Singularities
				new UniversalSingularity("metallurgy", Collections.singletonList(
						new UniversalSingularityWrapper("tartarite", "blockTartarite", 89, 0xAE3400, 0x792400)
				)),
				// ProjectE Singularities
				/*
				new UniversalSingularity("projectE", Arrays.asList(
						new UniversalSingularityWrapper("darkMatter", "blockDarkMatter", 67, 0x38173C, 0x240F27),
						new UniversalSingularityWrapper("redMatter", "blockRedMatter", 95, 0x9A0707, 0x7B0606)
				)),
				*/
				// Redstone Arsenal Singularities
				new UniversalSingularity("redstoneArsenal", Arrays.asList(
						new UniversalSingularityWrapper("electrumFlux", "blockElectrumFlux", 77, 0xD0B64D, 0xA40606),
						new UniversalSingularityWrapper("crystalFlux", "blockCrystalFlux", 76, 0xFE333A, 0xE8111A)
				)),
				// Tinkers' Construct Singularities
				new UniversalSingularity("tinkersConstruct", Arrays.asList(
						new UniversalSingularityWrapper("aluminumBrass", "blockAluminumBrass", 88, 0xE2BE4E, 0xD4B148),
						new UniversalSingularityWrapper("alumite", "blockAlumite", 84, 0xE9ADDA, 0xE298D1),
						new UniversalSingularityWrapper("ardite", "blockArdite", 86, 0xD24900, 0x960000),
						new UniversalSingularityWrapper("cobalt", "blockCobalt", 67, 0x2376DD, 0x023C9B),
						new UniversalSingularityWrapper("manyullyn", "blockManyullyn", 73, 0xA97DE0, 0x926AC3)//,
						//new UniversalSingularityWrapper("pigIron", "blockPigIron", 93, 0xF0A8A4, 0xEF7E4A)
				)),
				// General (Not Mod Specific)
				new UniversalSingularity("general", Arrays.asList(
						new UniversalSingularityWrapper("aluminum", "blockAluminum", 92, 0xEDEDED, 0xC5C5C5),
						new UniversalSingularityWrapper("brass", "blockBrass", 67, 0xD89634, 0x8B5F1B),
						new UniversalSingularityWrapper("bronze", "blockBronze", 90, 0xCA904E, 0xC97A25),
						new UniversalSingularityWrapper("charcoal", "blockCharcoal", 81, 0x605543, 0x100E0B),
						new UniversalSingularityWrapper("electrum", "blockElectrum", 94, 0xFDEF5A, 0xEFE252),
						new UniversalSingularityWrapper("invar", "blockInvar", 95, 0xD0D7CE, 0xACB4B1),
						new UniversalSingularityWrapper("osmium", "blockOsmium", 69, 0xAABACE, 0x9BABC4),
						new UniversalSingularityWrapper("steel", "blockSteel", 88, 0x9F9F9F, 0x888888),
						new UniversalSingularityWrapper("zinc", "blockZinc", 76, 0xBFC55C, 0x939836),
						new UniversalSingularityWrapper("ruby", "blockRuby", 91, 0xB44848, 0x993D3D),
						new UniversalSingularityWrapper("sapphire", "blockSapphire", 86, 0x5173D1, 0x466ACE),
						new UniversalSingularityWrapper("peridot", "blockPeridot", 95, 0x58A52B, 0x4E9226)
				))
		);
		final Configuration config = new Configuration(new File("." + separatorChar + "config" + separatorChar + "UniversalSingularities.cfg"));
		universalSingularities.forEach(universalSingularity -> {
			if (config.get(universalSingularity.name, "enabled", true).getBoolean()) {
				universalSingularity.singularities.forEach(universalSingularityWrapper -> {
					if (config.get(universalSingularity.name, universalSingularityWrapper.name, true).getBoolean()) {
						if (!allowed.containsKey(universalSingularity.name))
							allowed.put(universalSingularity.name, new HashSet<>());
						allowed.get(universalSingularity.name).add(universalSingularityWrapper.name);
					}
				});
				singularities.add(new UniversalSingularityItem(universalSingularity.name, universalSingularity.singularities));
			}
		});
		if (config.hasChanged())
			config.save();
	}

	public final void preInit()
	{
		singularities.forEach(singularity -> GameRegistry.registerItem(singularity, "universal." + singularity.name + ".singularity"));
	}

	public void init() {}

	public final void postInit()
	{
		if (!craftingOnly)
			addToRecipeInput();
	}

	private void addToRecipeInput()
	{
		singularities.forEach(singularity -> {
			for (int i = 0; i < singularity.universalSingularities.size(); i++) {
				final UniversalSingularityWrapper universalSingularityWrapper = singularity.universalSingularities.get(i);
				if (allowed.get(singularity.name).contains(universalSingularityWrapper.name)) {
					final List<ItemStack> oreList = OreDictionary.getOres(universalSingularityWrapper.oreName, false);
					if (oreList != null && !oreList.isEmpty()) {
						CompressorManager.addOreRecipe(new ItemStack(singularity, 1, i), universalSingularityWrapper.recipeBaseValue, universalSingularityWrapper.oreName);
						Grinder.catalyst.getInput().add(new ItemStack(singularity, 1, i));
					}
				}
			}
		});
	}

	public UniversalSingularityItem getSingularityByName(@Nonnull final String name)
	{
		for (final UniversalSingularityItem singularity : singularities)
			if (singularity.name.equals(name))
				return singularity;
		return null;
	}
}
