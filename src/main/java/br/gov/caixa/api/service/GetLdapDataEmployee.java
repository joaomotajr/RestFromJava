package br.gov.caixa.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import br.gov.caixa.api.result.LdapResult;
import br.gov.caixa.api.result.PersonGroupsDto;

public class GetLdapDataEmployee implements Runnable {
	
	private int sleepTime = 0;
	private String ip;	
	private String uriGet;
	private String uriPost;	
	private Map<String, String> grupo;

	public void run() {
				
		try {												
			RestTemplate restTemplate = new RestTemplateBuilder().build();

			LdapResult ldapResult = restTemplate.getForObject(getUrlGet(), LdapResult.class, getGrupo());
			
			System.out.print("Empregados Encontrados: " + ldapResult.getList().size());
			
			List<PersonGroupsDto> personGroupsDto = ldapResult.getList();		

			restTemplate.postForObject(getUrlPost(), personGroupsDto, PersonGroupsDto.class);
			
			System.out.print("Finalizado com Sucesso");
		 }
		 catch(Exception exception) {
			 
			 exception.printStackTrace();
		 }			
	}
	
	public GetLdapDataEmployee() {
		this.grupo = new HashMap<String, String>();
	}
	
	public GetLdapDataEmployee(int sleepTime, String grupo, String ip, String uriGet, String uriPost) {
		
		this.grupo = new HashMap<String, String>();		
		this.grupo.put("grupo", grupo);
		this.sleepTime = sleepTime;
		this.ip = ip;
		this.uriGet = uriGet;
		this.uriPost = uriPost;
	}
	
	protected int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	public Map<String, String> getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo.put("grupo", grupo);
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUriGet() {
		return uriGet;
	}

	public void setUriGet(String uriGet) {
		this.uriGet = uriGet;
	}
	
	public String getUriPost() {
		return uriPost;
	}

	public void setUriPost(String uriPost) {
		this.uriPost = uriPost;
	}
	
	public String getUrlGet() {
		StringBuilder url = new StringBuilder().append(this.ip).append(this.uriGet);
		return url.toString();
	}
	
	public String getUrlPost() {
		StringBuilder url = new StringBuilder().append(this.ip).append(this.uriPost);
		return url.toString();
	}

}
