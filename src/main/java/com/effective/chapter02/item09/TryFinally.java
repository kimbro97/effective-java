package com.effective.chapter02.item09;

import java.io.*;

public class TryFinally {

    static String firstLineOfFile1(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        try {
            return bufferedReader.readLine();
        } finally {
            bufferedReader.close();
        }
    }

    static void copy1(String src, String dst) throws IOException {
        FileInputStream in = new FileInputStream(src);
        try {
            FileOutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[1024];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            }finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    static String firstLineOfFile2(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    static void copy2(String src, String dst) throws IOException {
        try (FileInputStream in = new FileInputStream(src);
             FileOutputStream out = new FileOutputStream(dst)) {
                byte[] buf = new byte[1024];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
             }
    }

    static String firstLineOfFile3(String path, String defaultValue) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultValue;
        }
    }

}
