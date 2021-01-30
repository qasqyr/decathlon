import java.util.ArrayList;
import java.util.List;

public class Competitor implements Comparable<Competitor> {

    private String name;
    private float result100m;
    private float resultLongJump;
    private float resultShotPut;
    private float resultHighJump;
    private float result400m;
    private float result110H;
    private float resultDiscus;
    private float resultPoleVault;
    private float resultJavelin;
    private float result1500mInS;
    private String place = "UNDEFINED";

    public Competitor(String[] resultString) {
        this.name = resultString[0];
        this.result100m = Float.parseFloat(resultString[1]);
        this.resultLongJump = Float.parseFloat(resultString[2]);
        this.resultShotPut = Float.parseFloat(resultString[3]);
        this.resultHighJump = Float.parseFloat(resultString[4]);
        this.result400m = Float.parseFloat(resultString[5]);
        this.result110H = Float.parseFloat(resultString[6]);
        this.resultDiscus = Float.parseFloat(resultString[7]);
        this.resultPoleVault = Float.parseFloat(resultString[8]);
        this.resultJavelin = Float.parseFloat(resultString[9]);
        this.result1500mInS = Float.parseFloat(
                String.valueOf(Integer.parseInt(resultString[10].split("[.]")[0]) * 60
                + Integer.parseInt(resultString[10].split("[.]")[1])
                + Double.parseDouble(resultString[10].split("[.]")[2]) / 100)
        );
    }

    public Competitor(String name,
                      float result100m,
                      float resultLongJump,
                      float resultShotPut,
                      float resultHighJump,
                      float result400m,
                      float result110H,
                      float resultDiscus,
                      float resultPoleVault,
                      float resultJavelin,
                      float result1500mInS) {
        this.name = name;
        this.result100m = result100m;
        this.resultLongJump = resultLongJump;
        this.resultShotPut = resultShotPut;
        this.resultHighJump = resultHighJump;
        this.result400m = result400m;
        this.result110H = result110H;
        this.resultDiscus = resultDiscus;
        this.resultPoleVault = resultPoleVault;
        this.resultJavelin = resultJavelin;
        this.result1500mInS = result1500mInS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getResult100m() {
        return result100m;
    }

    public void setResult100m(float result100m) {
        this.result100m = result100m;
    }

    public float getResultLongJump() {
        return resultLongJump;
    }

    public void setResultLongJump(float resultLongJump) {
        this.resultLongJump = resultLongJump;
    }

    public float getResultShotPut() {
        return resultShotPut;
    }

    public void setResultShotPut(float resultShotPut) {
        this.resultShotPut = resultShotPut;
    }

    public float getResultHighJump() {
        return resultHighJump;
    }

    public void setResultHighJump(float resultHighJump) {
        this.resultHighJump = resultHighJump;
    }

    public float getResult400m() {
        return result400m;
    }

    public void setResult400m(float result400m) {
        this.result400m = result400m;
    }

    public float getResult110H() {
        return result110H;
    }

    public void setResult110H(float result110H) {
        this.result110H = result110H;
    }

    public float getResultDiscus() {
        return resultDiscus;
    }

    public void setResultDiscus(float resultDiscus) {
        this.resultDiscus = resultDiscus;
    }

    public float getResultPoleVault() {
        return resultPoleVault;
    }

    public void setResultPoleVault(float resultPoleVault) {
        this.resultPoleVault = resultPoleVault;
    }

    public float getResultJavelin() {
        return resultJavelin;
    }

    public void setResultJavelin(float resultJavelin) {
        this.resultJavelin = resultJavelin;
    }

    public float getResult1500mInS() {
        return result1500mInS;
    }

    public void setResult1500mInS(float result1500mInS) {
        this.result1500mInS = result1500mInS;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Competitor{" +
                "name='" + name + '\'' +
                ", result100m=" + result100m +
                ", resultLongJump=" + resultLongJump +
                ", resultShotPut=" + resultShotPut +
                ", resultHighJump=" + resultHighJump +
                ", result400m=" + result400m +
                ", result110H=" + result110H +
                ", resultDiscus=" + resultDiscus +
                ", resultPoleVault=" + resultPoleVault +
                ", resultJavelin=" + resultJavelin +
                ", result1500mInS=" + result1500mInS +
                ", totalPoints=" + getTotalPoint() +
                ", place='" + place + '\'' +
                '}';
    }

    public float getResultByIndex(int i) {
        List<Float> resultL = new ArrayList<>();
        resultL.add(this.result100m);
        resultL.add(this.resultLongJump);
        resultL.add(this.resultShotPut);
        resultL.add(this.resultHighJump);
        resultL.add(this.result400m);
        resultL.add(this.result110H);
        resultL.add(this.resultDiscus);
        resultL.add(this.resultPoleVault);
        resultL.add(this.resultJavelin);
        resultL.add(this.result1500mInS);

        return resultL.get(i);
    }

    @Override
    public int compareTo(Competitor competitor) {
        return Integer.compare(this.getTotalPoint(), competitor.getTotalPoint());
    }

    public int getTotalPoint() {
        return CompetitionUtils.getTotalPoints(this);
    }
}