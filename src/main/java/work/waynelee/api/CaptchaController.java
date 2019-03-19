/**
 * 
 */
package work.waynelee.api;

import java.awt.Font;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;

import work.waynelee.captcha.CaptchaProperties;
import work.waynelee.service.CaptchaStorageService;

/**
 *
 * @author 李文庆
 * 2019年3月19日 下午1:43:57
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private CaptchaProperties properties;
	@Autowired
	private CaptchaStorageService captchaStorageService;
	
	@GetMapping("/render")
	public void getCaptcha() throws IOException{
		 
		// 设置请求头为输出图片类型
	     CaptchaUtil.setHeader(response);
	     // 三个参数分别为宽、高、位数
	     GifCaptcha gifCaptcha = new GifCaptcha(properties.getWidth(),properties.getHeight(),properties.getLength());
	     // 设置字体
	     gifCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
	     // 设置类型，纯数字、纯字母、字母数字混合
	     gifCaptcha.setCharType(properties.getCharType().getCode());
	     // 存储验证码
	     String sessionId = request.getSession().getId();
	     String captcha = gifCaptcha.text().toLowerCase();
	     captchaStorageService.storageToCache(sessionId, captcha);
	     // 输出图片流
	     gifCaptcha.out(response.getOutputStream());
	     
	}
	
	@PostMapping("/valid")
	public ResponseEntity<Object> valid(@RequestParam String code){
		boolean valid = captchaStorageService.valid(request,code);
		if (valid) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
