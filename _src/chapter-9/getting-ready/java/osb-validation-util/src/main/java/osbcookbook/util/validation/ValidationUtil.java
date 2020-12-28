package osbcookbook.util.validation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ValidationUtil {
	private static ApplicationContext ctx = 
		new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

	public static boolean shouldValidate() {
		ValidationConfiguration config = (ValidationConfiguration)ctx.getBean("validationConfiguration");
		return config.isValidationEnabled();
	}
}
