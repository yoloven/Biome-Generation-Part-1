package ddooss.init;

import java.util.ArrayList;
import java.util.List;

import ddooss.util.enums.EBiome;
import ddooss.util.interfaces.IBiome;
import ddooss.world.biome.BiomeLapis;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeInit {

	public static final BiomeDictionary.Type LAPIS = BiomeDictionary.Type.getType("LAPIS");
	/**
	 * Список наших биомов
	 */
	public static final List<Biome> BIOMES = new ArrayList<Biome>();

	public static final Biome LAPISLAND = new BiomeLapis("lapisalnd", new Biome.BiomeProperties("Lapisland").setBaseHeight(-0.1F).setHeightVariation(0.1F).setTemperature(0.8F).setRainfall(0.7F), EBiome.LAPISLAND, BiomeLapis.LapisType.NORMAL);

	public static final Biome LAPISLAND_EDGE = new BiomeLapis("lapisalnd_edge", new Biome.BiomeProperties("Lapisland Edge").setBaseHeight(0.9F).setHeightVariation(0.1F).setTemperature(0.8F).setRainfall(0.7F), EBiome.LAPIS, BiomeLapis.LapisType.EDGE);

	public static final Biome LAPISVALLEY = new BiomeLapis("lapisvalley", new Biome.BiomeProperties("Lapisvalley").setBaseBiome("lapisalnd").setBaseHeight(-0.1F).setHeightVariation(0.1F).setTemperature(0.8F).setRainfall(0.7F), EBiome.LAPIS, BiomeLapis.LapisType.MUTATED);

	/**
	 * Метод, в котором вызываем регистрацию всех биомов из BIOMES
	 */
	public static void initBiomes() {
		for (Biome biome : BIOMES) {
			if (biome instanceof IBiome) {
				((IBiome) biome).registerBiome();
			}
		}
	}
}
