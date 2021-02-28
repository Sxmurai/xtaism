package xyz.aesthetical.xtaism.managers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import xyz.aesthetical.xtaism.commands.*;
import xyz.aesthetical.xtaism.entities.commands.Command;
import xyz.aesthetical.xtaism.entities.commands.annotations.Arg;
import xyz.aesthetical.xtaism.entities.commands.annotations.Args;
import xyz.aesthetical.xtaism.utils.ChatUtil;
import xyz.aesthetical.xtaism.utils.ChatUtil.ChatColor;

public class CommandManager {
	protected ArrayList<Command> modules = new ArrayList();
	protected Logger LOGGER = LogManager.getLogger(CommandManager.class);
	
	// TODO: make this a minecraft setting
	protected String prefix = "?";
	public String xtaismChat = ChatUtil.text(ChatColor.RED, true, "Xtaism");
	
	public void load() {
		// add shit here
		modules.add(new Toggle());
		modules.add(new Settings());
		
		LOGGER.info("Loaded {} commands", modules.size());
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void handleCommand(String input) {
		if (!input.startsWith(prefix)) {
			return;
		}
		
		List<String> preArgs = Arrays.asList(input.substring(prefix.length()).split(" "));
		
		Command command = null;
		
		for (Command cmd : modules) {
			if (cmd.getName().equalsIgnoreCase(preArgs.get(0))) {
				command = cmd;
				break;
			}
			
			for (String thing : cmd.getAliases()) {
				if (thing.equalsIgnoreCase(preArgs.get(0))) {
					command = cmd;
					break;
				}
			}
		}
		
		if (command != null) {
			List<String> args = preArgs.subList(1, preArgs.size());
			
			if (command.getCmdInfo().needsArgs() && args.isEmpty()) {
				command.invalidArgs();
				return;
			}
			
			Class clazz = command.getClass();
			Method method = null;
			
			for (Method m : clazz.getMethods()) {
				if (m.getName() == "execute") {
					method = m;
					break;
				}
			}
			
			List<Arg> argAnnotated = Arrays.asList(method.getAnnotationsByType(Arg.class));
			
			if (!argAnnotated.isEmpty()) {
				for (Arg argument : argAnnotated) {
					if (argument.index() != -1) {
						String failureString = command.parseCustom(args.get(argument.index()), argument.key(), args);
						
						if (failureString.isEmpty()) {
							continue;
						} else {
							command.send(ChatUtil.text(ChatColor.RED, true, "Xtaism") + failureString);
							return;
						}
					}
				}
			}			
			
			command.execute(args);
		} else {
			Minecraft.getMinecraft().player.sendMessage(
				new TextComponentString(xtaismChat + "Invalid command! Run: " + ChatUtil.text(ChatColor.GRAY, false, prefix + "help"))
			);
		}
	}
}
