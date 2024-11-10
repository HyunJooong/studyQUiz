package miniQuiz;

import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;

public class PageNavigation {
    public static void main(String[] args) {
        // 초기값 설정
        int totalPosts = 127;          // 전체 게시글 수
        int postsPerPage = 10;         // 한 페이지당 보여지는 글의 수
        int blockPageCount = 10;        // 페이지 네비게이션에서 보여주는 블럭 수
        int currentPage = 1;           // 현재 페이지 번호

        // 페이지네비게이션 HTML 생성
        String pageNavigationHtml = generatePageNavigation(totalPosts, postsPerPage, blockPageCount, currentPage);

        // HTML 파일 생성
        String fileName = "index.html";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("<html><head><title>Page Navigation</title></head><body>");
            writer.write("<h1>Page Navigation</h1>");
            writer.write(pageNavigationHtml);
            writer.write("</body></html>");
            System.out.println("HTML file created: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing HTML file: " + e.getMessage());
        }

        // 웹브라우저에서 index.html 파일 열기
       /* try {
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

    public static String generatePageNavigation(int totalPosts, int postsPerPage, int blockPageCount, int currentPage) {
        StringBuilder html = new StringBuilder();

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalPosts / postsPerPage);

        // 현재 블록의 시작 페이지와 끝 페이지 계산
        int currentBlock = (currentPage - 1) / blockPageCount;
        int startPage = currentBlock * blockPageCount + 1;
        int endPage = Math.min(startPage + blockPageCount - 1, totalPages);

        // HTML 페이지네비게이션 생성
        html.append("<nav><ul style='display: flex; list-style-type: none;'>");

        // 이전 블록으로 가는 버튼
        if (startPage > 1) {
            html.append("<li><a href='?page=").append(startPage - 1).append("'>이전</a></li>");
        }

        // 페이지 번호들 출력
        for (int i = startPage; i <= endPage; i++) {
            if (i == currentPage) {
                // 현재 페이지는 강조 표시
                html.append("<li style='margin: 0 5px; font-weight: bold;'>").append(i).append("</li>");
            } else {
                // 다른 페이지는 링크로 표시
                html.append("<li style='margin: 0 5px;'><a href='?page=").append(i).append("'>").append(i).append("</a></li>");
            }
        }

        // 다음 블록으로 가는 버튼
        if (endPage < totalPages) {
            html.append("<li><a href='?page=").append(endPage + 1).append("'>다음</a></li>");
        }

        html.append("</ul></nav>");

        return html.toString();
    }
}
