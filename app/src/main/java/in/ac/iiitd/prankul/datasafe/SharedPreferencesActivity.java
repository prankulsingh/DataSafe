package in.ac.iiitd.prankul.datasafe;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesActivity extends AppCompatActivity {

    EditText key,value;
    LinearLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        key = (EditText) findViewById(R.id.key);
        value = (EditText) findViewById(R.id.value);
        table = (LinearLayout) findViewById(R.id.table);

        if(savedInstanceState!=null)
        {
            key.setText(savedInstanceState.getString("key"));
            value.setText(savedInstanceState.getString("value"));
        }

        updateTable();
    }

    public void clickCommitToSharedPreferences(View view)
    {
        if(key.getText().toString().isEmpty() || value.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Please enter all fields",Toast.LENGTH_LONG).show();
        }
        else
        {
            SharedPreferences.Editor editor = getSharedPreferences("MySharedPreference", MODE_PRIVATE).edit();
            editor.putString(key.getText().toString(), value.getText().toString());
            editor.commit();

            key.setText("");
            value.setText("");

            updateTable();
        }

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void clickCleared(View view)
    {
        SharedPreferences.Editor editor = getSharedPreferences("MySharedPreference", MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        updateTable();
    }

    public void updateTable()
    {

        table.removeAllViews();
        SharedPreferences sp = getSharedPreferences("MySharedPreference",MODE_PRIVATE);
        Map<?,?> hm = sp.getAll();
        for(Object t : hm.keySet())
        {
            TextView text = new TextView(this);
            text.setText(t.toString()+" : "+hm.get(t).toString());
            table.addView(text);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key",key.getText().toString());
        outState.putString("value",value.getText().toString());
    }
}
