package pl.edu.agh.mobilechat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SimpleChatActivity extends AppCompatActivity {
    private String nick;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nick = getIntent().getStringExtra(ConnectActivity.NICK);
        ip = getIntent().getStringExtra(ConnectActivity.IP);


        TextView nickTextView = (TextView) findViewById(R.id.nickTextView);
        nickTextView.setText(nick + "@" + ip);
    }

}
