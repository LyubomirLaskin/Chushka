package chushka.util;

import java.io.*;

public class HtmlReader {

    public String readHtmlFile(String path) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(path)
                        )
                )
        );

        StringBuilder parsedHtml = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null){
            parsedHtml.append(line).append(System.lineSeparator());
        }

        return parsedHtml.toString().trim();
    }
}
