/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package af.asr.notification.config;

import af.asr.notification.ServiceConstants;
import af.gov.anar.lang.infrastructure.exception.service.EnableServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("WeakerAccess")
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAsync
@EnableServiceException
@EnableConfigurationProperties
@ComponentScan({
		"af.*"
})
public class NotificationConfiguration extends WebMvcConfigurerAdapter {
	
	private final Environment environment;
	
	public NotificationConfiguration(Environment environment) {
		super();
		this.environment = environment;
	}
	
	@Override
	public void configurePathMatch(final PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(Boolean.FALSE);
	}



	
	@Bean(
			name = {ServiceConstants.LOGGER_NAME}
	)
	public Logger loggerBean() {
		return LoggerFactory.getLogger(ServiceConstants.LOGGER_NAME);
	}
	
}
