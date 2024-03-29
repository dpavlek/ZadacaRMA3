package hr.ferit.danielpavlekovic.zadacarmatasky;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTask extends Activity implements View.OnClickListener {

    private static final String TAG = "PARSE_ERROR";
    private static final String TAG_DB = "DBCATEGORIES";
    private Spinner spnCategory, spnPriority;
    private Button btnAdd;
    private String[] Categories;
    private Task task = new Task();
    private EditText etTaskName, etDueDate;
    private SimpleDateFormat DueFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        DatabaseCategory.getInstance(this);
        DatabaseHelper.getInstance(this);
        getCategoriesFromDB();
        initUI();
    }

    private void getCategoriesFromDB() {
        Cursor res = DatabaseCategory.getInstance(getApplicationContext()).getAllData();
        Categories = new String[res.getCount()];
        int i=0;
        while(res.moveToNext()){
            Categories[i]=(res.getString(0));
            i++;
        }
    }


    private void initUI() {
        etTaskName = (EditText) findViewById(R.id.etTaskName);
        etDueDate = (EditText) findViewById(R.id.etTaskDueDate);
        spnCategory = (Spinner) findViewById(R.id.spnCategory);
        spnPriority = (Spinner) findViewById(R.id.spnPriority);
        btnAdd = (Button) findViewById(R.id.btnAddTask);
        ArrayAdapter<CharSequence> adapterPriority = ArrayAdapter.createFromResource(this,R.array.Priority, android.R.layout.simple_spinner_item);
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPriority.setAdapter(adapterPriority);
        spnPriority.setSelection(0);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Categories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(adapterCategory);
        spnCategory.setSelection(0);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent ReturnToMain = new Intent(getApplicationContext(), ListActivity.class);
        if(setupTask()) {

            boolean Inserted = DatabaseHelper.getInstance(getApplicationContext()).insertData(task.getDateTimeCreated(), task.getName(), task.getDueDate(), task.getCategory(), task.getPriority());
            if (Inserted == true) {
                Toast.makeText(AddTask.this, "Added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddTask.this, "Added....NOT!", Toast.LENGTH_SHORT).show();
            }
            startActivity(ReturnToMain);
        }
    }

    private boolean setupTask() {

            String checkDate, checkName,checkCategory;

            checkDate = etDueDate.getText().toString();
            checkName = etTaskName.getText().toString();
            checkCategory = spnCategory.getSelectedItem().toString();

            if(checkDate.matches("") || checkName.matches("") || checkCategory.matches("")){
                Toast.makeText(this, "All data not entered!", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                task.setDueDate(etDueDate.getText().toString());
                task.setName(etTaskName.getText().toString());
                task.setPriority(spnPriority.getSelectedItem().toString());
                task.setCategory(spnCategory.getSelectedItem().toString());
                return true;
            }
    }
}
