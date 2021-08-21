package Models;

public class Shoots {
    int attempts;
    int success;

    public Shoots(int success, int attempts) {
        this.success = success;
        this.attempts = attempts;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return " (" + success + "/" + attempts + ")";
    }
}
