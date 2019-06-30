package com.t3h.ailatrieuphu.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.t3h.ailatrieuphu.database.ListQuestion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ManagerSql {

    private static final String db_Name = "Question";
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public ManagerSql(Context context) {
        this.context = context;
        copyExApp();
    }

    private void copyExApp(){
        String path =
                Environment.getDataDirectory().getPath() + "/data/"+ context.getPackageName() + "/db";
        if ( new File(path+"/"+db_Name).exists() ){
            return;
        }
        try {
            //lay inputsteam trong asset
            InputStream in = context.getAssets().open(db_Name);
            //lay duong duong dan luu vao ex app

            new File(path).mkdir();
            OutputStream out = new FileOutputStream(path+"/"+db_Name);
            byte[] b = new byte[1024];
            int le = in.read(b);
            while (le >-1){
                out.write(b, 0, le);
                le = in.read(b);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDB(){
        if ( sqLiteDatabase == null || sqLiteDatabase.isOpen() == false){
            String path =
                    Environment.getDataDirectory().getPath() + "/data/"+ context.getPackageName() + "/db";

            sqLiteDatabase =
                    SQLiteDatabase.openDatabase(path+"/"+db_Name, null, SQLiteDatabase.OPEN_READWRITE);
        }


    }

    public void closeDB(){
        if (sqLiteDatabase == null || sqLiteDatabase.isOpen() == false){
            return;
        }
        sqLiteDatabase.close();
        sqLiteDatabase = null;
    }

    public List<ListQuestion> getAllQuestion(){
        List<ListQuestion> listQuestions = new ArrayList<>();
        openDB();
        Cursor c =
                sqLiteDatabase.rawQuery(
                        "select * from (select *  from question order by random()) group by level ORDER by level "
                        , null);
        c.moveToFirst();
        int indexQues = c.getColumnIndex("question");
        int indexCaseA = c.getColumnIndex("casea");
        int indexCaseB = c.getColumnIndex("caseb");
        int indexCaseC = c.getColumnIndex("casec");
        int indexCaseD = c.getColumnIndex("cased");
        int indexTrueCase = c.getColumnIndex("truecase");
        while (!c.isAfterLast()){
            listQuestions.add(new ListQuestion(c.getString(indexQues),
                    c.getString(indexCaseA),
                    c.getString(indexCaseB),
                    c.getString(indexCaseC),
                    c.getString(indexCaseD),
                    c.getString(indexTrueCase))
            );
            c.moveToNext();
        }
        closeDB();
        return listQuestions;
    }

}
