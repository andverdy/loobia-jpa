package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;
import it.objectmethod.loobia.dto.OrderDetailsDto;
import it.objectmethod.loobia.dto.OrderDto;
import it.objectmethod.loobia.repository.ProductRepository;

@SuppressWarnings("unchecked")
public class OrderDetailsSinglePriceRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		OrderDto orderDto = (OrderDto) paramsToValidate.get("orderDto");
		ProductRepository prodRepo = (ProductRepository) paramsToValidate.get("productRepo");

		List<OrderDetailsDto> cdcList = orderDto.getDetailOrdersDto();
		if (cdcList != null && !cdcList.isEmpty()) {
			for (OrderDetailsDto cdc : cdcList) {

				if (prodRepo != null && !cdcList.isEmpty()) {
					Float price = prodRepo.findPrezzoById(cdc.getIdProdotto());
					if (!price.equals(cdc.getPrezzoSingolo())) {
						errors.add("Il campo prezzo singolo non corrisponde al prezzo reale del prodotto");
					}
				}
			}
		}

	}

}
