package in.ac.iiitd.prankul.datasafe;

import android.os.Environment;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileActivity extends AppCompatActivity {

    TextView content;
    RadioButton radio1,radio2,radio3,srd;
    RadioGroup rdg;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        content = (TextView) findViewById(R.id.content);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);

        rdg = (RadioGroup) findViewById(R.id.radio_group);

        if(savedInstanceState!=null)
        {
            int b = savedInstanceState.getInt("radio");
            if(b==1)
            {
                rdg.check(radio1.getId());
                file = new File(this.getFilesDir(),"internal.txt");
            }
            else if(b==2)
            {
                rdg.check(radio2.getId());
                file = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),"externalPrivate.txt");
            }
            else if(b==3)
            {
                rdg.check(radio3.getId());
                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"externalPublic.txt");
            }
        }
        else
        {
            rdg.check(radio1.getId());
            file = new File(this.getFilesDir(),"internal.txt");
        }
    }

    public void clickRadio(View view)
    {
        RadioButton rd = (RadioButton) view;
        if(rd.getId()==radio1.getId())
        {
            file = new File(this.getFilesDir(),"internal.txt");
        }
        else if(rd.getId() == radio2.getId())
        {
            file = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),"externalPrivate.txt");
        }
        else if(rd.getId() == radio3.getId())
        {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"externalPublic.txt");
        }
    }

    public void clickDelete(View view)
    {
        rdg.check(radio1.getId());
        content.setText("");
        if(file.exists())
        {
            file.delete();
            Toast.makeText(this,"Deleted!",Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(this,"File doesn't exist!",Toast.LENGTH_LONG).show();
        }
    }
    public void clickSave(View view) throws IOException {
        BufferedWriter bf = new BufferedWriter(new FileWriter(file,false));
        bf.write(content.getText().toString());
        bf.close();
        content.setText("");
        Toast.makeText(this,"File successfully saved",Toast.LENGTH_LONG).show();
    }
    public void clickRead(View view) throws IOException {
        if(file.exists())
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = "";
            str = br.readLine();
            String finstr = "";
            while (str!=null)
            {

                finstr = finstr + str + "\n";
                str = br.readLine();
            }

            content.setText(finstr);
        }
        else
        {
            content.setText("");
            Toast.makeText(this,"File doesn't exist!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        RadioButton rd = (RadioButton) findViewById(rdg.getCheckedRadioButtonId());
        if(rd.getId()==radio1.getId())
        {
            outState.putInt("radio",1);
        }
        else if(rd.getId() == radio2.getId())
        {
            outState.putInt("radio",2);
        }
        else if(rd.getId() == radio3.getId())
        {
            outState.putInt("radio",3);
        }
    }
}
