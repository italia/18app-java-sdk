package sdk.model;

/**
 * Object that represents the SSL certificate that must be used to authenticate
 * the merchant.
 *
 * The keystorePath is the path where the .p12 file is located and the password is
 * the password of that .p12 file.
 *
 * This object can be used with the
 * @see <a href="https://www.tutorialspoint.com/design_pattern/builder_pattern.htm">builder pattern</a>,
 * or with the start object initialization.
 *
 * @author riccardobusetti
 */
public class Certificate {

    private String keystorePath;
    private String password;

    Certificate(Builder builder) {
        this.keystorePath = builder.keystorePath;
        this.password = builder.password;
    }

    public Certificate() {
        this.keystorePath = null;
        this.password = null;
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Builder class that is needed to apply the builder pattern to {@link Certificate}
     *
     * @author riccardobusetti
     */
    public static class Builder {

        private String keystorePath;
        private String password;

        public Builder() {
            this.keystorePath = null;
            this.password = null;
        }

        public Builder setKeystorePath(String keystorePath) {
            this.keystorePath = keystorePath;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Certificate build() {
            return new Certificate(this);
        }
    }

}
