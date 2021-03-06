package com.eleng.p014_androidmenu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chb1, chb2, chb3;
    Menu menuGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chb1 = (CheckBox) findViewById(R.id.checkBox);
        chb2 = (CheckBox) findViewById(R.id.checkBox2);
        chb3 = (CheckBox) findViewById(R.id.checkBox3);

        chb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // проверяем, какой чек-бокс
                if (compoundButton.getId()==R.id.checkBox3) {
                    // на всякий случай проверим menuGlobal,
                    // вдруг onCreateOptionsMenu еще не вызывался
                    if (menuGlobal != null) {
                        MenuItem item = menuGlobal.findItem(R.id.action_mail);
                        // boolean b - чекнутость chb1
                        // ее нам дает метод onCheckedChanged
                        item.setVisible(b);
                    }
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }});
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        /*MenuItem action_mail = menu.findItem(R.id.action_item1);
        if (chb2.isChecked()) {
            action_mail.setVisible(true);
        } */           //код сделан для одиночного чекбокса. Длинный и работает через одно место.

        menu.setGroupVisible(R.id.group1, chb1.isChecked()); //Адекватный код для группы чекбоксов
        menu.setGroupVisible(R.id.group2, chb2.isChecked());//Как оказалось, проще сделать группу
        // в main_menu и работать с ней
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menuGlobal = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, getString(R.string.action_settings), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_item1:
                Toast.makeText(MainActivity.this, getString(R.string.action_item1), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_item2:
                Toast.makeText(MainActivity.this, getString(R.string.action_item2), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_item3:
                Toast.makeText(MainActivity.this, getString(R.string.action_item3), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
