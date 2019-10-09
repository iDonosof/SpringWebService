package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.Ingrediente;

public interface IIngredienteService {

	public List<Ingrediente> findAll();

	public Ingrediente findById(int id);

	public Ingrediente save(Ingrediente ingrediente);

	public void delete(int id);
}
