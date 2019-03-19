/**
 * 
 */
package work.waynelee.captcha;

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
	private Integer length = 5;
	
	//验证码图片的字符类型
	private CharTypeEnum charType = CharTypeEnum.TYPE_DEFAULT;
	
	//缓存的名称
	private String captchaKey = "captcha";
	
	//验证码的存储方式，默认ehcache
	private CaptchaStorageEnum captchaStorageType = CaptchaStorageEnum.EHCACHE;

	//验证码过期时间，只有当存储方式设置为redis时生效，默认为5分钟
	private long expireTime = 5*60;

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

	public String getCaptchaKey() {
		return captchaKey;
	}

	public void setCaptchaKey(String captchaKey) {
		this.captchaKey = captchaKey;
	}

	public CaptchaStorageEnum getCaptchaStorageType() {
		return captchaStorageType;
	}

	public void setCaptchaStorageType(CaptchaStorageEnum captchaStorageType) {
		this.captchaStorageType = captchaStorageType;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	
}
