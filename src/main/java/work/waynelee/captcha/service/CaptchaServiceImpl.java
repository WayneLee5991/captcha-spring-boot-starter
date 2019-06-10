/**
 * 
 */
package work.waynelee.captcha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import com.wf.captcha.Captcha;

import work.waynelee.captcha.CaptchaGenerateFactory;
import work.waynelee.captcha.properties.CaptchaProperties;

/**
 *
 * @author 李文庆
 * 2019年3月29日 下午1:37:01
 */
public class CaptchaServiceImpl implements CaptchaService{
	
	@Autowired
	private CaptchaProperties properties;

	/**
	 * 生成图形验证码
	 * @return
	 */
	public Captcha generate(ServletWebRequest request){
		Captcha captcha = CaptchaGenerateFactory.getCaptcha(properties,request);
		//设置类型
		captcha.setCharType(properties.getCharType().getCode());
		return captcha;
	}
	
}
