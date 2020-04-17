package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.CommissionCopy;
import it.objectmethod.loobia.entity.CommissionDetailCopy;
import it.objectmethod.loobia.entity.PaymentConditions;
import it.objectmethod.loobia.repository.PaymentConditionsRepository;

@SuppressWarnings("unchecked")
public class CommissionCopyTotalAmountRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CommissionCopy cC = (CommissionCopy) paramsToValidate.get("commissionCopy");
		PaymentConditionsRepository paymentCondRepo = (PaymentConditionsRepository) paramsToValidate
				.get("paymentConditionRepo");
		Float cdcAmount = (float) 0;
		Float cdcAmountDiscount = (float) 0;
		Float newCdcAmountDiscount = (float) 0;
		Float przSingoloPerQnt = (float) 0;
		Float discountValue = (float) 0;
		boolean incassoSpesaExist = false;
		List<CommissionDetailCopy> dettList = cC.getCommissionDetailCopy();

		// valorizza spesa incasso della copiaCommissione
		PaymentConditions paymCondition = paymentCondRepo.findSpesById(cC.getIdCondizioniPagamento());
		if (paymCondition.getSpesa() > 0) {
			cC.setSpesaIncasso(paymCondition.getSpesa());
		} else {
			cC.setSpesaIncasso((float) 0.0);
		}
		// controllare se gli importi totali inseriti dall'utente sono coerenti
		if (dettList != null) {
			for (CommissionDetailCopy cdc : dettList) {
				if (cdc.getPrezzoSingolo() != null && cdc.getSconto() == 0) {
					przSingoloPerQnt = cdc.getPrezzoSingolo() * cdc.getTotPezzi();
					cdcAmount += przSingoloPerQnt;
					if (cC.getSpesaIncasso() > 0 && incassoSpesaExist == false) {
						cdcAmount += cC.getSpesaIncasso();
						newCdcAmountDiscount += cC.getSpesaIncasso();
						incassoSpesaExist = true;
					}
					newCdcAmountDiscount += przSingoloPerQnt;

				} else if (cdc.getPrezzoSingolo() != null && cdc.getSconto() > 0) {
					przSingoloPerQnt = cdc.getPrezzoSingolo() * cdc.getTotPezzi();
					cdcAmount += przSingoloPerQnt;
					discountValue = (przSingoloPerQnt * cdc.getSconto()) / 100;
					cdcAmountDiscount = przSingoloPerQnt - discountValue;
					newCdcAmountDiscount += cdcAmountDiscount;
					if (cC.getSpesaIncasso() > 0 && incassoSpesaExist == false) {
						cdcAmount += cC.getSpesaIncasso();
						newCdcAmountDiscount += cC.getSpesaIncasso();
						incassoSpesaExist = true;
					}
				}
			}

			if (!cC.getImportoTot().equals(cdcAmount)) {
				errors.add("Importo totale errato!");
			}

			float newCdcAmountDiscountCut = (int) (newCdcAmountDiscount * 100);
			newCdcAmountDiscountCut /= 100;
			if (!cC.getImportoTotScontato().equals(newCdcAmountDiscountCut)) {
				errors.add("Importo totale scontato errato!");
			}

		}

	}

}
