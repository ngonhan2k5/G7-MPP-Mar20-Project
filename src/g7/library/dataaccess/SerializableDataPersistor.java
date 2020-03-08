package g7.library.dataaccess;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import g7.library.dataaccess.storage.StorageType;
import g7.library.utils.SerializableUtil;

public class SerializableDataPersistor<E> {
	private final StorageType type;
	private final E data;
	
	public SerializableDataPersistor(StorageType type, E data) {
		this.type = type;
		this.data = data;
	}

	public SaveMessage save() {
		SaveMessage message = new SaveMessage();
		
		try {
			SerializableUtil.saveToStorage(type, data);
			message.setSuccessed(true);
			message.setMessage(type + " Saved Successfully.");
			System.out.println(type + " Saved Successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			message.setE(e);
			message.getErrors().add(type + " Saved Unsuccessfully. Failed :)");
			message.setSuccessed(false);
			System.out.println(type + " Saved Unsuccessfully. Failed :)");
		}
		
		return message;
	}
	
	public static class SaveMessage {
		private String message;
		private boolean isSuccessed;
		private Set<String> errors = new HashSet<String>();
		private Throwable e;
		
		public String showErrors() {
			String errorMsgs = "";
			for(String e : errors)
				errorMsgs += e + "\n";
			return errorMsgs;
		}
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public boolean isSuccessed() {
			return isSuccessed;
		}
		public void setSuccessed(boolean isSuccessed) {
			this.isSuccessed = isSuccessed;
		}
		public Set<String> getErrors() {
			return errors;
		}
		public void setErrors(Set<String> errors) {
			this.errors = errors;
		}
		public Throwable getE() {
			return e;
		}
		public void setE(Throwable e) {
			this.e = e;
		}
		@Override
		public String toString() {
			return "SaveMessage [message=" + message + ", isSuccessed=" + isSuccessed + ", errors=" + errors + ", e="
					+ e + ", getMessage()=" + getMessage() + ", isSuccessed()=" + isSuccessed() + ", getErrors()="
					+ getErrors() + ", getE()=" + getE() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
					+ ", toString()=" + super.toString() + "]";
		}
	}
}
