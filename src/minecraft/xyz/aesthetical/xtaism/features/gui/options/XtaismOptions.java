package xyz.aesthetical.xtaism.features.gui.options;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

public class XtaismOptions {
	private static boolean SHOW_FPS = true;
	private static boolean SHOW_COORDS = true;
	
	public File optionsFile;
	
	public XtaismOptions() throws IOException {
		optionsFile = new File(Minecraft.getMinecraft().gameDir, "optionsXtaism.txt");
		if (!optionsFile.exists()) {
			optionsFile.createNewFile();
		}
	}
	
	public boolean shouldShowFPS() {
		return SHOW_FPS;
	}
	
	public boolean shouldShowCoords() {
		return SHOW_COORDS;
	}
	
	public void set(Opt option, boolean value) {
		switch (option) {
			case SHOW_FPS:
				SHOW_FPS = value;
				break;
				
			case SHOW_COORDS:
				SHOW_COORDS = value;
				break;
		}
	}
	
	public void setKeybind(KeybindOpt keybind, int key) {
		keybind.setKey(key);
	}
	
	public void saveSettings() throws FileNotFoundException {
		PrintWriter writer;
		
		try {			
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.optionsFile), StandardCharsets.UTF_8));
			
			writer.printf("showFps=%s\n", SHOW_FPS);	
			writer.printf("showCoords=%s\n", SHOW_COORDS);	
			
			// keybinds
			for (KeybindOpt k : KeybindOpt.values()) {
				if (k.getName() == "default") {
					continue;
				}
				
				writer.printf("%s=%s\n", "key_" + k.getName(), k.getKey());
			}
			
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean loadOptions() throws FileNotFoundException, IOException {
		if (!this.optionsFile.exists()) {
			return false;
		}
		
		List<String> settings = IOUtils.readLines(new FileInputStream(this.optionsFile));
		
		for (String line : settings) {
			String[] data = line.split("=");
			
			switch (data[0].toLowerCase()) {
				case "showFps":
					SHOW_FPS = data[1].equals("true");
					break;
					
				case "showCoords":
					SHOW_COORDS = data[1].equals("true");
					break;
			}
			
			if (data[0].toLowerCase().startsWith("key_")) {
				String actualName = data[0].toLowerCase().substring(4);
				
				for (KeybindOpt k : KeybindOpt.values()) {
					if (k.getName() == actualName) {
						k.setKey(Integer.parseInt(data[1]));
					}
				}
			}
		}
		
		return true;
	}
	
	public enum Opt {
		SHOW_FPS,
		SHOW_COORDS,
	}
	
	public enum KeybindOpt {
		KEYBIND_DEFAULT("default", -1),
		
		// Blocks
		KEYBIND_ANTICACTUS("antiCactus"),
		
		// Combat
		KEYBIND_AUTOCRYSTAL("autoCrystal"),
		KEYBIND_AUTOTOTEM("autoTotem"),
		KEYBIND_CRITICALS("crits"),
		KEYBIND_KILLAURA("killAura"),
		
		// Movement
		KEYBIND_AUTOSNEAK("autoSneak"),
		KEYBIND_AUTOSPRINT("autoSprint"),
		KEYBIND_BLINK("blink"),
		KEYBIND_BUNNYHOP("bHop"),
		KEYBIND_FLIGHT("flight"),
		KEYBIND_JESUS("jesus"),
		KEYBIND_SPIDER("spider"),
		
		// Other
		KEYBIND_FANCYCHAT("fancyChat"),
		KEYBIND_CLICKGUI("clickGui", Keyboard.KEY_RSHIFT),
		
		// Player
		KEYBIND_AUTORESPAWN("autoRespawn"),
		KEYBIND_NOFALL("noFall"),
		
		// Render
		KEYBIND_CHESTESP("chestEsp"),
		KEYBIND_FREECAM("freeCam"),
		KEYBIND_FULLBRIGHT("fullBright"),
		KEYBIND_PLAYERESP("playerEsp");
		
		private String name;
		private int key = -1;
		
		private KeybindOpt(String name) {
			this.name = name;
		}
		
		private KeybindOpt(String name, int key) {
			this.name = name;
			this.key = key;
		}
		
		
		public String getName() {
			return name;
		}
		
		public void setKey(int key) {
			this.key = key;
		}
		
		public int getKey() {
			return this.key;
		}
	}
}
