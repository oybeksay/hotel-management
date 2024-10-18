package revolusion.hotelmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import revolusion.hotelmanagement.util.CSVFileManagement;
import revolusion.hotelmanagement.util.ExcelFileManagementService;

import java.io.IOException;

@RestController
@RequestMapping("/export")
@PreAuthorize("isAuthenticated()")
@Tag(name = "Export", description = "Export Data")
public class FileExportController {

    private final ExcelFileManagementService excelFileManagementService;
    private final CSVFileManagement csvFileManagement;

    public FileExportController(ExcelFileManagementService excelFileManagementService, CSVFileManagement csvFileManagement) {
        this.excelFileManagementService = excelFileManagementService;
        this.csvFileManagement = csvFileManagement;
    }

    @Operation(summary = "Export hotels to excel file", description = "Export all hotels to excel file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Export hotels to excel file",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/hotels/export/excel")
    public ResponseEntity<Void> exportHotelsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=hotels.xlsx";

        response.setHeader(headerKey, headerValue);

        excelFileManagementService.generateHotelExcelFile(response);

        response.flushBuffer();

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Export guests to excel file", description = "Export all guests to excel file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Export guests to excel file",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/guests/export/excel")
    public ResponseEntity<Void> exportGuestsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=guests.xlsx";

        response.setHeader(headerKey, headerValue);

        excelFileManagementService.generateGuestExcelFile(response);

        response.flushBuffer();

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Export orders to excel file", description = "Export all orders to excel file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Export orders to excel file",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/orders/export/excel")
    public ResponseEntity<Void> exportOrdersToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=orders.xlsx";

        response.setHeader(headerKey, headerValue);

        excelFileManagementService.generateOrderExcelFile(response);

        response.flushBuffer();

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Export hotels to csv file", description = "Export all hotels to csv file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Export hotels to csv file",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/hotels/export/csv")
    public ResponseEntity<Void> exportHotelsToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=hotels.csv";

        response.setHeader(headerKey, headerValue);

        csvFileManagement.generateHotelCsvFile(response);

        response.flushBuffer();

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Export orders to csv file", description = "Export all orders to csv file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Export orders to csv file",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/orders/export/csv")
    public ResponseEntity<Void> exportOrdersToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=orders.csv";

        response.setHeader(headerKey, headerValue);

        csvFileManagement.generateOrderCsvFile(response);

        response.flushBuffer();

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Export guests to csv file", description = "Export all guests to csv file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Export guests to csv file",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/guests/export/csv")
    public ResponseEntity<Void> exportGuestsToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=guests.csv";

        response.setHeader(headerKey, headerValue);

        csvFileManagement.generateGuestCsvFile(response);

        response.flushBuffer();

        return ResponseEntity.noContent().build();
    }
}