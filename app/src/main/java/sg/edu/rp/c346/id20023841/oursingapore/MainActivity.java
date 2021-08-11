package sg.edu.rp.c346.id20023841.oursingapore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShowList, btnInsert;
    EditText etName, etDescription, etSQKM;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsert);
        btnShowList = findViewById(R.id.buttonShowList);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etSQKM = findViewById(R.id.etSQKM);
        rb = findViewById(R.id.ratingBarStars);


        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                int SQKM = Integer.parseInt(etSQKM.getText().toString().trim());
                if (name.length() == 0 || description.length() == 0) {
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }


                DBHelper dbh = new DBHelper(MainActivity.this);

                int stars = getStars();
                dbh.insertIsland(name, description, SQKM, stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etName.setText("");
                etDescription.setText("");
                etSQKM.setText("");
            }
        });

    }

    private int getStars() {
        int stars = 1;
        stars = (int)rb.getRating();
        return stars;
    }
}