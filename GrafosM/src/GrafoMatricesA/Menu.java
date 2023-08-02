package GrafoMatricesA;

import javax.swing.JOptionPane;

public class Menu {

	GrafoM info = new GrafoM();
	
	public void MenuOpciones() {
		

		int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
				"BIENVENIDO A LAS FUNCIONES BASICAS DE LOS GRAFOS\n 1.Insertar nuevo vertice\n 2. Mostrar la información del grafo\n3. SALIR"));

		do {
			switch (opcion) {
			case 1:
				info.pedirVerticesAlUsuario();
				MenuOpciones();
				break;
			case 2:
				info.mostrarInformacionGrafo();
				MenuOpciones();
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Gracias por utilizar la aplicacion");
				System.exit(0);
				break;
			default:
				JOptionPane.showInputDialog(null, "Opcion Incorrecta");
			}
		} while (opcion != 3);
	}
}
