package sdk.soap.fault;

import sdk.EighteenAppMerchantService;

/**
 * Enum representing the errorFaults that the server can send. This faults
 * contain an ID and a message description that is added after inside {@link EighteenAppMerchantService}
 *
 * @author riccardobusetti
 */
public enum FaultErrorCode {

    INPUT_PARAMETERS_ERROR("01", "Error in the input parameters, check and try again."),
    VOUCHER_NOT_AVAILABLE("02", "The requested voucher is not available on the system. It could be already collected or canceled."),
    USER_ACTIVATION_PROBLEM("03", "Impossible to activate the user. Please verify input parameters and that the user has not been already activated."),
    NOT_ENOUGH_CREDITS("04", "The amount claimed is greater than the amount of the selected voucher."),
    USER_INACTIVE("05", "User inactive, voucher impossible to verify."),
    CATEGORY_AND_TYPE_NOT_ALIGNED("06", "Category and type of this voucher are not aligned with category and type managed by the user.");

    private String errorCode;
    private String errorDescription;

    FaultErrorCode(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
