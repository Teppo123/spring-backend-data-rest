package com.example.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.validation.Validator;

// Bugfix for https://github.com/spring-projects/spring-data-rest/issues/898
@Configuration
public class ValidatorEventRegister implements InitializingBean {

	@Autowired
	ValidatingRepositoryEventListener validatingRepositoryEventListener;

	@Autowired
	private Map<String, Validator> validators;

	@Override
	public void afterPropertiesSet() throws Exception {
		List<String> events = Arrays.asList("beforeCreate");
		this.validators.entrySet().forEach(entry -> events.stream().filter(p -> entry.getKey().startsWith(p))
				.findFirst().ifPresent(p -> this.validatingRepositoryEventListener.addValidator(p, entry.getValue())));
	}

}