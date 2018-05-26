package tail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@EnableScheduling
@Controller
public class LogsController {
	@Autowired
	private SimpMessagingTemplate template;
	@Autowired
	private LogFileReaderService fileReaderService;

	@Scheduled(fixedDelay = 5000)
	@SendTo("/topic/logs")
	public void logs() throws Exception {
		this.template.convertAndSend("/topic/logs", new Log(fileReaderService.readFile()));
	}

}
