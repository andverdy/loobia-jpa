package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.Order;

@SuppressWarnings("unchecked")
public class OrderCcdExistRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		Order order = (Order) paramsToValidate.get("order");

		if (order != null) {
			if (order.getDetailOrders() == null || order.getDetailOrders().isEmpty()) {
				errors.add("Errore, deve esistere almeno una CCD");
			}
		}
	}

}
