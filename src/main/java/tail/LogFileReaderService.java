package tail;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class LogFileReaderService {
	private long lastPostion = 0;
	private File logFile = null;
	private final static Logger looger = Logger.getLogger(LogFileReaderService.class.getName());	
	public LogFileReaderService(@Value("${file.location}") final String FileLocation) {
		logFile = new File(FileLocation);
	} 
	public List<String> readFile() {
		List<String> list=new ArrayList<String>();
		try {
			long fileLength = logFile.length();
			if (fileLength > lastPostion) { 
				RandomAccessFile readWriteFileAccess = new RandomAccessFile(logFile, "r");
				readWriteFileAccess.seek(lastPostion);
				String content = null;
				while ((content = readWriteFileAccess.readLine()) != null) {
					if(!content.isEmpty())
						list.add(content);
				}
				lastPostion = readWriteFileAccess.getFilePointer();
				readWriteFileAccess.close();
			}
		} catch (Exception e) {
			looger.info(e.getMessage());
		}
		return list;

	}
}
