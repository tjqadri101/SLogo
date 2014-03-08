package preferences;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;

import view.TurtleDisplayPanel;


public class PreferenceHelper {
	 public static void write(TurtleDisplayPanel t, String filename) throws Exception{
		 	File f = writeFile(filename);
	        XMLEncoder encoder =
	           new XMLEncoder(
	              new BufferedOutputStream(
	                new FileOutputStream(f)));
	       encoder.writeObject(t);
	        encoder.close();
	    }
	 
	 public static TurtleDisplayPanel read() throws Exception {
		 	File preferenceFile = PreferenceChooser.initFileChooser();
	        XMLDecoder decoder =
	            new XMLDecoder(new BufferedInputStream(
	                new FileInputStream(preferenceFile)));
	        TurtleDisplayPanel t = (TurtleDisplayPanel)decoder.readObject();
	        decoder.close();
	        return t;
	    }
	 public static File writeFile(String filename){
		 String dirName = "./preferences/";
		 File dir = new File (dirName);
		 File actualFile = new File (dir, filename);
		 return actualFile;
	 }
}





   