package xyz.aesthetical.xtaism.entities.commands.annotations;

import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
public @interface Cmd {
	String name();
	String[] aliases();
	String description() default "No description provided";
	String usage() default "";
	boolean needsArgs() default false;
}
