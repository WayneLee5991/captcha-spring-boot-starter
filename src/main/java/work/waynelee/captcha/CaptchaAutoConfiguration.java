/**
 * 
 */
package work.waynelee.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author 李文庆
 * 2019年3月19日 下午1:41:52
 */
@Configuration
@EnableConfigurationProperties({CaptchaProperties.class})
public class CaptchaAutoConfiguration {
	
	@Autowired
	private CaptchaProperties captchaProperties;
	
	@Bean
	@ConditionalOnMissingBean(CaptchaService.class)  
	public CaptchaService captchaService(){
		return new CaptchaService(captchaProperties);
	}
	
}
