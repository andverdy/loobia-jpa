package it.objectmethod.loobia.controller.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.entity.User;
import it.objectmethod.loobia.repository.AreaRepository;
import it.objectmethod.loobia.repository.CustomerRepository;
import it.objectmethod.loobia.repository.UserRepository;

@Component
public class UserPermissionsManipulationAddress {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private AreaRepository areaRepo;

	public String usersPermissionCheck(HttpServletRequest request, CustomerAddressesDto customerAddresDto) {

		String email = (String) request.getAttribute("email");
		String message = null;

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
			message = "L'utente non ha ancora aggiunto nessun indirizzo cliente!";
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
				message = "L'utente non ha un cliente con questo id!";
			}
		}
		return message;
	}
}
