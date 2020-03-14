package af.asr.csc.api;

import java.util.Objects;

public class ScanEvent {

    private final String number;

    private final String scanIdentifier;

    public ScanEvent(final String number, final String scanIdentifier) {
        this.number = number;
        this.scanIdentifier = scanIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScanEvent scanEvent = (ScanEvent) o;
        return Objects.equals(number, scanEvent.number) &&
                Objects.equals(scanIdentifier, scanEvent.scanIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, scanIdentifier);
    }

    @Override
    public String toString() {
        return "ScanEvent{" +
                "number='" + number + '\'' +
                ", scanIdentifier='" + scanIdentifier + '\'' +
                '}';
    }
}
