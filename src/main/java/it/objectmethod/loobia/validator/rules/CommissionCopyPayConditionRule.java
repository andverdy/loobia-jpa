package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.objectmethod.loobia.entity.CommissionCopy;
import it.objectmethod.loobia.entity.PaymentConditions;
import it.objectmethod.loobia.repository.PaymentConditionsRepository;

@SuppressWarnings("unchecked")
public class CommissionCopyPayConditionRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CommissionCopy cC = (CommissionCopy) paramsToValidate.get("commissionCopy");
		PaymentConditionsRepository paymConditionRepo = (PaymentConditionsRepository) paramsToValidate
				.get("paymConditionRepo");

		if (cC != null) {
			PaymentConditions payCondition;

			try {
				payCondition = paymConditionRepo.findById(cC.getIdCondizioniPagamento()).get();
				if (!payCondition.getAttivo()) {
					errors.add("La Condizione pagamento non è attiva!");
				}
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Non esiste una condizione pagamento con l'id inserito: ");
			}

		}
	}

}
