package teclag.c20130026.sensoresapp_u5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnConsultarSensoresClick ( View v ) {
        Intent intent = new Intent ( this, ConsultarSensoresActivity.class );
        startActivity ( intent );
    }

    public void btnMonitorearSensoresClick ( View v ) {
        Intent intent = new Intent ( this, MonitorearSensoresActivity.class );
        startActivity ( intent );
    }

    public void btnAcelerometroClick ( View v ) {
        Intent intent = new Intent ( this, AcelerometroActivity.class );
        startActivity ( intent );
    }
}