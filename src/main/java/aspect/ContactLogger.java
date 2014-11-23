package aspect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Classe pour réaliser les logs des transactions faites
 * 
 * FIXME Refactor with new ways. Maybe using Log4j/Logback
 * 
 * @author Adriean
 *
 */
public class ContactLogger {
	
	public static final DateFormat DF_DATE =  new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat DF_DATETIME =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void log(String firstname, String lastname) throws Throwable{
		File file;
		FileWriter output;
		BufferedWriter writer;

		try {
			String logFolder = "contactLogs";
			if(! (new File(logFolder)).exists()){
				if(! (new File(logFolder).mkdirs())){
					System.err.println("Cannot create the folder " + logFolder);
					return;
				}
			}
			
			
			Calendar cal = Calendar.getInstance();
			file = new File(logFolder + "/contactLog-" + DF_DATE.format(cal.getTime()));
			if(! file.exists()){
				file.createNewFile();
			}
			
			output = new FileWriter(file.getAbsoluteFile(), true);
			writer = new BufferedWriter(output);

			writer.append(DF_DATETIME.format(cal.getTime()) + 
					" try to add the contact(firstname, lastname) : " + firstname + " " + lastname);
			writer.newLine();
			
			// TODO Move elsewhere.
			System.out.println("Logger's location : " + file.getAbsolutePath());
			
			writer.close();			
		} catch (Exception | Error e) {
			e.printStackTrace();
		}
	}
}