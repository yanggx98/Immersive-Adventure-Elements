package io.github.yanggx98.immersive.aelements;

import com.google.common.collect.Lists;
import io.github.yanggx98.immersive.aelements.gemslot.GemSlotClient;
import net.fabricmc.api.ClientModInitializer;

import java.util.List;

public class ImmersiveAdventureElementsClient implements ClientModInitializer {
	private static final List<IAEClientModule> modules = Lists.newArrayList(new GemSlotClient());
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		modules.forEach(IAEClientModule::onClientModuleInitialize);
	}
}