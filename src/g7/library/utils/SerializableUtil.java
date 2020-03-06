package g7.library.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


public class SerializableUtil {
	public static String OUTPUT_DIR = System.getProperty("user.dir") + "\\src\\lib\\dataaccess\\storage";
	
	public static <T, E> void saveToStorage(T type, E obj) throws IOException {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(obj);
		} finally {
			if(out != null) out.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T, E> E readFromStorage(T type) throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		E retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = (E)in.readObject();
		} finally {
			if(in != null) in.close();
		}
		return retVal;
	}
}
