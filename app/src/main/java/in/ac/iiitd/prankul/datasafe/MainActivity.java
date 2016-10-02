package in.ac.iiitd.prankul.datasafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickSharedPreferences(View view)
    {
        Intent i = new Intent(this, SharedPreferencesActivity.class);
        startActivity(i);
    }

    public void clickFiles(View view)
    {
        Intent i = new Intent(this, FileActivity.class);
        startActivity(i);
    }

    public void clickDatabase(View view)
    {
        Intent i = new Intent(this,DatabaseActivity.class);
        startActivity(i);
    }

}
