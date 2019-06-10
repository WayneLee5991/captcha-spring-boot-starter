/**
 * 
 */
package work.waynelee.captcha.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author lwq
 * 2019年3月19日 下午1:34:15
 */
@ConfigurationProperties("java.captcha")
public class CaptchaProperties {
	
	//验证码图片的宽度
	private Integer width = 130;
	
	//验证码图片的高度
	private Integer height = 48;
	
	//验证码图片的位数
	private Integer length = 4;
	
	//验证码图片的字符类型
	private CharTypeEnum charType = CharTypeEnum.TYPE_DEFAULT;
	
	//生成的验证码图片类型
	private CaptchaType captchaType = CaptchaType.PNG;
	
	//缓存的名称
	private String captchaKey = "captcha";

	//验证码过期时间，默认为5分钟
	private int expireTimeInSeconds = 300;

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public CharTypeEnum getCharType() {
		return charType;
	}

	public void setCharType(CharTypeEnum charType) {
		this.charType = charType;
	}

	public CaptchaType getCaptchaType() {
		return captchaType;
	}

	public void setCaptchaType(CaptchaType captchaType) {
		this.captchaType = captchaType;
	}

	public String getCaptchaKey() {
		return captchaKey;
	}

	public void setCaptchaKey(String captchaKey) {
		this.captchaKey = captchaKey;
	}

	public int getExpireTimeInSeconds() {
		return expireTimeInSeconds;
	}

	public void setExpireTimeInSeconds(int expireTimeInSeconds) {
		this.expireTimeInSeconds = expireTimeInSeconds;
	}

}
