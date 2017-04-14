package hr.ferit.danielpavlekovic.zadacarmatasky;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListActivity extends Activity implements View.OnClickListener {
    DatabaseHelper myDb;
    ListView lvTasks;
    TaskAdapter mTaskAdapter;
    Button btnAddTask, btnAddCategory;

    private SimpleDateFormat DueFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ArrayList<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DatabaseHelper.getInstance(this);
        initUI();
    }

    private ArrayList<Task> loadTasks() {
        Cursor res = DatabaseHelper.getInstance(getApplicationContext()).getAllData();
        while(res.moveToNext()){
                tasks.add(new Task(res.getString(0),res.getString(1),res.getString(2),res.getString(3),res.getString(4)));

        }
        return tasks;
    }

    private void initUI() {
        this.lvTasks = (ListView) this.findViewById(R.id.lvTasks);
        this.mTaskAdapter = new TaskAdapter(this.loadTasks(),this);
        this.lvTasks.setAdapter(this.mTaskAdapter);
        btnAddCategory = (Button) findViewById(R.id.btnAddCatMain);
        btnAddTask = (Button) findViewById(R.id.btnAddTaskMain);
        btnAddTask.setOnClickListener(this);
        btnAddCategory.setOnClickListener(this);
        this.lvTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String itemCreated = tasks.get(position).getDateTimeCreated();

                if(DatabaseHelper.getInstance(getApplicationContext()).deleteData(String.valueOf(itemCreated)))
                    Toast.makeText(ListActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ListActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();

                mTaskAdapter.deleteAt(position);
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent TaskIntent = new Intent(getApplicationContext(), AddTask.class);
        Intent CatIntent = new Intent(getApplicationContext(), AddCategory.class);
        switch(v.getId()){
            case(R.id.btnAddTaskMain):
                startActivity(TaskIntent);
                break;
            case(R.id.btnAddCatMain):
                startActivity(CatIntent);
                break;
        }
    }
}
