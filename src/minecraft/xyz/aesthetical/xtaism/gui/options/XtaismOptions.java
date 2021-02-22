package xyz.aesthetical.xtaism.gui.options;

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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

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
	
	public void saveSettings() throws FileNotFoundException {
		PrintWriter writer;
		
		try {			
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.optionsFile), StandardCharsets.UTF_8));
			
			writer.printf("showFps=%s", SHOW_FPS);	
			writer.printf("showCoords=%s", SHOW_COORDS);	
			
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
		}
		
		return true;
	}
	
	public enum Opt {
		SHOW_FPS,
		SHOW_COORDS
	}
}
