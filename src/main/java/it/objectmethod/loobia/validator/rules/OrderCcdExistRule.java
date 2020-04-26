package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;
import it.objectmethod.loobia.dto.OrderDto;

@SuppressWarnings("unchecked")
public class OrderCcdExistRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		OrderDto orderDto = (OrderDto) paramsToValidate.get("orderDto");

		if (orderDto != null) {
			if (orderDto.getDetailOrdersDto() == null || orderDto.getDetailOrdersDto().isEmpty()) {
				errors.add("Errore, deve esistere almeno una CCD");
			}
		}
	}

}
