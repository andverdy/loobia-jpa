package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import it.objectmethod.loobia.dto.OrderDto;
import it.objectmethod.loobia.entity.PaymentConditions;
import it.objectmethod.loobia.repository.PaymentConditionsRepository;

@SuppressWarnings("unchecked")
public class OrderPayConditionRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		OrderDto orderDto = (OrderDto) paramsToValidate.get("orderDto");
		PaymentConditionsRepository paymConditionRepo = (PaymentConditionsRepository) paramsToValidate
				.get("paymConditionRepo");

		PaymentConditions payCondition;

		try {
			payCondition = paymConditionRepo.findById(orderDto.getIdCondizioniPagamento()).get();
			if (!payCondition.getAttivo()) {
				errors.add("La Condizione pagamento non è attiva!");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Non esiste una condizione pagamento con l'id inserito!");
		}

	}

}
