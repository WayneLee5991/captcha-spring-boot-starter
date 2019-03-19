/**
 * 
 */
package work.waynelee.service;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lwq
 * 2019年3月19日 下午4:20:43
 */
public interface CaptchaStorageService {

	/**
	 * 将验证码存储进session
	 * @param key
	 * @param value
	 */
	void storageToSession(HttpServletRequest request,String value);
	
	/**
	 * 将验证码存储进缓存
	 * @param key
	 * @param value
	 */
	void storageToCache(String key,String value);
	
	/**
	 * 验证
	 * @param request
	 * @param value
	 * @return
	 */
	boolean valid(HttpServletRequest request,String value);
}
