package it.objectmethod.loobia.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import it.objectmethod.loobia.entity.Order;
import it.objectmethod.loobia.entity.OrderDetails;
import it.objectmethod.loobia.repository.OrderRepository;


@Component
public class ExcelFileDownloadService {

	@Autowired
	private OrderRepository orderRepo;

	@Value("${order.fileExcelName}")
	private String fileNamePath;

	public HSSFWorkbook excelFileGenerate(Integer id, HttpServletRequest request) throws IOException {
		Order order = orderRepo.findById(id).get();
		HSSFWorkbook workbook = new HSSFWorkbook();
		if (order != null) {
			HSSFSheet sheet = workbook.createSheet();
			FileOutputStream out = null;
			String orderNumber = Integer.toString(order.getNumero());
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String dataParse = df.format(order.getData());
			int index = 0, rownum = 0, cellOrdernum = 0, cellDettNum = 0;
			Row row = sheet.createRow(rownum++);
			Cell cell = row.createCell(cellOrdernum++);
			cell.setCellValue(order.getCustomerOrder().getCodiceCliente());
			cell = row.createCell(cellOrdernum++);
			cell.setCellValue(order.getRagioneSocialeCliente());
			cell = row.createCell(cellOrdernum++);
			cell.setCellValue(order.getCustomerOrder().getCodZona());
			cell = row.createCell(cellOrdernum++);
			cell.setCellValue(order.getNumero());
			cell = row.createCell(cellOrdernum++);
			cell.setCellValue(dataParse);
			cell = row.createCell(cellOrdernum++);
			cell.setCellValue(order.getImportoTot());
			cell = row.createCell(cellOrdernum++);
			cell.setCellValue(order.getImportoTotScontato());
			while (index < order.getDetailOrders().size()) {
				row = sheet.createRow(rownum++);
				List<OrderDetails> orderListDett = order.getDetailOrders();
				Cell orderCell = row.createCell(cellDettNum++);
				orderCell.setCellValue(orderListDett.get(index).getId());
				orderCell = row.createCell(cellDettNum++);
				orderCell.setCellValue(orderListDett.get(index).getProduct().getDescrizione());
				orderCell = row.createCell(cellDettNum++);
				orderCell.setCellValue(orderListDett.get(index).getTotPezzi());
				orderCell = row.createCell(cellDettNum++);
				orderCell.setCellValue(orderListDett.get(index).getPrezzoSingolo());
				orderCell = row.createCell(cellDettNum++);
				orderCell.setCellValue(orderListDett.get(index).getSconto());
				cellDettNum = 0;
				index++;
			}
			try {
				String filePathName = this.fileNamePath.replace("NUMPH", orderNumber);
				filePathName = filePathName.replace("ZONAPH", order.getArea().getCodZona());
				filePathName = filePathName.replace("DATEPH", dataParse);
				request.setAttribute("fileName", filePathName);
				out = new FileOutputStream(filePathName);
				workbook.write(out);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return workbook;
	}
}
