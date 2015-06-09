package com.Biosys.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Biosys.Controlers.ConversationsControler;
import com.Biosys.Controlers.Session;
import com.Biosys.Naming.BSRodeInfo;
import com.Biosys.Naming.BSUser;
import com.Biosys.Naming.ConversationFull;
import com.Biosys.Naming.MemberFull;
import com.Biosys.Naming.MessageFull;
import com.Biosys.Utils.DateUtil;
import com.Biosys.bsmobilesleuth.R;

/**
 * Created by wojdy_000 on 2015-06-08.
 */
public class MobileMessageAdapter  extends ArrayAdapter<MessageFull> {
    private Context _context;
    private MessageFull[] _objects;

    public MobileMessageAdapter(Context context,
                              int textViewResourceId, MessageFull[] objects) {
        super(context , textViewResourceId, objects);
        _context = context;
        _objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SharedPreferences sp = _context.getSharedPreferences(
                "com.biosys.mobilesleuth", Context.MODE_PRIVATE);
        int pos = sp.getInt("string.convPosition", 1);

        ConversationsControler controler = Session.getControler();
        ConversationFull conversationFull = controler.getConversations().get(pos);


        LayoutInflater inflater = (LayoutInflater) _context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.mobile_message_row, parent, false);
        TextView textViewTitle = (TextView) rowView.findViewById(R.id.thirdLine);
        TextView textViewDescription = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        MessageFull messageFull = _objects[position];


        String name = "";
        BSUser user = null;
        for(MemberFull memberFull : conversationFull.getMembers()){
            if(memberFull.getUser().getId() == messageFull.getMessage().getSenderId()){
                name = memberFull.getUser().getName() + " " + memberFull.getUser().getSurname();
                user = memberFull.getUser();
            }
        }

        textViewDescription.setText(messageFull.getMessage().getContent());
        textViewTitle.setText(name);

        if(user.getPosition().getId() == 1)
            imageView.setImageResource(R.drawable.manager);
        else if(user.getPosition().getId() == 2)
            imageView.setImageResource(R.drawable.trader);
        else
            imageView.setImageResource(R.drawable.employee);


        return rowView;
    }
}

