package com.iset.ClientSecours.managedbean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import org.primefaces.context.RequestContext;

import com.iset.secoursEJB.entities.Agence;
import com.iset.secoursEJB.entities.Caisse;
import com.iset.secoursEJB.entities.Retrait;
import com.iset.secoursEJB.entities.Client;
import com.iset.secoursEJB.entities.Compte;
import com.iset.secoursEJB.entities.Utilisateur;
import com.iset.secoursEJB.entities.Versement;
import com.iset.secoursEJB.entities.Virement;
import com.iset.secoursEJB.entities.Operation;
import com.iset.secoursEJB.entities.RemiseCheque;
import com.iset.secoursEJB.entities.RemiseEffet;
import com.iset.secoursEJB.interfaces.CaisseImpl;
import com.iset.secoursEJB.interfaces.ClientImpl;
import com.iset.secoursEJB.interfaces.CompteImpl;
import com.iset.secoursEJB.interfaces.UtilisateurImpl;
import com.iset.secoursEJB.interfaces.VersementImpl;
import com.iset.secoursEJB.interfaces.VirementImpl;
import com.iset.secoursEJB.interfaces.OperationImpl;
import com.iset.secoursEJB.interfaces.RemiseChequeImpl;
import com.iset.secoursEJB.interfaces.RemiseEffetImpl;
import com.iset.secoursEJB.interfaces.RetraitImpl;

@SuppressWarnings("serial")
@ManagedBean(name="clientBean")
@SessionScoped
public class ClientBean implements Serializable {

	@EJB
	private CaisseImpl daocaisse;
	
	@EJB
	private ClientImpl daoclient;
	
	@EJB 
	private RetraitImpl daoretrait;
	
	@EJB 
	private VersementImpl daoversement;
	
	@EJB 
	private VirementImpl daovirement;
	
	@EJB
	private RemiseEffetImpl daoremiseEffet;
	
	@EJB
	private RemiseChequeImpl daoremiseCheque;
	
	@EJB
	private CompteImpl daocompte;
	
	@EJB
	private OperationImpl daooperation;
	
