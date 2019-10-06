import com.beust.jcommander.*;

import java.util.Map;


public class CLIParameter {


    @Parameter(names = {"Method Type","get|post"},
        required = true
    )private String CLIRequest;

    @Parameter(names = {"-v"}
    )private Boolean verbose;

    @Parameter(names = {"-h"}
    )private Map <String, String> header;

    @Parameter(names = {"-d", "-f"}
    )private Map <String, String> option;

    @Parameter(names = {"-url"},
            description = "URL address",
            required = true)
    private String url;



    /*public CLIParameter(String CLIRequest, Boolean verbose, Map<String, String> header, Map<String, String> option, String url) {
        this.CLIRequest = CLIRequest;
        this.verbose = verbose;
        this.header = header;
        this.option = option;
        this.url = url;
    }

    public CLIParameter(String CLIRequest, Boolean verbose, Map<String, String> header, String file, String url) {
        this.CLIRequest = CLIRequest;
        this.verbose = verbose;
        this.header = header;
        this.file = file;
        this.url = url;
    }*/

    public String getCLIRequest() {
        return CLIRequest;
    }

    public Boolean getVerbose() {
        return verbose;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public Map<String, String> getOption() {
        return option;
    }

    public String getUrl() {
        return url;
    }

}
