package g7.library.ui.validation;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;

public class Attributes<T> {
	private T[] fieldControls;
	private String[] fieldNames;
	private List<Attribute<T>> list = new ArrayList<>();
	
	public T[] getFieldControls() {
		return fieldControls;
	}

	public void setFieldControls(T[] fieldControls) {
		this.fieldControls = fieldControls;
	}

	public String[] getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	public List<Attribute<T>> getList() {
		return list;
	}

	public void setList(List<Attribute<T>> list) {
		this.list = list;
	}

	public Attributes(String[] fieldNames, T[] fieldControls) {
		if (fieldControls.length != fieldNames.length) return;
		
		this.fieldControls = fieldControls;
		this.fieldNames = fieldNames;
		for(int i=0; i< fieldControls.length; i++)
			list.add(new Attribute<T>(fieldNames[i], fieldControls[i]));
	}
}
