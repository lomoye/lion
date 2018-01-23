package com.lomoye.lion.core.util;

import com.alibaba.media.MediaConfiguration;
import com.alibaba.media.Result;
import com.alibaba.media.client.MediaClient;
import com.alibaba.media.client.impl.DefaultMediaClient;
import com.alibaba.media.upload.UploadPolicy;
import com.alibaba.media.upload.UploadRequest;
import com.alibaba.media.upload.UploadResponse;
import com.google.common.base.Strings;
import com.lomoye.common.exception.BusinessException;
import com.lomoye.common.util.SerializationUtil;
import com.lomoye.lion.core.constant.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 顽兔CDN接口
 */

public class UploadUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadUtil.class);

    private static final MediaClient client;

    static {
        /**
         * 初始化 MediaConfiguration
         * 配置图片服务的AK、SK
         * 配置图片服务的Namespace
         */
        MediaConfiguration configuration = new MediaConfiguration();
        configuration.setAk("24741902");
        configuration.setSk("a4fe4aaabc9b7dcbfd9452fcd9377b50");
        configuration.setNamespace("lomoye");

        /**
         * 初始化 MediaClient
         * 覆盖默认的getDefaultUploadPolicy方法
         *
         * 默认情况下得到的上传策略是不允许上传文件直接覆盖已存在的文件的.
         * 在这里我们希望能够允许覆盖上传
         * 那么就可以通过匿名类的方式, 改写默认的getDefaultUploadPolicy方法
         */
        client = new DefaultMediaClient(configuration) {
            @Override
            protected UploadPolicy getDefaultUploadPolicy() {
                UploadPolicy uploadPolicy = super.getDefaultUploadPolicy();
                uploadPolicy.setInsertOnly(UploadPolicy.INSERT_ONLY_NONE);
                uploadPolicy.setMimeLimit("image/*");
                uploadPolicy.setExpiration(System.currentTimeMillis() + 3600 * 1000);

                return uploadPolicy;
            }
        };
    }

    /**
     * 服务端上传文件接口,指定保存的文件流
     *
     * @param fileName    文件名
     * @param inputStream 文件流
     */
    public static String upload(String dir, String fileName, InputStream inputStream) {

        if (Strings.isNullOrEmpty(fileName) || inputStream == null) {
            LOGGER.warn("upload photo fileName is null|fileName" + fileName);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "上传文件不能为空,请重新上传");
        }

        if (Strings.isNullOrEmpty(dir)) {
            LOGGER.warn("upload photo dir is null");
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "上传文件目录为空");
        }

        UploadRequest uploadRequest = new UploadRequest();
        try {
            uploadRequest.setInputStream(inputStream, inputStream.available());
        } catch (IOException e) {
            LOGGER.warn("upload photo setInputStream is error|fileName=" + fileName, e);
            throw new BusinessException(ErrorCode.WANTU_API_FAILED, "调用淘宝上传接口错误, 请稍后重试");
        }
        uploadRequest.setDir(dir);
        uploadRequest.setName(fileName);

        try {
            Result<UploadResponse> result = client.upload(uploadRequest);
            if (result.isSuccess()) {
                String url = result.getData().getUrl();
                if (Strings.isNullOrEmpty(url)) {
                    LOGGER.warn("Upload photo to the CDN failure,url is null|result=" + SerializationUtil.gson2String(result));
                    throw new BusinessException(ErrorCode.WANTU_API_FAILED, "调用淘宝上传接口错误,返回的链接为空,请稍后重试");
                } else {
                    return url.replace("http://", "https://");
                }
            } else {
                LOGGER.warn("Upload photo to the CDN fail|result=" + SerializationUtil.gson2String(result));
                throw new BusinessException(ErrorCode.WANTU_API_FAILED, "调用淘宝图片上传接口错误,请稍后重试");
            }
        } catch (Exception e) {
            LOGGER.error("Upload photo to the CDN error|fileName=" + fileName, e);
            throw new BusinessException(ErrorCode.WANTU_API_FAILED, "调用淘宝图片上传接口异常,请稍后重试");
        }
    }

    /**
     * 服务端上传文件接口,指定保存的文件名
     *
     * @param fileName 文件名
     * @param file     文件对象
     */
    public static String upload(String dir, String fileName, File file) {
        if (file == null) {
            LOGGER.error("upload file is null|fileName=" + fileName);
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "上传的文件不能为空,请重试");
        }
        if (Strings.isNullOrEmpty(dir)) {
            LOGGER.warn("upload photo dir is null");
            throw new BusinessException(ErrorCode.PARAMETER_IS_ILLEGAL, "上传文件目录为空");
        }

        try {
            InputStream in = new FileInputStream(file);
            return upload(dir, fileName, in);
        } catch (Exception e) {
            LOGGER.error("Upload photo to the CDN error with no input stream|fileName=" + fileName, e);
            throw new BusinessException(ErrorCode.WANTU_API_FAILED, "调用图片上传接口异常,请稍后重试");
        }
    }

    /**
     * 服务端上传文件接口，自动生成文件名
     *
     * @param inputStream 文件流
     */
    public static String upload(String dir, InputStream inputStream) {
        String fileName = UUID.randomUUID().toString();
        return upload(dir, fileName, inputStream);
    }


    /**
     * 服务端上传文件接口，自动生成文件名
     *
     * @param file 文件对象
     */
    public static String upload(String dir, File file) {
        String fileName = UUID.randomUUID().toString();
        return upload(dir, fileName, file);
    }
}
