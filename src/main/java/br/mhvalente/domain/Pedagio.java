package br.mhvalente.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PEDAGIO")
public class Pedagio {
	@Id
	@GeneratedValue
	private Integer codigo;
	
	@Column(scale=10, precision=2)
	private Double valorTotal;
	
	@Column(scale=10, precision=2)
	private Double valorRecebido;
	
	@Column(scale=10, precision=2)
	private Double valorTroco;
	
	@Column(length=8)
	private String placa;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column(name="tpVeiculo")
	private String tipoVeiculo;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Double getValorRecebido() {
		return valorRecebido;
	}
	public void setValorRecebido(Double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}
	public Double getValorTroco() {
		return valorTroco;
	}
	public void setValorTroco(Double valorTroco) {
		this.valorTroco = valorTroco;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	
	
}
