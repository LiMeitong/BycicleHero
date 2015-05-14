package com.course.byciclehero.frament;

import com.course.byciclehero.ChatSingle;
import com.course.byciclehero.MyMsgListViewAdapter;
import com.course.byciclehero.MySellListViewAdapter;
import com.course.byciclehero.R;
import com.course.byciclehero.ShowPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyFrament3 extends Fragment {

    private ListView listView;

    private MyMsgListViewAdapter adapter;

    private List<String> list ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout3, container, false);

        listView = (ListView) view.findViewById(R.id.MsgListView);
        list = getdata();
        adapter = new MyMsgListViewAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getActivity().startActivityForResult(new Intent(getActivity(), ChatSingle.class),666);
                Toast.makeText(getActivity(),"begin chat"+i,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private List<String> getdata(){
        int size = 0;
        if (list != null) {
            size = list.size();

        }
        if (list == null) {
            list = new ArrayList<String>();
        }

        for (int i = 0; i < 20; i++) {
            list.add("item" + i + size);
        }
        return list;
    }
}
