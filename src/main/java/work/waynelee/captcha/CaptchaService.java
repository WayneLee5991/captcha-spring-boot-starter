/**
 * 
 */
package work.waynelee.captcha;

import java.awt.Font;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wf.captcha.GifCaptcha;

/**
 *
 * @author 李文庆
 * 2019年3月29日 下午1:37:01
 */
@Component
public class CaptchaService {
	
	@Autowired
	private CaptchaProperties properties;
	
	public CaptchaService() {
		super();
	}

	public CaptchaService(CaptchaProperties captchaProperties) {
		this.properties = captchaProperties;
	}

	public GifCaptcha generateCaptcha(){
		return generateCaptcha(null);
	}
	
	/**
	 * 生成图形验证码
	 * @param font 设置字体，可以不用设置，有默认字体
	 * @return
	 */
	public GifCaptcha generateCaptcha(Font font){
		
	    // 三个参数分别为宽、高、位数
	    GifCaptcha gifCaptcha = new GifCaptcha(properties.getWidth(),properties.getHeight(),properties.getLength());
	    // 设置字体
	    if (!Objects.equals(font, null)) {
	    	gifCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
		}
	    // 设置类型，纯数字、纯字母、字母数字混合
	    gifCaptcha.setCharType(properties.getCharType().getCode());
	    //String captcha = gifCaptcha.text().toLowerCase();
	    return gifCaptcha;
	}
	
}
