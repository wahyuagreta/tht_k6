package angga.si.com.accident;

import android.os.Parcel;
import android.os.Parcelable;

public class Accident implements Parcelable{

    public int id_lapor;
    public String lokasi;
    public String tipe;
    public String waktu;
    public String keterangan;
    public String foto;

    public Accident(int id_lapor, String lokasi, String tipe, String waktu, String keterangan, String foto) {
        this.id_lapor = id_lapor;
        this.lokasi = lokasi;
        this.tipe = tipe;
        this.waktu = waktu;
        this.keterangan = keterangan;
        this.foto = foto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.id_lapor);
        dest.writeString(this.lokasi);
        dest.writeString(this.tipe);
        dest.writeString(this.waktu);
        dest.writeString(this.keterangan);
        dest.writeString(this.foto);
    }

    protected Accident(Parcel in){
        this.id_lapor = in.readInt();
        this.lokasi = in.readString();
        this.tipe = in.readString();
        this.waktu = in.readString();
        this.keterangan = in.readString();
        this.foto = in.readString();
    }

    public static final Parcelable.Creator<Accident> CREATOR = new Parcelable.Creator<Accident>() {
        @Override
        public Accident createFromParcel(Parcel source) {
            return new Accident(source);
        }

        @Override
        public Accident[] newArray(int size) {
            return new Accident[size];
        }
    };
}
