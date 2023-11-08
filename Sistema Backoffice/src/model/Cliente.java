package model;

public class Cliente {
    public String codigo;
	public String cpf; 
    public String cnpj;
    public String nome;
    public String nomeFantasia;
    public String enderecoPF;
    public String enderecoPJ;
    public String numeroEndPF;
    public String numeroEndPJ;
    public String complementoPF;
    public String complementoPJ;
    public String cepPF;
    public String cepPJ;
    public String telefone; // para pessoa jurídica
    public String celular; // para pessoa física
    public String email; // para pessoa jurídica
	
	@Override
	public String toString() {
		return codigo+";"+cpf+";"+cnpj+";"+nome+";"+nomeFantasia+";"+enderecoPF+";"
	+enderecoPJ+";"+numeroEndPF+";"+numeroEndPJ+";"+complementoPF+";"+complementoPJ+";"+complementoPJ+";"+cepPF+";"
	+cepPJ+";"+telefone+";"+celular+";"+email;
	}
}

