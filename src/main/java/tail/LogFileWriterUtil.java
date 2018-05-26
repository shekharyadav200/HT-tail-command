package tail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogFileWriterUtil {
	@Value("${file.location}")
	private  String filePath;
	
	private final static Logger looger = Logger.getLogger(LogFileWriterUtil.class.getName());
	
	@Scheduled(fixedDelay = 1000)
	private void insertLogs() {
		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter(filePath, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			String data = "\napplication.log file content: " + Math.random();
			bufferWritter.write(data);
			bufferWritter.close();

		} catch (Exception e) {
			looger.info(e.getMessage());
		}

	}

}
