public class Statistik {
    private String userName;
    private long punkte;
    private long millis;

    public Statistik(String userName, long punkte, long millis) {
        this.userName = userName;
        this.punkte = punkte;
        this.millis = millis;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getPunkte() {
        return punkte;
    }

    public void setPunkte(long punkte) {
        this.punkte = punkte;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }
}
