package fachada;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import daojpa.DAO;
import daojpa.DAOPessoa;
import daojpa.DAOParticipante;
import daojpa.DAOPalestrante;
import daojpa.DAOTitulacao;
import daojpa.DAOEvento;
import daojpa.DAOPalestra;
import modelo.Pessoa;
import modelo.Titulacao;
import modelo.Palestrante;
import modelo.Participante;
import modelo.Palestrante;
import modelo.Titulacao;
import modelo.Evento;
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
	private static DAOEvento daoevento = new DAOEvento();
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
	
	public static Evento cadastrarEvento(String nome, String inicio, String fim) throws Exception{
		DAO.iniciar();
		
		Evento e = daoevento.localizarPeloNome(nome);
		
		if(e!= null){
			DAO.cancelar();
			throw new Exception("Evento já cadastrado");
		}
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		
		Date inicio_data = sdf.parse(inicio);
		Date fim_data = sdf.parse(fim);
		
		e = new Evento(nome, inicio_data, fim_data);
		daoevento.persistir(e);
		
		DAO.efetivar();
		return e;
	}
	
	public static void adicionarParticipanteEvento(String evento, String cpf) throws Exception{
		DAO.iniciar();
		
		Evento e = daoevento.localizarPeloNome(evento);
		if(e == null) throw new Exception("Evento não cadastrado! " + evento);
		
		Participante p = daoparticipante.localizarPeloCPF(cpf);
		if(p == null) throw new Exception("Participante não cadastrado! " + cpf);
		
		e.adicionarParticipante(p);
		daoevento.atualizar(e);
		DAO.efetivar();
	}
	public static void adicionarPalestraEvento(String evento, String titulo) throws Exception{
		DAO.iniciar();
		
		Evento e = daoevento.localizarPeloNome(evento);
		if(e == null) throw new Exception("Evento não cadastrado! " + evento);
		
		Palestra p = daopalestra.localizarPeloTitulo(titulo);
		if(p == null) throw new Exception("Palestra não cadastrada! " + titulo);
		
		e.adicionarPalestra(p);
		daoevento.atualizar(e);
		DAO.efetivar();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

