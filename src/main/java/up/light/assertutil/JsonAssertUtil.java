package up.light.assertutil;

import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonNodeReader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import up.light.FolderType;
import up.light.Setting;

public class JsonAssertUtil {
	
	/**
	 * 此方法使用于在Test类中使用，需要jsonSchema于测试类名一致并放于json文件夹内
	 * @param jsonPath 传入被检测的json字符串
	 */
	/**
	 * 校验返回的json格式是否正确
	 * @param except 期待结果
	 * @param jsonStr 待传入的被测json字符串
	 * @param jsonSchemaName schema校验文件的文件名（无需加.json）
	 */
	public static void JsonAssert(String except,String jsonStr,String jsonSchemaName) {
		/*JsonNode jsonSchema = readJsonFile(System.getProperty("user.dir") + "/json/jsonSchame001.json");
		JsonNode json = readJsonFile(System.getProperty("user.dir") + "/json/json001.json");*/
		
		//使用映射获取调用类名
		String ClassName;
		StackTraceElement[] stack = (new Throwable()).getStackTrace();
		String CallerName = stack[1].getClassName();
		String[] Name = CallerName.split("\\.");
		ClassName = Name[Name.length-1];
		//括号内路径为拼接路径
		JsonNode jsonSchema = readJsonFile(Setting.getPath(FolderType.JSON)+jsonSchemaName+".json");
		//JsonNode jsonSchema = readJsonFile(jsonSchemaPath);
		JsonNode json = null;
		try {
			json = new ObjectMapper().readTree(jsonStr);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//JsonNode json = readJsonFile(jsonPath);
		ProcessingReport report = null;
		try {
			report = JsonSchemaFactory.byDefault().getValidator().validate(jsonSchema, json, true);
		} catch (ProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (report.toString().indexOf("error") != -1) {
				String re = report.toString();
				int start = re.indexOf("--- BEGIN MESSAGES ---") + "--- BEGIN MESSAGES ---".length() + 1;
				int end = re.indexOf("---  END MESSAGES  ---") - 1;
				re = (String) re.subSequence(start, end);
				if (re.indexOf("error") != -1) {
					throw new AssertFailException(except,re);
				}
			}
		}
	}

	
	/**
	 * 从文件中读取转换为jsonNode类
	 * @param filePath文件路径
	 * @return
	 */
	public static JsonNode readJsonFile(String filePath) {
		JsonNode instance = null;
		try {
			instance = new JsonNodeReader().fromReader(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
