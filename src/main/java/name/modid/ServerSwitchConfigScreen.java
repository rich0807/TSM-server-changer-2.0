package name.modid;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ServerSwitchConfigScreen extends Screen {
    private static final String[] options = {"不自動發送", "server01", "server02"};
    private static int selected = 0;

    public static String getSelectedCommand() {
        return selected == 0 ? null : options[selected];
    }

    public ServerSwitchConfigScreen() {
        super(Text.literal("分服自動切換設定"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // 選擇自動指令
        this.addDrawableChild(ButtonWidget.builder(Text.literal("自動指令: " + options[selected]), btn -> {
            selected = (selected + 1) % options.length;
            btn.setMessage(Text.literal("自動指令: " + options[selected]));
        }).dimensions(centerX - 100, centerY - 20, 200, 20).build());

        // 立即發送按鈕
        this.addDrawableChild(ButtonWidget.builder(Text.literal("立即發送"), btn -> {
            sendCurrentCommand();
        }).dimensions(centerX - 100, centerY + 10, 200, 20).build());
    }

    private void sendCurrentCommand() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.player.networkHandler != null && selected != 0) {
            client.player.networkHandler.sendChatCommand("server " + options[selected]);
        }
    }
}