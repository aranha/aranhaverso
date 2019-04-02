package br.com.gastronomia.bo;

import br.com.gastronomia.dao.ImagemDAO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Imagem;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//branch demo
public class ImagemBO {
	private ImagemDAO imagemDAO;

	public ImagemBO() {
		imagemDAO = new ImagemDAO();
	}

	public void setImagemDAO(ImagemDAO imagemDAO) {
		this.imagemDAO = imagemDAO;
	}

	public boolean createImagem(Imagem imagem) throws ValidationException, NoSuchAlgorithmException {
		if (imagem != null) {
			imagemDAO.save(imagem);
			return true;
		}
		throw new ValidationException("invalido");

	}

	public long updateImagem(Imagem imagem) throws ValidationException {
		if (imagem != null) {
			return imagemDAO.updateImagem(imagem);
		}
		throw new ValidationException("invalido");

	}
	
	//Mantive este m�todo, mas n�o vejo qual a utilidade dele
	public Imagem validate(Imagem newAtributo) {
		return newAtributo;
	}

	public HashMap<String, List<Imagem>> listImagens() {
		ArrayList<Imagem> imagens = null;
		HashMap<String, List<Imagem>> listAtributos = new HashMap<String, List<Imagem>>();
		imagens = (ArrayList<Imagem>) imagemDAO.listAllOrder(Imagem.class, "Ordem");
		listImagens().put("Imagens", imagens);
		return listImagens();
	}

	public Imagem getImagemById(long id) throws ValidationException {
		if (id > 0) {
			return imagemDAO.findImagemByID(id);
		}
		throw new ValidationException("invalido");

	}
}
