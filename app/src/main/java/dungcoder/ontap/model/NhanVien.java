package dungcoder.ontap.model;

/**
 * Created by DungHigh on 3/6/2018.
 */

public class NhanVien {
    private String mID;
    private boolean misMale;
    private String mName;

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public boolean isMisMale() {
        return misMale;
    }

    public void setMisMale(boolean misMale) {
        this.misMale = misMale;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public NhanVien() {

    }

    public NhanVien(String mID, boolean misMale, String mName) {

        this.mID = mID;
        this.misMale = misMale;
        this.mName = mName;
    }
}
