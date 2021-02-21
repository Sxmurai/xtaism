package xyz.aesthetical.xtaism.entities.hacks.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Keybind {
	int key() default -1;
}
