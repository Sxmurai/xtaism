package xyz.aesthetical.xtaism.entities.hacks.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

@Retention(RetentionPolicy.RUNTIME)
public @interface Keybind {
	KeybindOpt setting() default KeybindOpt.KEYBIND_DEFAULT;
}
