package tail;

import java.util.List;

public class Log {

	private List<String> logs;

	public Log() {
	}

	public Log(List<String> logs) {
		this.logs = logs;
	}

	public List<String> getLogs() {
		return logs;
	}

	public void setLogs(List<String> logs) {
		this.logs = logs;
	}

}
