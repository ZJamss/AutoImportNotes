package com.zjamss.util;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.zjamss.model.vo.FrontMatter;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Program: AutoImportNote
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-29 14:41
 **/
public class Utils {

    static Pattern pattern = Pattern.compile("[\\s\\S]");

    public static Matcher match(String str) {
        return pattern.matcher(str);
    }

    public static byte[] fileToBin(File file) {
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            return bytes;
        } catch (Exception ex) {
            throw new RuntimeException("transform file into bin String 出错", ex);
        }
    }

    public static FrontMatter appendSign(File file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fileName = file.getName().split("\\.")[0];
        FrontMatter frontMatter = new FrontMatter(
                fileName,
                sdf.format(new Date()),
                sdf.format(new Date()),
                fileName.toLowerCase(Locale.ROOT));
        try {
            OutputStream fos = new FileOutputStream(file);
            fos.write(frontMatter.toString().getBytes());
            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return frontMatter;
        }

    }

    public static void displayTray(String msg, TrayIcon.MessageType type) {

        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage(Utils.class.getResource("icon.png"));


        TrayIcon trayIcon = new TrayIcon(image, "笔记自动上传");

        trayIcon.setImageAutoSize(true);

        trayIcon.setToolTip("笔记自动上传");

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {

        }

        trayIcon.displayMessage("笔记自动上传", msg, type);

    }

}
