/**
 * 
 */
package work.waynelee.captcha;

import java.util.Objects;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.wf.captcha.Captcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;

import work.waynelee.captcha.properties.CaptchaProperties;
import work.waynelee.captcha.properties.CaptchaType;

/**
 *
 * @author 李文庆
 * 2019年5月20日 下午3:11:56
 */
public class CaptchaGenerateFactory {

	public static Captcha getCaptcha(CaptchaProperties properties,ServletWebRequest request){
		
		boolean isGif = ServletRequestUtils.getBooleanParameter(request.getRequest(),"gif",false);
		Integer width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",properties.getWidth());
		Integer height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",properties.getHeight());
		Integer length = ServletRequestUtils.getIntParameter(request.getRequest(), "length",properties.getLength());
		
		if (isGif) {
			return new GifCaptcha(width,height,length);
		}else{
			if (Objects.equals(properties.getCaptchaType(), CaptchaType.PNG)) {
				return new SpecCaptcha(width,height,length);
			}
			if (Objects.equals(properties.getCaptchaType(),CaptchaType.GIF)) {
				return new GifCaptcha(width,height,length);
			}
		}
		return null;
	}
	
}
