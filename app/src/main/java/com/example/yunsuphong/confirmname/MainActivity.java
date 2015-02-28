package com.example.yunsuphong.confirmname;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Prepare a variable to hold editText
        //If an object refers to a variable in the surrounding code, it needs to be final
        final EditText firstName = (EditText)findViewById(R.id.firstName);

        firstName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            //The listener is always the same for all scenarios.  Copy/paste is the way to go.
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    Editable editable = firstName.getText();
                    String string = editable.toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("First Name");
                    builder.setMessage("Confirm that your name is " + string + ".");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //Chop off the new line.
                            String s = firstName.getText().toString().trim();
                            firstName.setText(s);
                        }
                    });

                    builder.setNegativeButton("Reject", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //Erase the EditText's contents.
                            firstName.setText("");
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                return false;
            }
        });
    }


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
}
