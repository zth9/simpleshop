package cn.javak.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: theTian
 * @date: 2020/3/10 11:23
 */
public interface FileService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    boolean uploadFile(MultipartFile file) throws IOException;
}
