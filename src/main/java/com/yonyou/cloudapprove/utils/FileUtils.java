package com.yonyou.cloudapprove.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/6/30
 * @des
 */
public class FileUtils {

    public static InputStream readFile(String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        return classPathResource.getInputStream();
    }
}
