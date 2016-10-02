package in.ac.iiitd.prankul.datasafe;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {

    TextView name, roll, cgpa;
    Button action;
    RadioGroup rdg;
    RadioButton radio1,radio2,radio3;
    DBMS db;
    SQLiteDatabase ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        db = new DBMS(this,null,null,1);
        ss = db.getWritableDatabase();

        name = (TextView) findViewById(R.id.name);
        roll = (TextView) findViewById(R.id.roll);
        cgpa = (TextView) findViewById(R.id.cgpa);
        rdg = (RadioGroup) findViewById(R.id.radio_group);
        action = (Button) findViewById(R.id.action);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio1);
        radio3 = (RadioButton) findViewById(R.id.radio1);

    }

    public void clickRadio1(View view)
    {
        name.setHint("Name (Required)");
        roll.setHint("Roll No. (Required)");
        cgpa.setHint("CGPA (Required)");
        name.setEnabled(true);
        cgpa.setEnabled(true);
        action.setText("Add");
    }
    public void clickRadio2(View view)
    {
        name.setHint("Name");
        roll.setHint("Roll No. (Required)");
        cgpa.setHint("CGPA");
        name.setEnabled(true);
        cgpa.setEnabled(true);
        action.setText("Update");
    }
    public void clickRadio3(View view)
    {
        name.setHint("Name");
        roll.setHint("Roll No. (Required)");
        cgpa.setHint("CGPA");
        roll.setText("");
        cgpa.setText("");
        name.setEnabled(false);
        cgpa.setEnabled(false);
        action.setText("Delete");
    }
    public void clickAction(View view)
    {
        Boolean b = (name.getText().toString().equals("") && roll.getText().toString().equals("") && cgpa.getText().toString().equals(""));
        if(rdg.getCheckedRadioButtonId()==radio1.getId())
        {
            if(name.getText().toString().equals("") && roll.getText().toString().equals("") && cgpa.getText().toString().equals(""))
            {
                Toast.makeText(this,"Fill all required fields!",Toast.LENGTH_SHORT);
            }
            else
            {
                db.addStudent(new Student(name.getText().toString(), roll.getText().toString(), cgpa.getText().toString()));
            }
        }
        if(rdg.getCheckedRadioButtonId()==radio2.getId())
        {
            if(roll.getText().toString().equals(""))
            {
                Toast.makeText(this,"Fill all required fields!",Toast.LENGTH_SHORT);
            }
            else
            {
                if(db.getStudent(roll.getText().toString())==null)
                {
                    Toast.makeText(this,"Entry not found!",Toast.LENGTH_SHORT);
                }
                else {
                    String namee, rolll, cgpaa;
                    rolll = roll.getText().toString();
                    if (name.getText().toString().equals("")) {
                        namee = db.getStudent(rolll).name;
                    } else {
                        namee = name.getText().toString();
                    }
                    if (cgpa.getText().toString().equals("")) {
                        cgpaa = db.getStudent(rolll).cgpa;
                    } else {
                        cgpaa = cgpa.getText().toString();
                    }
                    db.addStudent(new Student(namee, rolll, cgpaa));
                }
            }
        }
        if(rdg.getCheckedRadioButtonId()==radio3.getId())
        {
            if(roll.getText().toString().equals(""))
            {
                Toast.makeText(this,"Fill all required fields!",Toast.LENGTH_SHORT);
            }
            else
            {
                if(db.getStudent(roll.getText().toString())==null)
                {
                    Toast.makeText(this,"Entry not found!",Toast.LENGTH_SHORT);
                }
                else
                {
                    db.deleteStudent(roll.getText().toString());
                }
            }
        }
    }
    public void clickViewDatabase(View view)
    {
        Intent i = new Intent(this,ShowDatabaseActivity.class);
        startActivity(i);
    }

}
