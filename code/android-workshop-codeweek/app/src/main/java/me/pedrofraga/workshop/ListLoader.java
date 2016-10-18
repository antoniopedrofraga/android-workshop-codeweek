package me.pedrofraga.workshop;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Pedro Fraga on 17-Oct-16.
 */

public class ListLoader {

    Context context;
    ListLoader(Context context) { this.context = context; };

    public void saveList(ArrayList<String> listElements) throws IOException {
        try {
            FileOutputStream tempo = context.openFileOutput("list.dat", context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(tempo);
            objectOut.writeObject(listElements);
            objectOut.flush();
            objectOut.close();
            tempo.close();
        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    public ArrayList<String> loadList()  {

        try {

            ArrayList<String> list;
            File listFile = context.getFileStreamPath("list.dat");
            if (listFile.exists()) {

                FileInputStream tempLocations = context.openFileInput("list.dat");

                ObjectInputStream objectIn = new ObjectInputStream(tempLocations);
                list = (ArrayList<String>) objectIn.readObject();

                objectIn.close();
                tempLocations.close();

                return list;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
