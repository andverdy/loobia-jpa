package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.CommissionCopy;
import it.objectmethod.loobia.entity.CommissionDetailCopy;
import it.objectmethod.loobia.repository.ProductRepository;

@SuppressWarnings("unchecked")
public class CommissionDetailSinglePriceRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CommissionCopy cC = (CommissionCopy) paramsToValidate.get("commissionCopy");
		ProductRepository prodRepo = (ProductRepository) paramsToValidate.get("productRepo");

		List<CommissionDetailCopy> cdcList = cC.getCommissionDetailCopy();
		if (cdcList != null && !cdcList.isEmpty()) {
			for (CommissionDetailCopy cdc : cdcList) {
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
