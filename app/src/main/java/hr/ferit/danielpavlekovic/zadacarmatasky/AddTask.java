package hr.ferit.danielpavlekovic.zadacarmatasky;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AddTask extends Activity implements View.OnClickListener {

    private Spinner spnCategory, spnPriority;
    private Button btnAdd;
    private String[] Categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getCategoriesFromDB();
        initUI();
    }

    private void getCategoriesFromDB() {
        Cursor res = DatabaseCategory.getInstance(getApplicationContext()).getAllData();
        int i=0;
        while(res.moveToNext()){
            Categories[i]=(res.getString(0));
        }
    }

    private void initUI() {
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

    }
}
