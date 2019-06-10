/**
 * 
 */
package work.waynelee.captcha.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import com.wf.captcha.Captcha;
import com.wf.captcha.utils.CaptchaUtil;

import work.waynelee.captcha.properties.CaptchaProperties;
import work.waynelee.captcha.service.CaptchaService;

/**
 * 验证码使用示例，此代码并不会执行
 * @author 李文庆
 * 2019年5月20日 下午3:47:28
 */
public class CaptchaDemoApi {

	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private CaptchaProperties properties;
	
	public void getCaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		CaptchaUtil.setHeader(response);
		Captcha captcha = captchaService.generate(new ServletWebRequest(request));
		String text = captcha.text().toLowerCase();
		
		request.getSession().setAttribute(properties.getCaptchaKey(),text);
		
		captcha.out(response.getOutputStream());
	}
	
}

