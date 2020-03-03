package io.github.nearchos.rovercoder;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.github.nearchos.rovercoder.ui.main.SectionsPagerAdapter;
import robutev3.android.BrickService;
import robutev3.core.Brick;

public class ControlActivity extends AppCompatActivity {

    private BrickService brickService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        final TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    // todo init brick

    public Brick brick() {
        return brickService.brick();
    }

    public void toggleLooper(final boolean on) {

    }

    public void download() {
        //todo
    }

    public void reset() {
        reset();
    }
}