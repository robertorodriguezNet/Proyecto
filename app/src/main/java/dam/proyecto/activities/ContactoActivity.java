package dam.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import dam.proyecto.R;
import dam.proyecto.databinding.ActivityContactoBinding;

public class ContactoActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityContactoBinding binding;

    boolean errorFormualrio;                // Controla si se ha producido un error en el formulario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityContactoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.contactoSalir.setOnClickListener(this);
        binding.contactoEnviar.setOnClickListener(this);

    }

    /**
     * Método para enviar el correo electrónico.
     */
    private void sendEmail() {

        errorFormualrio = false;

        // Obtener los datos
        HashMap<String, String> datos = new HashMap<>();
        datos.put("nombre", binding.contactoNombre.getText().toString());
        datos.put("asunto", binding.contactoAsunto.getText().toString());
        datos.put("remite", binding.contactoRemite.getText().toString());
        datos.put("mensaje", binding.contactoMensaje.getText().toString());

        datos.forEach((k, v) -> {
            if (v.isEmpty()) {
                Toast.makeText(
                        this,
                        "El campo \"" + v + "\" no puede estar vacío",
                        Toast.LENGTH_SHORT).show();
                errorFormualrio = true;
            }
        });

        // No hay error en el formualrio
        if (!errorFormualrio) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(
                    Intent.EXTRA_EMAIL,
                    new String[]{"roberto.rodjim.1@educa.jcyl.es"}
            );
            intent.putExtra(Intent.EXTRA_SUBJECT,datos.get("asunto"));
            intent.putExtra(
                    Intent.EXTRA_TEXT,
                    datos.get("nombre")
                            + " ("
                            + datos.get("remite")
                            + ")\n"
                            + datos.get("mensaje")
            );
            intent.setType("message/rfc822");
            startActivity( intent );

            Toast.makeText(this, "Correo enviado", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, AcercaDeActivity.class));

        } else {
            Toast.makeText(this, "No se pudo enviar el correo", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.contacto_salir:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.contacto_enviar:
                sendEmail();
                break;

            default:
        }

    }
}