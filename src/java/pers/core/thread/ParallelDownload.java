package pers.core.thread;

import pers.core.util.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 多线程下载
 * @author: Mr.Deng 315764194@qq.com
 * @Date: 2018-11-17 22:40
 **/
public class ParallelDownload {

    ParallelDownload(String urlStr, String fileName) throws IOException {
        long now = System.currentTimeMillis();
        HttpURLConnection connection = (HttpURLConnection) new URL(urlStr).openConnection();
        final int size = connection.getContentLength();
        final int blockSize = size / 5 + 1;
        ArrayList<DownloadThread> list = new ArrayList<>();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; ++i) {
            int start = blockSize * i;
            int end = start + blockSize > size - 1 ? size - 1 : start + blockSize;
            DownloadThread downloadThread = new DownloadThread(start, end, urlStr);
            list.add(downloadThread);
            executor.execute(new Thread(downloadThread));
        }
        executor.shutdown();
        while (true) {
            if (executor.isTerminated()) {
                byte[] data = new byte[size];
                list.forEach((thread) -> {
                    System.arraycopy(thread.data, 0, data, thread.start, thread.end - thread.start);
                    System.out.printf("%d %d %d\n", thread.start, thread.end, size);
                });
                File dir = new File("./"); // 整个项目根目录
                File file = new File(dir.getPath() + File.separator + fileName);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(data);
                fileOutputStream.close();
                System.out.print(System.currentTimeMillis() - now);
                return;
            }
        }
    }

    public static void main(String[] args) {
        try {
            new ParallelDownload("https://dldir1.qq.com/invc/tt/QQ/SEM/QQBrowser_Setup_QB10_10026002.exe", "QQBrowser_Setup_QB10_10026002.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DownloadThread implements Runnable {
    private String urlStr;
    public int start;
    public int end;
    public byte[] data;

    DownloadThread(int start, int end, String urlStr) {
        this.start = start;
        this.end = end;
        this.urlStr = urlStr;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlStr).openConnection();
            connection.setConnectTimeout(3 * 1000);
            connection.setRequestProperty("Range", "bytes=" + this.start + "-" + this.end);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
            InputStream stream = connection.getInputStream();
            this.data = IOUtils.toByteArray(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}