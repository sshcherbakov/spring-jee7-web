package io.pivotal.example.service;

import org.jboss.logging.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ExecutorServiceImpl implements ExecutorService {
	private static final Logger log = Logger.getLogger(ExecutorServiceImpl.class.getName()); 

	@Async
	@Override
	public void submit(String task) {
		log.info("Submitted task: "+task);
	}

}
