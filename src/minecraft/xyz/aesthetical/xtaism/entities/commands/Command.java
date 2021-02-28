package xyz.aesthetical.xtaism.entities.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import xyz.aesthetical.xtaism.entities.Feature;
import xyz.aesthetical.xtaism.entities.commands.annotations.Cmd;
import xyz.aesthetical.xtaism.utils.ChatUtil;
import xyz.aesthetical.xtaism.utils.ChatUtil.ChatColor;

public abstract class Command implements Feature {
	public Cmd getCmdInfo() throws NullPointerException {
		return this.getClass().getDeclaredAnnotation(Cmd.class);
	}
	
	public String getName() {
		return getCmdInfo().name();
	}
	
	public String[] getAliases() {
		return getCmdInfo().aliases();
	}
	
	public String getDescription() {
		return getCmdInfo().description();
	}
	
	public String getUsage() {
		return getCmdInfo().usage();
	}
	
	public void execute(List<String> args) {}
	
	public void send(String text) {
		Minecraft.getMinecraft().player.sendMessage(new TextComponentString(text));
	}
	
	public void invalidArgs() {
		String prefix = Minecraft.getMinecraft().xtaism.getCommandManager().getPrefix();
		
		send(
			ChatUtil.text(ChatColor.RED, true, "Xtaism") + 
			"Invalid usage. Try: " +
			ChatUtil.text(ChatColor.DARK_GRAY, true, prefix + getName() + " " + getUsage())
		);
	}
	
	public String parseCustom(String arg, String key, List<String> args) {
		return "";
	}
}
