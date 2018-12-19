package angga.si.com.accident;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AccidentAdapter extends RecyclerView.Adapter<AccidentAdapter.AccidentHolder> {

    ArrayList<Accident> dataKecelakaan;
    OnItemClick handler;

    public void setDataKecelakaan(ArrayList<Accident> Balantak){
        this.dataKecelakaan = Balantak;
        notifyDataSetChanged();
    }

    public void setHandler(OnItemClick clickHandler){
        handler = clickHandler;
    }

    @NonNull
    @Override
    public AccidentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View a = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_lapor,viewGroup,false);
        AccidentHolder holder = new AccidentHolder(a);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccidentHolder accidentHolder, int i) {
        Accident accident = dataKecelakaan.get(i);
        accidentHolder.textLokasi.setText(accident.lokasi);
        accidentHolder.textWaktu.setText(accident.waktu);

        String foto_url = "http://127.0.0.1:8000/image/" + accident.foto;

        Glide.with(accidentHolder.itemView)
                .load(foto_url)
                .into(accidentHolder.imgAccident);
    }

    @Override
    public int getItemCount() {
        if(dataKecelakaan != null){
            return dataKecelakaan.size();
        }
        return 0;
    }

    public class AccidentHolder extends RecyclerView.ViewHolder {

        ImageView imgAccident;
        TextView textLokasi,textWaktu;

        public AccidentHolder(View itemView) {
            super(itemView);
            imgAccident = itemView.findViewById(R.id.img_accident);
            textLokasi = itemView.findViewById(R.id.text_lokasi);
            textWaktu = itemView.findViewById(R.id.text_waktu);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Accident a = dataKecelakaan.get(position);
                    handler.click(a);
                }
            });
        }
    }

    public interface OnItemClick{
        void click(Accident a);
    }
}
