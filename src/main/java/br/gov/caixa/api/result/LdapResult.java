package br.gov.caixa.api.result;

import java.util.List;

public class LdapResult {

	private List<PersonGroupsDto> list;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PersonGroupsDto> getList() {
		return list;
	}

	public void setList(List<PersonGroupsDto> list) {
		this.list = list;
	}
}