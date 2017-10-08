package sdk.soap;

import sdk.soap.check.CheckRequestObj;
import sdk.soap.check.CheckResponseListener;
import sdk.soap.confirm.ConfirmRequestObj;
import sdk.soap.confirm.ConfirmResponseListener;

/**
 * Interface that describes the standard behavior of the server calls
 *
 * @author riccardobusetti
 */
public interface SOAPServerRequest {

    void makeCheckRequest(CheckRequestObj requestObj, CheckResponseListener checkResponseListener);

    void makeConfirmRequest(ConfirmRequestObj confirmObj, ConfirmResponseListener confirmResponseListener);

}
