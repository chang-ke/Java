package pers.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description: IO工具
 * @author: deng
 * @create: 2018-11-23
 */
public class IOUtils {
    private static final int BUFFER_SIZE = 2048;

    /**
     * @param in 输入流
     * @return byte[]
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) {
            out.write(data, 0, count);
        }
        data = null;
        return out.toByteArray();
    }
    public static byte[] toByteArray(InputStream in, int off, int len) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        in.read(data, off, len);
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) {
            out.write(data, 0, count);
        }
        data = null;
        return out.toByteArray();
    }
}
