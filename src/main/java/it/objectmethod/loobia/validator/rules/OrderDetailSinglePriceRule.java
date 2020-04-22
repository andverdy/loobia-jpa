package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.Order;
import it.objectmethod.loobia.entity.OrderDetails;
import it.objectmethod.loobia.repository.ProductRepository;

@SuppressWarnings("unchecked")
public class OrderDetailSinglePriceRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		Order order = (Order) paramsToValidate.get("order");
		ProductRepository prodRepo = (ProductRepository) paramsToValidate.get("productRepo");

		List<OrderDetails> cdcList = order.getDetailOrders();
		if (cdcList != null && !cdcList.isEmpty()) {
			for (OrderDetails cdc : cdcList) {

				if (prodRepo != null && !cdcList.isEmpty()) {
					Float price = prodRepo.findPrezzoById(cdc.getProduct().getId());
					if (!price.equals(cdc.getPrezzoSingolo())) {
						errors.add("Il campo prezzo singolo non corrisponde al prezzo reale del prodotto");
					}
				}
			}
		}

	}

}
