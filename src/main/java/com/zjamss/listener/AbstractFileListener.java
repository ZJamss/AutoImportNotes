package com.zjamss.listener;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * @Program: AutoImportNote
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-30 19:11
 **/
public abstract class AbstractFileListener extends FileAlterationListenerAdaptor {

    @Override
    public void onFileCreate(File file) {
        boolean i = file.getName().equals("Untitled.md");
        if (file.getName().equals("Untitled.md")) return;
        else if(!file.getName().contains(".md") ) return;;
    }

    @Override
    public void onFileChange(File file) {
        if (!file.getName().contains(".md")) return;
    }


}
