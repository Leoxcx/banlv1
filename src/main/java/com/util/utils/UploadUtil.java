package com.util.utils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class UploadUtil {

    public static DiskFileItemFactory getFactory(File file) {

        DiskFileItemFactory factory = new DiskFileItemFactory();

        //设置一个缓冲区，若文件大小超过缓冲区，放到临时路径
        factory.setSizeThreshold(1024 * 1024 * 5);
        factory.setRepository(file);
        return factory;
    }

    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度
        upload.setProgressListener (new ProgressListener() {
            @Override
            public void update(long pBytesRead, long pContentlenth, int pItems) {
                //pContentlenth:文件大小
                //pBytesRead:已读取文件大小
                System.out.println("总大小:" + pContentlenth + "   已上传:" + pBytesRead);
            }
        });
        //防止乱码
        upload.setHeaderEncoding("UTF-8");

        //设置上传单个文件最大值（1 M）
        upload.setFileSizeMax(1024 * 1024);

        //设置总共可以上传文件的大小
        upload.setSizeMax(1024 * 1024 * 10);

        return upload;
    }

    public static String upLoadParseRequest(ServletFileUpload upload, javax.servlet.http.HttpServletRequest request, String uploadPath) throws IOException, FileUploadException {
        String msg = "";
        //使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            //判断是否为普通的表单
            if (fileItem.isFormField()) {
                //普通表单
                String name = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                System.out.println(name + ":" + value);
            } else {

                //==================================处理文件=================================
                //拿到文件名
                String uploadFileName = fileItem.getName();
                System.out.println("上传的文件名：" + uploadFileName);
                //判断文件名是否存在
                if (uploadFileName.trim().equals("") || uploadFileName == null) {
                    continue;
                }
                //获得真的文件名（去掉多余的路径）
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                //得到文件后缀名
                String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);

                //文件后缀不符合，直接return，不处理
                System.out.println("文件信息[ 文件名：" + fileName + "----文件类型：" + fileExtName + "]");



                //=====================================文件处理完毕========================================

                //=====================================存放地址处理========================================
                //得到文件保存的名称
                String saveFilename = makeFileName(fileName);
                //得到文件的保存目录
                String realPath = makePath(saveFilename, uploadPath);
                //====================================存放地址处理完毕=====================================

                //获得文件上传流
                InputStream inputStream = fileItem.getInputStream();

                //创建文件输出流
                FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);

                //创建缓冲区
                byte[] buffer = new byte[1024 * 8];

                //判断是否读取完毕
                int len = 0;
                //如果大于0，说明还存在数据
                while ((len = inputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                inputStream.close();

                msg = "文件上传成功";
                //清除临时文件
                //fileItem.delete();
            }

        }
        return msg;
    }
    /**
     * @Method: makeFileName
     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * @param filename 文件的原始名称
     * @return uuid+"_"+文件的原始名称
     */
    public static String makeFileName(String filename){  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;
    }

    /**
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
     * @Method: makePath
     * @param filename 文件名，要根据文件名生成存储目录
     * @param savePath 文件存储路径
     * @return 新的存储目录
     */
    public static String makePath(String filename,String savePath){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}

