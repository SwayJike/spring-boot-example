package com.jourwon.spring.boot.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩解压工具类
 *
 * @author JourWon
 * @date 2021/5/30
 */
public class ZipUtils {

    /**
     * 缓存取大小
     */
    private static final int BUFFER_SIZE = 4 * 1024;

    /**
     * 文件压缩成zip
     * 把文件打成压缩包并输出到客户端浏览器中
     *
     * @param response 响应
     * @param filePath 源文件路径
     */
    public static void zip(HttpServletResponse response, String filePath) {
        ZipOutputStream zos = null;
        try {
            File sourceFile = new File(filePath);
            if (!sourceFile.exists()) {
                throw new IOException("文件不存在");
            }
            // 不保存在本地再输出,通过response流输出直接输出到客户端浏览器中
            zos = new ZipOutputStream(getOutputStream(response, sourceFile.getName()));

            compress(sourceFile, zos, sourceFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * zip文件解压
     *
     * @param zipFilePath zip文件路径
     * @param outPath     输出路径
     */
    public static void unzip(String zipFilePath, String outPath) {
        ZipInputStream inZip = null;
        try {
            inZip = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry zipEntry;
            while ((zipEntry = inZip.getNextEntry()) != null) {
                String name = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    // 获取部件的文件夹名
                    File folder = new File(outPath + File.separator + name);
                    folder.mkdirs();
                } else {
                    File file = new File(outPath + File.separator + name);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    // 获取文件的输出流
                    FileOutputStream out = new FileOutputStream(file);
                    int len;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    // 读取（字节）字节到缓冲区
                    while ((len = inZip.read(buffer)) != -1) {
                        // 从缓冲区（0）位置写入（字节）字节
                        out.write(buffer, 0, len);
                        out.flush();
                    }
                    out.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inZip != null) {
                try {
                    inZip.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inZip != null) {
                try {
                    inZip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 递归压缩文件
     *
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       压缩后的名称
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name) {
        try {
            // 判断文件是否存在
            if (sourceFile.exists()) {
                // 判断是文件还是文件夹
                if (sourceFile.isFile()) {
                    fileToZip(sourceFile, zos, name);
                } else {
                    File[] listFiles = sourceFile.listFiles();
                    if (listFiles == null || listFiles.length == 0) {
                        // 空文件夹的处理
                        zos.putNextEntry(new ZipEntry(name + "/"));
                        // 没有文件，不需要文件的copy
                        zos.closeEntry();
                    } else {
                        for (File file : listFiles) {
                            // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                            // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                            compress(file, zos, name + "/" + file.getName());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件添加到zip输出流
     *
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       zip文件名
     * @throws IOException IOException
     */
    private static void fileToZip(File sourceFile, ZipOutputStream zos, String name) throws IOException {
        // 设置读取数据缓存大小
        byte[] buf = new byte[BUFFER_SIZE];

        // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
        zos.putNextEntry(new ZipEntry(name));
        int len;
        // 创建输入流读取文件
        // BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
        FileInputStream bis = new FileInputStream(sourceFile);
        // copy文件到zip输出流中
        while ((len = bis.read(buf)) != -1) {
            zos.write(buf, 0, len);
        }
        // 关闭输入输出流
        zos.closeEntry();
        bis.close();
    }

    /**
     * 获取输出流
     * 在响应response设置字符集,响应头等信息
     *
     * @param response 响应
     * @param filename 文件名
     * @return OutputStream 输出流
     * @throws IOException IOException
     */
    private static OutputStream getOutputStream(HttpServletResponse response, String filename) throws IOException {
        response.reset();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/octet-stream");

        String fn = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
        response.setHeader("Content-Disposition", "attachment;filename=" + fn + ".zip");
        response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader("Pragma", "no-cache");

        return response.getOutputStream();
    }

}
