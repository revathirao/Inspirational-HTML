import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
* The HtmlWriterInPeople program that writes HTML documents.
*
* @author  RR
* @version 1.0
* @since   2016-1-31 
*/
public class HtmlWriterInPeople {

	private ArrayList<String> mytag = null;
	private File myFile = null;
	private PrintWriter output = null;
	private static Connection connection = getConnection();
	private static ResultSet result;

	/**
	 * Creates a new HtmlWriterInPeople.
	 * *throws IOExceptionwhen file is not found or unable to open
	 */
	public HtmlWriterInPeople() {
		try {
			mytag = new ArrayList<>();
			output = new PrintWriter(new BufferedWriter(new FileWriter("Inspiration.html")));
			output.print("");
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes out a start tag for the element.
	 * @param myStrings
	 */
	public void StartTag(String myStrings) {
		output.println("<" + myStrings + ">");
		mytag.add(myStrings);
		output.flush();
	}
	
	/**
	 * Writes out an end tag for the element.
	 */
	public void EndTag() {
		String currentTag = mytag.get(mytag.size() - 1);
		mytag.remove(mytag.size() - 1);
		output.println("</" + currentTag + ">");
		output.flush();
	}
	
	/**
	 * Writes out the text.
	 * @throws SQLException which provides information on a database access error or other errors.
	 */
	public void writeData() {
		
		try {
			Statement statement = connection.createStatement();
			result = statement
					.executeQuery("Select People.PeopleName,paragraph.text  "
							+ "From People " + "inner join paragraph "
							+ "on People.peopleKey=paragraph.peopleKey order by PeopleName");
			String currentName = "";
			String PeopleName = "";
			while (result.next()) {
				PeopleName = result.getString("PeopleName");				
				String text = result.getString("text");
				if(currentName!=PeopleName)
				{
					output.println("<strong>" + PeopleName+ "</strong>");	
					currentName = PeopleName;
					output.println("<br>");
				}				
				output.println(text);
				output.println("<br>");
			}
			output.flush();
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
	}
	
	/**
	 * this method closes the HTML file
	 */
	public void closeFile() {
		output.close();
	}
	
	/**
	 * this method connects to derby database and gets the connection
	 * @return Connection
	 * @throws SQLException if it cannot connect to the database
	 */
	public static Connection getConnection() {
		try {
			String dbDirectory = "C:/Users/MAX-Student/Desktop/java/db";
			System.setProperty("derby.system.home", dbDirectory);
			
			String url = "jdbc:derby:InspirationalDB";
			String username = "";
			String password = "";

			connection = DriverManager.getConnection(url, username, password);
			return connection;
		   } catch (SQLException e) {
			for (Throwable t : e)
				t.printStackTrace(); // for debugging
			return null;
		     }
	}

}
