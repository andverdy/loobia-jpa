package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.Order;
import it.objectmethod.loobia.entity.OrderDetails;

@SuppressWarnings("unchecked")
public class OrderDetailDiscountRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		Order order = (Order) paramsToValidate.get("order");
		List<String> errors = (List<String>) paramsToValidate.get("errList");

		List<OrderDetails> commissionDetailCopyList = null;
		if (order != null) {

			commissionDetailCopyList = order.getDetailOrders();
			if (commissionDetailCopyList != null) {
				for (OrderDetails cdc : commissionDetailCopyList) {
					if (cdc.getSconto() > 100) {
						errors.add("Non è possibile inserire uno sconto maggiore del 100%");
					}
				}
			}

		}

	}

}
