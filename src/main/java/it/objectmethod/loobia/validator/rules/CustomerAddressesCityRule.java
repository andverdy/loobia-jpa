package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.entity.Municipality;
import it.objectmethod.loobia.repository.MunicipalityRepository;

@SuppressWarnings("unchecked")
public class CustomerAddressesCityRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CustomerAddressesDto customAddressDto = (CustomerAddressesDto) paramsToValidate.get("customerAddressDto");
		MunicipalityRepository municipRepo = (MunicipalityRepository) paramsToValidate.get("municipalityRepo");

		Municipality municipality = municipRepo.findByNome(customAddressDto.getCitta());

		String capInserito = customAddressDto.getCap();

		if (municipality != null && customAddressDto != null) {
			String capMunicRepo = municipality.getCap();
			if (!(splitCap(capInserito, capMunicRepo))) {
				errors.add("Il Cap inserito è errato!");
			}
			if (!(customAddressDto.getProvincia().equals(municipality.getProvincia()))) {
				errors.add("La Provincia inserita è errata!");
			}
		} else {
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
