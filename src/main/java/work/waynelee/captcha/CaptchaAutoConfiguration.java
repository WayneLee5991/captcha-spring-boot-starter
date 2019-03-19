/**
 * 
 */
package work.waynelee.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import work.waynelee.service.CaptchaStorageService;

/**
 *
 * @author 李文庆
 * 2019年3月19日 下午1:41:52
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties({CaptchaProperties.class})
@ComponentScan("work.waynelee.api")
public class CaptchaAutoConfiguration {
	
	@Autowired
	private CaptchaProperties properties;
	
	@Bean
	public CaptchaStorageService captchaStorageService(){
		CaptchaStorageServiceImpl captchaStorageService = new CaptchaStorageServiceImpl(properties);
		return captchaStorageService;
	}
	
}
