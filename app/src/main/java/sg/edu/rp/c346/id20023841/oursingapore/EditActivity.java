package sg.edu.rp.c346.id20023841.oursingapore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText etMSQKM, etIname, etIdescription, etSongID;
    Button btnUpdate, btnDelete, btnCancel;
    RatingBar rbStars;
    Island island;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etSongID = findViewById(R.id.etIslandID);
        etIdescription = findViewById(R.id.etIslandDesciption);
        etIname = findViewById(R.id.etIslandName);
        etMSQKM = findViewById(R.id.etModifiedSQKM);
        btnCancel = findViewById(R.id.buttonCancel);
        btnDelete = findViewById(R.id.buttonDelete);
        btnUpdate = findViewById(R.id.buttonUpdate);
        rbStars = findViewById(R.id.ratingBarStars3);


        Intent i = getIntent();
        island = (Island) i.getSerializableExtra("song");

        etSongID.setText(island.get_id() + "");
        etIname.setText(island.getName());
        etIdescription.setText(island.getDescription());
        etMSQKM.setText(island.getSqkm() + "");
        rbStars.setRating(island.getStars());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the island" + island.getName());
                myBuilder.setCancelable(false);

                //Configure the 'positive' button
                myBuilder.setPositiveButton("Cancel", null);

                //Configure the 'negative' button
                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    DBHelper dbh = new DBHelper(EditActivity.this);
                    int result = dbh.deleteIsland(island.get_id());
                        finish();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                island.setName(etIname.getText().toString().trim());
                island.setDescription(etIdescription.getText().toString().trim());
                int SQKM = 0;
                try {
                    SQKM = Integer.valueOf(etMSQKM.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(EditActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                island.setSqkm(SQKM);

                island.setStars((int) rbStars.getRating());
                int result = dbh.updateIsland(island);
                if (result>0){
                    Toast.makeText(EditActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);

                //Configure the 'positive' button
                myBuilder.setPositiveButton("Discard", null);

                //Configure the 'negative' button
                myBuilder.setNegativeButton("Do Not Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}