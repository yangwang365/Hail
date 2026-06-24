# Hail 编译文档

## 环境要求

### 必需组件

| 组件 | 版本要求 | 说明 |
|------|---------|------|
| JDK | 17 或更高 | Android Gradle Plugin 8.12.3+ 需要 JDK 17+ |
| Android SDK | 最新版 | 包含 Android SDK Build-Tools、Platform-Tools |
| Gradle | 8.13（项目自带） | 使用项目自带的 Gradle Wrapper |

### 推荐配置

- **Android Studio** 最新版（自带 JDK 21，满足要求）
- **Windows**：PowerShell 5.1+ 或 CMD

---

## 环境配置

### 1. 配置 `local.properties`

在项目根目录创建 `local.properties` 文件，指向 Android SDK：

```properties
sdk.dir=C:/Users/<用户名>/AppData/Local/Android/Sdk
```

### 2. 配置 `gradle.properties`（如需要）

如果系统默认 JDK 版本低于 17，需在 `gradle.properties` 中指定 JDK 路径：

```properties
# 使用 Android Studio 自带的 JDK
org.gradle.java.home=C:/Program Files/Android/Android Studio/jbr
```

> **提示**：Android Studio 的 `jbr` 目录自带 JDK 21，可直接使用。

---

## 编译步骤

### 方式一：命令行编译（推荐）

在项目根目录执行：

```bash
# Windows CMD
gradlew.bat assembleRelease

# PowerShell
.\gradlew.bat assembleRelease

# 先 clean 再编译
gradlew.bat clean assembleRelease
```

编译成功后，APK 位于：
```
app/build/outputs/apk/release/Hail-v1.10.0-g79b008b.apk
```

### 方式二：Android Studio 编译

1. 打开 Android Studio
2. 打开项目（`e:\Code\Hail`）
3. 点击菜单 **Build → Build Bundle(s) / APK(s) → Build APK(s)**
4. 编译完成后，APK 位于上述路径

---

## 编译变体

| 命令 | 说明 |
|------|------|
| `gradlew.bat assembleDebug` | 编译 Debug 版本（未混淆，可调试） |
| `gradlew.bat assembleRelease` | 编译 Release 版本（混淆优化，需签名） |
| `gradlew.bat assemble` | 同时编译 Debug 和 Release |

---

## 签名配置

Release 编译需要配置签名。在项目根目录创建 `signing.properties`：

```properties
storeFile=path/to/your/keystore.jks
storePassword=your_store_password
keyAlias=your_key_alias
keyPassword=your_key_password
```

可参考 `signing.properties.sample` 文件。

---

## 常见问题

### 1. 编译报错：`Android Gradle Plugin requires JDK 17 or later`

**原因**：系统默认 JDK 版本过低。

**解决**：在 `gradle.properties` 中配置 `org.gradle.java.home` 指向 JDK 17+ 路径。

### 2. 编译报错：`SDK location not found`

**原因**：缺少 `local.properties` 文件。

**解决**：在项目根目录创建 `local.properties`，配置 `sdk.dir` 指向 Android SDK 路径。

### 3. R8 优化阶段失败

**原因**：Gradle 缓存损坏。

**解决**：
```bash
gradlew.bat clean assembleRelease
```

或手动删除 `app/build` 和 `build` 目录后重新编译。

### 4. 编译超时或卡住

**原因**：Gradle 守护进程异常。

**解决**：
```bash
gradlew.bat --stop
```
然后重新编译。

---

## 输出文件

| 路径 | 说明 |
|------|------|
| `app/build/outputs/apk/release/` | Release APK 输出目录 |
| `app/build/outputs/apk/debug/` | Debug APK 输出目录 |
| `app/build/outputs/bundle/` | AAB 包输出目录 |
| `app/build/reports/` | 编译报告和 Lint 报告 |
