package io.pivotal.example.web;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.pivotal.example.domain.Greeting;
import io.pivotal.example.service.ExecutorService;

@Controller
@Path("/greeting")
public class MainController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    ExecutorService executorService;
    
	@GET
	@Produces("application/json")
    public Greeting greeting(@DefaultValue("none") @QueryParam("name") String name) {
		
		executorService.submit(name);
		
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
