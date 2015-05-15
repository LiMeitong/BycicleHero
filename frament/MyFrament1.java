package com.course.byciclehero.frament;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.course.byciclehero.AddItem;
import com.course.byciclehero.DetailAty;
import com.course.byciclehero.MyListViewAdapter;
import com.course.byciclehero.R;
import com.google.zxing.client.android.ViewfinderView;

import java.util.ArrayList;
import java.util.List;

/**
 * get lost and found page.
 */
public class MyFrament1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;

    private MyListViewAdapter adapter;

    private List<AVObject> list ;

    private SwipeRefreshLayout swipeRefreshLayoutLost;

    private static int pageNum = 0 ;
    private static int pageSize = 1;


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


        /**
         * set swipe to refresh layout
         */

        swipeRefreshLayoutLost = (SwipeRefreshLayout) view.findViewById(R.id.swipe_lost_container);
        swipeRefreshLayoutLost.setOnRefreshListener(this);
        swipeRefreshLayoutLost.setColorSchemeResources(R.color.titleFocusedColor);


        return view;

    }

    /**
     * get data from server.
     * @return list of lost result
     */
    private List<AVObject> getdata(){

        if (list == null) {
            list = new ArrayList<AVObject>();
        }

        AVQuery<AVObject> query = new AVQuery<AVObject>("LostItem");
        query.orderByDescending("createdAt");
        query.setLimit(pageSize);
        query.setSkip(pageSize*pageNum);

            query.findInBackground(new FindCallback<AVObject>() {
                public void done(List<AVObject> avObjects, AVException e) {
                    if (e == null) {
                        if( avObjects.size() == 0 ){
                            Toast.makeText(getActivity(),"no more",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            pageNum++;
                            list.addAll(avObjects);
                        }
                    } else {
                        Log.d("失败", "查询错误: " + e.getMessage());
                    }
                }
            });

        return list;


    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getdata();
                swipeRefreshLayoutLost.setRefreshing(false);
            }
        }, 3000);
    }
}
