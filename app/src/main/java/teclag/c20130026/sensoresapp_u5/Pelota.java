package teclag.c20130026.sensoresapp_u5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Pelota extends View implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;

    private int x;
    private int y;
    private int ancho;
    private int alto;
    private Paint paint;
    private Bitmap marioBitmap;

    public Pelota(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // Obtener las dimensiones de la pantalla del dispositivo
        Display pantalla = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point dimensiones = new Point();
        pantalla.getRealSize(dimensiones);
        ancho = dimensiones.x;
        alto = dimensiones.y;
        x = alto / 2;
        y = alto / 2;

        // Cargar la imagen de Mario desde los recursos
        marioBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fantasma); // Asegúrate de que tengas la imagen mario.png en res/drawable

        // Establecer el color con el que se pinta la pelota (ya no es necesario si usamos una imagen)
        paint = new Paint();

        // Obtenemos la instancia del acelerómetro
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void iniciar() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    public void detener() {
        sensorManager.unregisterListener(this, sensor);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Calculemos la nueva posición x, y de la imagen en base a las lecturas del acelerómetro
        // en los ejes X, Y.
        x -= sensorEvent.values[0] * 2;
        y += sensorEvent.values[1] * 2;

        // Verificar si no se sobrepasa los límites de la pantalla
        if (x < marioBitmap.getWidth() / 2) {
            x = marioBitmap.getWidth() / 2;
        } else if (x > ancho - marioBitmap.getWidth() / 2) {
            x = ancho - marioBitmap.getWidth() / 2;
        }

        if (y < marioBitmap.getHeight() / 2) {
            y = marioBitmap.getHeight() / 2;
        } else if (y > alto - marioBitmap.getHeight() / 2) {
            y = alto - marioBitmap.getHeight() / 2;
        }

        // Invocar invalidate() de este View para invocar al método draw()
        invalidate();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        // Dibujar la imagen de Mario en las coords x, y
        canvas.drawBitmap(marioBitmap, x - marioBitmap.getWidth() / 2, y - marioBitmap.getHeight() / 2, null);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No implementado
    }
}
