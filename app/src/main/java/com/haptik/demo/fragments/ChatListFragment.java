package com.haptik.demo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.text.ICUCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.haptik.demo.R;
import com.haptik.demo.activities.ChatActivity;
import com.haptik.demo.adapters.ChatListAdapter;
import com.haptik.demo.models.ChatMessage;
import com.haptik.demo.webapi.chat.ChatServiceFacade;
import com.haptik.demo.webapi.core.IOCContainer;
import com.haptik.demo.webapi.core.ServiceName;
import com.haptik.demo.webapi.core.response.ChatApiResponse;
import com.haptik.demo.webapi.core.response.IResponseSubscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Sanjiv Patel on 22/02/17.
 * This fragments class handles to show all the Chart List.
 */

public class ChatListFragment extends Fragment implements IResponseSubscribe {
    private RecyclerView rvChatList;

    private ChatListAdapter chatListAdapter;
    private ProgressBar pbChatLoader;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat,container,false);

        rvChatList = (RecyclerView)rootView.findViewById(R.id.rv_chat_list);
        pbChatLoader =(ProgressBar)rootView.findViewById(R.id.pb_chatLoader);


        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvChatList.setLayoutManager(verticalLayoutManager);
       // rvChatList.setNestedScrollingEnabled(false);



        return rootView;

    }

    /**
     * @param chatList {@link ArrayList} of {@link ChatMessage}
     *                                  This method set custom adapter for all chats.
     *                                  This method checks if list is null or empty then not set the lst adater set the empty message.
     */
    private void setChatListAdapter(ArrayList<ChatMessage> chatList){
        if(chatList != null){
            chatListAdapter  = new ChatListAdapter(getActivity(),chatList);
            rvChatList.setAdapter(chatListAdapter);
        }else{
            Toast.makeText(getActivity(),getString(R.string.no_chat),Toast.LENGTH_SHORT).show();
        }

        pbChatLoader.setVisibility(View.GONE);

    }


    @Override
    public void onResume() {
        super.onResume();
        IOCContainer.getInstance().publisher.registerResponseSubscribe(this);
        if(chatListAdapter == null){
            ((ChatServiceFacade)IOCContainer.getInstance().getObject(ServiceName.CHAT_SERVICE,"CHAT")).getAllChats();
        }

    }


    @Override
    public void onPause() {
        IOCContainer.getInstance().publisher.unregisterResponseSubscribe(this);
        super.onPause();
    }

    @Override
    public void onSuccess(ChatApiResponse response, String tag, int apiCode) {
        switch (apiCode){
            case ServiceName.CHAT_SERVICE:
                if(response != null){
                    final ArrayList<ChatMessage> msgList = response.getMessages();
                    Collections.sort(msgList,comparator);
                    setChatListAdapter(response.getMessages());
                    if(getActivity() instanceof ChatActivity){

                        ((ChatActivity)getActivity()).setChatList(response.getMessages());
                    }
                }else{
                    Toast.makeText(getActivity(),getString(R.string.unknown_exception),Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onError(Throwable t, int apiCode) {
        Toast.makeText(getActivity(),getString(R.string.no_internet_connectivity),Toast.LENGTH_SHORT).show();
    }

    /**
     * Comparator for Sorting the List of Chat Messages according to date.
     */
    Comparator<ChatMessage> comparator = new Comparator<ChatMessage>() {
        @Override
        public int compare(ChatMessage o1, ChatMessage o2) {
            try{
                Date d1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(o1.getMessageTime());
                Date d2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(o2.getMessageTime());
                return  d1.compareTo(d2);
            }catch (ParseException e){
                e.printStackTrace();
            }

            return 0;
        }
    };
}
