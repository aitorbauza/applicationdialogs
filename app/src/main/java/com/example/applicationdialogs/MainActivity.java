package com.example.applicationdialogs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mDialogButton1, mDialogButton2, mDialogButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazamos el xml
        mTextView = findViewById(R.id.textView);
        mDialogButton1 = findViewById(R.id.dialogButton1);
        mDialogButton2 = findViewById(R.id.dialogButton2);
        mDialogButton3 = findViewById(R.id.dialogButton3);

        // Texto que se va a cambiar
        mTextView.setText("Hello World!");

        // Configuración botón DIALEG 1
        mDialogButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1();
            }
        });

        // Configuración botón DIALEG 2
        mDialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog2();
            }
        });

        // Configuración botón DIALEG 3
        mDialogButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog3();
            }
        });
    }


    //AlertDialog para DIALEG 1
    private void alertDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hello"); //Título de la caja
        builder.setMessage("Missatge Alerta"); //Mensaje de la caja
        builder.setCancelable(true);

        // botón OK
        builder.setPositiveButton("OK", (dialog, which) -> {
            mTextView.setText("Has pitjat Ok"); // Texto que se muestra al pulsar OK
        });

        // botón Cancel
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            mTextView.setText("Has pitjat Cancel"); // Texto que se muestra al pulsar Cancel
        });

        // Muestra el messagebox
        builder.create().show();
    }


    //AlertDialog para DIALEG 2
    private void alertDialog2() {
        // Creamos el RadioGroup dentro del AlertDialog
        final RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        // Creamos los RadioButton
        RadioButton radioFCBarcelona = new RadioButton(this);
        radioFCBarcelona.setText("FC Barcelona");
        RadioButton radioRealMadrid = new RadioButton(this);
        radioRealMadrid.setText("Real Madrid");
        RadioButton radioRCMallorca = new RadioButton(this);
        radioRCMallorca.setText("RCD Mallorca");

        // Añadimos los RadioButton al RadioGroup
        radioGroup.addView(radioFCBarcelona);
        radioGroup.addView(radioRealMadrid);
        radioGroup.addView(radioRCMallorca);

        // Creamos el AlertDialog con el RadioGroup
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Team") //Título
                .setView(radioGroup)
                .setCancelable(true)
                .setPositiveButton("OK", (dialog, which) -> {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    String selectedTeam = ""; //Si das OK sin pulsar en ningún radiobutton, no aparece texto

                    // Se verifica cuál RadioButton se ha elegido
                    if (selectedId == radioFCBarcelona.getId()) {
                        selectedTeam = "Has escollit el FC Barcelona";
                    } else if (selectedId == radioRealMadrid.getId()) {
                        selectedTeam = "Has escollit l'equip que roba";
                    } else if (selectedId == radioRCMallorca.getId()) {
                        selectedTeam = "Has escollit el RCD Mallorca";
                    }

                    // Muestra el resultado en el textview
                    mTextView.setText(selectedTeam);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    mTextView.setText("Has pitjat Cancel"); // Si pulsas cancel aparece este botón
                });

        builder.create().show();
    }


    //AlertDialog para DIALEG 3
    private void alertDialog3() {
        // Creamos los campos del formulario (NO HAY VALIDACIÓN)
        final EditText emailEditText = new EditText(this);
        emailEditText.setHint("Correo Electrónico"); // Campo de correo

        final EditText passwordEditText = new EditText(this);
        passwordEditText.setHint("Contraseña"); // Campo de contraseña
        passwordEditText.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance()); // Muestra los asteriscos

        // Creamos el layout del formulario
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 20, 40, 20);
        layout.addView(emailEditText);
        layout.addView(passwordEditText);

        // Creamos el AlertDialog con el formulario
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Iniciar Sesión") // Título
                .setView(layout)
                .setCancelable(true)
                .setPositiveButton("OK", (dialog, which) -> {
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    // Se verifica si los campos están llenos
                    if (!email.isEmpty() && !password.isEmpty()) { // Si no hay campos vacíos
                        mTextView.setText("Inici de Sesio correcte"); // Aparece este mensaje
                    } else {
                        mTextView.setText("Inici de Sesió Incorrecte"); // Y sino este mensaje
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    mTextView.setText("Inici de Sesió Incorrecte"); // Si le das a cancel aparece este botón
                });

        builder.create().show();
    }
}
