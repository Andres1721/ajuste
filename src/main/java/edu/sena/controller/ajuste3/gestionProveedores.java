package edu.sena.controller.ajuste3;

import edu.sena.entity.ajuste3.Documento;
import edu.sena.entity.ajuste3.Proveedor;
import edu.sena.facade.ajuste3.DocumentoFacadeLocal;
import edu.sena.facade.ajuste3.ProveedorFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author ai00214
 */
@Named(value = "gestionProveedores")
@ViewScoped
public class gestionProveedores implements Serializable {

    private List tiposDocumento = new ArrayList<>();
    @EJB
    ProveedorFacadeLocal proveedorFacadeLocal;
    @EJB
    DocumentoFacadeLocal documentoFacadeLocal;

    private Proveedor pro = new Proveedor();
    private int tipoDocumento;

    @PostConstruct
    public void cargarPostConst() {
        tiposDocumento.addAll(documentoFacadeLocal.findAll());
    }

    public gestionProveedores() {
    }

    public List<Documento> listarTipoDocumentos() {
        tiposDocumento = documentoFacadeLocal.findAll();
        return tiposDocumento;
    }

    public List<Proveedor> listarProveedores() {
        return proveedorFacadeLocal.findAll();
    }

    public void registrarProveedor() {
        Documento docu = documentoFacadeLocal.find(tipoDocumento);
        pro.setIdTipoDocumento(docu);
        proveedorFacadeLocal.create(pro);

        PrimeFaces.current().executeScript("Swal.fire({"
                + "  title: 'OK!',"
                + "  text: 'Proveedor' Registrado'+," + pro.getRazonSocialProveedor()
                + "  icon: 'success',"
                + "  confirmButtonText: 'Aceptar'"
                + "})");
        pro = new Proveedor();

    }

    public Proveedor getPro() {
        return pro;
    }

    public void setPro(Proveedor pro) {
        this.pro = pro;
    }

    public List getTiposDocumento() {
        return tiposDocumento;
    }

    public void setTiposDocumento(List tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
