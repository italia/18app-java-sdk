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

	public VoucherConfirmer() {
		verificaVoucher = new VerificaVoucher_Service().getVerificaVoucherSOAP();
		objectFactory = new ObjectFactory();
	}

	public ConfirmResponse confirm(String voucher, double amount) {
		return confirm(voucher, amount, "1");
	}
	
	public ConfirmResponse confirmAndConsume(String voucher, double amount) {
		return confirm(voucher, amount, "2");
	}
	
	private ConfirmResponse confirm(String voucher, double amount, String operation) {
		ConfirmRequestObj confirmRequest = objectFactory.createConfirmRequestObj();
		Confirm confirm = objectFactory.createConfirm();
		
		confirm.setCodiceVoucher(voucher);
		confirm.setImporto(amount);
		confirm.setTipoOperazione(operation);
		
		confirmRequest.setCheckReq(confirm);
		return verificaVoucher.confirm(confirmRequest).getCheckResp();
	}
	
}
