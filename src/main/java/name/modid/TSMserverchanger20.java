package name.modid;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class TSMserverchanger20 implements ModInitializer {
    private static final String TARGET_SERVER = "mc.stud3nt.xyz";
    private static KeyBinding openConfigKey;

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            // 註冊快捷鍵
            openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.tsmserverchanger20.openconfig",
                GLFW.GLFW_KEY_K,
                "category.tsmserverchanger20"
            ));

            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                if (openConfigKey.wasPressed()) {
                    client.setScreen(new ServerSwitchConfigScreen());
                }
            });

            // 連線時自動發送
            ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
                String serverAddress = client.getCurrentServerEntry() != null ? client.getCurrentServerEntry().address : "";
                if (serverAddress.contains(TARGET_SERVER)) {
                    String cmd = ServerSwitchConfigScreen.getSelectedCommand();
                    if (cmd != null) {
                        // 延遲 2 秒發送
                        client.execute(() -> {
                            try { Thread.sleep(2000); } catch (Exception e) {}
                            if (client.player != null && client.player.networkHandler != null) {
                                client.player.networkHandler.sendChatCommand("server " + cmd);
                            }
                        });
                    }
                }
            });
        }
    }
}