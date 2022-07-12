package com.tt.utilidades.usuario;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tt.modelo.usuario.Usuario;

public class ExportarExcelUsuario {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Usuario> usuariosList;

	public ExportarExcelUsuario(List<Usuario> listUsuarios) {
		this.usuariosList = listUsuarios;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("ListaUsuarios");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Id", style);
		createCell(row, 1, "Nombre", style);
		createCell(row, 2, "Apellido", style);
		createCell(row, 3, "Contraseña", style);
		createCell(row, 4, "Telefono", style);
		createCell(row, 5, "Direccion", style);
		createCell(row, 6, "Correo", style);
		createCell(row, 7, "Identificacion", style);
		createCell(row, 8, "Fecha Nacimiento", style);
		createCell(row, 9, "Tipo Documento", style);
		createCell(row, 10, "Rol", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Usuario usu : this.usuariosList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, usu.getId(), style);
			createCell(row, columnCount++, usu.getNombre(), style);
			createCell(row, columnCount++, usu.getApellido(), style);
			createCell(row, columnCount++, usu.getContrasena(), style);
			createCell(row, columnCount++, usu.getTelefono(), style);
			createCell(row, columnCount++, usu.getDireccion(), style);
			createCell(row, columnCount++, usu.getCorreo(), style);
			createCell(row, columnCount++, usu.getIdentificacion(), style);
			createCell(row, columnCount++, usu.getFechaNacimiento().toString(), style);
			createCell(row, columnCount++, usu.getIdTipoDocumento().getAbreviatura() + ": " + usu.getIdentificacion(),style);
			createCell(row, columnCount++, usu.getIdRol().getNombre(), style);

		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
