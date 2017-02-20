package up.light.parser;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;

/**
 * @version 1.0
 */
public class JsonUtil{
	
	
	/**
	 * @param json string类型的json
	 * @param path 指定路径如：$.a[0].b 一级路径下的a是一个数组，取第一组数据中的key值为b的键对的value
	 * @return 返回对应的value值
	 */
	public static String getValue(String json,String path){
		String ret = null;
		try{
			Object o = JsonPath.read(json,path,new Predicate[0]);
			ret = String.valueOf(o);
		}catch(Exception e){
			e.printStackTrace();
			ret = "";
		}
		return ret;
	}
}
