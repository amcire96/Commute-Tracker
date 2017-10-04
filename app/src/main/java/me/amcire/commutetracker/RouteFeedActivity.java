package me.amcire.commutetracker;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class RouteFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_feed_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.route_feed_toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        // create an adapter
        CommuteOptionsPagerAdapter adapter = new CommuteOptionsPagerAdapter(
                getSupportFragmentManager());

        // add your fragments to the adapter
        Fragment fragment1 = new CommuteOptionFragment();
        Bundle args = new Bundle();
        args.putInt(CommuteOptionFragment.ARG_SECTION_NUMBER, 1);
        fragment1.setArguments(args);
        adapter.addFragment(fragment1, "Commute Option 1");

        Fragment fragment2 = new CommuteOptionFragment();
        Bundle args2 = new Bundle();
        args2.putInt(CommuteOptionFragment.ARG_SECTION_NUMBER, 2);
        fragment2.setArguments(args2);
        adapter.addFragment(fragment2 , "Commute Option 2");

        // set the adapter to the ViewPager
        viewPager.setAdapter(adapter);
    }


    // adapter class
    class CommuteOptionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public CommuteOptionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}