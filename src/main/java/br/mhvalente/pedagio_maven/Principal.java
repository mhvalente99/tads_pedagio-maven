package br.mhvalente.pedagio_maven;

import javax.swing.JFrame;

import br.mhvalente.view.LoginView;
import br.mhvalente.view.PedagioView;

public class Principal extends JFrame{	
	
	private static final long serialVersionUID = 2866832676817847954L;

	public static void main(String[] args) {
		LoginView login = new LoginView();
		login.setLocationRelativeTo(null);
	}

}
