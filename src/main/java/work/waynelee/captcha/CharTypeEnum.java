/**
 * 
 */
package work.waynelee.captcha;

/**
 *
 * @author 李文庆
 * 2019年3月19日 下午2:23:42
 */
public enum CharTypeEnum {

	TYPE_DEFAULT(1,"数字和字母混合"),
	TYPE_ONLY_NUMBER(2,"纯数字"),
	TYPE_ONLY_CHAR(3,"纯字母"),
	TYPE_ONLY_UPPER(4,"纯大写字母"),
	TYPE_ONLY_LOWER(5,"纯小写字母"),
	TYPE_NUM_AND_UPPER(6,"数字和大写字母");
	
	private Integer code;
	
	private String charType;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCharType() {
		return charType;
	}

	public void setCharType(String charType) {
		this.charType = charType;
	}

	private CharTypeEnum(Integer code, String charType) {
		this.code = code;
		this.charType = charType;
	}
	
}
