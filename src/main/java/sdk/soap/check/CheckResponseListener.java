package sdk.soap.check;

import sdk.EighteenAppMerchantService;
import sdk.soap.fault.FaultErrorCode;

/**
 * Interface that describe the various callbacks from the makeCheckRequest
 * in {@link EighteenAppMerchantService}
 *
 * @author riccardobusetti
 */
public interface CheckResponseListener {

    /**
     * Called when we get a response from the server
     *
     * @param response contains an object with all the data related
     *                 to a specific coupon
     */
    void onCheckSuccess(CheckResponse response);

    /**
     * Called when we get a response, but that contains an errorFault
     *
     * @param errorCode enum that contains the error returned by the server
     *                  with a short message description
     */
    void onCheckError(FaultErrorCode errorCode);

    /**
     * Called when we cannot make the request, because of some null objects
     *
     * @param failType enum that contains the request fail type
     */
    void onCheckRequestFailed(EighteenAppMerchantService.RequestFailType failType);

    /**
     * Called when we we receive a
     *
     * @param failType enum that contains the response fail type
     */
    void onCheckResponseFailed(EighteenAppMerchantService.ResponseFailType failType);

}
