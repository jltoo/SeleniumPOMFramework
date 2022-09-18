package propertyfilereader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyFileReaderWriter {

	/** The property file name. */
	private String propFilePath;

	/** The property object. */
	private Properties properties;

	/**
	 * @param propertyfile
	 *            the text file
	 * @throws IOException 
	 */
	public PropertyFileReaderWriter(final String propertyfile) {
		this.propFilePath = propertyfile;
		//loadProperty();
	}


	/**
	 * Load the properties
	 * @throws IOException 
	 */
	private final void loadProperty() {

		InputStream in = null;

		try {
			in = new FileInputStream(propFilePath);
			properties = new Properties();
			properties.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	public String getproperty(String key) {
		loadProperty();
		String value = properties.getProperty(key);
		return value;
	}
	
	/**
	 * @param key, value
	 * @return
	 * @throws IOException 
	 */
	public void writeData(String key, String value) {
		loadProperty();
		OutputStream out = null;
		try {
			out = new FileOutputStream(propFilePath);
			properties.setProperty(key, value);
			properties.store(out, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}//end
