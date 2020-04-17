package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.objectmethod.loobia.entity.CommissionCopy;
import it.objectmethod.loobia.entity.CommissionDetailCopy;

public class CommissionDetailDiscountRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		CommissionCopy commissionCopy = (CommissionCopy) paramsToValidate.get("commissionCopy");

		List<CommissionDetailCopy> commissionDetailCopyList = null;
		if (commissionCopy != null) {

			commissionDetailCopyList = commissionCopy.getCommissionDetailCopy();
			if (commissionDetailCopyList != null) {
				for (CommissionDetailCopy cdc : commissionDetailCopyList) {
					if (cdc.getSconto() > 100) {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
								"Non è possibile inserire uno sconto maggiore del 100%");
					}
				}
			}

		}

	}

}
