# captcha-spring-boot-starter
这是一个springboot的关于验证码的starter，只要引入依赖，就可以生成漂亮的验证码，静态的、动态的都可以。为了满足不同需求，只做生成，不做存储和验证功能。
## 一、添加依赖
maven依赖，先添加以下仓库
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
再添加以下依赖
```
<dependency>
	  <groupId>com.github.WayneLee5991</groupId>
	  <artifactId>captcha-spring-boot-starter</artifactId>
	  <version>1.0.2</version>
</dependency>
```
## 二、使用

1、只需要在controller层注入CaptchaService就可以生成验证码了。
```
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private CaptchaProperties properties;
	
	public void getCaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//设置响应头
		CaptchaUtil.setHeader(response);
    //生成验证码
		Captcha captcha = captchaService.generate(new ServletWebRequest(request));
    //获取验证码的字符串
		String text = captcha.text().toLowerCase();
		
		request.getSession().setAttribute(properties.getCaptchaKey(),text);
		
		captcha.out(response.getOutputStream());
	}
 ```
 2、默认配置已经可以生成验证码了，如果你想修改某些配置，你可以在application.properties里配置一下内容
 ```
 #验证码图片的宽度
java.captcha.width=130
#验证码图片的高度
java.captcha.height=48
#验证码字符串的位数
java.captcha.length=4
#验证码字符的类型，一共有6种可以设置，默认完全可以
java.captcha.char-type=type-default
#验证码图片的格式，gif和png
java.captcha.captcha-type=png
```
3、如果项目已经启动，你想修改验证码图片的大小或者其他，但是你又不想修改任何代码，那么，你可以传递参数来修改验证码的样式。
    例如：http://localhost:8080/admin/captcha?gif=true 。虽然你配置的是png格式的，但是通过传递参数，你可以获取GIF格式的验证码。
    另外还有：http://localhost:8080/admin/captcha?gif=true&length=5 等等，目前只有width，height，length，gif这四个参数是可以修改的。
