package ddooss.world.biome;

import java.util.Random;

import ddooss.util.enums.EBiome;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeLapis extends BiomeBase {

	private final LapisType type;

	public BiomeLapis(String registryName, Biome.BiomeProperties properties, EBiome eBiome, LapisType type) {
		super(registryName, properties, eBiome);
		this.type = type;

		if (this.type == LapisType.EDGE) {
			this.decorator.extraTreeChance = 0F;
			this.topBlock = Blocks.STONE.getDefaultState();
			this.fillerBlock = Blocks.STONE.getDefaultState();
		} else if (this.type == LapisType.MUTATED) {
			this.decorator.treesPerChunk = 3;
		}
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return this.type == LapisType.MUTATED ? BIG_TREE_FEATURE : TREE_FEATURE;
	}

	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return this.type == LapisType.EDGE ? super.getGrassColorAtPos(pos) : 0x00ffff;
	}

	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return this.type == LapisType.EDGE ? super.getFoliageColorAtPos(pos) : 0x00ffff;
	}

	public static enum LapisType {
		NORMAL, EDGE, MUTATED;
	}
}
