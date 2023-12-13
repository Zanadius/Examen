package cuarto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CONTRATO")
public class Contrato {

	@Id
	@SequenceGenerator(name = "CONTRATOOGEN", sequenceName = "CONTRATO_GEN", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTRATOOGEN")
	private long id;
	@Column(columnDefinition = "VARCHAR(20)", name = "NOMBRE_CLIENTE")
	private String nombreCliente;
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	@Column(name = "FECHA_FIN")
	private Date fechaFin;
	@Column(name = "IMPORTE")
	private int importe;
	
	
	public Contrato() {
		super();
	}


	
	Contrato(String nombreCliente, Date fechaInicio, Date fechaFin, int importe) {
		super();
		this.nombreCliente = nombreCliente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.importe = importe;
	}



	Contrato(long id, String nombreCliente, Date fechaInicio, Date fechaFin, int importe) {
		super();
		this.id = id;
		this.nombreCliente = nombreCliente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.importe = importe;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNombreCliente() {
		return nombreCliente;
	}



	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}



	public Date getFechaInicio() {
		return fechaInicio;
	}



	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public Date getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}



	public int getImporte() {
		return importe;
	}



	public void setImporte(int importe) {
		this.importe = importe;
	}



	@Override
	public String toString() {
		return "Contrato [id=" + id + ", nombreCliente=" + nombreCliente + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", importe=" + importe + "]";
	}

	
	
}
