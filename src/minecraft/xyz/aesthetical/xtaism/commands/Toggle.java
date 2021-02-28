package xyz.aesthetical.xtaism.commands;

import java.util.List;

import net.minecraft.client.Minecraft;
import xyz.aesthetical.xtaism.Xtaism;
import xyz.aesthetical.xtaism.entities.commands.Command;
import xyz.aesthetical.xtaism.entities.commands.annotations.Cmd;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.utils.ChatUtil;
import xyz.aesthetical.xtaism.utils.ChatUtil.ChatColor;

@Cmd(
	name = "toggle", 
	aliases = {"t"}, 
	description = "Toggles hacks", 
	usage = "<hackname>", 
	needsArgs = true
)
public class Toggle extends Command {
	@Override
	public void execute(List<String> args) {
		Xtaism xtaism = Minecraft.getMinecraft().xtaism;
		
		Mod hack = xtaism.getHackManager().getHack(String.join("", args));
		
		if (hack == null) {
			send(xtaism.getCommandManager().xtaismChat + "I couldn't find anything for that. Check your spelling?");
		} else {
			hack.toggle();
			
			send(
				xtaism.getCommandManager().xtaismChat +
				(hack.isToggled() ? "Enabled " : "Disabled ") + hack.getName()
			);
		}
	}
}
