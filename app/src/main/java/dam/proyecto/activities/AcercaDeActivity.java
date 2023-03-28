package dam.proyecto.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dam.proyecto.R;
import dam.proyecto.databinding.ActivityAcercaDeBinding;

public class AcercaDeActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityAcercaDeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // "Inflar" la vista
        binding = ActivityAcercaDeBinding.inflate(getLayoutInflater());     // Obtener el inflater
        View view = binding.getRoot();                       // view es el elemento raíz de la vista
        setContentView(view);                              // Establecemos la vista como contenido

        // Oyentes para los componentes
        binding.aboutAutor.setOnClickListener(this);
        binding.aboutMail.setOnClickListener(this);
        binding.aboutGithub.setOnClickListener(this);
        binding.aboutSalir.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.about_salir:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.about_mail:
                startActivity( new Intent( this, ContactoActivity.class));
                break;
            case R.id.about_github:
                startActivity(
                        new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/robertorodriguezNet/Proyecto")
                        )
                );                break;
            case R.id.about_autor:
                startActivity(
                        new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://robertorodriguez.net")
                        )
                );
                break;
            default:
        }

    }
}