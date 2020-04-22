package it.objectmethod.loobia.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.objectmethod.loobia.dto.OrderDetailDto;
import it.objectmethod.loobia.dto.OrderDto;
import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.entity.Order;
import it.objectmethod.loobia.entity.OrderDetails;
import it.objectmethod.loobia.entity.PaymentConditions;
import it.objectmethod.loobia.entity.Product;
import it.objectmethod.loobia.repository.CustomerRepository;
import it.objectmethod.loobia.repository.PaymentConditionsRepository;
import it.objectmethod.loobia.repository.ProductRepository;

@Component
public class OrderMapperCustom implements EntityMapper<OrderDto, Order> {

	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PaymentConditionsRepository payCondRepo;

	@Autowired
	private ProductRepository productRepo;

	@Override
	public Order toEntity(OrderDto dto) {
		if (dto == null) {
			return null;
		}

		Order order = new Order();
		order.setDetailOrders(orderDetailMapper.toEntity(dto.getDetailOrdersDto()));

		Customer customer = new Customer();
		try {
			customer = customerRepo.findById(dto.getIdCliente()).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Il cliente inserito non esiste!");
		}

		PaymentConditions payCond;
		try {
			payCond = payCondRepo.findById(dto.getIdCondizioniPagamento()).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Non esiste una condizione pagamento con l'id inserito!");
		}

		order.setData(dto.getData());
		order.setEsportato(dto.getEsportato());
		order.setEvaso(dto.getEvaso());
		order.setId(dto.getId());
		order.setIdAgente(dto.getIdAgente());
		order.setIdIndirizziCliente(dto.getIdIndirizziCliente());
		order.setImportoTot(dto.getImportoTot());
		order.setImportoTotScontato(dto.getImportoTotScontato());
		order.setMezzoConsegnaVettore(dto.getMezzoConsegnaVettore());
		order.setNote(dto.getNote());
		order.setNumero(dto.getNumero());
		order.setRagioneSocialeCliente(dto.getRagioneSocialeCliente());
		order.setSpesaIncasso(dto.getSpesaIncasso());
		order.setStato(dto.getStato());
		order.setCustomerOrder(customer);
		order.setPaymentConditions(payCond);

		List<OrderDetails> orderDet = order.getDetailOrders();
		for (int i = 0; i < orderDet.size(); i++) {
			if (orderDet.get(i).getOrder() == null) {
				Product product = productRepo.findById(dto.getDetailOrdersDto().get(i).getIdProdotto()).get();
				orderDet.get(i).setProduct(product);
				orderDet.get(i).setOrder(order);
			}

		}

		return order;
	}

	@Override
	public OrderDto toDto(Order entity) {
		if (entity == null) {
			return null;
		}

		OrderDto orderDto = new OrderDto();

		orderDto.setIdCliente(entityCustomerOrderId(entity));
		orderDto.setIdCondizioniPagamento(entityPaymentConditionsId(entity));
		orderDto.setDetailOrdersDto(orderDetailMapper.toDto(entity.getDetailOrders()));
		orderDto.setId(entity.getId());
		orderDto.setData(entity.getData());
		orderDto.setNote(entity.getNote());
		orderDto.setStato(entity.getStato());
		orderDto.setNumero(entity.getNumero());
		orderDto.setMezzoConsegnaVettore(entity.getMezzoConsegnaVettore());
		orderDto.setIdIndirizziCliente(entity.getIdIndirizziCliente());
		orderDto.setIdAgente(entity.getIdAgente());
		orderDto.setRagioneSocialeCliente(entity.getRagioneSocialeCliente());
		orderDto.setEsportato(entity.getEsportato());
		orderDto.setEvaso(entity.getEvaso());
		orderDto.setImportoTot(entity.getImportoTot());
		orderDto.setImportoTotScontato(entity.getImportoTotScontato());
		orderDto.setSpesaIncasso(entity.getSpesaIncasso());

		List<OrderDetailDto> orderDetDto = orderDto.getDetailOrdersDto();
		for (int i = 0; i < orderDetDto.size(); i++) {

			orderDto.getDetailOrdersDto().get(i).setIdProdotto(entity.getDetailOrders().get(i).getProduct().getId());

		}

		return orderDto;
	}

	private Integer entityCustomerOrderId(Order order) {
		if (order == null) {
			return null;
		}
		Customer customerOrder = order.getCustomerOrder();
		if (customerOrder == null) {
			return null;
		}
		Integer id = customerOrder.getId();
		if (id == null) {
			return null;
		}
		return id;
	}

	private Long entityPaymentConditionsId(Order order) {
		if (order == null) {
			return null;
		}
		PaymentConditions paymentConditions = order.getPaymentConditions();
		if (paymentConditions == null) {
			return null;
		}
		Long id = paymentConditions.getId();
		if (id == null) {
			return null;
		}
		return id;
	}

	@Override
	public List<Order> toEntity(List<OrderDto> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDto> toDto(List<Order> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

}
