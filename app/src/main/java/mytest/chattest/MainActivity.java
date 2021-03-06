package mytest.chattest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private List<Msg> msgList = new ArrayList<Msg>();

  private EditText inputText;

  private Button send;

  private RecyclerView msgRecyclerView;

  private MsgAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initMsgs(); // 初始化消息数据
    inputText = findViewById(R.id.input_text);
    send = findViewById(R.id.send);
    msgRecyclerView = findViewById(R.id.msg_recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    msgRecyclerView.setLayoutManager(layoutManager);

    adapter = new MsgAdapter(msgList);
    msgRecyclerView.setAdapter(adapter);

    send.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String content = inputText.getText().toString();
        if (!"".equals(content)) {
          Msg msg = new Msg(content, Msg.TYPE_SENT);
          msgList.add(msg);
          adapter.notifyItemInserted(msgList.size() - 1); // 当有新消息时，刷新ListView中的显示
          msgRecyclerView.scrollToPosition(msgList.size() - 1); // 将ListView定位到最后一行
          inputText.setText(""); // 清空输入框中的内容
        }
        if ("rua".equals(content)) {
          new Handler().postDelayed(new Runnable() {
            public void run() {
              Msg msg = new Msg("惊了", Msg.TYPE_RECEIVED);
              msgList.add(msg);
              msgRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
              adapter.notifyItemInserted(msgList.size() - 1); // 当有新消息时，刷新ListView中的显示
              msgRecyclerView.scrollToPosition(msgList.size() - 1); // 将ListView定位到最后一行
              inputText.setText(""); // 清空输入框中的内容
            }
          }, 2000);
        }
        if ("114514".equals(content)) {
          new Handler().postDelayed(new Runnable() {
            public void run() {
              Msg msg = new Msg("1919", Msg.TYPE_RECEIVED);
              msgList.add(msg);
              msgRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
              adapter.notifyItemInserted(msgList.size() - 1); // 当有新消息时，刷新ListView中的显示
              msgRecyclerView.scrollToPosition(msgList.size() - 1); // 将ListView定位到最后一行
              inputText.setText(""); // 清空输入框中的内容
            }
          }, 2000);
        }
      }
    });
  }

  private void initMsgs() {
    Msg msg1 = new Msg("zaima", Msg.TYPE_RECEIVED);
    msgList.add(msg1);
    Msg msg2 = new Msg("buzai,cmn", Msg.TYPE_SENT);
    msgList.add(msg2);
    Msg msg3 = new Msg("摸了", Msg.TYPE_RECEIVED);
    msgList.add(msg3);
  }
}
