package br.gov.caixa.api.controller;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.gov.caixa.api.service.GetLdapDataEmployee;

@SpringBootApplication
public class Initialize {
	 
	 public static void main(String[] args) {
		 
		 Logger logger = Logger.getLogger("Initialize.class");
		 
		 logger.info("Iniciando Atualização em: " + args[0]);
	 		 
		 GetLdapDataEmployee employee = new GetLdapDataEmployee();
		 
		 employee.setSleepTime(10000);
		 employee.setIp(args[0]);		 
		 employee.setUriGet("api-sharepoint/api/ldap/obterTodosUsuariosPorDepartamento/{grupo}");
		 employee.setUriPost("api-sharepoint/api/ldap/atualizarFuncionarios");
		 employee.setGrupo("CEDES - CN DESENVOLVIMENTO TI SAO PAULO, SP");
				 
		 ExecutorService threadExecutor = Executors.newSingleThreadExecutor();				
		 threadExecutor.execute(employee);
		 
		 threadExecutor.shutdown();		        
		
		System.out.print("Thread Delegate." + threadExecutor.toString() + "\n");
	 }
 }