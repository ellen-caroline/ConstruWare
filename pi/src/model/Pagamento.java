package model;

import java.util.Date;


public class Pagamento {
	
    private int id;
    private String metodo;
    private Double valor;
    private Date data;
    private Date hora;
	
	public Pagamento() {
		super();
	}


	public Pagamento(int id, String metodo, Double valor, Date data, Date hora) {
		super();
		
		this.id = id;
		this.metodo = metodo;
		this.valor = valor;
		this.data = data;
		this.hora = hora;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMetodo() {
		return metodo;
	}


	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public Date getHora() {
		return hora;
	}


	public void setHora(Date hora) {
		this.hora = hora;
	}


	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", metodo=" + metodo + ", valor=" + valor + ", data=" + data + ", hora=" + hora
				+ "]";
	}
    

}