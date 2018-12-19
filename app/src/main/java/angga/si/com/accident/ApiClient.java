package angga.si.com.accident;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface ApiClient {
    @GET("kecelakaan")
    Call <AccidentData> getAccident();
}
