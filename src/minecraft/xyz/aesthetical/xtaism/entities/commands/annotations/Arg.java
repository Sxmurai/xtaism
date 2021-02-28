package xyz.aesthetical.xtaism.entities.commands.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import xyz.aesthetical.xtaism.entities.commands.ArgType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(value = Args.class)
public @interface Arg {
	String key();
	int index();
	ArgType type() default ArgType.STRING;
}