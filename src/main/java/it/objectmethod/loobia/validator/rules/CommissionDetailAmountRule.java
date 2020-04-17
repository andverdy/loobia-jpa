package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.CommissionCopy;
import it.objectmethod.loobia.entity.CommissionDetailCopy;

@SuppressWarnings("unchecked")
public class CommissionDetailAmountRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CommissionCopy cC = (CommissionCopy) paramsToValidate.get("commissionCopy");

		Double differenzeAmounts = 0.0;
		Double resultBetwDiffAndAmount = 0.0;
		Integer discount = 0;
		List<CommissionDetailCopy> cdcList = null;

		if (cC != null) {
			cdcList = cC.getCommissionDetailCopy();
			if (cdcList != null && !cdcList.isEmpty()) {
				for (CommissionDetailCopy cdc : cdcList) {
					differenzeAmounts = (cdc.getImporto() - cdc.getImportoScontato());
					resultBetwDiffAndAmount = (differenzeAmounts / cdc.getImporto());
					discount = (int) (resultBetwDiffAndAmount * 100);

					if (discount != null && !discount.equals(cdc.getSconto())) {
						errors.add("Importo ed importo scontato non sono coerenti con lo sconto applicato!");
						break;
					}
				}
			}
		}

	}

}
