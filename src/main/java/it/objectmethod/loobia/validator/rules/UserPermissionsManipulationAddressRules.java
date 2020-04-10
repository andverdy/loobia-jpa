package it.objectmethod.loobia.validator.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.entity.User;
import it.objectmethod.loobia.repository.AreaRepository;
import it.objectmethod.loobia.repository.CustomerRepository;
import it.objectmethod.loobia.repository.UserRepository;

@SuppressWarnings("unchecked")
public class UserPermissionsManipulationAddressRules implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		String email = (String) paramsToValidate.get("email");
		UserRepository userRepo = (UserRepository) paramsToValidate.get("userRepo");
		CustomerAddressesDto customerAddresDto = (CustomerAddressesDto) paramsToValidate.get("customerAddressDto");
		CustomerRepository custRepo = (CustomerRepository) paramsToValidate.get("custRepo");
		AreaRepository areaRepo = (AreaRepository) paramsToValidate.get("areRepository");
		boolean customerIdUserLoginExist = false;
		Map<String, List<Integer>> allCodZonaUserMap = new HashMap<String, List<Integer>>();

		User user = userRepo.findByEmail(email);

		List<String> allCodZonaBYIdUser = areaRepo.findAllCodZonaById(user.getIdUtente());
		List<Integer> listCustomerIdUserLogin = new ArrayList<Integer>();

		for (String codZonaBYIdUser : allCodZonaBYIdUser) {
			listCustomerIdUserLogin = custRepo.findAllCustomerIdByCodZona((codZonaBYIdUser));
			allCodZonaUserMap.put(codZonaBYIdUser, listCustomerIdUserLogin);
		}

		if (listCustomerIdUserLogin != null && listCustomerIdUserLogin.isEmpty()) {
			errors.add("L'utente non ha ancora aggiunto nessun indirizzo cliente!");
		} else if (!listCustomerIdUserLogin.isEmpty()) {

			for (Map.Entry<String, List<Integer>> entry : allCodZonaUserMap.entrySet()) {
				if (customerIdUserLoginExist) {
					break;
				}
				for (int i = 0; i < entry.getValue().size(); i++) {

					if (entry.getValue().get(i) == customerAddresDto.getIdCliente()) {
						customerIdUserLoginExist = true;
						break;
					}
				}
			}
			if (customerIdUserLoginExist == false) {
				errors.add("L'utente non ha un cliente con questo id!");
			}
		}
	}
}
