package com.charleshsu.tsmserverchanger;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ServerSelectionScreen extends Screen {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 25;
    
    public ServerSelectionScreen() {
        super(Text.literal("TSM 伺服器選擇"));
    }
    
    @Override
    protected void init() {
        super.init();
        
        int centerX = this.width / 2;
        int startY = this.height / 2 - 60;
        
        // 標題
        int titleY = startY - 40;
        
        // 伺服器1按鈕
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("§a第1伺服器 §7(主伺服器)"),
                button -> {
                    TSMServerChangerMod.changeServer(1);
                    this.close();
                })
                .dimensions(centerX - BUTTON_WIDTH / 2, startY, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
        
        // 伺服器2按鈕
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("§b第2伺服器 §7(次伺服器)"),
                button -> {
                    TSMServerChangerMod.changeServer(2);
                    this.close();
                })
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + BUTTON_SPACING, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
        
        // 伺服器3按鈕 (如果有的話)
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("§e第3伺服器 §7(備用伺服器)"),
                button -> {
                    TSMServerChangerMod.changeServer(3);
                    this.close();
                })
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + BUTTON_SPACING * 2, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
        
        // 大廳按鈕
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("§d返回大廳"),
                button -> {
                    if (this.client != null && this.client.getNetworkHandler() != null) {
                        this.client.getNetworkHandler().sendChatCommand("lobby");
                        if (this.client.player != null) {
                            this.client.player.sendMessage(Text.literal("§a正在返回大廳..."), false);
                        }
                    }
                    this.close();
                })
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + BUTTON_SPACING * 3, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
        
        // 取消按鈕
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("§c取消"),
                button -> this.close())
                .dimensions(centerX - 50, startY + BUTTON_SPACING * 4 + 10, 100, BUTTON_HEIGHT)
                .build());
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 繪製半透明背景
        this.renderBackground(context, mouseX, mouseY, delta);
        
        // 繪製標題
        Text title = Text.literal("§l§6TSM 伺服器選擇器");
        int titleWidth = this.textRenderer.getWidth(title);
        int titleX = (this.width - titleWidth) / 2;
        int titleY = this.height / 2 - 100;
        context.drawText(this.textRenderer, title, titleX, titleY, 0xFFFFFF, true);
        
        // 繪製說明文字
        Text instruction = Text.literal("§7選擇您要切換的伺服器");
        int instructionWidth = this.textRenderer.getWidth(instruction);
        int instructionX = (this.width - instructionWidth) / 2;
        int instructionY = titleY + 15;
        context.drawText(this.textRenderer, instruction, instructionX, instructionY, 0xAAAAAA, false);
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    @Override
    public boolean shouldPause() {
        return false; // 不暫停遊戲
    }
}