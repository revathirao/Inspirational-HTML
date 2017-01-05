import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.text.html.HTMLWriter;

/**
* The InspirationalDbtoHtmlApp program implements an application that gets the data from the database
* and displays the information about three  Inspirational People to the HTML page.
* 
* @author  RR
* @version 1.0
* @since   2016-1-31 
*/
public class InspirationalDbtoHtmlApp {

	private static ResultSet result;
	private static HtmlWriterInPeople writer;

	public static void main(String[] args) {
		System.out.println("Welcome to HTML writer");
		System.out.println();
		writer = new HtmlWriterInPeople();
		writer.StartTag("html");
		writer.StartTag("head");
		writer.EndTag();
		writer.StartTag("body");
		writer.writeData();		
		writer.EndTag();
		writer.EndTag();
		writer.closeFile();
	}
	
}
