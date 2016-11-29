package com.example.anoop.noteapp_v1.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anoop.noteapp_v1.R;
import com.example.anoop.noteapp_v1.activities.MainActivity;
import com.example.anoop.noteapp_v1.adapters.RealmNoteAdapter;
import com.example.anoop.noteapp_v1.models.Note;
import com.example.anoop.noteapp_v1.realm.RealmNoteController;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;

/**
 * Created by anoop on 17/10/2016.
 */

public class NotesActivity extends AppCompatActivity{

    @BindView(R.id.note_date)
    TextView date_tv;

    @BindView(R.id.notes_title_et)
    EditText getTitle;

    @BindView(R.id.view_note)
    EditText getNote;

    private Realm realm;
    private RealmChangeListener realmChangeListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity);
        setTitle(R.string.application_name);
        ButterKnife.bind(this);

        this.realm=RealmNoteController.with(this).getRealm();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        date_tv.setText(dateFormat.format(date));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object element) {
                RealmNoteController.with(NotesActivity.this).refresh();
            }
        };
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.note_save:
                saveNote();
                Toast.makeText(this, "Save works", Toast.LENGTH_SHORT).show();
                break;
            case R.id.note_send:
                Toast.makeText(this, "Settings work", Toast.LENGTH_SHORT).show();
                break;
            case R.id.note_lock:
                Toast.makeText(this, "Lock works", Toast.LENGTH_SHORT).show();
                break;
            case R.id.note_reminder:
                Toast.makeText(this, "reminder works", Toast.LENGTH_SHORT).show();
                break;
            case R.id.note_delete:
                Toast.makeText(this, "delete works", Toast.LENGTH_SHORT).show();
                break;
            case R.id.note_colour:
                Toast.makeText(this, "colour works", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                //also save notes
                break;

            default: Toast.makeText(this, "did not work", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if(menu != null){
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try{
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                }catch (Exception e){

                }
            }
        }

        return super.onPrepareOptionsPanel(view, menu);
    }

    private void saveNote(){
        Note note = new Note();
        if(getTitle.getText().toString().equals("") && getNote.getText().toString().equals("")){
            Intent intent = new Intent(NotesActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(getTitle.getText().toString().equals("") && getNote.getText().toString().length() > 0){
            getTitle.setText(getNote.getText().toString());
        }

        Log.i("NOTEID", String.valueOf(getNoteID()));   //        note.setNoteID(getNoteID());
        Log.i("TITLE", getTitle.getText().toString());  //        note.setTitle(getTitle.getText().toString());
        Log.i("NOTE", getNote.getText().toString());    //        note.setNote(getNote.getText().toString());
        Log.i("CURRENTDATE", getCurrentDate());         //        note.setDate(getCurrentDate());
        Log.i("CURRENTIME", getCurrentTime());          //        note.setTime(getCurrentTime());


    }

    private long getNoteID(){
        boolean check = false;
        while(check==false){
            check = true;
            Random random = new Random();
            long idRange = random.nextLong();

            if(RealmNoteController.getInstance().checkNoteID(idRange) == true){
                check=false;

            }else {
                return idRange;
            }
        }
        return 0;
    }

    private String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime()).toString();
    }

    private String getCurrentTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime()).toString();
    }

    public static class LineEditText extends EditText{
        public LineEditText(Context context, AttributeSet attrs) {
            super(context, attrs);
            mRect = new Rect();
            mPaint = new Paint();

            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(Color.BLUE);
        }

        private Rect mRect;
        private Paint mPaint;

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int height = getHeight();
            int line_height = getLineHeight();
            int count = height/line_height;

            if(getLineCount() > count)

                count = getLineCount();
                Rect r = mRect;
                Paint paint = mPaint;
                int baseline = getLineBounds(0,r);

                for(int i=0; i<count; i++){
                    canvas.drawLine(r.left, baseline+1, r.right, baseline+1, paint);
                    baseline += getLineHeight();
                }
        }
    }




}
