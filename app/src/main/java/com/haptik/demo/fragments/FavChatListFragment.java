package com.haptik.demo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.haptik.demo.R;
import com.haptik.demo.activities.ChatActivity;
import com.haptik.demo.adapters.ConversationListAdapter;
import com.haptik.demo.models.ChatMessage;
import com.haptik.demo.models.FavConversation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sanjiv on 22/02/17.
 */

public class FavChatListFragment extends Fragment {
    private RecyclerView rvFavChatList;

    private ConversationListAdapter conversationListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fav_chat,container,false);

        Log.e(getClass().getName(),"onCreateView()");

        rvFavChatList = (RecyclerView)rootView.findViewById(R.id.rv_chat_list);


        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvFavChatList.setLayoutManager(verticalLayoutManager);
        rvFavChatList.setNestedScrollingEnabled(true);

        //conversationListAdapter  = new ConversationListAdapter(getActivity(),getData(((ChatActivity)getActivity()).getAllChatMsgList()));
        //rvFavChatList.setAdapter(conversationListAdapter);


        return  rootView;
    }



    public void setList(){

        conversationListAdapter  = new ConversationListAdapter(getActivity(),getData(((ChatActivity)getActivity()).getAllChatMsgList()));
        rvFavChatList.setAdapter(conversationListAdapter);
    }


    /**
     * @param list List of {@link ChatMessage}
     * @return Returns the {@link ArrayList} if {@link FavConversation}
     * This method is  used to get all the list of chat messages and get the count of fav messages from the list.
     * Here all the data of the lst is added in {@link HashMap} then map is converted to {@link ArrayList} of  {@link FavConversation}
     */
    private ArrayList<FavConversation> getData (ArrayList<ChatMessage> list){
        if(list != null){
            HashMap<String,FavConversation> map = new HashMap<>();
            for(ChatMessage chatMessage : list){
                if(map.containsKey(chatMessage.getUsername())){
                    FavConversation conversation = map.get(chatMessage.getUsername());
                    conversation.setGetFavCount(chatMessage.isFavorite() ? conversation.getGetFavCount()+1 : conversation.getGetFavCount());

                    ArrayList<ChatMessage> currentList = conversation.getMsgList();
                    if(currentList != null){
                        currentList.add(chatMessage);
                    }
                    conversation.setMsgCount(conversation.getMsgList().size());
                    map.put(chatMessage.getUsername(),conversation);
                }else{
                    FavConversation favConversation = new FavConversation();
                    ArrayList<ChatMessage> msgList = new ArrayList<>();
                    msgList.add(chatMessage);
                    favConversation.setMsgList(msgList);
                    favConversation.setGetFavCount(chatMessage.isFavorite() ? 1 : 0);
                    favConversation.setMsgCount(favConversation.getMsgList().size());
                    map.put(chatMessage.getUsername(),favConversation);
                }

            }

            return getConversationList(map);
        }
        return null;
    }

    /**
     * @param map
     * @return
     * This method get the list of  {@link FavConversation from the {@link HashMap}}
     */
    private ArrayList<FavConversation> getConversationList(HashMap<String, FavConversation> map){
            Iterator it = map.entrySet().iterator();
            ArrayList<FavConversation> conversationsList = new ArrayList<>();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                conversationsList.add(map.get(pair.getKey()));

                System.out.println(pair.getKey() + " = " + pair.getValue());
            }

        return conversationsList;
    }


}
