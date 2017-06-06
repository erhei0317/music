package lha.music.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;


@Component
@Scope("prototype")
public class JumpAction extends ActionSupport{

	public String jump(){
		return SUCCESS;
	}
	public String jump1(){
		return SUCCESS;
	}
	public String jump2(){
		return SUCCESS;
	}
}
