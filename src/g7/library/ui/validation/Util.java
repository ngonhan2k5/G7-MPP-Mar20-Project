package g7.library.ui.validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class Util {
	public static boolean isInRangeAtoZ(char c) {
		return (int)'A' <= (int)c && (int)c <= (int)'Z';
	}
	public static boolean isInRangeatoz(char c) {
		return (int)'a' <= (int)c && (int)c <= (int)'z';
	}
	
	
	public static Boolean objectHasProperty(Object obj, String propertyName){
	    List<Field> properties = getAllFields(obj);
	    for(Field field : properties){
	        if(field.getName().equalsIgnoreCase(propertyName)){
	            return true;
	        }
	    }
	    return false;
	}
	
	private static List<Field> getAllFields(Object obj){
	    List<Field> fields = new ArrayList<Field>();
	    getAllFieldsRecursive(fields, obj.getClass());
	    return fields;
	}

	private static List<Field> getAllFieldsRecursive(List<Field> fields, Class<?> type) {
	    for (Field field: type.getDeclaredFields()) {
	        fields.add(field);
	    }

	    if (type.getSuperclass() != null) {
	        fields = getAllFieldsRecursive(fields, type.getSuperclass());
	    }

	    return fields;
	}
	
	public static void showAlert(String msg, String headerText, String desc) {
	    Platform.runLater(new Runnable() {
	      public void run() {
	          Alert alert = new Alert(Alert.AlertType.ERROR);
	          alert.setTitle("Error");
	          alert.setHeaderText(msg);
	          alert.setContentText(desc);
	          alert.showAndWait();
	      }
	    });
	}
	
	public static void showAlert(String msg, String headerText) {
		Util.showAlert(msg, headerText, "");
	}
	
	public static void showAlert(String msg) {
		Util.showAlert(msg, "Error", "");
	}
}
