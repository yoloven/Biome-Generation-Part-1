package ddooss.util.enums;

import ddooss.init.BiomeInit;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public enum EBiome {
	/**
	 * Окраина лазуритовой земли, Лазуритовая долина
	 */
	LAPIS(BiomeInit.LAPIS),
	/**
	 * Лазуритовая земля
	 */
	LAPISLAND(BiomeManager.BiomeType.WARM, 10, BiomeInit.LAPIS);

	private final BiomeManager.BiomeType biomeType;

	private final int weight;

	private final BiomeDictionary.Type[] types;

	EBiome(BiomeDictionary.Type... types) {
		this(null, 0, types);
	}

	EBiome(BiomeManager.BiomeType biomeType, int weight, BiomeDictionary.Type... types) {
		this.biomeType = biomeType;
		this.weight = weight;
		this.types = types;
	}

	public BiomeManager.BiomeType getBiomeType() {
		return this.biomeType;
	}

	public int getWeight() {
		return this.weight;
	}

	public BiomeDictionary.Type[] getTypes() {
		return this.types;
	}
}
