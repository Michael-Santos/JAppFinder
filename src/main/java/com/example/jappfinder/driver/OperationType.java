package com.example.jappfinder.driver;

public enum OperationType {
	
	SELL("venda"),
	RENT("aluguel"),
	LAUNCHPLAN("imoveis-lancamento");
	
	private String value;
	
	OperationType(String value) {
        this.value = value;
    }
	
	public String getValue() {
		return this.value;
	}
}
