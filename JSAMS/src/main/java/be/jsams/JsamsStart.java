package be.jsams;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;

/**
 * Startup class for the Jsams Application.
 * 
 * 
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
public class JsamsStart {

	private static final String JSAMS_APPLICATION_CONTEXT = "jsams";

	/**
	 * Main starting method for the Jsams Application
	 * 
	 * @param args
	 *            for now, no argument are needed
	 */
	public static void main(String[] args) {
		System.setProperty("application.context", JSAMS_APPLICATION_CONTEXT);
		JsamsApplicationContext.setContext(new ClassPathXmlApplicationContext(
				JsamsApplicationContext.CONFIG));
		
		JsamsDesktop application = new JsamsDesktop();
		application.start();
	}

}