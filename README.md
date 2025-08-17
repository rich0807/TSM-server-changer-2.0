# TSM-server-changer 2.0

**TSM-server-changer 2.0** 是一款基於 **Fabric** 的 Minecraft 客戶端 Mod，  
用於在TSM(台灣學生麥塊)更方便的切換伺服器。

---

## 功能

- 當玩家連線到 `mc.stud3nt.xyz` 伺服器時，模組會自動根據你的設定發送切換伺服器的指令。
- 預設是不自動發送指令。
- 按"K"打開介面(可於按鍵設定中調整)。
- 可以一鍵切換伺服器。
- 解決掛機時伺服器重啟或斷線重連後自動跑到「1 服」的問題。

---



## 建置與安裝

- **直接下載編譯好的 Mod**（無需自己建置）：  
[點此下載 TSM-server-changer-2.0.jar](https://www.dropbox.com/scl/fi/2k89xagojk7t7hz8hg9p1/tsm-server-changer-2.0.jar?rlkey=b540eskvpudxuepac51kju4zc&st=mt5p9vfs&dl=0)

- **自行建置**（可選）：
  1. 確認已安裝 **Java 21** 與 **Gradle**  
  2. 使用 **Fabric Loom v1.4.1** 進行專案建置  
  3. 執行建置指令：
     - Windows: `gradlew.bat build`  
     - Linux/macOS: `./gradlew build`  
  4. 編譯完成後，`.jar` 檔案會位於 `build/libs` 資料夾  
  5. 將 `.jar` 放入 Minecraft `mods` 資料夾即可使用

---

## 注意事項

- 請確認 **Fabric API** 版本為 `0.119.2`  
- Minecraft 版本建議為 `1.21.4`  
- 所有版本設定均可在 `build.gradle` 中修改

---

## 致謝

本 Mod 改編自 [CharlesHsu 的 TSM_server_changer](https://github.com/CharlesHsu-noob/TSM_server_changer/)
