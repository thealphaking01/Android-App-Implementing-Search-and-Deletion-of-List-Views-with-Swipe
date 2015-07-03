package com.example.abdur.tempprojpiyushsir;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private AutoCompleteTextView auto;
    LinearLayout li;
    ListView listView;
    TextView res;
    String names[]={"abdur","abd","abdure","abdddd","fill","frank","hello","hiye","powe","super","sqwa"};
    ArrayList<String> results= new ArrayList<String>();

    ArrayAdapter<String> adapter,ad2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auto=(AutoCompleteTextView)findViewById(R.id.autoComplete);
        listView=(ListView) findViewById(R.id.list);

        adapter=new ArrayAdapter<String>(this,R.layout.select_dialog_item_material,names);
        ad2=new ArrayAdapter<String>(this,R.layout.listview,results);
        listView.setAdapter(ad2);
        auto.setThreshold(3);
        auto.setAdapter(adapter);

        final SwipeDetector swipeDetector = new SwipeDetector();
        listView.setOnTouchListener(swipeDetector);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(swipeDetector.swipeDetected()){
                    if(swipeDetector.getAction() == SwipeDetector.Action.RL || swipeDetector.getAction()==SwipeDetector.Action.LR) { results.remove(position);
                    ad2.notifyDataSetChanged(); }
                }
            }
        });
        auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),((TextView)view).getText(),Toast.LENGTH_LONG).show();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                auto.setText("");
                results.add((String) ((TextView) view).getText());
                ad2.notifyDataSetChanged();
//                updateList(s);
            }
        });

    }
//    void updateList(){
//        results.add((String) ((TextView) view).getText());
//                ad2.notifyDataSetChanged();
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchAgain(View view){
        auto.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0,0);
//        Toast.makeText(getApplicationContext(),"button",Toast.LENGTH_LONG).show();
    }
}
