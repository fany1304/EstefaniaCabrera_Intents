package net.example.intents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.health.PackageHealthStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pagina = (Button) findViewById(R.id.abrir);
        Button llamada = (Button) findViewById(R.id.llamada);
        Button google = (Button) findViewById(R.id.google);
        Button foto = (Button) findViewById(R.id.foto);
        Button correo = (Button) findViewById(R.id.correo);

        pagina.setOnClickListener(this);
        llamada.setOnClickListener(this);
        google.setOnClickListener(this);
        foto.setOnClickListener(this);
        correo.setOnClickListener(this);
    }



    //como acceder a la web
    public void pgWeb(View view){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://campus.somtic.net/"));
        startActivity(intent);
    }

    //para llamar por teléfono
    public void llamadaTelefono(View view){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(this.checkSelfPermission(Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED){

                Intent intent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:966870700"));
                startActivity(intent);
            }
        }
        else{

            Intent intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:966870700"));
            startActivity(intent);
        }
    }

    //para meterse a google maps
    public void googleMaps(View view){

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:38.553468, -0.121579"));
        startActivity(intent);
    }

    //para meterse a la camara
    public void hacerFoto(View view){

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

    //para mandar correos
    public void mandarCorreo(View view){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"smira@iesperemaria.com"});

        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.abrir:
                pgWeb(view);
                break;

            case R.id.llamada:
                llamadaTelefono(view);
                break;

            case R.id.google:
                googleMaps(view);
                break;

            case R.id.foto:
                hacerFoto(view);
                break;

            case R.id.correo:
                mandarCorreo(view);
                break;
        }
    }
}
