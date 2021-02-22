package xyz.aesthetical.xtaism.gui.options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class XtaismGUISettings extends GuiScreen {
	protected XtaismOptions options;
	protected GuiScreen lastScreen;
	
	public XtaismGUISettings(XtaismOptions options, GuiScreen lastScreen) {
		this.options = options;
		this.lastScreen = lastScreen;
	}
	
	public void initGui() {
        this.buttonList.add(new GuiButton(420, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done")));
	}
	
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
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
