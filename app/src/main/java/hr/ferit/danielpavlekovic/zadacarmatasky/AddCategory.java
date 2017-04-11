package hr.ferit.danielpavlekovic.zadacarmatasky;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddCategory extends Activity implements View.OnClickListener {

    EditText etCategoryName;
    Button btnAddCategory,btnDeleteCategory;
    Spinner spnCategories;
    String[] Categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        DatabaseCategory.getInstance(this);
        DatabaseHelper.getInstance(this);
        getCategoriesFromDB();
        initUI();
    }

    private void initUI() {
        etCategoryName = (EditText) findViewById(R.id.etCategoryName);
        btnAddCategory = (Button) findViewById(R.id.btnAddCategory);
        btnDeleteCategory = (Button) findViewById(R.id.btnDeleteCategory);
        spnCategories = (Spinner) findViewById(R.id.spnDelCat);
        btnAddCategory.setOnClickListener(this);
        btnDeleteCategory.setOnClickListener(this);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Categories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategories.setAdapter(adapterCategory);

        if(Categories.length==0)
            btnDeleteCategory.setEnabled(false);
        else
            btnDeleteCategory.setEnabled(true);
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

    @Override
    public void onClick(View v) {
        Intent ToMainIntent = new Intent(getApplicationContext(), ListActivity.class);
        switch(v.getId()){

            case(R.id.btnAddCategory):
                boolean Inserted = DatabaseCategory.getInstance(getApplicationContext()).insertData(etCategoryName.getText().toString());
                if(Inserted==true) {
                    Toast.makeText(AddCategory.this,"Added",Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(AddCategory.this,"Added....NOT!",Toast.LENGTH_SHORT).show();
                }
                startActivity(ToMainIntent);
                break;
            case(R.id.btnDeleteCategory):
                Integer deletedRows = DatabaseCategory.getInstance(getApplicationContext()).deleteData(spnCategories.getSelectedItem().toString());
                if(deletedRows>0)
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Deleted...NOT!", Toast.LENGTH_SHORT).show();
                startActivity(ToMainIntent);
        }


        }
}
