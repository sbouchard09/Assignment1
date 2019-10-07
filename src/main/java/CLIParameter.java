import com.beust.jcommander.*;

import java.util.Map;


public class CLIParameter {

	@Parameter(names = {"help"}, help = true)
	private Boolean help = false;
	
	public Boolean getHelp() {
		return help;
	}
}
