package it.objectmethod.loobia.controller;

import java.io.File;
import java.io.FileInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import it.objectmethod.loobia.service.FlatFileDownloadService;
import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin
@RequestMapping("/api/download")
public class FlatFileDownloadController {

	@Autowired
	private FlatFileDownloadService fileDownlService;

	@GetMapping("/flat-file")
	public void flatFileDownload(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "id") Integer id) throws IOException {
		File file = fileDownlService.flatFileGenerate(id, request);
		String fileNamePath = (String) request.getAttribute("fileName");

		if (file != null) {
			response.setContentType("application/txt");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileNamePath);
			InputStream inputStream = new FileInputStream(file);

			int nRead;
			while ((nRead = inputStream.read()) != -1) {
				response.getWriter().write(nRead);
			}
		}
	}
}
