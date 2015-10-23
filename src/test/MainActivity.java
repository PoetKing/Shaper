package test;

import java.util.ArrayList;
import java.util.List;

import test.testview.TestShaperImageView;
import test.testview.TestShutterImageView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.poet.shaper.R;

public class MainActivity extends Activity {

    List<TestItem> testList = new ArrayList<TestItem>();
    {
        testList.add(new TestItem("ShaperImageView", TestShaperImageView.class));
        testList.add(new TestItem("ShutterImageView", TestShutterImageView.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class<? extends View> clazz = testList.get(position).viewClass;
                try {
                    showDialog(clazz.getConstructor(Context.class).newInstance(MainActivity.this));
                } catch (Exception e) {
                }
            }
        });
        listView.setAdapter(new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(MainActivity.this);
                tv.setText(getItem(position).name);
                int p = getResources().getDimensionPixelSize(R.dimen.dp10);
                tv.setPadding(p, p, p, p);
                return tv;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public TestItem getItem(int position) {
                return testList.get(position);
            }

            @Override
            public int getCount() {
                return testList.size();
            }
        });
        setContentView(listView);
    }

    void showDialog(View view) {
        Dialog d = new Dialog(this, R.style.Dialog_Fullscreen);
        d.setContentView(view);
        d.show();
    }

    class TestItem {
        String name;
        Class<? extends View> viewClass;

        public TestItem(String name, Class<? extends View> viewClass) {
            super();
            this.name = name;
            this.viewClass = viewClass;
        }
    }
}