	@EJB
	private UtilisateurImpl daologin;
	

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}



	private Agence agence=new Agence();
	private Client client=new Client();
	private Client clientBeneficiaire=new Client();
	private List<Client> listClients;
	private List<Compte> listeComptes;
	private List<Compte> listeComptesBeneficiaire;
	private Compte compte=new Compte();
	private Compte compteBeneficiaire=new Compte();
	private Utilisateur login=new Utilisateur();
	private Operation operation=new Operation();
	private long numCompte;
	public int numCompteBeneficiaire;
	private String type_operation;
	private String clientinoutBTK;
	private double solde; 
	public double soldeBeneficiaire;
	public String username;
	public double montantVR;
	public int numCheque;
	private int codeClient;
	public int codeBeneficiaire;
	public String rib_Beneficiaire;
	public String radioValue;
	public String motif;
	public long numEffet ;
	public Date date=new  Date();
	String loginFromSession = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");

	
	public String getLoginFromSession() {
		return loginFromSession;
	}

	public void setLoginFromSession(String loginFromSession) {
		this.loginFromSession = loginFromSession;
	}

	public String getType_operation() {
		return type_operation;
	}

	public void setType_operation(String type_operation) {
		this.type_operation = type_operation;
	}
	
	public long getNumEffet() {
		return numEffet;
	}

	public void setNumEffet(long numEffet) {
		this.numEffet = numEffet;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getRadioValue() {
		return radioValue;
	}
	
	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
	}

	public String getRib_Beneficiaire() {
		return rib_Beneficiaire;
	}

	public void setRib_Beneficiaire(String rib_Beneficiaire) {
		this.rib_Beneficiaire = rib_Beneficiaire;
	}

	public int getCodeBeneficiaire() {
		return clientBeneficiaire.getCodeClient();
	}

	public void setCodeBeneficiaire(int codeBeneficiaire) {
		this.codeBeneficiaire = codeBeneficiaire;
	}

	public List<Compte> getListeComptesBeneficiaire() {
		return listeComptesBeneficiaire;
	}

	public void setListeComptesBeneficiaire(List<Compte> listeComptesBeneficiaire) {
		this.listeComptesBeneficiaire = listeComptesBeneficiaire;
	}

	public double getSoldeBeneficiaire() {
		return compteBeneficiaire.getSolde();
	}

	public void setSoldeBeneficiaire(double soldeBeneficiaire) {
		this.soldeBeneficiaire = soldeBeneficiaire;
	}

	public int getNumCompteBeneficiaire() {
		return numCompteBeneficiaire;
	}

	public void setNumCompteBeneficiaire(int numCompteBeneficiaire) {
		this.numCompteBeneficiaire = numCompteBeneficiaire;
	}

	public int getCodeClient() {
		return client.getCodeClient();
	}

	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}

	public int getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(int numCheque) {
		this.numCheque = numCheque;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	//chercher client par cin 
	public void findClientByCIN()
	{
		try 
		{
			System.out.println("cinClient="+client.getNID());
			if((client.getNID()==null))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "inserer un numero de cin");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
			else
			{
				if(client!=null)
				{
					client=daoclient.findClient(client.getNID());
					System.out.println("cin client ="+client.getNID());
					listeComptes=this.ListCompteClientByCIN();
					System.out.println("client trouver par cin");
				}
			}
		} 
		catch (Exception e) 
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", " Client NOT FOUND Please try again!!");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);	
		}
	}
	
	//chercher beneficiaire par cin
	public void findBeneficiaireByCIN()
	{
		try 
		{
			if(client.getNID()==clientBeneficiaire.getNID())
			{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cin identique  ");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
			else
			if(clientBeneficiaire.getNID()==null)
				{	
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "inserer un cin correct");
					RequestContext.getCurrentInstance().update("growl");
					FacesContext context=FacesContext.getCurrentInstance();
					context.addMessage(null, message);
			}
			else
			if(clientBeneficiaire!=null)
			{
				clientBeneficiaire=daoclient.findClient(clientBeneficiaire.getNID());
				System.out.println("cin Beneficiaire ="+clientBeneficiaire.getNID());
				listeComptesBeneficiaire=this.ListCompteBeneficiaireByCIN();
				System.out.println("Beneficiaire trouver par cin");
			}
			
		} 
		catch (Exception e) 
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", " client Beneficiaire NOT FOUND Please try again!!");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);	
		}
	}
	
	public List<Client> findAll()
	{
		return daoclient.findAll();
	}
	
	public List<Retrait> findAllRetrait()
	{
		return daoretrait.findAll();
	}
	
	public List<Versement> findAllVersement()
	{
		return daoversement.findAll();
	}
	
	public List<Virement> findAllVirement()
	{
		return daovirement.findAll();
	}
	
	public List<RemiseCheque> findAllRCheque()
	{
		return daoremiseCheque.findAll();
	}
	
	public List<RemiseEffet> findAllremiseE()
	{
		return daoremiseEffet.findAll();
	}
	//chercher compte par code 
	public void searchCompte( )
	{
		System.out.println(client.getNID());
		if(client.getNID()==null)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Warning", "insert cin avant d'afficher le compte");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
        }
		else
		{
			if (numCompte==0 ) 
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Warning", "Selectionnez un compte avant");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
	        }
			else
			{
				System.out.println("numcompte="+numCompte);
				compte= daocompte.findByCode(numCompte);
			}
		}
	}
	
	//chercher compte beneficiaire
	public void searchCompteBeneficiaire( ){
		if(clientBeneficiaire.getNID()==null)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Warning", "insert cin avant");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
        }
		else
		{
			if (numCompteBeneficiaire==0) 
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Warning", "Selectionnez un compte avant");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
	        }
			else
			{
				System.out.println("numCompteBeneficiaire="+numCompteBeneficiaire);
				compteBeneficiaire= daocompte.findByCode(numCompteBeneficiaire);
			}
		}
	}
	
	public List<Compte> ListCompteClientByCIN()
	{
		 return listeComptes=daocompte.findListCompteByCINClient(client.getNID());
	}
	
	//liste des comptes du beneficiaire( client btk)
	public List<Compte> ListCompteBeneficiaireByCIN ()
	{
		return listeComptesBeneficiaire=daocompte.findListCompteByCINClient(clientBeneficiaire.getNID());
	}
	
	public List<Compte> getListeComptes() {
		return listeComptes;
	}

	public List<Compte> setListeComptes(List<Compte> listeComptes) {
		this.listeComptes = listeComptes;
		return listeComptes;
	}
	
	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientBean other = (ClientBean) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (compte == null) {
			if (other.compte != null)
				return false;
		} else if (!compte.equals(other.compte))
			return false;
		if (daoclient == null) {
			if (other.daoclient != null)
				return false;
		} else if (!daoclient.equals(other.daoclient))
			return false;
		if (daocompte == null) {
			if (other.daocompte != null)
				return false;
		} else if (!daocompte.equals(other.daocompte))
			return false;
	
		if (listeComptes == null) {
			if (other.listeComptes != null)
				return false;
		} else if (!listeComptes.equals(other.listeComptes))
			return false;
		return true;
	}

	public long getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(long numCompte) {
		this.numCompte = numCompte;
	}

	public double getSolde() {
		return compte.getSolde();
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public double getMontantVR() {
		return montantVR;
	}

	public void setMontantVR(double montantVR) {
		this.montantVR = montantVR;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	public List<Client> getListClients() {
		return listClients;
	}

	public void setListClients(List<Client> listClients) {
		this.listClients = listClients;
	}

	public String getClientinoutBTK() {
		return clientinoutBTK;
	}

	public void setClientinoutBTK(String clientinoutBTK) {
		this.clientinoutBTK = clientinoutBTK;
	}
	
	public Client getClientBeneficiaire() {
		return clientBeneficiaire;
	}

	public void setClientBeneficiaire(Client clientBeneficiaire) {
		this.clientBeneficiaire = clientBeneficiaire;
	}

	public Compte getCompteBeneficiaire() {
		return compteBeneficiaire;
	}

	public void setCompteBeneficiaire(Compte compteBeneficiaire) {
		this.compteBeneficiaire = compteBeneficiaire;
	}
	
	
	
	private  Retrait retrait=new Retrait();
	private Versement versement=new Versement();
	private Virement virement=new Virement();
	private RemiseCheque remiseCheque=new RemiseCheque();
	private RemiseEffet remiseEffet=new RemiseEffet();

	public void addRetrait()
	{
		
	 	//solde	
		retrait.setMontant(montantVR);
		System.out.println("montant="+retrait.getMontant());
		 	
		//numcompte
		 	System.out.println("code compte="+getNumCompte());
		 	compte=daocompte.findByCode(getNumCompte());
		 	retrait.setCompte(compte);
		 System.out.println("numCompte="+retrait.getCompte().getCodeCompte());	
		 
		 //codeClient	
		 
		 	int code=getCodeClient();
		 	System.out.println("code "+code);
		 	client=daoclient.findByCode(code);
		 	retrait.setClient(client);
		 System.out.println("code client="+retrait.getClient().getCodeClient());
		 	
		 //date courante
		 	retrait.setDate(date);
		 System.out.println("date="+retrait.getDate());
		 
		 //motif
		 	retrait.setMotif(motif);
		 	System.out.println("motif="+retrait.getMotif());
		 	
		 	
		 //login user
		System.out.println("ussre="+loginFromSession);	
		 login.setLogin(loginFromSession);
			retrait.setUtilisateur(login); 	
		 System.out.println("login="+retrait.getUtilisateur().getLogin());
		
	}

	public void addVersement()
	{
		
	 	//solde	
		versement.setMontant(montantVR);
		System.out.println("montant="+versement.getMontant());
		 	
		//numcompte
		 	System.out.println("code compte="+getNumCompte());
		 	compte=daocompte.findByCode(getNumCompte());
		 	versement.setCompte(compte);
		 System.out.println("numCompte="+versement.getCompte().getCodeCompte());	
		 
		 //codeClient	
		 
		 	int code=getCodeClient();
		 	System.out.println("code "+code);
		 	client=daoclient.findByCode(code);
		 	versement.setClient(client);
		 System.out.println("code client="+versement.getClient().getCodeClient());
		 	
		 //date courante
		 versement.setDate(date);
		 System.out.println("date="+versement.getDate());
		 
		 //motif
		 versement.setMotif(motif);
		 	System.out.println("motif="+versement.getMotif());
		 	
		 	
		 //login user
		 System.out.println("ussre="+loginFromSession);	
		 login.setLogin(loginFromSession);
		 versement.setUtilisateur(login); 	
		 System.out.println("login="+versement.getUtilisateur().getLogin());
		
	}
	
	double montantcaisse;
	
	
	public void operationChoisi()
	{
		Caisse caisse=daocaisse.findCaisseByLogin(loginFromSession);
		System.out.println("retrait/versement");
		System.out.println("typeop="+type_operation);
		double montant=0;
		if((type_operation==null)&&(montantVR==0))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Warning", "veuillez inserer le montant et choisir une operation");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}
		else
		if (type_operation==null) 
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Warning", "choisir une operation ");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
        }
		else
		if(montantVR==0)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Warning", "ajouter montant");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}
		else
		{
		if(type_operation.equals("Versement"))
		{
			
			
				System.out.println("Versement");
				montant=daocompte.versement(numCompte,compte.getSolde(),montantVR);
				compte.setSolde(montant);
				daocompte.updateCompte(compte);
				System.out.println("solde="+compte.getSolde());
				System.out.println("montant a verser="+montantVR);
				System.out.println(" montant versé="+compte.getSolde()+"+"+montantVR+"="+montant);
				setSolde(montant);
				System.out.println("solde updated="+getSolde());
				System.out.println("solde de caisse="+caisse.getSoldeCaisse());
				
				montantcaisse=daocaisse.versementCaisse(loginFromSession,caisse.getSoldeCaisse(),montantVR);
				
				System.out.println("solde caisse="+caisse.getSoldeCaisse());
				System.out.println("equation ="+caisse.getSoldeCaisse()+"+"+montantVR+"="+montantcaisse);
				caisse.setSoldeCaisse(montantcaisse);
				daocaisse.updateCaisse(caisse);
				addVersement();
				this.insertVersement();
				Clear();
			
			
			
		}
		else
		if(type_operation.equals("Retrait"))
		{
			System.out.println("Retrait");
			double autosolde=compte.getAutorisation()+compte.getSolde();
			if((montantVR)>(autosolde))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "impossible de retirer de largent!!");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
			else 
			{
				
				if(caisse.getSoldeCaisse()<montantVR)
				{
					System.out.println("soldecaisse="+caisse.getSoldeCaisse());
					System.out.println("montant a retirer"+montantVR);
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "erreur", "impossible de retirer a partir de la caisse");
					RequestContext.getCurrentInstance().update("growl");
					FacesContext context=FacesContext.getCurrentInstance();
					context.addMessage(null, message);
				}
				else
				{
					System.out.println("solde+autorisation="+autosolde);
					System.out.println("montant retiré="+montantVR);
					montant=daocompte.retrait(numCompte,compte.getSolde(),montantVR);
					System.out.println(" montant retiré="+compte.getSolde()+"-"+montantVR+"="+montant);
					compte.setSolde(montant);
					daocompte.updateCompte(compte);
					setSolde(montant);
					System.out.println("solde updated="+getSolde());

					System.out.println("solde de caisse="+caisse.getSoldeCaisse());
					
					montantcaisse=daocaisse.retraitCaisse(loginFromSession,caisse.getSoldeCaisse(),montantVR);
					
					System.out.println("solde caisse="+caisse.getSoldeCaisse());
					System.out.println("equation ="+caisse.getSoldeCaisse()+"-"+montantVR+"="+montantcaisse);
					caisse.setSoldeCaisse(montantcaisse);
					daocaisse.updateCaisse(caisse);
					addRetrait();
					this.insertRetrait();
					Clear();
				}
				
				
			}
				
		}
		}
		
	}
	

	public void insertRemiseCheque()
	{
		if(daoremiseCheque.insertRemiseCheque(remiseCheque)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Remise Cheque", "Remise Cheque effectuée!");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Remise Cheque", "Remise Cheque non effectuée.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
	   }
	}

	public void insertRemiseEffet()
	{
		if(daoremiseEffet.insertRemiseEffet(remiseEffet)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "remise Effet", "remiseEffet effectuée!");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "remise Effet", "remiseEffet non effectuée.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
	   }
	}
	public void insertRetrait()
	{
		if(daoretrait.insertRetrait(retrait)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Retrait", "Retrait effectuée!");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Retrait", "Retrait non effectuée.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
	   }
	}

	public void insertVersement()
	{
		if(daoversement.insertVersement(versement)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Versement", "Versement effectuée!");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Versement", "Versement non effectuée.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
	   }
	}

	public void insertVirement()
	{
		if(daovirement.insertVirement(virement)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "virement", "virement effectuée!");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "virement", "virement non effectuée.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
	   }
	}
	
	public void addChequeAutreBanque()
	{
		//codeClient
		int code=getCodeClient();
	 	System.out.println("code client "+code);
	 	client=daoclient.findByCode(code);
	 	remiseCheque.setClient(client);
	 	System.out.println("code client= "+remiseCheque.getClient().getCodeClient());
		
	 	//montant du client updated
	 	remiseCheque.setMontant(montantVR);
	 	System.out.println("montant="+remiseCheque.getMontant());
	 	
	 	
	 	//num cheque
	 	int cheque=remiseCheque.getNumCheque();
	 	System.out.println(cheque);
	 	int numc=getNumCheque();
	 	cheque=numc;
	 	System.out.println("cheque="+cheque);
	 	remiseCheque.setNumCheque(cheque);
	 	System.out.println("num cheque="+remiseCheque.getNumCheque());
	 	
	 	//numcompte
	 	System.out.println("code compte="+numCompte);
	 	compte=daocompte.findByCode(numCompte);
	 	remiseCheque.setCompte(compte);
	 	System.out.println("num compte="+remiseCheque.getCompte().getCodeCompte());
	 	
	 	//rib beneficiaire autre banque
	 	System.out.println("rib Beneficiaire Autre banque"+rib_Beneficiaire);
	 	remiseCheque.setRib(rib_Beneficiaire);
	 	System.out.println("rib="+remiseCheque.getRib());
	 	
	 	//motif
	 	remiseCheque.setMotif(motif);
	 	System.out.println("motif="+remiseCheque.getMotif());
	 	
	 	//date courante
	 	remiseCheque.setDate(date);
	 	System.out.println("date="+remiseCheque.getDate());

	 	//login user
	 	login.setLogin(loginFromSession);
		remiseCheque.setUtilisateur(login); 
	 	System.out.println("login="+remiseCheque.getUtilisateur().getLogin());
	 	
		
		System.out.println("remise Cheque autre banque effectue with success");
	}

	public void addChequeClientsBTK()
	{
		//ajouter
		
		//id Operation autoincrement
		
		//codeClient
			codeClient=this.getCodeClient();
		 	System.out.println(" code client "+codeClient);
		 	client=daoclient.findByCode(codeClient);
		 	remiseCheque.setClient(client);
		 	System.out.println("code client= "+remiseCheque.getClient().getCodeClient());
		 	
		 //montant du client updated
		 	remiseCheque.setMontant(montantVR);
		 	System.out.println("montant="+remiseCheque.getMontant());
		
	 	//numCompteClient
	 		System.out.println("code compte="+numCompte);
	 		compte=daocompte.findByCode(numCompte);
	 		remiseCheque.setCompte(compte);
	 		 	
	 	//numCheque
		 	int cheque=remiseCheque.getNumCheque();
		 	System.out.println(cheque);
		 	int numc=getNumCheque();
		 	cheque=numc;
		 	System.out.println("cheque="+cheque);
		 	remiseCheque.setNumCheque(cheque);
		
	 	//code Benficiaire
		 	System.out.println("codeBeneficiaire"+codeBeneficiaire);
		 	int codeB=remiseCheque.getCodeClt();
		 	System.out.println("codeB"+codeB);
		 	codeBeneficiaire=getCodeBeneficiaire();
		 	codeB=codeBeneficiaire;
		 	System.out.println("code Beneficiaire="+codeB);
		 	remiseCheque.setCodeClt(codeB);
	 	
		 //num Compte beneficiaire
		 	int numB=remiseCheque.getCodeCpte();
		 	System.out.println(numB);
		 	int numCompteB=getNumCompteBeneficiaire();
		 	numB=numCompteB;
		 	System.out.println(" code compte beneficiaire="+numB);
		 	remiseCheque.setCodeCpte(numB);
	 	
		 	//motif
		 	remiseCheque.setMotif(motif);
				
		 //date courante
		 	remiseCheque.setDate(date);
		 
		 //login user
		 	login.setLogin(loginFromSession);
			remiseCheque.setUtilisateur(login); 
			
			
			
			System.out.println("ajout effectue avec succes cheque btk");
	}
	
	public void remiseCheque()
	{
		double montant=0;
		double autosolde=compte.getAutorisation()+compte.getSolde();
		int lengthnumCheque = (String.valueOf(numCheque)).length();

		System.out.println("remise cheque");
		System.out.println("taille numero cheque"+lengthnumCheque);
		
		if((montantVR==0)||(numCheque==0)||(radioValue==null))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "veuillez remplir tous les champs ");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}
		
		else
			
		if(lengthnumCheque!=7)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "taille numero cheque doit etre egale a 7");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}
		else
		{
		if(radioValue.equals("btk"))
		{
			System.out.println("compte client");
			if(clientBeneficiaire.getNID()==null)
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "veuillez inserer le cin avant");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
			else
			if((montantVR)>(autosolde))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "impossible de retirer de largent!!");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
			else
			{
				
				//compte client
				System.out.println("montant à retirer par cheque="+montantVR);
				System.out.println("numCompte"+getNumCompte());
				System.out.println(compte.getSolde());
				System.out.println("solde+autorisation="+autosolde);
				System.out.println("montant retiré="+montant);
				montant=daocompte.retrait(getNumCompte(),compte.getSolde(),montantVR);
				System.out.println(" montant retiré="+compte.getSolde()+"-"+montantVR+"="+montant);
				compte.setSolde(montant);
				daocompte.updateCompte(compte);
				System.out.println("solde client"+daocompte.updateCompte(compte));
					
				
				//compte beneficiaire
				System.out.println("compte beneficiaire");
				double montantB=0;
				System.out.println("montant à verser par cheque au beneficiaire ="+montant);
				System.out.println("numCompteBeneficiaire"+getNumCompteBeneficiaire());
				System.out.println(compteBeneficiaire.getSolde());
				System.out.println("montantversé"+montant);
				montantB=daocompte.versement(getNumCompteBeneficiaire(),compteBeneficiaire.getSolde(),montant);
				System.out.println(" montant versé="+compteBeneficiaire.getSolde()+"+"+montant+"="+montantB);
				System.out.println("motant"+montantB);
				compteBeneficiaire.setSolde(montantB);
				daocompte.updateCompte(compteBeneficiaire);
				System.out.println("soldde b"+daocompte.updateCompte(compteBeneficiaire));
				
					
				
				addChequeClientsBTK();
				this.insertRemiseCheque();
				Clear();
					
			}
				
			}
		else
		if(radioValue.equals("autre_banque"))
		{
			if((montant)>(autosolde))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "impossible de retirer de largent!!");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
			else
			{
				//client de autre banque
				//test rib
				
				int lengthrib = rib_Beneficiaire.length();
				
				System.out.println("rib = "+rib_Beneficiaire);
				System.out.println("taille rib = "+lengthrib);
				
				
				if(lengthrib!=20)
				{
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "taille rib doit etre egale a 20 ");
						FacesContext.getCurrentInstance().addMessage(null, message);
				}
				else
				{
				//compte client
				System.out.println("montant à retirer par cheque="+montantVR);
				System.out.println("numCompte"+getNumCompte());
				System.out.println(compte.getSolde());
				System.out.println("montantversé"+montantVR);
				compte.setSolde(montant);
				
				
				addChequeAutreBanque();
				this.insertRemiseCheque();
				Clear();
			}
			}
			
		}
		}
		
		}
	
	//remise effet
	public void addEffetAutreBanque()
	{
		//codeClient
		int code=getCodeClient();
	 	System.out.println("code client "+code);
	 	client=daoclient.findByCode(code);
	 	remiseEffet.setClient(client);
	 	System.out.println("code client= "+remiseEffet.getClient().getCodeClient());
		
	 	//montant du client updated
	 	remiseEffet.setMontant(montantVR);
	 	System.out.println("montant="+remiseEffet.getMontant());
	 	
	 	
	 	//num Effet
	 	long Effet=remiseEffet.getNumEffet();
	 	System.out.println(Effet);
	 	long numc=getNumEffet();
	 	Effet=numc;
	 	System.out.println("Effet="+Effet);
	 	remiseEffet.setNumEffet(Effet);
	 	System.out.println("num Effet="+remiseEffet.getNumEffet());
	 	
	 	//numcompte
	 	System.out.println("code compte="+numCompte);
	 	compte=daocompte.findByCode(numCompte);
	 	remiseEffet.setCompte(compte);
	 	System.out.println("num compte="+remiseEffet.getCompte().getCodeCompte());
	 	
	 	//rib beneficiaire autre banque
	 	System.out.println("rib Beneficiaire Autre banque"+rib_Beneficiaire);
	 	remiseEffet.setRib(rib_Beneficiaire);
	 	System.out.println("rib="+remiseEffet.getRib());
	 	
	 	//motif
	 	remiseEffet.setMotif(motif);
	 	System.out.println("motif="+remiseEffet.getMotif());
	 	
	 	//date courante
	 	remiseEffet.setDate(date);
	 	System.out.println("date="+remiseEffet.getDate());

	 	//login user
	 	login.setLogin(loginFromSession);
		remiseEffet.setUtilisateur(login); 
	 	System.out.println("login="+remiseEffet.getUtilisateur().getLogin());
	 	
		
		System.out.println("remise Effet autre banque effectue with success");
	}

	public void addEffetClientsBTK()
	{
		//ajouter
		
		//id Operation autoincrement
		
		//codeClient
			codeClient=this.getCodeClient();
		 	System.out.println(" code client "+codeClient);
		 	client=daoclient.findByCode(codeClient);
		 	remiseEffet.setClient(client);
		 	System.out.println("code client= "+remiseEffet.getClient().getCodeClient());
		 	
		 //montant du client updated
		 	remiseEffet.setMontant(montantVR);
		 	System.out.println("montant="+remiseEffet.getMontant());
		
	 	//numCompteClient
	 		System.out.println("code compte="+numCompte);
	 		compte=daocompte.findByCode(numCompte);
	 		remiseEffet.setCompte(compte);
	 		 	
	 	//numeffet
		 	long effet=remiseEffet.getNumEffet();
		 	System.out.println(effet);
		 	long numc=getNumEffet();
		 	effet=numc;
		 	System.out.println("effet="+effet);
		 	remiseEffet.setNumEffet(effet);
		
	 	//code Benficiaire
		 	System.out.println("codeBeneficiaire"+codeBeneficiaire);
		 	int codeB=remiseEffet.getCodeClt();
		 	System.out.println("codeB"+codeB);
		 	codeBeneficiaire=getCodeBeneficiaire();
		 	codeB=codeBeneficiaire;
		 	System.out.println("code Beneficiaire="+codeB);
		 	remiseEffet.setCodeClt(codeB);
	 	
		 //num Compte beneficiaire
		 	int numB=remiseEffet.getCodeCpte();
		 	System.out.println(numB);
		 	int numCompteB=getNumCompteBeneficiaire();
		 	numB=numCompteB;
		 	System.out.println(" code compte beneficiaire="+numB);
		 	remiseEffet.setCodeCpte(numB);
	 	
		 	//motif
		 	remiseEffet.setMotif(motif);
				
		 //date courante
		 	remiseEffet.setDate(date);
		 
		 //login user
		 	login.setLogin(loginFromSession);
			remiseEffet.setUtilisateur(login); 
			
			
			
			System.out.println("remise effet effectue avec succes effet btk");
	}
	
	public void RemiseEffet()
	{
		double montant=0;
		double autosolde=compte.getAutorisation()+compte.getSolde();
		long lengthnumEffet = (String.valueOf(numEffet)).length();
		System.out.println("remise cheque");
		System.out.println("taille numero cheque"+lengthnumEffet);
		
		if((montantVR==0)||(numEffet==0)||(radioValue==null))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "veuillez remplir tous les champs ");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}
		
		else
		if(lengthnumEffet!=14)
		{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "taille numero effet doit etre egale a 14");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
		}
		else
		{
		if(radioValue.equals("btk"))
		{
			System.out.println("compte client");
			if(clientBeneficiaire.getNID()==null)
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "veuillez inserer le cin avant");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
			else
			if((montantVR)>(autosolde))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "impossible de retirer de largent!!");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
			else
			{
				
				//compte client
				System.out.println("montant à retirer par cheque="+montantVR);
				System.out.println("numCompte"+getNumCompte());
				System.out.println(compte.getSolde());
				System.out.println("solde+autorisation="+autosolde);
				System.out.println("montant retiré="+montant);
				montant=daocompte.retrait(getNumCompte(),compte.getSolde(),montantVR);
				System.out.println(" montant retiré="+compte.getSolde()+"-"+montantVR+"="+montant);
				compte.setSolde(montant);
				daocompte.updateCompte(compte);
				System.out.println("solde client"+daocompte.updateCompte(compte));
					
				
				//compte beneficiaire
				System.out.println("compte beneficiaire");
				double montantB=0;
				System.out.println("montant à verser par cheque au beneficiaire ="+montant);
				System.out.println("numCompteBeneficiaire"+getNumCompteBeneficiaire());
				System.out.println(compteBeneficiaire.getSolde());
				System.out.println("montantversé"+montant);
				montantB=daocompte.versement(getNumCompteBeneficiaire(),compteBeneficiaire.getSolde(),montant);
				System.out.println(" montant versé="+compteBeneficiaire.getSolde()+"+"+montant+"="+montantB);
				System.out.println("motant"+montantB);
				compteBeneficiaire.setSolde(montantB);
				daocompte.updateCompte(compteBeneficiaire);
				System.out.println("soldde b"+daocompte.updateCompte(compteBeneficiaire));
				
					
				
				addEffetClientsBTK();
				this.insertRemiseEffet();
				
				Clear();
			}
				
			}
		else
		if(radioValue.equals("autre_banque"))
		{
			if((montant)>(autosolde))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "impossible de retirer de largent!!");
				RequestContext.getCurrentInstance().update("growl");
				FacesContext context=FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
			else
			{
				//client de autre banque
				//test rib
				
				int lengthrib = rib_Beneficiaire.length();
				
				System.out.println("rib = "+rib_Beneficiaire);
				System.out.println("taille rib = "+lengthrib);
				
				
				if(lengthrib!=20)
				{
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "taille rib doit etre egale a 20 ");
						FacesContext.getCurrentInstance().addMessage(null, message);
				}
				else
				{
				//compte client
				System.out.println("montant à retirer par cheque="+montantVR);
				System.out.println("numCompte"+getNumCompte());
				System.out.println(compte.getSolde());
				System.out.println("montantversé"+montantVR);
				compte.setSolde(montant);
				
				
				addEffetAutreBanque();
				this.insertRemiseEffet();
				Clear();
			}
			}
			
		}
		}
}

	//remise virement
			public void addVirementAutreBanque()
			{
				//codeClient
				int code=getCodeClient();
			 	System.out.println("code client "+code);
			 	client=daoclient.findByCode(code);
			 	virement.setClient(client);
			 	System.out.println("code client= "+virement.getClient().getCodeClient());
				
			 	//montant du client updated
			 	virement.setMontant(montantVR);
			 	System.out.println("montant="+virement.getMontant());
			 		 	
			 	//numcompte
			 	System.out.println("code compte="+numCompte);
			 	compte=daocompte.findByCode(numCompte);
			 	virement.setCompte(compte);
			 	System.out.println("num compte="+virement.getCompte().getCodeCompte());
			 	
			 	//rib beneficiaire autre banque
			 	System.out.println("rib Beneficiaire Autre banque"+rib_Beneficiaire);
			 	virement.setRib(rib_Beneficiaire);
			 	System.out.println("rib="+virement.getRib());
			 	
			 	//motif
			 	virement.setMotif(motif);
			 	System.out.println("motif="+virement.getMotif());
			 	
			 	//date courante
			 	virement.setDate(date);
			 	System.out.println("date="+virement.getDate());

			 	//login user
			 	login.setLogin(loginFromSession);
				virement.setUtilisateur(login); 
			 	System.out.println("login="+virement.getUtilisateur().getLogin());
			 	
				
				System.out.println("virement autre banque effectue with success");
			}

			public void addVirementClientsBTK()
			{
				//ajouter
				
				//id Operation autoincrement
				
				//codeClient
					codeClient=this.getCodeClient();
				 	System.out.println(" code client "+codeClient);
				 	client=daoclient.findByCode(codeClient);
				 	virement.setClient(client);
				 	System.out.println("code client= "+virement.getClient().getCodeClient());
				 	
				 //montant du client updated
				 	virement.setMontant(montantVR);
				 	System.out.println("montant="+virement.getMontant());
				
			 	//numCompteClient
			 		System.out.println("code compte="+numCompte);
			 		compte=daocompte.findByCode(numCompte);
			 		virement.setCompte(compte);
			 		 				
			 	//code Benficiaire
				 	System.out.println("codeBeneficiaire"+codeBeneficiaire);
				 	int codeB=virement.getCodeClt();
				 	System.out.println("codeB"+codeB);
				 	codeBeneficiaire=getCodeBeneficiaire();
				 	codeB=codeBeneficiaire;
				 	System.out.println("code Beneficiaire="+codeB);
				 	virement.setCodeClt(codeB);
			 	
				 //num Compte beneficiaire
				 	int numB=virement.getCodeCpte();
				 	System.out.println(numB);
				 	int numCompteB=getNumCompteBeneficiaire();
				 	numB=numCompteB;
				 	System.out.println(" code compte beneficiaire="+numB);
				 	virement.setCodeCpte(numB);
			 	
				 	//motif
				 	virement.setMotif(motif);
						
				 //date courante
				 	virement.setDate(date);
				 
				 //login user
				 	login.setLogin(loginFromSession);
					virement.setUtilisateur(login); 
					
					
					
					System.out.println("virement effectue avec succes effet btk");
			}
			

			public void virement()
			{
				double montant=0;
				double autosolde=compte.getAutorisation()+compte.getSolde();
				long lengthnumEffet = (String.valueOf(numEffet)).length();
				System.out.println("remise cheque");
				System.out.println("taille numero cheque"+lengthnumEffet);
				
				if((montantVR==0)||(radioValue==null))
				{
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "veuillez remplir tous les champs ");
					RequestContext.getCurrentInstance().update("growl");
					FacesContext context=FacesContext.getCurrentInstance();
					context.addMessage(null, message);
				}
				
				else
				{
				if(radioValue.equals("btk"))
				{
					System.out.println("compte client");
					if(clientBeneficiaire.getNID()==null)
					{
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "veuillez inserer le cin avant");
						RequestContext.getCurrentInstance().update("growl");
						FacesContext context=FacesContext.getCurrentInstance();
						context.addMessage(null, message);
					}
					else
					if((montantVR)>(autosolde))
					{
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "impossible de retirer de largent!!");
						RequestContext.getCurrentInstance().update("growl");
						FacesContext context=FacesContext.getCurrentInstance();
						context.addMessage(null, message);
					}
					else
					{
						
						//compte client
						System.out.println("montant à retirer par cheque="+montantVR);
						System.out.println("numCompte"+getNumCompte());
						System.out.println(compte.getSolde());
						System.out.println("solde+autorisation="+autosolde);
						System.out.println("montant retiré="+montant);
						montant=daocompte.retrait(getNumCompte(),compte.getSolde(),montantVR);
						System.out.println(" montant retiré="+compte.getSolde()+"-"+montantVR+"="+montant);
						compte.setSolde(montant);
						daocompte.updateCompte(compte);
						System.out.println("solde client"+daocompte.updateCompte(compte));
							
						
						//compte beneficiaire
						System.out.println("compte beneficiaire");
						double montantB=0;
						System.out.println("montant à verser par cheque au beneficiaire ="+montant);
						System.out.println("numCompteBeneficiaire"+getNumCompteBeneficiaire());
						System.out.println(compteBeneficiaire.getSolde());
						System.out.println("montantversé"+montant);
						montantB=daocompte.versement(getNumCompteBeneficiaire(),compteBeneficiaire.getSolde(),montant);
						System.out.println(" montant versé="+compteBeneficiaire.getSolde()+"+"+montant+"="+montantB);
						System.out.println("motant"+montantB);
						compteBeneficiaire.setSolde(montantB);
						daocompte.updateCompte(compteBeneficiaire);
						System.out.println("soldde b"+daocompte.updateCompte(compteBeneficiaire));
						
							
						
						addVirementClientsBTK();
						this.insertVirement();
						
						Clear();	
					}
						
					}
				else
				if(radioValue.equals("autre_banque"))
				{
					if((montant)>(autosolde))
					{
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "impossible de retirer de largent!!");
						RequestContext.getCurrentInstance().update("growl");
						FacesContext context=FacesContext.getCurrentInstance();
						context.addMessage(null, message);
					}
					else
					{
						//client de autre banque
						//test rib
						
						int lengthrib = rib_Beneficiaire.length();
						
						System.out.println("rib = "+rib_Beneficiaire);
						System.out.println("taille rib = "+lengthrib);
						
						
						if(lengthrib!=20)
						{
								FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "taille rib doit etre egale a 20 ");
								FacesContext.getCurrentInstance().addMessage(null, message);
						}
						else
						{
						//compte client
						System.out.println("montant à retirer par cheque="+montantVR);
						System.out.println("numCompte"+getNumCompte());
						System.out.println(compte.getSolde());
						System.out.println("montantversé"+montantVR);
						compte.setSolde(montant);
						
						
						addVirementAutreBanque();
						this.insertVirement();
						Clear();
					}
					}
					
				}
				}
	}

	

	

	public void Clear()
	{
		
		System.out.println("clearing");
		
		
		
		System.out.println("cin avant "+client.getNID());
		client.setNID(null);
		System.out.println("cin apres"+client.getNID());
		client.setNom(null);
		client.setCodeClient(0);
		setNumCompte(0);
		setCodeBeneficiaire(0);
		client.setCodeClient(0);
		compte.setSolde(0);
		compte.setAutorisation(0);
		compte.setAgence(null);
		
		System.out.println(type_operation);
		
		System.out.println(motif);
		setMotif(null);
		setMontantVR(0);
		
		compte.setSolde(0);
		
	}
	
	
	
	public void mail()
	{
		List<String> emails =daologin.findAllEmail();
		
		List<String> MDPUsers =daologin.findAllMDPUsers();
		try
		{
			for(String email : emails)
				{
					daologin.Mail(email,MDPUsers);
				}
			FacesMessage message = new FacesMessage(FacesMessage.FACES_MESSAGES, "email envoyé");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}
		catch (Exception e)
	    {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur", "email non envoyé");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
        }
		
	}
	
	
}
