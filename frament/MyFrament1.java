package com.course.byciclehero.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.course.byciclehero.AddItem;
import com.course.byciclehero.DetailAty;
import com.course.byciclehero.MyListViewAdapter;
import com.course.byciclehero.R;

import java.util.ArrayList;
import java.util.List;

/**
 * get lost and found page.
 */
public class MyFrament1 extends Fragment {

    private ListView listView;

    private MyListViewAdapter adapter;

    private List<String> list ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout1, container, false);

        listView = (ListView) view.findViewById(R.id.LostListview);
        list = getdata();
        adapter = new MyListViewAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle bundle = new Bundle();
                TextView descriptionString = (TextView) view.findViewById(R.id.lostDescriptionText);
                bundle.putString("descript",descriptionString.getText().toString());
                TextView priceString = (TextView) view.findViewById(R.id.rewardText);
                bundle.putString("priceOrReward",priceString.getText().toString());

                Intent toDetailIntent = new Intent(getActivity(), DetailAty.class);
                toDetailIntent.putExtras(bundle);
                getActivity().startActivityForResult(toDetailIntent,10);

                Toast.makeText(getActivity(),"he"+i,Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

    /**
     * get data from leancloud.
     * @return list of result
     */
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
