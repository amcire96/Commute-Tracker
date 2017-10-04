package me.amcire.commutetracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int ADD_ROUTE_RESULT = 1;

    private ListView mListView;
    private ArrayList<Route> mRouteList = new ArrayList<>();
    private FloatingActionButton mAddRouteButton;
    private RouteListAdapter mRouteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.route_list);
        mRouteListAdapter = new RouteListAdapter(this, mRouteList);
        mListView.setAdapter(mRouteListAdapter);

        mAddRouteButton = (FloatingActionButton) findViewById(R.id.addRouteBtn);
        mAddRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddRouteActivity.class);
                startActivityForResult(intent, ADD_ROUTE_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_ROUTE_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                Log.i("ERIC MA", "In onActivityResult with RESULT_OK");
                String src_location = data.getStringExtra("source_location");
                String dest_location = data.getStringExtra("destination_location");
                String title = data.getStringExtra("title");
                mRouteList.add(new Route(src_location, dest_location, title));
                mRouteListAdapter.update(mRouteList);
            }
        }
    }

    class RouteListAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mInflater;
        private ArrayList<Route> mDataSource;

        public RouteListAdapter(Context context, ArrayList<Route> dataSource) {
            mContext = context;
            mDataSource = dataSource;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void update(ArrayList<Route> updatedData) {
            mDataSource = updatedData;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mDataSource.size();
        }

        @Override
        public Object getItem(int i) {
            return mDataSource.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_route, parent, false);

                holder = new ViewHolder(
                        (TextView) convertView.findViewById(R.id.route_source_location),
                        (TextView) convertView.findViewById(R.id.route_destination_location),
                        (TextView) convertView.findViewById(R.id.route_title));

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Route route = (Route) getItem(i);

            TextView src_location_text = holder.srcTextView;
            src_location_text.setText(route.mSrc);
            TextView dest_location_text = holder.destTextView;
            dest_location_text.setText(route.mDest);
            TextView title_text = holder.titleTextView;
            title_text.setText(route.mTitle);

            return convertView;
        }
    }

    class Route {
        String mTitle;
        // TODO: change these to whatever Google maps wants.
        String mSrc;
        String mDest;
        public Route(String newTitle, String newSrc, String newDest) {
            mTitle = newTitle;
            mSrc = newSrc;
            mDest = newDest;
        }
    }

    class ViewHolder {
        TextView srcTextView;
        TextView destTextView;
        TextView titleTextView;

        public ViewHolder(TextView newSrc, TextView newDest, TextView newTitle) {
            srcTextView = newSrc;
            destTextView = newDest;
            titleTextView = newTitle;
        }
    }

}
