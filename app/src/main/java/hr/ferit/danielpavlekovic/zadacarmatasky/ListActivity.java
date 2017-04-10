package hr.ferit.danielpavlekovic.zadacarmatasky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends Activity implements View.OnClickListener {
    DatabaseHelper myDb;
    ListView lvTasks;
    Button btnAddTask, btnAddCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        myDb = new DatabaseHelper(this);
        initUI();
    }

    private void initUI() {
        btnAddCategory = (Button) findViewById(R.id.btnAddCatMain);
        btnAddTask = (Button) findViewById(R.id.btnAddTaskMain);
        btnAddTask.setOnClickListener(this);
        btnAddCategory.setOnClickListener(this);

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
