package edu.sena.controller.ajuste3;

import com.opencsv.CSVReader;
import edu.sena.entity.ajuste3.EstadoCompra;
import edu.sena.entity.ajuste3.FormaPago;
import edu.sena.entity.ajuste3.Usuario;
import edu.sena.entity.ajuste3.Variedad;
import edu.sena.entity.ajuste3.Ventas;
import edu.sena.facade.ajuste3.EstadoCompraFacadeLocal;
import edu.sena.facade.ajuste3.FormaPagoFacadeLocal;
import edu.sena.facade.ajuste3.OrdenVentaFacadeLocal;
import edu.sena.facade.ajuste3.VariedadFacadeLocal;
import edu.sena.facade.ajuste3.VentasFacadeLocal;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Julian Paredes
 */
@Named(value = "ordenVenta")
@SessionScoped
public class ordenVenta implements Serializable {

    @EJB
    EstadoCompraFacadeLocal estadoCompraFacadeLocal;
    @EJB
    FormaPagoFacadeLocal formaPagoFacadeLocal;
    @EJB
    VentasFacadeLocal ventasFacadeLocal;
    @EJB
    VariedadFacadeLocal variedadFacadeLocal;
    @Resource(lookup = "java:app/ajuste3")
    DataSource dataSource;

    private int compra;
    private int pago;
    private Part archivoCsv;
    private Ventas ventaReg = new Ventas();
    private Ventas temVenta = new Ventas();
    
    
    /**
     * Creates a new instance of ordenVentas
     */
//    private List<FormaPago> listPago = new ArrayList<>();
//    private List<EstadoCompra> listEstado = new ArrayList<>();

//    @PostConstruct
//    public void post() {
//
//        listEstado.addAll(estadoCompraFacadeLocal.findAll());
//        listPago.addAll(formaPagoFacadeLocal.findAll());
//
//    }
    public ordenVenta() {
    }

    public List<Ventas> listarVentas() {
        return ventasFacadeLocal.findAll();
    }

