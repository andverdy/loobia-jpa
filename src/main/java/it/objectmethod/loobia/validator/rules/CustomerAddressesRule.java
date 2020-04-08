package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.CustomerAddresses;
import it.objectmethod.loobia.entity.Municipality;
import it.objectmethod.loobia.repository.MunicipalityRepository;

@SuppressWarnings("unchecked")
public class CustomerAddressesRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CustomerAddresses customAddress = (CustomerAddresses) paramsToValidate.get("customerAddress");
		MunicipalityRepository municipRepo = (MunicipalityRepository) paramsToValidate.get("municipalityRepo");

		Municipality municipality = municipRepo.findByNome(customAddress.getCitta());

		String capInserito = customAddress.getCap();
		

		if (municipality != null && customAddress != null) {
			String capMunicRepo = municipality.getCap();
			if (!(splitCap(capInserito, capMunicRepo))) {
				errors.add("Il Cap inserito è errato!");
			}
			if (!(customAddress.getProvincia().equals(municipality.getProvincia()))) {
				errors.add("La Provincia inserita è errata!");
			}
		}else {
			errors.add("La Città inserita è errata!");
		}

	}

	private boolean splitCap(String capInseritoParam, String capMunicipalityParam) {
		String capMuniciSplittato = "";
		String capInseritoSplittato = "";
		int qntX = 0;

		for (int i = 0; i < capMunicipalityParam.length(); i++) {
			if (capMunicipalityParam.charAt(i) == 'x') {
				qntX++;
			}
		}

		int lunghezzaCapMunicp = capMunicipalityParam.length();
		int numCaratteriRimanenti = lunghezzaCapMunicp - qntX;

		for (int i = 0; i < numCaratteriRimanenti; i++) {
			capMuniciSplittato += capMunicipalityParam.charAt(i);
			capInseritoSplittato += capInseritoParam.charAt(i);
		}
		if (capMuniciSplittato.equals(capInseritoSplittato)) {
			return true;
		} else {
			return false;
		}

	}

}
