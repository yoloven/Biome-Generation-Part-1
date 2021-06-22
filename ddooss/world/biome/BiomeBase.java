package ddooss.world.biome;

import ddooss.init.BiomeInit;
import ddooss.lib.Reference;
import ddooss.util.enums.EBiome;
import ddooss.util.interfaces.IBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public abstract class BiomeBase extends Biome implements IBiome {

	// Тип BiomeType, определяющий генерацию биома
	private final BiomeManager.BiomeType biomeType;

	// Вес биома
	private final int weight;

	// Типы биома
	private final BiomeDictionary.Type[] types;

	public BiomeBase(String registryName, Biome.BiomeProperties properties, EBiome eBiome) {
		super(properties);
		this.setRegistryName(Reference.MODID, registryName);
		this.biomeType = eBiome.getBiomeType();
		this.weight = eBiome.getWeight();
		this.types = eBiome.getTypes();
		BiomeInit.BIOMES.add(this);
	}

	@Override
	public void registerBiome() {
		ForgeRegistries.BIOMES.register(this);
		BiomeDictionary.addTypes(this, this.types);

		if (this.weight > 0) {
			BiomeManager.addBiome(biomeType, new BiomeEntry(this, this.weight));
			BiomeManager.addSpawnBiome(this);
		}
	}
}
