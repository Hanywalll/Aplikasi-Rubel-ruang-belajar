package com.example.rubel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class editorActivity extends AppCompatActivity {
    private EditText editEmail,editKritik;
    private Button btn_Save;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private  String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        editEmail = findViewById(R.id.email);
        editKritik = findViewById(R.id.kritikSaran);
        btn_Save = findViewById(R.id.btn_simpan);

        btn_Save.setOnClickListener(v ->{
            if (editEmail.getText().length()>0 && editKritik.getText().length()>0){
                saveData(editEmail.getText().toString(),editKritik.getText().toString());
            }else {
                Toast.makeText(getApplicationContext(),"Silahkan Isi Data Dengan Lengkap",Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if(intent!=null){
            id = intent.getStringExtra("id");
            editEmail.setText(intent.getStringExtra("email"));
            editKritik.setText(intent.getStringExtra("Kritik"));
        }

    }



    private void saveData(String email,String kritik) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("Kritik", kritik);

        if (id!=null) {
            db.collection("users").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                                finish();

                            }else {
                                Toast.makeText(getApplicationContext(),"Gagal",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else {

            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }
}