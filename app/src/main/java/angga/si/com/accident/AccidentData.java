package angga.si.com.accident;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccidentData {
    @SerializedName("results")
    @Expose
    public List<Accident> results = null;
}
