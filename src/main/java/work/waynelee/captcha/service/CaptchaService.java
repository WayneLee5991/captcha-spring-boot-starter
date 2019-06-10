/**
 * 
 */
package work.waynelee.captcha.service;

import org.springframework.web.context.request.ServletWebRequest;

import com.wf.captcha.Captcha;

/**
 *
 * @author 李文庆
 * 2019年5月20日 下午3:49:14
 */
public interface CaptchaService {

	Captcha generate(ServletWebRequest request);
	
}
