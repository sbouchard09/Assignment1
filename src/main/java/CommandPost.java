import com.beust.jcommander.*;

import java.util.Map;

@Parameters(commandDescription = "POST request")
public class CommandPost {

    @Parameter(names = {"-v"})
	private Boolean verbose = false;
	
	@Parameter(names = {"-o"})
	private String outputFile;

    @Parameter(names = {"-h"})
	private String header;
	
	@Parameter(names = {"-d"})
	private String option;
	
	@Parameter(names = {"-f"})
	private String inputFile;
	
	@Parameter(description = "Host name")
	private String url;

    public Boolean getVerbose() {
        return verbose;
    }

    public String getHeader() {
        return header;
    }

    public String getUrl() {
        return url;
    }
	
	public String getOutputFile() {
		return outputFile;
	}
	
	public String getOption() {
        return option;
    }
	
	public String getInputFile() {
		return inputFile;
	}

}
