package com.haptik.demo.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haptik.demo.R;
import com.haptik.demo.models.ChatMessage;
import com.haptik.demo.utils.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by sanjiv on 22/02/17.
 *
 * This {@link ChatListAdapter} class handles to bind the custom view for the chat list with recycler View.
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.StudentViewHolder> {

    private ArrayList<ChatMessage> alChatList;
    private Context mContext;
    private DisplayImageOptions imageOptions;

    public ChatListAdapter(Context mContext, ArrayList<ChatMessage> list) {
        this.mContext = mContext;
        this.alChatList = list;
        imageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .displayer(new SimpleBitmapDisplayer())
                .build();

    }


    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat_list, parent, false);

        return new StudentViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        ChatMessage student = alChatList.get(position);
        holder.bindView(student,position);

    }

    @Override
    public int getItemCount() {
        return alChatList != null ? alChatList.size() : 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView  tvUserName,tvMessageBody,tvTime;
        private CircleImageView ivUserIcon;
        private AppCompatButton btnFav;


        public StudentViewHolder(View view) {
            super(view);
            tvUserName = (AppCompatTextView) view.findViewById(R.id.tv_user_name);
            tvMessageBody = (AppCompatTextView) view.findViewById(R.id.tv_chat_message);
            tvTime = (AppCompatTextView) view.findViewById(R.id.tv_chat_time);
            ivUserIcon = (CircleImageView)view.findViewById(R.id.iv_chat_icon);
            btnFav = (AppCompatButton)view.findViewById(R.id.btn_fav);

        }

        public void bindView(final ChatMessage chatMessage, final int position){
            //TODO Add Here for Cab Category
            tvUserName.setText(chatMessage.getUsername());
            tvMessageBody.setText(chatMessage.getBody());
            tvTime.setText(Utils.getDate(chatMessage.getMessageTime(),"dd/MM/yyyy hh:mm"));

            if(chatMessage.isFavorite()){
                btnFav.setBackgroundResource(R.drawable.ic_fav);
            }else{
                btnFav.setBackgroundResource(R.drawable.ic_un_fav);
            }

            ImageLoader.getInstance().displayImage(chatMessage.getImageUrl(),ivUserIcon,imageOptions);


            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO Add Code here for Ride Selection
                if(chatMessage.isFavorite()){
                    chatMessage.setFavorite(false);
                    btnFav.setBackgroundResource(R.drawable.ic_un_fav);
                }else{
                    chatMessage.setFavorite(true);
                    btnFav.setBackgroundResource(R.drawable.ic_fav);
                }

                notifyDataSetChanged();
                }
            });
        }

    }
}