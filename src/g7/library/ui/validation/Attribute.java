package g7.library.ui.validation;

import javafx.scene.control.Control;

public class Attribute <T> {
	public String name;
	public T control;
	public Attribute( String name, T fieldControls) {
		this.name = name;
		this.control = fieldControls;
	}
}
