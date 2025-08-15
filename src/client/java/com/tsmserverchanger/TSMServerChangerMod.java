package com.charleshsu.tsmserverchanger;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class TSMServerChangerMod implements ClientModInitializer {
    public static final String MOD_ID = "tsm_server_changer";
    
    private static KeyBinding serverMenuKeyBinding;
    
    @Override
    public void onInitializeClient() {
        // 註冊按鍵綁定 (預設為 'V' 鍵)
        serverMenuKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.tsm_server_changer.open_menu", // 翻譯鍵
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V, // 預設按鍵
                "category.tsm_server_changer" // 分類
        ));
        
        // 註冊 tick 事件處理器
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (serverMenuKeyBinding.wasPressed()) {
                openServerMenu();
            }
        });
        
        System.out.println("TSM Server Changer Mod 已載入！按 V 鍵開啟伺服器選擇介面。");
    }
    
    private void openServerMenu() {
        MinecraftClient client = MinecraftClient.getInstance();
        
        // 檢查是否在正確的伺服器
        if (client.player != null && client.getCurrentServerEntry() != null) {
            String serverAddress = client.getCurrentServerEntry().address;
            
            if (serverAddress.contains("mc.stud3nt.xyz") || serverAddress.contains("stud3nt.xyz")) {
                client.setScreen(new ServerSelectionScreen());
            } else {
                // 如果不在目標伺服器，顯示提示訊息
                client.player.sendMessage(Text.literal("§c此功能只能在 TSM 伺服器中使用！"), false);
            }
        } else if (client.player != null) {
            client.player.sendMessage(Text.literal("§c請先連接到 TSM 伺服器！"), false);
        }
    }
    
    public static void changeServer(int serverNumber) {
        MinecraftClient client = MinecraftClient.getInstance();
        
        if (client.player != null) {
            String command = "/server " + serverNumber;
            
            // 發送伺服器切換指令
            if (client.getNetworkHandler() != null) {
                client.getNetworkHandler().sendChatCommand(command.substring(1)); // 移除 "/" 符號
                client.player.sendMessage(Text.literal("§a正在切換到第 " + serverNumber + " 伺服器..."), false);
            }
        }
    }
}