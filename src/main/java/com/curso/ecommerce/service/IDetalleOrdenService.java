package com.curso.ecommerce.service;

import com.curso.ecommerce.model.DetalleOrden;

import java.util.List;

public interface IDetalleOrdenService {
	DetalleOrden save(DetalleOrden detalleOrden);

	List<DetalleOrden> findByOrden(Integer id);

}
