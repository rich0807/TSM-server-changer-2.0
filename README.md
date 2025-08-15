# TSM-server-changer 2.0

**TSM-server-changer 2.0** 是一款基於 **Fabric** 的 Minecraft 客戶端 Mod，  
用於在進入伺服器時自動切換到「2 服」。

---

## 功能

- 當玩家連線到 `mc.stud3nt.xyz` 伺服器時，模組會自動發送切換伺服器的指令。  
- 解決掛機時伺服器重啟或斷線重連後自動跑到「1 服」的問題。

---

## 與 CharlesHsu 原版的不同

- **自動切換後不強制回二服**  
  在自動切換完成後，再次進入「1 服」時，不會被強制送回「2 服」。  

- **不再顯示多餘訊息**  
  玩家重新進入伺服器時，不會再看到 `"您已經連接到此伺服器了!"` 的提示。  

---

## 建置與安裝

- **直接下載編譯好的 Mod**（無需自己建置）：  
[點此下載 TSM-server-changer-2.0.jar](https://www.dropbox.com/scl/fi/0drh4xssjc2smi3f33bk9/TSM-server-changer-2.0.jar?rlkey=bmz5pcgosqa0ehnx4a7r6j5av&st=ps8g5dkm&dl=0)

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

- 請確認 **Fabric API** 版本為 `0.119.3`  
- Minecraft 版本建議為 `1.21.4`  
- 所有版本設定均可在 `build.gradle` 中修改

---

## 致謝

本 Mod 改編自 [CharlesHsu 的 TSM_server_changer](https://github.com/CharlesHsu-noob/TSM_server_changer/)
