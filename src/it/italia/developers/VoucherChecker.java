package it.italia.developers;

import it.mibact.bonus.verificavoucher.Check;
import it.mibact.bonus.verificavoucher.CheckRequestObj;
import it.mibact.bonus.verificavoucher.CheckResponse;
import it.mibact.bonus.verificavoucher.ObjectFactory;
import it.mibact.bonus.verificavoucher.VerificaVoucher;
import it.mibact.bonus.verificavoucher.VerificaVoucher_Service;

public class VoucherChecker {

	private VerificaVoucher verificaVoucher;
	private ObjectFactory objectFactory;

	public VoucherChecker() {
		verificaVoucher = new VerificaVoucher_Service().getVerificaVoucherSOAP();
		objectFactory = new ObjectFactory();
	}

	public CheckResponse check(String voucher) {
		return check(voucher, "1");
	}
	
	public CheckResponse checkAndConsume(String voucher) {
		return check(voucher, "2");
	}
	
	private CheckResponse check(String voucher, String operation) {
		CheckRequestObj checkRequest = objectFactory.createCheckRequestObj();
		Check check = objectFactory.createCheck();
		
		check.setCodiceVoucher(voucher);
		check.setTipoOperazione(operation);
		
		checkRequest.setCheckReq(check);
		return verificaVoucher.check(checkRequest).getCheckResp();
	}
	
}
