package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Поиск и определение частоты слова в файле</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='app' method='post'>");
        out.println("<p>Введите слово для поиска: <input type='text' name='word' size='25'></p>");
        out.println("<input type='submit' value='Поиск'>");
        out.println("</form>");
        out.println("<br>");
        out.println("<p>Частота встречаемости: </p>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileName = "C:\\Users\\HOME\\Desktop\\TextForJava.txt"; // путь к файлу
        String wordToSearch= request.getParameter("word"); // слово, частоту которого нужно найти

        Map<String, Integer> wordFrequency = new HashMap<>();
       Integer CountChecker =0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equals(wordToSearch)) {
                        Integer count = wordFrequency.get(word);
                        if (count == null) {
                            count = 0;
                        }
                        wordFrequency.put(word, count + 1);
                        CountChecker= count;
                    }
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Поиск и определение частоты слова в файле</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='app' method='post'>");
        out.println("<p>Введите слово для поиска: <input type='text' word='angle' size='15'></p>");
        out.println("<input type='submit' value='Поиск'>");
        out.println("</form>");
        out.println("<br>");
        out.println("<p>Частота встречаемости: " + CountChecker + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}