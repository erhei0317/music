package test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Person {

	public void eat(){
		System.out.println("eat food");
	}
}
