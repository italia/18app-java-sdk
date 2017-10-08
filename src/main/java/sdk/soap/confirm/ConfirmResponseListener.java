package sdk.soap.confirm;

import sdk.EighteenAppMerchantService;
import sdk.soap.fault.FaultErrorCode;

/**
 * Interface that describe the various callbacks from the makeConfirmRequest
 * in {@link EighteenAppMerchantService}
 *
 * @author riccardobusetti
 */
public interface ConfirmResponseListener {

    /**
     * Called when we get a response from the server
     */
    void onConfirmSuccess(boolean isOperationResultOk);

    /**
     * Called when we get a response, but that contains an errorFault
     *
     * @param errorCode enum that contains the error returned by the server
     *                  with a short message description
     */
    void onConfirmError(FaultErrorCode errorCode);

    /**
     * Called when we cannot make the request, because of some null objects
     *
     * @param failType enum that contains the request fail type
     */
    void onConfirmRequestFailed(EighteenAppMerchantService.RequestFailType failType);

    /**
     * Called when we we receive a
     *
     * @param failType enum that contains the response fail type
     */
    void onConfirmResponseFailed(EighteenAppMerchantService.ResponseFailType failType);

}
