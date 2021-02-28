package xyz.aesthetical.xtaism;

import java.io.File;
import java.io.IOException;

import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import net.minecraft.client.Minecraft;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions;
import xyz.aesthetical.xtaism.features.gui.clickgui.GUIManager;
import xyz.aesthetical.xtaism.managers.*;

public class Xtaism {
	public XtaismOptions settings;
	public GUIManager gui;
	
	protected CommandManager commandManager;
	protected HackManager hackManager;
	
	public String version = "0.5-beta";
	public String githubUrl = "https://github.com/Sxmurai/xtaism/tree/1.12.2";
		
	public void init() throws InstantiationException, IllegalAccessException, IOException {
		this.settings = new XtaismOptions();
		this.gui = new GUIManager();

		this.hackManager = new HackManager();
		this.commandManager = new CommandManager();
		
		this.hackManager.load();
		this.commandManager.load();
		
		try {
			boolean wasSuccessful = this.settings.loadOptions();

			if (!wasSuccessful) {
				new File(Minecraft.getMinecraft().gameDir, "optionsXtaism.txt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.gui.setTheme(new SimpleTheme());
		this.gui.setup();
	}
	
	public HackManager getHackManager() {
		return hackManager;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}
}
