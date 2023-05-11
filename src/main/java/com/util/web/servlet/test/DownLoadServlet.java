package com.util.web.servlet.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet("/downloadtestservlet")
public class DownLoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String filepath = request.getSession().getServletContext().getRealPath("");
        System.out.println(filepath);
        String filename = "Main.0000_(3).png";
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\Main.0000_(3)\\Main.0000_(3).png");
        // 使用转化流规定编码格式
        InputStreamReader reader = new InputStreamReader(fileInputStream,"UTF-8");
        long fileLength = 0;
        File file = new File("C:\\Users\\Administrator\\Desktop\\Main.0000_(3)\\Main.0000_(3).png");
        if (file.exists() && file.isFile()) {
            String fileName = file.getName();
            System.out.println("文件"+fileName+"的大小是："+file.length());
            fileLength = file.length();
        }
        response.setHeader("Content-Length", String.valueOf(fileLength));

        // 输出流创建
        //PrintWriter out = response.getWriter();

        OutputStream out = response.getOutputStream();

        byte[] outputByte = new byte[1024];
        int readTmp = 0;
        while ((readTmp = fileInputStream.read(outputByte)) != -1) {
            out.write(outputByte, 0, readTmp); //并不是每次都能读到1024个字节，所有用readTmp作为每次读取数据的长度，否则会出现文件损坏的错误
        }

        fileInputStream.close();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
