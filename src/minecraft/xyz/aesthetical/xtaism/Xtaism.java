package xyz.aesthetical.xtaism;

import java.io.IOException;

import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import xyz.aesthetical.xtaism.gui.clickgui.GUIManager;
import xyz.aesthetical.xtaism.gui.options.XtaismOptions;
import xyz.aesthetical.xtaism.managers.*;

public class Xtaism {
	public XtaismOptions settings;
	public GUIManager gui;
	
	protected CommandManager commandManager;
	protected HackManager hackManager;
		
	public void init() throws InstantiationException, IllegalAccessException, IOException {
		this.settings = new XtaismOptions();
		this.gui = new GUIManager();

		this.hackManager = new HackManager();
		this.commandManager = new CommandManager();
		
		this.hackManager.load();
		this.commandManager.load();
		
		gui.setTheme(new SimpleTheme());
		gui.setup();
	}
	
	public HackManager getHackManager() {
		return hackManager;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}
}
