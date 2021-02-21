package xyz.aesthetical.xtaism.entities.hacks.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Hack {
	String name();
	String description() default "No description provided for hack";
	int color();
}
