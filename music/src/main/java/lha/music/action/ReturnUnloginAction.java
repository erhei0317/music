package lha.music.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class ReturnUnloginAction extends ActionSupport{

	private Map<Object,Object> resultMap=new HashMap<Object,Object>();

	public Map<Object, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public String execute() throws Exception{
		resultMap.put("result", "unlogin");
		return SUCCESS;
	}
}
