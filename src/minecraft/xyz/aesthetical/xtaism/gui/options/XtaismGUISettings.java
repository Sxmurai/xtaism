package xyz.aesthetical.xtaism.gui.options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import xyz.aesthetical.xtaism.gui.options.XtaismOptions.Opt;

public class XtaismGUISettings extends GuiScreen {
	protected XtaismOptions options;
	protected GuiScreen lastScreen;
	
	private GuiButton showFpsButton;
	private GuiButton showCoordsButton;
	
	public XtaismGUISettings(XtaismOptions options, GuiScreen lastScreen) {
		this.options = options;
		this.lastScreen = lastScreen;
	}
	
	public void initGui() {
		this.showFpsButton = new GuiButton(418, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, options.shouldShowFPS() ? "Hide FPS Counter" : "Show FPS Counter");
		this.buttonList.add(this.showFpsButton);
		
		this.showCoordsButton =  new GuiButton(419, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, options.shouldShowCoords() ? "Hide Coordinates" : "Show Coordinates");
		this.buttonList.add(this.showCoordsButton);
		
        this.buttonList.add(new GuiButton(420, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done")));
	}
	
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
			case 418:
				options.set(Opt.SHOW_FPS, !options.shouldShowFPS());
				this.showFpsButton.displayString = options.shouldShowFPS() ? "Hide FPS Counter" : "Show FPS Counter";
				break;
			
			case 419:
				options.set(Opt.SHOW_COORDS, !options.shouldShowCoords());
				this.showCoordsButton.displayString = options.shouldShowCoords() ? "Hide Coordinates" : "Show Coordinates";
				break;
		
			case 420:
				options.saveSettings();
				this.mc.displayGuiScreen(lastScreen);
				break;
		}
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Xtaism Options", this.width / 2, 15, 16777215);
		super.drawScreen(mouseX, mouseY, partialTick);
	}
	
	public boolean load() throws FileNotFoundException, IOException {
		return options.loadOptions();
	}
	
	public File getOptionsFile() {
		return options.optionsFile;
	}
}
