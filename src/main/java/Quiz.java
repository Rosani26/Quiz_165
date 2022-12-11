public class Quiz {
    private String frage;

    // Antwortmöglichkeiten
    private Double a;
    private Double b;
    private Double c;

    // Richtige Antwort
    private String antwort;

    public Quiz(){}

    public Quiz(String frage, Double a, Double b, Double c, String antwort) {
        this.frage = frage;
        this.a = a;
        this.b = b;
        this.c = c;
        this.antwort = antwort;
    }

    public String getFrage() {
        return frage;
    }

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }

    public Double getC() {
        return c;
    }

    public String getAntwort() {
        return antwort;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "frage='" + frage + '\'' +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", antwort='" + antwort + '\'' +
                '}';
    }
}
