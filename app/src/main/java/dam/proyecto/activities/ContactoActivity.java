package dam.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import dam.proyecto.R;
import dam.proyecto.databinding.ActivityContactoBinding;

public class ContactoActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityContactoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityContactoBinding.inflate( getLayoutInflater() );
        View view = binding.getRoot();
        setContentView( view );

        binding.contactoSalir.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.contacto_salir:
                startActivity(new Intent(this, MainActivity.class));
                break;

            default:
        }


    }
}