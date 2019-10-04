import request.GetRequest;
import request.PostRequest;
import request.Request;

public class Httpc {

    public static void main(String[] args) {
        boolean needsHelp = false;
        String requestType = "";

        if(needsHelp) {
            Help help = new Help(requestType);
            System.out.println(help.printHelp());
        }


    }
}
