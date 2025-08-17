package name.modid;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;

public class TSMserverchanger20 implements ModInitializer {
    private static final String TARGET_SERVER = "mc.stud3nt.xyz";
    private static boolean sentSwitchCommand = false;

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
                String serverAddress = client.getCurrentServerEntry() != null ? client.getCurrentServerEntry().address : "";
                sentSwitchCommand = false;
                if (serverAddress.contains(TARGET_SERVER)) {
                    client.execute(() -> {
                        if (!sentSwitchCommand) {
                            client.player.networkHandler.sendChatMessage("/server server02");
                            sentSwitchCommand = true;
                        }
                    });
                }
            });
        }
    }
}