package it.cnr.isti.sedc.bieco.groot.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Sub {

	public Sub(final double op1, final double op2) {

	}

//	public static String readFileOLD(String filePath) {
//
//		String text="";
//		
//		try {
//		Path fileName = Paths.get(filePath);
//
//	    text = Files.readString(fileName, StandardCharsets.ISO_8859_1);
//	    		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	    return text;
//
//	}
//	

	public static String readFile(String filePath) {

		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.ISO_8859_1)) {

			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			// handle exception
		}

		String fileContent = contentBuilder.toString();
		return fileContent;
	}

	public static String newSessionLogger() {

		return "\n\n-----------------------------------------------------\n"
				+ "- NEW GROOT SESSION - ALL COMPONENTS RESTARTED -\n"
				+ "-----------------------------------------------------\n\n\n";
	}
}
