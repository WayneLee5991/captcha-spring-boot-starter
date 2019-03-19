/**
 * 
 */
package work.waynelee.captcha;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import work.waynelee.service.CaptchaStorageService;
import work.waynelee.utils.RedisUtils;

/**
 *
 * @author 李文庆
 * 2019年3月19日 下午2:09:53
 */
public class CaptchaStorageServiceImpl implements CaptchaStorageService{

	@Autowired
	private CacheManager cacheManager;
	
	private CaptchaProperties properties;

	@Override
	public void storageToSession(HttpServletRequest request, String value) {
		
		request.getSession().setAttribute(properties.getCaptchaKey(), value);
	}

	@Override
	public void storageToCache(String key, String value) {
		Integer code = properties.getCaptchaStorageType().getCode();
		
		if (Objects.equals(code,1)) {
			RedisUtils.set(key, value, properties.getExpireTime());
		}else{
			Cache cache = cacheManager.getCache(properties.getCaptchaKey());
			cache.put(key, value);
		}
	}
	
	@Override
	public boolean valid(HttpServletRequest request,String value){
		
		String sessionId = request.getSession().getId();
		Integer code = properties.getCaptchaStorageType().getCode();
		
		boolean isValid = false;
		
		if (Objects.equals(code,1)) {
			String captcha = RedisUtils.get(sessionId).toString();
			if (Objects.equals(captcha, null)) {
				return isValid;
			}
			if (captcha.equalsIgnoreCase(value)) {
				isValid = true;
			}
		}
		if(Objects.equals(code,2)){
			Cache cache = cacheManager.getCache(properties.getCaptchaKey());
			String captcha = cache.get(sessionId, String.class);
			if (Objects.equals(captcha, null)) {
				return isValid;
			}
			if (captcha.equalsIgnoreCase(value)) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	public CaptchaStorageServiceImpl(CaptchaProperties properties) {
		this.properties = properties;
	}

}
