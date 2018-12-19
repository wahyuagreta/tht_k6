package angga.si.com.accident;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements AccidentAdapter.OnItemClick{

    ProgressBar pbAccident;
    RecyclerView rvAccident;
    AccidentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbAccident = findViewById(R.id.progress_bar);
        pbAccident.setVisibility(View.INVISIBLE);


        adapter = new AccidentAdapter();
        adapter.setHandler(this);

        rvAccident = findViewById(R.id.recycler_view);
        rvAccident.setLayoutManager(new LinearLayoutManager(this));
        rvAccident.setAdapter(adapter);
        rvAccident.setVisibility(View.VISIBLE);

        getDataAccident();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_refresh){

        }
        return super.onOptionsItemSelected(item);
    }

    public void getDataAccident() {
        pbAccident.setVisibility(View.VISIBLE);
        rvAccident.setVisibility(View.INVISIBLE);

        ApiClient client = (new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build())
                .create(ApiClient.class);

        Call<AccidentData> call = client.getAccident();

        call.enqueue(new Callback<AccidentData>() {
            @Override
            public void onResponse(Call<AccidentData> call, Response<AccidentData> response) {
                AccidentData accidentData = response.body();
                List<Accident> results = accidentData.results;
                adapter.setDataKecelakaan(new ArrayList<Accident>(results));

                pbAccident.setVisibility(View.INVISIBLE);
                rvAccident.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<AccidentData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                pbAccident.setVisibility(View.INVISIBLE);
                rvAccident.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void click(Accident a) {
        Intent detailActivityIntent = new Intent(this, DetailActivity.class);
        detailActivityIntent.putExtra("accident_extra_key", a);
        startActivity(detailActivityIntent);
    }
}
