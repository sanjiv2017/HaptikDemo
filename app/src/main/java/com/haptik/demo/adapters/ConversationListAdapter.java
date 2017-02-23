package com.haptik.demo.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haptik.demo.R;
import com.haptik.demo.models.ChatMessage;
import com.haptik.demo.models.FavConversation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by sanjiv on 22/02/17.
 *
 * This {@link ConversationListAdapter} class handles to bind the custom view for the chat list with recycler View.
 */
public class ConversationListAdapter extends RecyclerView.Adapter<ConversationListAdapter.StudentViewHolder> {

    private ArrayList<FavConversation> favConversationArrayList;
    private Context mContext;

    public ConversationListAdapter(Context mContext, ArrayList<FavConversation> map) {
        this.mContext = mContext;
        this.favConversationArrayList = map;

    }


    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fav_chat, parent, false);

        return new StudentViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        FavConversation favConversation = favConversationArrayList.get(position);
        holder.bindView(favConversation,position);

    }

    @Override
    public int getItemCount() {
        return favConversationArrayList != null ? favConversationArrayList.size() : 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView  tvUserName,tvLastMsg,tvFavCount,tvMsgCount;
        private CircleImageView ivUserIcon;


        public StudentViewHolder(View view) {
            super(view);
            tvUserName = (AppCompatTextView) view.findViewById(R.id.tv_user_name);
            tvLastMsg = (AppCompatTextView) view.findViewById(R.id.tv_last_msg);
            tvFavCount = (AppCompatTextView) view.findViewById(R.id.tv_fav_count);
            tvMsgCount = (AppCompatTextView) view.findViewById(R.id.tv_msg_count);
            ivUserIcon = (CircleImageView)view.findViewById(R.id.iv_chat_icon);

        }

        public void bindView(final FavConversation favConversation, final int position){
            //TODO Add Here for Cab Category
            tvUserName.setText(favConversation.getMsgList().get(0).getUsername());
            tvMsgCount.setText(String.valueOf(favConversation.getMsgCount()));
            tvFavCount.setText(String.valueOf(favConversation.getGetFavCount()));


            tvLastMsg.setText(favConversation.getMsgList().get((favConversation.getMsgList().size() - 1)).getBody());


            // Picasso Image Loader.
            final String imageUrl = favConversation.getMsgList().get(0).getImageUrl();

            if(imageUrl != null && imageUrl.trim().length() > 0){
                Picasso.with(mContext).load(imageUrl).into(ivUserIcon);
            }

        }

    }
}