package in.ac.iiitd.prankul.datasafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBMS extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";
    public static final String TABLE = "students";

    //table columns
    public static final String TABLE_ROLL = "roll";
    public static final String TABLE_NAME = "name";
    public static final String TABLE_CGPA = "cgpa";

    //constructor

    public DBMS(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    //other functions

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE + " ( " +
                TABLE_ROLL + " text primary key , " +
                TABLE_NAME + " text, " +
                TABLE_CGPA + " text " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addStudent(Student s )
    {
//        ContentValues values = new ContentValues();
//        values.put(TABLE_ROLL, Integer.parseInt(s.roll));
//        values.put(TABLE_NAME, s.name);
//        values.put(TABLE_CGPA, s.cgpa);
//        SQLiteDatabase db = getWritableDatabase();
//        db.insert(TABLE, null, values);
//        db.close();

        String query = "insert into " +TABLE+ " values('"+s.roll+"','"+s.name+"','"+s.cgpa+"');";
        //String query = "insert into " +TABLE+ " values(1212,'POPO','5');";
        Log.i("QUERY",query);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

        Cursor cs = db.rawQuery("Select * from"+ " students ;", null);
        int count=0;
        while(cs.moveToNext())
            count++;
        Log.i("COUT",""+count);
    }

    public void deleteStudent(String roll)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE + " WHERE " + TABLE_ROLL + "='" + roll + "' ;");
    }

    public ArrayList<Student> getAllStudents()
    {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE + " ;";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results

        //help
        Cursor s = db.rawQuery("Select * from"+ " students ;", null);
        int count=0;
        while(s.moveToNext())
            count++;
        Log.i("COUT-22",""+count);

        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(TABLE_ROLL)) != null) {
                Student temp = new Student();
                temp.roll = c.getString(c.getColumnIndex(TABLE_ROLL));
                temp.name = c.getString(c.getColumnIndex(TABLE_NAME));
                temp.cgpa = c.getString(c.getColumnIndex(TABLE_CGPA));
                students.add(temp);
                Log.i("ALLDATA",temp.roll+" "+temp.name+" "+temp.cgpa);
            }
            c.moveToNext();
        }
        db.close();
        return students;
    }

    public Student getStudent(String rollno)
    {
        String query = "SELECT * FROM " + TABLE + " WHERE " + TABLE_ROLL + "= '" + rollno + "' ;";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        if(c.isAfterLast())
        {
            return null;
        }
        Student temp = new Student(c.getString(c.getColumnIndex(TABLE_NAME)),
                ""+c.getInt(c.getColumnIndex(TABLE_ROLL)),c.getString(c.getColumnIndex(TABLE_CGPA)));
        return temp;
    }
}
