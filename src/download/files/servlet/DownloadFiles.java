package download.files.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet for handling list of files in return which is in JSON Format in text file.
 */


public class DownloadFiles extends HttpServlet {
	
	/**
	 Ê * The serialVersionUID is used as a version control.
	 Ê */
	 private static final long serialVersionUID = -1911747205652928577L;
	 private static final String DESTINATION_FILE_PATH ="/files/task.txt";
	 private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	 private static final String CONTENT_TYPE_JSON = "application/json";
	
  // if some tries to hack your system it will break out from loop if file is too big.
	 private static final int MAX_LINES = 10000; 
	 
	
    /** 
     * Handles the HTTP GET method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an Filing error occurs
     */
    
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException {

    	response.setContentType(CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        try {
        	 String read = readDestinationFile();
        	 
        	out.println(read);  
        
        } finally { 
            out.close();
        }
    } 

    /** 
     * Handles the HTTP POST method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       doGet(request, response);
    }

    /**
     * Read the contents of the given file
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @return Return it in a String after fetching the entire contents of a text file.
     */
    private String readDestinationFile()  throws FileNotFoundException, IOException {
    	log("Reading from file.");
    	
    	StringBuilder content = new StringBuilder();
    	
    	// Map a URI to an actual path.
		String realPath = getServletContext().getRealPath(DESTINATION_FILE_PATH);

		  //use buffering, reading one line at a time
	      // Defines the standard input stream!
	      BufferedReader input =  new BufferedReader(new FileReader(realPath));
	      try {
	        String line = null; //not declared within while loop
	        int lineCount = 0;
	        /*
	        * Read a line
	        * Appends the specified string to this character sequence.
	        * @param Gets the system property indicated by the specified key
	        */
	        while (( line = input.readLine()) != null || lineCount > MAX_LINES )
	        {
	        	lineCount++;
	        	content.append(line);
	        	content.append(LINE_SEPARATOR);
	        }
	      }
	      //Closes the stream and releases any system resources associated with it
	      finally {
	        input.close();
	      }
        // Returns a string representing the data in this sequence from the file
		return content.toString();
    }

}