public class AutomaticTest {

    private static String [] codVoucher  = {"11aa22bb","PqCSZjFp","Ex7jYMDg","lTTeLX5v"};
    private static String keystorePath = "AAAAAA00H01H501P.p12";
    private static String password = "m3D0T4aM" ;

    public static boolean isDebugMode() {
        return DEBUG_MODE;
    }

    public static void setDebugMode(boolean debugMode) {
        DEBUG_MODE = debugMode;
    }

    private static boolean DEBUG_MODE = true;

    public AutomaticTest(String [] code ,String key, String pass ) {
        codVoucher = code;
        keystorePath = key;
        password = pass;
    }

    public static void main(String[] args) {

        if (DEBUG_MODE){
            // Accept self-signed certificate of the testing server
            // You need to put the server self-signed certificate into the file cacerts
            System.setProperty("javax.net.ssl.trustStore", "cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        }

        TestCheck testCheck = new TestCheck(codVoucher[0] , keystorePath ,password);
        TestConfirm testConfirm = new TestConfirm(codVoucher[1] , keystorePath ,password);
        TestConsume testConsume = new TestConsume(codVoucher[2] , keystorePath ,password);
        TestFreezeAndConfirm testFreezeAndConfirm = new TestFreezeAndConfirm(codVoucher[3] , keystorePath ,password);

        testCheck.testMe();
        testConfirm.testMe();
        testConsume.testMe();
        testFreezeAndConfirm.testMe();

        System.out.println("Test finished");
    }
}