    public void descargaReporte() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");
        try {
            File jasper = new File(context.getRealPath("/reportes/ProductosMasVendidos.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), new HashMap(), dataSource.getConnection());

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=ProductosMasVendidos.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
        } catch (IOException | SQLException | JRException e) {
            System.out.println("gestionUsuario.descargaReporte() " + e.getMessage());

        }
    }
//
//    public void cargaInicial() {
//        try {
//
//            if (archivoCsv != null) {
//                if (archivoCsv.getSize() > 900000) {
//                    PrimeFaces.current().executeScript("Swal.fire({"
//                            + "  title: 'Error!',"
//                            + "  text: 'No se puede cargar este archivo, pór su tamaño',"
//                            + "  icon: 'error',"
//                            + "  confirmButtonText: 'Por favor intente mas tarde'"
//                            + "})");
//                } else if (archivoCsv.getContentType().equalsIgnoreCase("application/vnd.ms-excel")) {
//
//                    File carpeta = new File("C:/colaff/Archivos/Administrador");
//                    if (!carpeta.exists()) {
//                        carpeta.mkdirs();
//                    }
//                    try (InputStream is = archivoCsv.getInputStream()) {
//                        Calendar hoy = Calendar.getInstance();
//                        Files.copy(is, (new File(carpeta, archivoCsv.getSubmittedFileName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//                        try (CSVReader reader = new CSVReader(new FileReader("C:/colaff/Archivos/Administrador/" + archivoCsv.getSubmittedFileName()))) {
//                            List<String[]> r = reader.readAll();
//                            for (String[] st : r) {
//                                Ventas ven = ventasFacadeLocal.validarExistencia(st[4]);
//                                if (ven != null) {
//
//                                    ven.setIdVentas(Integer.parseInt( st[0]));
//                                    ven.setFecha(st[1]);                                  
//                                    ven.setNombres(st[2]);
//                                    ven.setApellidos(st[3]);
//                                    ven.setDireccion(st[4]);
//                                    ven.setCorreo(st[5]); 
//                                    ven.setCatidadArticulos (Integer.parseInt( st[6]));
//                                    ventasFacadeLocal.edit(ven);
//                                } else {
//                                    Ventas newC = new Ventas();
//                                    newC.setIdVentas(Integer.parseInt( st[0]));
//                                    newC.setFecha(st[1]);                                  
//                                    newC.setNombres(st[2]);
//                                    newC.setApellidos(st[3]);
//                                    newC.setDireccion(st[4]);
//                                    newC.setCorreo(st[5]); 
//                                    newC.setCatidadArticulos (Integer.parseInt( st[6]));
//                  
//                                }
//                            }
//                        }
//                        PrimeFaces.current().executeScript("Swal.fire({"
//                                + "  title: 'Imagen de perfil Actualizada !',"
//                                + "  text: 'Con Exito !!!',"
//                                + "  icon: 'success',"
//                                + "  confirmButtonText: 'Ok'"
//                                + "})");
//                        PrimeFaces.current().executeScript("document.getElementById('formReset').click()");
//
//                    } catch (Exception e) {
//                        PrimeFaces.current().executeScript("Swal.fire({"
//                                + "  title: 'Error!',"
//                                + "  text: 'No se puede realizar esta peticion',"
//                                + "  icon: 'error',"
//                                + "  confirmButtonText: 'Por favor intente mas tarde'"
//                                + "})");
//
//                    }
//
//                } else {
//                    PrimeFaces.current().executeScript("Swal.fire({"
//                            + "  title: 'Error!',"
//                            + "  text: 'Tipo de archivo no permitido, recuerde la extencion es "
//                            + ".csv',"
//                            + "  icon: 'error',"
//                            + "  confirmButtonText: 'Por favor intente mas tarde'"
//                            + "})");
//                }
//
//            } else {
//                PrimeFaces.current().executeScript("Swal.fire({"
//                        + "  title: 'Error!',"
//                        + "  text: 'No se puede realizar esta peticion',"
//                        + "  icon: 'error',"
//                        + "  confirmButtonText: 'Por favor intente mas tarde'"
//                        + "})");
//
//            }
//
//        } catch (Exception e) {
//            PrimeFaces.current().executeScript("Swal.fire({"
//                    + "  title: 'Error!',"
//                    + "  text: 'No se puede realizar esta peticion',"
//                    + "  icon: 'error',"
//                    + "  confirmButtonText: 'Por favor intente mas tarde'"
//                    + "})");
//        }
//
//        PrimeFaces.current().executeScript("document.getElementById('formReset').click()");
//    }


public void descReporteXlsx() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();

        try {
            File jasper = new File(context.getRealPath("/reportes/ProductosMasVendidos.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), new HashMap(), dataSource.getConnection());

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jp));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true);
            configuration.setSheetNames(new String[]{"Venta"});
            exporter.setConfiguration(configuration);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment; filename=ProductosMasVendidos.xlsx");
            exporter.exportReport();
            facesContext.responseComplete();
        } catch (SQLException | JRException | IOException e) {
            System.out.println("ordenVenta.descReporteXlsx() " + e.getMessage());
        }
    }

    public void registrarVenta() {
        System.out.println("Entro alantes");
        if (ventasFacadeLocal.registrarVenta(ventaReg)) {
            System.out.println("Entro al if");
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'OK!',"
                    + "  text: 'Venta Registrada',"
                    + "  icon: 'success',"
                    + "  confirmButtonText: 'Aceptar'"
                    + "})");

        } else {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'Usuario no Registrado',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Intentar de nuevo'"
                    + "})");
            System.out.println("Entro al else 2");
        }

        ventaReg = new Ventas();

    }

    public void eliminarVenta(Ventas vnt) {
        try {
            ventasFacadeLocal.remove(vnt);
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Venta !',"
                    + "  text: 'Removida satisfactoriamente !!!'"
                    + "  icon: 'success',"
                    + "  confirmButtonText: 'Ok'"
                    + "})");
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'No se puede realizar esta peticion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Por favor intente mas tarde'"
                    + "})");
        }

    }

    public void cargaTemporal(Ventas vnt) {
        temVenta = vnt;
    }

    public void editarVenta() {
        try {
            ventasFacadeLocal.edit(temVenta);
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Venta !',"
                    + "  text: 'Actualizado satisfactoriamente !!!'"
                    + "  icon: 'success',"
                    + "  confirmButtonText: 'Ok'"
                    + "})");
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'No se puede realizar esta peticion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Por favor intente mas tarde'"
                    + "})");
        }

    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

//    public List<EstadoCompra> getListEstado() {
//        return listEstado;
//    }
//
//    public void setListEstado(List<EstadoCompra> listEstado) {
//        this.listEstado = listEstado;
//    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

//    public List<FormaPago> getListPago() {
//        return listPago;
//    }
//
//    public void setListPago(List<FormaPago> listPago) {
//        this.listPago = listPago;
//    }

    public Part getArchivoCsv() {
        return archivoCsv;
    }

    public void setArchivoCsv(Part archivoCsv) {
        this.archivoCsv = archivoCsv;
    }

    public Ventas getVentaReg() {
        return ventaReg;
    }

    public void setVentaReg(Ventas ventaReg) {
        this.ventaReg = ventaReg;
    }

    public Ventas getTemVenta() {
        return temVenta;
    }

    public void setTemVenta(Ventas temVenta) {
        this.temVenta = temVenta;
    }

}
