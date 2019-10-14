import request.GetRequest;
import request.PostRequest;
import request.Request;
import com.beust.jcommander.*;

import java.nio.file.FileSystemNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Httpc {

	private static String url;
	private static Boolean isVerbose = false;
	private static Map<String, String> headers;
	private static Map<String, String> options;	

    public static void main(String[] args) {
        boolean needsHelp = false;
        String requestType = "";
        Request request;
		
		// parse
		CLIParameter parameters = new CLIParameter();
		CommandGet get = new CommandGet();
		CommandPost post = new CommandPost();
		JCommander parser = JCommander.newBuilder().addObject(parameters).addCommand("get", get).addCommand("post", post).build();
        parser.parse(args);

		requestType = parser.getParsedCommand(); // GET | POST
		
		if(parameters.getHelp()) {
			Help help = new Help(requestType);
			System.out.println(help.printHelp());
		} else {
			if(requestType.equals("get")) { // GET
				headers = new HashMap<String, String>();
				url = get.getUrl();
				isVerbose = get.getVerbose();
				parseParameters(get.getHeader()); // headers
				request = new GetRequest(url, isVerbose, headers);
				request.sendRequest();
			} else if(requestType.equals("post")) { // POST
				url = post.getUrl();
				isVerbose = post.getVerbose();
				headers = new HashMap<String, String>();
				options = new HashMap<String, String>();
				parseParameters(post.getHeader());
				if(post.getOption() != null) {
					options.put("inline_data", post.getOption());
				}
				if(post.getInputFile() != null) {
					options.put("input_file", readInputFile(post.getInputFile()));
				}
				
				try{
					request = new PostRequest(url, isVerbose, headers, options);
					request.sendRequest();
				} catch(IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
		}
    }
	
	private static void parseParameters(String parameters) {
		if(parameters == null || parameters.length() == 0) return;
		String[] header = parameters.split(":");
		headers.put(header[0], header[1]);
	}
	
	private static String readInputFile(String inputFileName) {
        String output = null;
		try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
			StringBuilder inputBuilder = new StringBuilder();
			String line = reader.readLine();

			while(line != null) {
				inputBuilder.append(line);
				line = reader.readLine();
			}
			output = inputBuilder.toString();
			reader.close();
		} catch(Exception e) {
			System.out.println("Could not read file");
		}
		finally {

		}
		return output;
	}
}
