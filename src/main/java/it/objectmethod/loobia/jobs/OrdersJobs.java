package it.objectmethod.loobia.jobs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import it.objectmethod.loobia.entity.Order;
import it.objectmethod.loobia.entity.OrderDetails;
import it.objectmethod.loobia.repository.OrderRepository;

@Component
public class OrdersJobs {
	@Autowired
	private OrderRepository orderRepo;

	@Value("${order.filename}")
	private String fileNamePath;

	@Value("${order.save.path}")
	private String orderPathFile;

	// esegui ogni 10 secondi => "*/10 * * * * ?"
	@Scheduled(cron = "*00 00 20 * * ?")
	public void writeOrdersToFlatFile() {
		List<Order> orderList = orderRepo.findByEsportato("N");
		if (orderList != null && !orderList.isEmpty()) {
			DateFormat df = new SimpleDateFormat("ddMMyyyy");
			for (Order order : orderList) {
				String dataParse = df.format(order.getData());
				String orderNumber = Integer.toString(order.getNumero());
				String totAmount = amountGenerate(order.getImportoTot());
				String totAmountDiscount = amountGenerate(order.getImportoTotScontato());

				// aggiungo spazi vuoti se la lung non è suffic. e lo inserisco nel file
				String customerCode = String.format("%-10s", order.getCustomerOrder().getCodiceCliente());
				String customerBuisnessName = String.format("%-21s", order.getRagioneSocialeCliente());
				String areaCode = String.format("%-4s", order.getCustomerOrder().getCodZona());
				String finalOrderNumber = String.format("%-11s", orderNumber);

				try {
					String filePathName = this.fileNamePath.replace("NUMPH", orderNumber);
					filePathName = filePathName.replace("ZONAPH", order.getArea().getCodZona());
					filePathName = filePathName.replace("DATEPH", dataParse);

					File file = new File(orderPathFile + filePathName);
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					String finalDataForExtensionFile = String.format("%-11s", dataParse);

					bw.write(customerCode + customerBuisnessName + areaCode + finalOrderNumber
							+ finalDataForExtensionFile + totAmount + totAmountDiscount);
					// Codice relativo alla costruzione e inserimento delle righe dei dettagli
					List<OrderDetails> listOrderDet = order.getDetailOrders();
					for (OrderDetails orderDetails : listOrderDet) {
						String idProd = Long.toString(orderDetails.getProduct().getId());
						String descrizione = orderDetails.getProduct().getDescrizione();
						String totPezzi = Integer.toString(orderDetails.getTotPezzi());
						String singlePrice = amountGenerate(orderDetails.getPrezzoSingolo());
						String convertedDiscount = discountConvert(orderDetails.getSconto());
						String finalProductId = String.format("%-20s", idProd);
						String finalTotPezzi = String.format("%-11s", totPezzi);
						String finalDescrizione = String.format("%-41s", descrizione);
						if (descrizione.length() > 40) {
							finalDescrizione = descrizione.substring(0, 41);
						}
						bw.newLine();
						bw.write(finalProductId + finalDescrizione + finalTotPezzi + singlePrice + convertedDiscount);
					}
					bw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				order.setEsportato("S");
				orderRepo.save(order);
			}
		}
	}

	private String amountGenerate(Float amount) {
		String fixedPosAmount = null;
		if (amount != null) {
			String amountString = amount.toString();
			amountString = amountString.replace(".", ",");
			String[] nums = amountString.split(",");

			String intPart = nums[0];
			while (intPart.length() < 15) {
				intPart = "0" + intPart;
			}
			if (nums.length > 1) {
				String decPart = nums[1];
				while (decPart.length() < 5) {
					decPart = "0" + decPart;
				}
				fixedPosAmount = intPart + decPart;
			} else {
				fixedPosAmount = intPart + "00000";
			}
		}
		return fixedPosAmount;
	}

	private String discountConvert(Integer discount) {
		String scontoString = null;
		if (discount != null) {
			scontoString = Integer.toString(discount);
			while (scontoString.length() < 3) {
				scontoString = "0" + scontoString;
			}
		}
		return scontoString;
	}
}
