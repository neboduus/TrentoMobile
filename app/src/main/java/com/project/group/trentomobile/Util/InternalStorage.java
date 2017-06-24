package com.project.group.trentomobile.Util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/*
 * Stores preferences internally on private space
 */
public final class InternalStorage{

    public static String path ="myPreferenze12";

    private InternalStorage() {}

    public static void writeObject(Context context, Object object) throws IOException {
        try {
            delObject(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = context.openFileOutput(path, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object readObject(Context context) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }

    public static boolean delObject(Context context) throws IOException,
            ClassNotFoundException {
            File dir = context.getFilesDir();
            File file = new File(dir, path);
            boolean deleted = file.delete();

        return deleted;
    }
}