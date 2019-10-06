import request.GetRequest;
import request.PostRequest;
import request.Request;
import com.beust.jcommander.*;


public class Httpc {



    public static void main(String[] args) {
        final CLIParameter cliArgs = new CLIParameter();
        boolean needsHelp = false;
        String requestType = "";
        Request request;
        Httpc input = new Httpc();
//        input.handleInput(args);
//        input.run();

        /*
        parse input
         */

        if(needsHelp) {
            Help help = new Help(requestType);
            System.out.println(help.printHelp());
        }

        if(cliArgs.getCLIRequest().equalsIgnoreCase("get")){
                request = cliArgs.getHeader().isEmpty() ? new GetRequest(cliArgs.getCLIRequest(),cliArgs.getVerbose())
                        : new GetRequest(cliArgs.getCLIRequest(),cliArgs.getVerbose(), cliArgs.getHeader());
        }
        else {
                if (cliArgs.getHeader().isEmpty()){
                    request = new PostRequest(cliArgs.getUrl(),cliArgs.getVerbose());
                }else if (cliArgs.getOption().equals(null)){
                    request = new PostRequest(cliArgs.getUrl(),cliArgs.getVerbose(), cliArgs.getHeader());
                }else {
                    // need to fix option attribute in CLIParameter class
                    request = new PostRequest(cliArgs.getUrl(),cliArgs.getVerbose(), cliArgs.getHeader(), cliArgs.getOption());
                }

            }


        /*
        if(GET) {
           request = new GetRequest(String url, boolean isVerbose, headers);
           request.sendRequest();
        }
        else if(POST) {
           request = new PostRequest(String url, boolean, ....);
           request.sendRequest();
        }
         */

    }
}
