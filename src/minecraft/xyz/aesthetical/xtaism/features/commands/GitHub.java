package xyz.aesthetical.xtaism.features.commands;

import java.util.List;

import net.minecraft.client.Minecraft;
import xyz.aesthetical.xtaism.Xtaism;
import xyz.aesthetical.xtaism.entities.commands.Command;
import xyz.aesthetical.xtaism.entities.commands.annotations.Cmd;
import xyz.aesthetical.xtaism.utils.ChatUtil;
import xyz.aesthetical.xtaism.utils.ChatUtil.ChatColor;

@Cmd(
	name = "github",
	aliases = {"gh", "code"},
	description = "Shows the GitHub link for Xtaism"
)
public class GitHub extends Command {
	@Override
	public void execute(List<String> args) {
		Xtaism xtaism = Minecraft.getMinecraft().xtaism;
		
		send(
			xtaism.getCommandManager().xtaismChat + 
			"Xtaism's code can be viewed at " + ChatUtil.text(ChatColor.GOLD, false, xtaism.githubUrl)
		);
	}
}
