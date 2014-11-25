package codel.as.aspect;

import java.util.logging.Logger;

/**
 * Classe pour réaliser les logs des transactions faites
 * 
 * FIXME update withLogback
 * 
 * @author Adriean
 * 
 */
public class ContactLogger {

	private static Logger log = Logger.getLogger("ContactLogger");


	public void log(String firstname, String lastname) {

		log.info("Attempt to create new user "+ firstname+", "+ lastname);	
		
	}
}