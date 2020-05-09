package it.objectmethod.loobia.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.objectmethod.loobia.service.ExcelFileDownloadService;

@RestController
@CrossOrigin
@RequestMapping("/api/download")
public class ExcelFileDownloadController {

	@Autowired
	private ExcelFileDownloadService excelDownlService;

	@GetMapping("/excel-file")
	public void downloadExcelFile(@RequestParam(value = "id") Integer id, HttpServletResponse response,
			HttpServletRequest request) throws IOException {

		HSSFWorkbook workbook = excelDownlService.excelFileGenerate(id, request);
		String fileNamePath = (String) request.getAttribute("fileName");
		if (workbook != null) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileNamePath);
			workbook.write(response.getOutputStream());
			workbook.close();
		}
	}
}