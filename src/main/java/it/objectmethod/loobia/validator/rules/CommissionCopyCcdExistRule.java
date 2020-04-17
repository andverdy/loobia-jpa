package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.CommissionCopy;

@SuppressWarnings("unchecked")
public class CommissionCopyCcdExistRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CommissionCopy cC = (CommissionCopy) paramsToValidate.get("commissionCopy");

		if (cC != null) {
			if (cC.getCommissionDetailCopy() == null || cC.getCommissionDetailCopy().isEmpty()) {
				errors.add("Errore, deve esistere almeno una CCD");
			}
		}
	}

}
