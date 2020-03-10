package af.asr.youtap.type;

/**
 * Links or unlinks a bank account from
 * mobile money account
 * ‘L’ – link account
 * ‘U’ – Unlink account
 */
public enum LinkType {

    LINKED("L"),
    UNLINKED("U");

    public String text;

    LinkType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
