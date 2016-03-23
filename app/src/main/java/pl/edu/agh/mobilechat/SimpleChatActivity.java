package pl.edu.agh.mobilechat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SimpleChatActivity extends AppCompatActivity {
    private String nick;
    private String ip;

    private ArrayList<String> listItems = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private TextView nickTextView;
    private ListView chatListView;
    private EditText messageEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nick = getIntent().getStringExtra(ConnectActivity.NICK);
        ip = getIntent().getStringExtra(ConnectActivity.IP);


        nickTextView = (TextView) findViewById(R.id.nickTextView);
        chatListView = (ListView) findViewById(R.id.chatListView);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        sendButton = (Button) findViewById(R.id.sendButton);

        nickTextView.setText(nick + "@" + ip);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        chatListView.setAdapter(adapter);

        final Handler myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                listItems.add("[" + msg.getData().getString("NICK") + "]" +
                    msg.getData().getString("MSG"));
                adapter.notifyDataSetChanged();
                chatListView.setSelection(listItems.size() - 1);
            }
        };

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = myHandler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("NICK", "JA");
                b.putString("MSG", messageEditText.getText().toString());
                msg.setData(b);
                myHandler.sendMessage(msg);
            }
        });
    }

}
