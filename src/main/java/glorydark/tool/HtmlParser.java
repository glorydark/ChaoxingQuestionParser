package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HtmlParser {
    public static void main(String[] args) {
        List<String> outputLines = new ArrayList<>();

        // 从剪切板获取HTML内容
        String html = getClipboardContents();
        if (html == null || html.isEmpty()) {
            System.out.println("剪切板中没有HTML内容");
            return;
        }

        // 使用Jsoup解析HTML
        Document doc = Jsoup.parse(html);

        String name = doc.selectFirst("h2.mark_title").text();
        System.out.println(name);
        // 获取包含题目的div
        Elements questionDivs = doc.select("div.singleQuesId");

        for (Element questionDiv : questionDivs) {
            // 提取题目类型
            String questionType = questionDiv.selectFirst("span.colorShallow").text().replaceAll("[()]", "");

            // 提取题目
            String questionText = questionDiv.selectFirst("h3.mark_name").ownText()
                    .replace("(", "（")
                    .replace(")", "）");

            // 提取选项
            Elements optionElements = questionDiv.select("ul.mark_letter li");
            String[] options = optionElements.stream().map(Element::text).toArray(String[]::new);

            // 提取正确答案
            String correctAnswer = questionDiv.select("span.colorGreen").first().text().split(":")[1].trim();

            // 打印结果
            outputLines.add("【" + questionType + "】" + questionText);
            Collections.addAll(outputLines, options);
            outputLines.add("答案：" + correctAnswer + "\n");
        }

        try {
            // 创建 BufferedWriter 对象，用于写入文件
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/hp/Desktop/" + name.replace(" ", "") + ".txt"));

            // 逐行写入文本内容
            for (String line : outputLines) {
                writer.write(line);
                writer.newLine(); // 添加换行符
            }

            // 关闭 writer
            writer.close();

            System.out.println("文件写入成功！");
        } catch (IOException e) {
            // 处理异常
            System.out.println("写入文件时出错：" + e.getMessage());
        }
    }

    // 从剪切板获取内容的工具方法
    private static String getClipboardContents() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableHtml =
                (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableHtml) {
            try {
                return (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex) {
                System.err.println("获取剪切板内容失败: " + ex.getMessage());
            }
        }
        return null;
    }
}