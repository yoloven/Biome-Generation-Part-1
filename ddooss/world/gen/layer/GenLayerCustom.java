package ddooss.world.gen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerCustom extends GenLayer {

	public GenLayerCustom(long seed) {
		super(seed);
	}

	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType) {
		GenLayer genlayer = new GenLayerIsland(1L);
		genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
		GenLayer genlayeraddisland = new GenLayerAddIsland(1L, genlayer);
		GenLayer genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
		GenLayer genlayeraddisland1 = new GenLayerAddIsland(2L, genlayerzoom);
		genlayeraddisland1 = new GenLayerAddIsland(50L, genlayeraddisland1);
		genlayeraddisland1 = new GenLayerAddIsland(70L, genlayeraddisland1);
		GenLayer genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland1);
		GenLayer genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
		GenLayer genlayeraddisland2 = new GenLayerAddIsland(3L, genlayeraddsnow);
		GenLayer genlayeredge = new GenLayerEdge(2L, genlayeraddisland2, GenLayerEdge.Mode.COOL_WARM);
		genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
		genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
		GenLayer genlayerzoom1 = new GenLayerZoom(2002L, genlayeredge);
		genlayerzoom1 = new GenLayerZoom(2003L, genlayerzoom1);
		GenLayer genlayeraddisland3 = new GenLayerAddIsland(4L, genlayerzoom1);
		GenLayer genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland3);
		GenLayer genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
		GenLayer genlayer4 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
		int biomeSize = 4;
		int riverSize = biomeSize;

		if (worldType == WorldType.LARGE_BIOMES) {
			biomeSize = 6;
		}

		biomeSize = GenLayer.getModdedBiomeSize(worldType, biomeSize);
		GenLayer lvt_7_1_ = GenLayerZoom.magnify(1000L, genlayer4, 0);
		GenLayer genlayerriverinit = new GenLayerRiverInit(100L, lvt_7_1_);
		GenLayer genlayerbiome = new GenLayerBiome(200L, genlayer4, worldType, null);
		GenLayer ret = GenLayerZoom.magnify(1000L, genlayerbiome, 2);
		GenLayer genlayerbiomeedge = new GenLayerBiomeEdge(1000L, ret);
		GenLayer lvt_9_1_ = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
		GenLayer genlayerhills = new GenLayerHills(1000L, genlayerbiomeedge, lvt_9_1_);
		GenLayer genlayer5 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
		genlayer5 = GenLayerZoom.magnify(1000L, genlayer5, riverSize);
		GenLayer genlayerriver = new GenLayerRiver(1L, genlayer5);
		GenLayer genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
		genlayerhills = new GenLayerRareBiome(1001L, genlayerhills);

		for (int k = 0; k < biomeSize; k++) {
			genlayerhills = new GenLayerZoom((long) (1000 + k), genlayerhills);

			if (k == 0) {
				genlayerhills = new GenLayerAddIsland(3L, genlayerhills);
			}

			if (k == 1 || biomeSize == 1) {
				genlayerhills = new GenLayerShoreCustom(1000L, genlayerhills); // ����� ��������� ��������� ����� � ��������� ������
			}
		}

		GenLayer genlayersmooth1 = new GenLayerSmooth(1000L, genlayerhills);
		GenLayer genlayerrivermix = new GenLayerRiverMixCustom(100L, genlayersmooth1, genlayersmooth); // ����� ��������� ���
		GenLayer genlayer3 = new GenLayerVoronoiZoom(10L, genlayerrivermix);
		genlayerrivermix.initWorldGenSeed(seed);
		genlayer3.initWorldGenSeed(seed);
		return new GenLayer[] { genlayerrivermix, genlayer3, genlayerrivermix };
	}
}
