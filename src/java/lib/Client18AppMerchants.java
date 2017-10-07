package lib;

import it.mibact.bonus.verificavoucher.*;

/**
 * Library Interface
 */
public class Client18AppMerchants {

    private static final String ONLY_CHECK= "1";
    private static final String CHECK_AND_CHARGE = "2";

    public void init() {
    }

    public boolean register(){
        // TODO
        return true;
    }

    public CheckResponse checkAndCharge(){

        Check check = new Check();
        check.setTipoOperazione(CHECK_AND_CHARGE);
        return null;

    }

    public CheckResponse checkOnly(){

        Check check = new Check();
        check.setTipoOperazione(ONLY_CHECK);
        return null;

    }


}
