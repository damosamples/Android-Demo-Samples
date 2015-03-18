package imperium.com.savedinstancedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";
    private int currentScore;
    private int currentLevel;
    private EditText scoreField;
    private EditText levelField;
    private Button saveButton;
    private TextView scoreLabel;
    private TextView levelLabel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        levelField = (EditText) findViewById(R.id.levelField);
        scoreField = (EditText) findViewById(R.id.scoreField);
        saveButton = (Button) findViewById(R.id.saveButton);
        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        levelLabel = (TextView) findViewById(R.id.levelLabel);
        saveButton.setOnClickListener(this);
        Toast.makeText(context, "Created", Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = getSharedPreferences("SavedInstanceState", MODE_PRIVATE);
        int score = prefs.getInt(STATE_SCORE, 0);
        int level = prefs.getInt(STATE_LEVEL, 0);
        if (score > 0) {
            scoreLabel.setText(String.valueOf(score));
            levelLabel.setText(String.valueOf(level));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try{
            currentLevel = Integer.valueOf(levelField.getText().toString());
            currentScore = Integer.valueOf(scoreField.getText().toString());
            Toast.makeText(context, "Paused",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = getSharedPreferences("SavedInstanceState", MODE_PRIVATE).edit();
            editor.putInt(STATE_SCORE, currentScore);
            editor.putInt(STATE_LEVEL, currentLevel);
            editor.commit();
        }catch (NumberFormatException e){
            Log.e("Error", e.toString());
        }

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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.saveButton:
                try{
                    currentLevel = Integer.valueOf(levelField.getText().toString());
                    currentScore = Integer.valueOf(scoreField.getText().toString());
                    scoreLabel.setText(String.valueOf(currentScore));
                    levelLabel.setText(String.valueOf(currentLevel));
                    Toast.makeText(context, "Data Saved",Toast.LENGTH_SHORT).show();
                }catch (NumberFormatException e){
                    Log.e("Error", e.toString());
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(context, "Destroyed",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(context, "Resumed",Toast.LENGTH_SHORT).show();
    }
}
