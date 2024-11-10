package miniQuiz;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Table1 {

    public static void main(String[] args) {
        // 시스템 속성 가져오기
        Properties properties = System.getProperties();

        // HTML 파일 생성
        String fileName = "property.html";
        try (FileWriter writer = new FileWriter(fileName)) {
            // HTML 문서 시작
            writer.write("<html><head><title>자바 환경정보</title>");
            writer.write("<style>");
            writer.write("table { border-collapse: collapse; width: 100%; }");
            writer.write("th, td { border: solid 1px #000; padding: 8px; text-align: left; }");
            writer.write("</style></head><body>");
            writer.write("<h1>자바 환경정보</h1>");
            writer.write("<table>");
            writer.write("<tr><th>키</th><th></th></tr>");

            // 속성 하나씩 HTML에 추가
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                writer.write("<tr><td>" + key + "</td><td>" + value + "</td></tr>");
            }

            // HTML 문서 끝
            writer.write("</table></body></html>");

            System.out.println("HTML file created: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing HTML file: " + e.getMessage());
        }

        // HTML 파일 웹브라우저로 열기
   /*     try {
            File htmlFile = new File(fileName);
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                System.out.println("Browsing is not supported on this system.");
            }
        } catch (IOException e) {
            System.out.println("Error opening HTML file in browser: " + e.getMessage());
        }*/
    }
}
