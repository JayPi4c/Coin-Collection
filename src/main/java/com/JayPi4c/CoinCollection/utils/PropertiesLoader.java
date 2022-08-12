package com.JayPi4c.CoinCollection.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * PropertiesLoader to load and store Properties. Default values for properties
 * will be set here in the loadDefaultProperties() method. Any other changes can
 * be made by obtaining the Properties instance via getInstance().
 *
 * @author Jonas Pohl
 * @since 2.0.0
 */
@Slf4j
public class PropertiesLoader {

	private final Properties properties;

	private static final String FILE = "collection.properties";
	private static final String DEFAULT_CONFIG_FILE = "/config.properties";
	private static final String COMMENTS = "properties for the coin collection program";

	private static PropertiesLoader propertiesLoader;

	/**
	 * Constructor for the PropertiesLoader.
	 */
	private PropertiesLoader() {
		properties = new Properties();
		try (InputStream in = new FileInputStream(getPropertiesFile())) {
			properties.load(in);
		} catch (Exception e) {
			log.error("Error while loading properties. First start is highly likely: {} ", e.getMessage());
			log.debug("Stackrace: {}", e);
			loadDefaultProperties();
		}
	}

	/**
	 * Obtaining an instance of the PropertiesLoader.
	 *
	 * @return the PropertiesLoader instance
	 */
	public static PropertiesLoader getInstance() {
		if (propertiesLoader == null) {
			propertiesLoader = new PropertiesLoader();
		}
		return propertiesLoader;
	}

	/**
	 * Loading the default values for the properties. This method will be called, if
	 * the properties file does not exist.
	 */
	private void loadDefaultProperties() {
		try (InputStream in = PropertiesLoader.class.getResourceAsStream(DEFAULT_CONFIG_FILE)) {
			properties.load(in);
		} catch (Exception e) {
			log.error("Error while loading default properties. ", e);
		}
	}

	/**
	 * When shutting down the application, this method will be called to save the
	 * properties.
	 *
	 * @return true if the properties were saved successfully
	 */
	public boolean finish() {
		try (FileOutputStream fos = new FileOutputStream(getPropertiesFile())) {
			properties.store(fos, COMMENTS);
			return true;
		} catch (Exception e) {
			log.error("Error while writing properties", e);
			return false;
		}
	}

	/**
	 * Getter for properties by their key.
	 *
	 * @param key the key of the property
	 * @return the value of the property
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * Setter for properties by their key.
	 *
	 * @param key   the key of the property
	 * @param value the value of the property
	 */
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	/**
	 * Getter for the program's path. This method will almost ever return the
	 * directory in which the jar file is located.
	 *
	 * @return the path of the program
	 */
	@SneakyThrows
	private String getProgramPath() {
		URL url = PropertiesLoader.class.getProtectionDomain().getCodeSource().getLocation();
		String jarPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
		return new File(jarPath).getParentFile().getPath();
	}

	/**
	 * Getter for the properties file. This method will return the file in which the
	 * properties are stored. The file will be located next to the program's jar
	 * file.
	 *
	 * @return the properties file
	 */
	private File getPropertiesFile() {
		String path = getProgramPath();
		return new File(path + File.separatorChar + FILE);
	}

}