package com.demo.controller;

import com.demo.domain.Upload;
import com.demo.utils.fastDfs.FastDFSClient;
import com.demo.utils.fastDfs.FastDFSFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 文件上传下载控制器，主要上传到fastdfs服务器
 *
 * @author Deng
 * Created on 2018/4/13 15:21
 **/
@Controller
@RequestMapping("/upload")
public class UploadDownloadController {

    /**
     * markdown上传的图片，保存
     *
     * @param file 图片
     */
    @RequestMapping("/mdImage")
    @ResponseBody
    public Upload singleFileUpload(@RequestParam(value = "editormd-image-file", required = true)
                                           MultipartFile file) throws Exception {
        Upload up = new Upload();
        if (file == null) {
            up.setUrl("");
            //不为1就是失败，只能是int类型
            up.setSuccess(0);
            up.setMessage("upload fail!");
            return up;
        }

        /**
         * todo 没有单独的服务器，图片上传的路径不能在项目下，绝对路径暂时无法引用
         * todo 所以此功能未完成。
         * todo 解决思路，用Nginx 的话可以用 Nginx 做个代理，图片等都访问你上传的目录
         */
        /*String fileName = "D:/upload" + "/" + getRandomFileName() + file.getOriginalFilename();

        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
        Path path = Paths.get(fileName);
        Files.write(path, bytes);*/

        String path;
        try {
            path = saveFile(file);
        } catch (Exception e) {
            up.setUrl("");
            //不为1就是失败，只能是int类型
            up.setSuccess(0);
            up.setMessage("upload fail!");
            return up;
        }

        //成功
        up.setUrl(path);
        up.setSuccess(1);
        up.setMessage("upload success!");

        return up;
    }

    /**
     * 根据日期和随记数生成文件名，防止重复名字上传
     *
     * @return 生成的文件名
     */
    public String getRandomFileName() {
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        int random = new Random().nextInt();
        return date + "_" + random + "_";
    }

    /**
     * 上传图片到fastDfs
     * @param multipartFile 要上传的文件
     * @return 文件访问的url
     * @throws Exception
     */
    public String saveFile(MultipartFile multipartFile) throws Exception {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            //upload to fastdfs
            fileAbsolutePath = FastDFSClient.upload(file);
        } catch (Exception e) {
            throw new Exception("upload file failed,please upload again!");
        }
        if (fileAbsolutePath == null) {
            throw new Exception("upload file failed,please upload again!");
        }
        return FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
    }
}
