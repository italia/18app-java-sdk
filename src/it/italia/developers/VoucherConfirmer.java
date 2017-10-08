package it.italia.developers;

import it.mibact.bonus.verificavoucher.Confirm;
import it.mibact.bonus.verificavoucher.ConfirmRequestObj;
import it.mibact.bonus.verificavoucher.ConfirmResponse;
import it.mibact.bonus.verificavoucher.ObjectFactory;
import it.mibact.bonus.verificavoucher.VerificaVoucher;
import it.mibact.bonus.verificavoucher.VerificaVoucher_Service;

public class VoucherConfirmer {

	private VerificaVoucher verificaVoucher;
	private ObjectFactory objectFactory;
	
	/**
	 * Create a new {@link VoucherConfirmer}, used to verify and consume a specific
	 * amount from a given voucher.
	 */
	public VoucherConfirmer() {
		verificaVoucher = new VerificaVoucher_Service().getVerificaVoucherSOAP();
		objectFactory = new ObjectFactory();
	}

	/**
	 * Check and consume an amount from a given voucher.
	 * @param voucher The voucher code
	 * @param amount The amount to consume
	 * @return The {@link ConfirmResponse} representing the response. 
	 */
	public ConfirmResponse consume(String voucher, double amount) {
		ConfirmRequestObj confirmRequest = objectFactory.createConfirmRequestObj();
		Confirm confirm = objectFactory.createConfirm();
		
		confirm.setCodiceVoucher(voucher);
		confirm.setImporto(amount);
		confirm.setTipoOperazione("1");
		
		confirmRequest.setCheckReq(confirm);
		return verificaVoucher.confirm(confirmRequest).getCheckResp();
	}
	
}
