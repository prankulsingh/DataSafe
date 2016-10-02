package in.ac.iiitd.prankul.datasafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowDatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);

        DBMS db = new DBMS(this,null,null,1);

        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        TextView text;

        for(Student s : db.getAllStudents())
        {
            text = new TextView(this);
            text.setText(s.roll+" "+s.name+" "+s.cgpa);
            linear.addView(text);
            Log.i("DATA",s.roll+" "+s.name+" "+s.cgpa);
        }
    }
}
