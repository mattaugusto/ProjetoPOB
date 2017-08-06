package fachada;
import java.util.List;

import daojpa.DAO;
import daojpa.DAOPessoa;
import daojpa.DAOParticipante;
import daojpa.DAOPalestrante;
import daojpa.DAOTitulacao;
//import daojpa.DAOEvento;
import daojpa.DAOPalestra;
import modelo.Pessoa;
import modelo.Titulacao;
import modelo.Palestrante;
import modelo.Participante;
import modelo.Palestrante;
import modelo.Titulacao;
//import modelo.Evento;
import modelo.Palestra;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

public class Fachada {
	private static DAOPessoa daopessoa = new DAOPessoa();
	private static DAOParticipante daoparticipante= new DAOParticipante() ;
	private static DAOPalestrante daopalestrante = new DAOPalestrante();
	private static DAOTitulacao daotitulacao = new DAOTitulacao() ;
//	private static DAOEvento daoevento = new DAOEvento();
	private static DAOPalestra daopalestra = new DAOPalestra() ;


	public static void inicializar(){
		DAO.abrir();
	}
	
	public static void finalizar(){
		DAO.fechar();
	}
	
	public static Participante cadastrarParticipante(String nome, String cpf, String email, String instituicao) throws Exception{
		DAO.iniciar();
		
	    Participante p = daoparticipante.localizarPeloCPF(cpf);
	    if(p!= null){
	    	DAO.cancelar();
	    	throw new Exception("CPF já cadastrado!");
	    }
	    if(nome == null){
			DAO.cancelar();
			throw new Exception("Nome vazio!");
		}
		if(cpf == null){
			DAO.cancelar();
			throw new Exception("CPF vazio!");
		}
		if(email == null){
			DAO.cancelar();
			throw new Exception("Email vazio!");
		}
		if(instituicao == null){
			DAO.cancelar();
			throw new Exception("Instituicao vazio!");
		}
		p = new Participante(nome, cpf, email, instituicao);
		daoparticipante.persistir(p);
			
		DAO.efetivar();
		return p;
	}
	
	public static Palestrante cadastrarPalestrante(String nome, String cpf, String email, Titulacao tipoTitulacao) throws Exception{
		DAO.iniciar();
		
	    Palestrante p = daopalestrante.localizarPeloCPF(cpf);
	    if(p!= null){
	    	DAO.cancelar();
	    	throw new Exception("CPF já cadastrado!");
	    }
	    if(nome == null){
			DAO.cancelar();
			throw new Exception("Nome vazio!");
		}
		if(cpf == null){
			DAO.cancelar();
			throw new Exception("CPF vazio!");
		}
		if(email == null){
			DAO.cancelar();
			throw new Exception("Email vazio!");
		}
		if(tipoTitulacao == null){
			DAO.cancelar();
			throw new Exception("Instituicao vazio!");
		}
		p = new Palestrante(nome, cpf, email, tipoTitulacao);
		daopalestrante.persistir(p);
			
		DAO.efetivar();
		return p;
	}
	
	public static Titulacao cadastrarTitulacao(String titulo) throws Exception{
		DAO.iniciar();
		
		Titulacao t = daotitulacao.localizarPeloTitulo(titulo);
		
		if(t!= null){
			DAO.cancelar();
			throw new Exception("Titulação já cadastrada!");
		}
		t = new Titulacao(titulo);
		daotitulacao.persistir(t);
		
		DAO.efetivar();
		return t;
	}
	
	public static Palestra cadastrarPalestra(String titulo, String descricao, int duracao, Palestrante palestrante) throws Exception{
		DAO.iniciar();
		
		Palestra p = daopalestra.localizarPeloTitulo(titulo);
		
		if(p!= null){
			DAO.cancelar();
			throw new Exception("Palestra já cadastrada");
		}
		
		p = new Palestra(titulo, descricao, duracao, palestrante);
		daopalestra.persistir(p);
		
		DAO.efetivar();
		return p;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

