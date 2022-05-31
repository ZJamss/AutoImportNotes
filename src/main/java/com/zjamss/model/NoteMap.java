package com.zjamss.model;

import java.io.*;
import java.util.HashMap;

/**
 * @Program: AutoImportNote
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-30 20:25
 **/
public class NoteMap extends HashMap<String, String> {
    public NoteMap() throws Exception {
        super();
        File file = new File("notes");
        if(file.exists()){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            NoteMap old = (NoteMap) ois.readObject();
            for (Entry<String, String> entry : old.entrySet()) {
                this.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public String put(String key, String value) {
        String returnValue = super.put(key, value);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("notes"));
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnValue;
    }
}
