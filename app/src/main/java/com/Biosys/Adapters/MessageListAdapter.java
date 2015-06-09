package com.Biosys.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Biosys.Controlers.Session;
import com.Biosys.Naming.BSMessage;
import com.Biosys.Naming.BSRodeInfo;
import com.Biosys.Naming.BSUser;
import com.Biosys.Naming.ConversationFull;
import com.Biosys.Naming.MemberFull;
import com.Biosys.Naming.MessageFull;
import com.Biosys.Utils.DateUtil;
import com.Biosys.bsmobilesleuth.R;

public class MessageListAdapter extends ArrayAdapter<ConversationFull>{
	private Context _context;
	private ConversationFull[] _objects;
	
	public MessageListAdapter(Context context,
			int textViewResourceId, ConversationFull[] objects) {
		super(context , textViewResourceId, objects);
		_context = context;
		_objects = objects;
	}

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {

        SharedPreferences sp = _context.getSharedPreferences("com.biosys.mobilesleuth", Context.MODE_PRIVATE);
        int userId = sp.getInt("string.userId", 1);

        String userName = "";

        for(MemberFull member : _objects[position].getMembers()){
            if(member.getUser().getId() != userId)
                userName = member.getUser().getName() + " " + member.getUser().getSurname();
        }

        boolean isNewMessage = false;

        for(MessageFull msg:_objects[position].getMessages()){
            boolean isReaded = false;
            for(BSRodeInfo info : msg.getRodeInfos()){
                if(info.getMessageId() == msg.getMessage().getId() && info.getUserId() == userId)
                    isReaded = true;
            }
            if(msg.getMessage().getSenderId() != userId && !isReaded)
                isNewMessage = true;
        }

	    LayoutInflater inflater = (LayoutInflater) _context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	    View rowView = inflater.inflate(R.layout.message_list_row, parent, false);
	    TextView textViewTitle = (TextView) rowView.findViewById(R.id.thirdLine);
	    TextView textViewDescription = (TextView) rowView.findViewById(R.id.secondLine);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

	    textViewTitle.setText(DateUtil.parseDateToString(_objects[position].getConversation().getTopic()));
	    textViewDescription.setText(userName);

        if(isNewMessage)
            imageView.setImageResource(R.drawable.new_message);
        else
            imageView.setImageResource(R.drawable.no_message);

	    return rowView;
	  }
}
