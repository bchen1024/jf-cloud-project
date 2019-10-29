package com.btsoft.jf.cloud.core.base.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传Service
 * @author jeo_cb
 * @date 2019/10/29 10:32
 **/
public interface IFileUploadService {

    void uploadFile(MultipartFile file);
}
