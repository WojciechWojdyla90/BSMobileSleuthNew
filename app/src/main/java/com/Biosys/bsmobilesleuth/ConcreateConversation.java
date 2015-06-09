package com.Biosys.bsmobilesleuth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.Biosys.Adapters.MessageListAdapter;
import com.Biosys.Adapters.MobileMessageAdapter;
import com.Biosys.Controlers.ConversationsControler;
import com.Biosys.Controlers.Session;
import com.Biosys.DaoClasses.Event;
import com.Biosys.DaoClasses.User;
import com.Biosys.LocalDatabaseComunication.DatabaseHelper;
import com.Biosys.Naming.BSMessage;
import com.Biosys.Naming.BSMobileMessage;
import com.Biosys.Naming.BSRodeInfo;
import com.Biosys.Naming.ConversationFull;
import com.Biosys.Naming.MessageFull;
import com.Biosys.Utils.SecurityUtil;
import com.Biosys.WebserviceComunication.IServiceChannel;
import com.Biosys.WebserviceComunication.RestServiceChannnel;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.apache.http.io.SessionInputBuffer;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class ConcreateConversation extends Activity {

    private TextView tvTopic;
    private ConversationFull conversationFull;
    private int position;
    private  Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concreate_conversation);

        SharedPreferences sp = getSharedPreferences(
                "com.biosys.mobilesleuth", Context.MODE_PRIVATE);
        position = sp.getInt("string.convPosition", 1);

        ConversationsControler controler = Session.getControler();
        conversationFull = controler.getConversations().get(position);

        tvTopic = (TextView) findViewById(R.id.tvConcreteMessage);//rowView.findViewById(R.id.tvDay);
        String topic = conversationFull.getConversation().getTopic();
        tvTopic.setText(topic);

        final ListView listview = (ListView) findViewById(R.id.lvConcreteMessage);

        MessageFull[] fulls= new  MessageFull[conversationFull.getMessages().size()];
        fulls = conversationFull.getMessages().toArray(fulls);

        final MobileMessageAdapter adapter = new MobileMessageAdapter(this, R.layout.mobile_message_row, fulls);

        listview.setAdapter(adapter);
        new AsyncReadMessages().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_concreate_conversation, menu);
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
    //onSendMobileMessage
    public void onSendMobileMessage(View v) {
        EditText et1 = (EditText)findViewById(R.id.etConcreteMessage);
        SharedPreferences sp = context.getSharedPreferences("com.biosys.mobilesleuth", Context.MODE_PRIVATE);
        int userId = sp.getInt("string.userId", 1);
        if(!et1.getText().toString().equals("")) {
            ArrayList<MessageFull>messages = Session.getControler().getConversations().get(position).getMessages();
            MessageFull msg = new MessageFull();
            BSMobileMessage bsMobileMessage = new BSMobileMessage();
            bsMobileMessage.setSenderId(userId);
            bsMobileMessage.setConversationId(conversationFull.getConversation().getId());
            bsMobileMessage.setContent(et1.getText().toString());
            msg.setMessage(bsMobileMessage);
            msg.setRodeInfos(new ArrayList<BSRodeInfo>());
            messages.add(msg);
            Session.getControler().getConversations().get(position).setMessages(messages);
            new AsyncRegisterUser().execute(Integer.toString(conversationFull.getConversation().getId()), Integer.toString(userId), et1.getText().toString());

        }
        et1.setText("");
    }

    private class AsyncRegisterUser extends AsyncTask<String, JSONObject, Boolean> {
        ProgressDialog progDailog;
        //String userName=null;
        @Override
        protected Boolean doInBackground(String... params) {
            IServiceChannel serviceChannel = new RestServiceChannnel();
            // boolean userAuth;
            try {

                //Boolean res = serviceChannel.;
                return serviceChannel.SendMessage( Integer.parseInt(params[0].toString()), Integer.parseInt(params[1].toString()),params[2].toString());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                String message = e.getMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                return false;
            }

        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            progDailog = new ProgressDialog(context);
            progDailog.setMessage("Wysyłanie wiadomości");
            progDailog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if(progDailog.isShowing())
                progDailog.dismiss();

            SharedPreferences sp = getSharedPreferences(
                    "com.biosys.mobilesleuth", Context.MODE_PRIVATE);
            position = sp.getInt("string.convPosition", 1);

            ConversationsControler controler = Session.getControler();
            conversationFull = controler.getConversations().get(position);

            tvTopic = (TextView) findViewById(R.id.tvConcreteMessage);//rowView.findViewById(R.id.tvDay);
            String topic = conversationFull.getConversation().getTopic();
            tvTopic.setText(topic);

            final ListView listview = (ListView) findViewById(R.id.lvConcreteMessage);

            MessageFull[] fulls= new  MessageFull[conversationFull.getMessages().size()];
            fulls = conversationFull.getMessages().toArray(fulls);

            final MobileMessageAdapter adapter = new MobileMessageAdapter(context, R.layout.mobile_message_row, fulls);

            listview.setAdapter(adapter);
            //User userPosition = new User("root", "root", "","admin" , -1, 1, util.EncryptString("admin"));
            //userDao.create(userPosition);


            //Toast.makeText(context, getResources().getString(R.string.datas_complete), Toast.LENGTH_LONG).show();
        }

    }

    private class AsyncReadMessages extends AsyncTask<String, JSONObject, Boolean> {
        ProgressDialog progDailog;
        //String userName=null;
        @Override
        protected Boolean doInBackground(String... params) {
            IServiceChannel serviceChannel = new RestServiceChannnel();
            // boolean userAuth;
            try {
                SharedPreferences sp = context.getSharedPreferences("com.biosys.mobilesleuth", Context.MODE_PRIVATE);
                int userId = sp.getInt("string.userId", 1);

                ArrayList<BSMobileMessage> messagesToRead = new ArrayList<BSMobileMessage>();

                for(MessageFull messageFull : conversationFull.getMessages()){
                    boolean res = false;
                    for(BSRodeInfo bsRodeInfo : messageFull.getRodeInfos()){
                        if(bsRodeInfo.getUserId() == userId)
                            res = true;
                    }
                    if(!res && messageFull.getMessage().getSenderId() !=  userId){
                        messagesToRead.add(messageFull.getMessage());
                    }
                }

                for(BSMobileMessage mess : messagesToRead){
                    serviceChannel.InsertRodeInfo(userId,mess.getId());
                }
                //Boolean res = serviceChannel.;
                //serviceChannel.I
                return true;//serviceChannel.SendMessage( Integer.parseInt(params[0].toString()), Integer.parseInt(params[1].toString()),params[2].toString());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                String message = e.getMessage();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                return false;
            }

        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            progDailog = new ProgressDialog(context);
            progDailog.setMessage("Odczytywanie wiadomości");
            progDailog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(progDailog.isShowing())
                progDailog.dismiss();

            Toast.makeText(context, getResources().getString(R.string.title_activity_concreate_end), Toast.LENGTH_LONG).show();
        }

    }
}
