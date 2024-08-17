package Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {

	public static List<HashMap<String,String>> ReadingJsonData(String JsonFilePath) throws IOException{
		// file Path : (System.getProperty("user.dir")+"\\src\\test\\java\\Data\\JsonDataReader.java"
		//Read the file and convert it to String
		String JsonContent = FileUtils.readFileToString(new File (JsonFilePath),StandardCharsets.UTF_8);
		ObjectMapper Mapper = new ObjectMapper();
		List<HashMap<String,String>> data = Mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;



	}

}
