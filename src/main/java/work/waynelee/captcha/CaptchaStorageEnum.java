/**
 * 
 */
package work.waynelee.captcha;

/**
 *
 * @author 李文庆
 * 2019年3月19日 下午2:20:07
 */
public enum CaptchaStorageEnum {

	REDIS(1,"redis"),
	EHCACHE(2,"ehcache");
	
	private Integer code;
	
	private String type;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private CaptchaStorageEnum(Integer code, String type) {
		this.code = code;
		this.type = type;
	}
}
