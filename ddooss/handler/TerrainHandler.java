package ddooss.handler;

import ddooss.world.gen.layer.GenLayerCustom;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TerrainHandler {
	@SubscribeEvent
	public void initializeAllBiomeGenerators(InitBiomeGens event) {
		GenLayer[] genlayer = GenLayerCustom.initializeAllBiomeGenerators(event.getSeed(), event.getWorldType());
		event.setNewBiomeGens(genlayer);
	}
}
