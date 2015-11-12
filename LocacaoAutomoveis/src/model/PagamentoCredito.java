package model;

import to.PagamentoCreditoTO;
import dao.factory.DAOFactory;
import dao.interfaces.PagamentoCreditoDAO;

public class PagamentoCredito extends Pagamento{
	   
    private String validadeCredito,operadoraCredito,numeroCartaoCredito;
	private int codigoSegurancaCredito;
 	
    public String getValidadeCredito(){
       return validadeCredito;
    }
    
    public void setValidadeCredito(String validadeCredito){
       this.validadeCredito = validadeCredito;
    }
    
    public String getOperadoraCredito(){
       return operadoraCredito;
    }
    
 	 public void setOperadoraCredito(String operadoraCredito){
       this.operadoraCredito = operadoraCredito ;
    }
    
    public String getNumeroCartaoCredito(){
       return numeroCartaoCredito;
    }
    
 	 public void setNumeroCartaoCredito(String numeroCartaoCredito){
       this.numeroCartaoCredito = numeroCartaoCredito ;
    }
    
    public int getCodigoSegurancaCredito(){
       return codigoSegurancaCredito;
    }
    
     public void setCodigoSegurancaCredito(int codigoSegurançaCredito){
       this.codigoSegurancaCredito = codigoSegurançaCredito ;
    }
     
     
    @Override
  	public String toString() {
  		return "PagamentoCredito [validadeCredito=" + validadeCredito + ", operadoraCredito=" + operadoraCredito
  				+ ", numeroCartaoCredito=" + numeroCartaoCredito + ", codigoSegurancaCredito=" + codigoSegurancaCredito + "]";
  	}
     
 	/*------------------------- MÉTODOS----------------------------*/
          	     
    public PagamentoCreditoTO consultarGeral() throws GenericException{
 		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);		
 		PagamentoCreditoDAO pag = factory.getPagamentoCreditoDAO();
 		return pag.consultar();
 	}  
    
    public PagamentoCredito consultar() throws GenericException{
 		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);		
 		PagamentoCreditoDAO pag = factory.getPagamentoCreditoDAO();
 		return pag.consultar(this);
 	}
     
     
}
 