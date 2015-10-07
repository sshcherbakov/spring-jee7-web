package io.pivotal.example.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.jboss.logging.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.DefaultManagedTaskExecutor;

import io.pivotal.example.service.ExecutorServiceImpl;

@Configuration
@EnableAsync
@ComponentScan("io.pivotal.example")
public class MainConfig implements AsyncConfigurer {
	private static final Logger log = Logger.getLogger(ExecutorServiceImpl.class.getName());
	
	@Bean
	public Executor taskExecutor() {
		return new DefaultManagedTaskExecutor();
	}

	@Override
	public Executor getAsyncExecutor() {
		return taskExecutor();
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		
		return new AsyncUncaughtExceptionHandler() {
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				log.error("Uncaught async exception: ", ex);
			}
		};
	}

}
