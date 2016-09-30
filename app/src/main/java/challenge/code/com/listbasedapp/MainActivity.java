package challenge.code.com.listbasedapp;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fab;
    FragmentManager fm;
    MyDialogFragment dialogFragment;
    String dummyListData;
    public static List<String> items = new ArrayList<>();
    RecyclerView recyclerview;
    public static RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initObj();
        initListeners();


        if(savedInstanceState == null)
        parseJSONData(dummyListData);
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, items);
        recyclerview.setAdapter(recyclerViewAdapter);
    }


    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recylerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(recylerViewLayoutManager);


    }

    private void initObj() {
        setSupportActionBar(toolbar);
        fm = getFragmentManager();
        dialogFragment = new MyDialogFragment();

        //Dummy data that is structured like JSONArray that contains JSONObjects
        dummyListData = "" +
                "{" +
                "\"Names\" :[ " +
                "{" +
                "\"firstname\":\"Jhonny\"," +
                "\"lastname\":\"Depp\"" +
                "}, " +
                "{" +
                "\"firstname\":\"tom\"," +
                "\"lastname\":\"cruise\"" +
                "}, " +
                "{" +
                "\"firstname\":\"Adam\"," +
                "\"lastname\":\"Sandler\"" +
                "}, " +
                "{" +
                "\"firstname\":\"dwayne\"," +
                "\"lastname\":\"johnson\"" +
                "}, " +
                "{" +
                "" +
                "\"firstname\":\"Leonardo\"," +
                "\"lastname\":\"DiCaprio\"" +
                "} " +
                "] " +
                "}";

    }

    private void initListeners() {
        fab.setOnClickListener(mGlobal_OnClickListener);
    }

    // Global Listener for all views
    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.fab: {
                    dialogFragment.show(fm, getApplicationContext().getResources().getString(R.string.dialog_tag));
                    break;
                }
            }
        }

    };


    void parseJSONData(String dummyListData) {
        try {
            JSONObject jsonRootObject = new JSONObject(dummyListData);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray(this.getResources().getString(R.string.json_array_name));
            //Iterate the jsonArray and print the info of JSONObjects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String firstName = jsonObject.optString(this.getResources().getString(R.string.json_obj_firstname)).toString();
                String lastName = jsonObject.optString(this.getResources().getString(R.string.json_obj_lastname)).toString();
                items.add(capitalizeFirstLetter(lastName) + this.getResources().getString(R.string.str_separator) + capitalizeFirstLetter(firstName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.msg_exception), Toast.LENGTH_LONG).show();
        }
    }

    public static String capitalizeFirstLetter(final String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
    }

    @Override
    public void onSaveInstanceState(Bundle outInstanceState) {
        outInstanceState.putInt("value", 1);
    }

}
