package angga.si.com.accident;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    ImageView imgAccident;
    TextView textLokasi, textWaktu, textTipe, textKeterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgAccident = findViewById(R.id.imgAccident);
        textLokasi = findViewById(R.id.lokasi);
        textWaktu = findViewById(R.id.waktu);
        textTipe = findViewById(R.id.tipe);
        textKeterangan = findViewById(R.id.keterangan);

        Intent intent = getIntent();
        if(intent != null){
            Accident accident = intent.getParcelableExtra("accident_extra_key");
            textLokasi.setText(accident.lokasi);

        }

    }

}
