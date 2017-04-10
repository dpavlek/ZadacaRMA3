package hr.ferit.danielpavlekovic.zadacarmatasky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCategory extends Activity implements View.OnClickListener {

    EditText etCategoryName;
    Button btnAddCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        DatabaseCategory.getInstance(this);
        initUI();
    }

    private void initUI() {
        etCategoryName = (EditText) findViewById(R.id.etCategoryName);
        btnAddCategory = (Button) findViewById(R.id.btnAddCategory);
        btnAddCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent ToMainIntent = new Intent(getApplicationContext(), ListActivity.class);
            boolean Inserted = DatabaseCategory.getInstance(getApplicationContext()).insertData(etCategoryName.getText().toString());
            if(Inserted==true) {
                startActivity(ToMainIntent);
                Toast.makeText(AddCategory.this,"Added",Toast.LENGTH_SHORT).show();
            }

            else{
                startActivity(ToMainIntent);
                Toast.makeText(AddCategory.this,"Added....NOT!",Toast.LENGTH_SHORT).show();
            }

        }
}
